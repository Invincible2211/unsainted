package de.prog2.dungeontop.control.manager;

import de.prog2.dungeontop.resources.AudioDefaultValues;
import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.utils.GlobalLogger;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class AudioManager
{

    /*----------------------------------------------ATTRIBUTE---------------------------------------------------------*/

    private final static AudioManager instance = new AudioManager();
    private HashMap<UUID,Clip> playingClips = new HashMap<>();
    private DoubleProperty volume = new SimpleDoubleProperty(AudioDefaultValues.DEFAULT_VOLUME);

    /*--------------------------------------------KONSTRUKTOREN-------------------------------------------------------*/

    /**
     * Der Konstruktor ist private, da der Audio ein Singelton ist. Ausserdem wird ein Change-Listener fuer die
     * volume-Property regestriert, welcher die Lautstaerke anpasst, sollte der Wert sich aendern.
     */
    private AudioManager()
    {
        volume.addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
            {
                changeVolumeForAll(newValue.doubleValue());
            }
        });
    }

    /*----------------------------------------------METHODEN----------------------------------------------------------*/

    /**
     * Diese Methode spielt einen Sound ab
     * @param soundID die ID des Assets, welches den Sound beinhaltet
     */
    public UUID playSound(int soundID, boolean loop)
    {
        UUID clipUUID = UUID.randomUUID();
        GlobalLogger.log(String.format(LoggerStringValues.PLAY_SOUND, soundID));
        File soundFile = AssetsManager.getAssetById(soundID);
        try
        {
            final AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            if (loop){
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            clip.open(audioInputStream);
            playingClips.put(clipUUID,clip);
            LineListener listener = event ->
            {
                if (event.getType() != LineEvent.Type.STOP)
                {
                    return;
                }
                System.out.println("removed");
                playingClips.remove(clip);
            };
            clip.addLineListener(listener );
            clip.start(); //TODO auf Thread auslagern sobald erlaubt
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e)
        {
            GlobalLogger.warning(e.getMessage());
        }
        return clipUUID;
    }

    public void stopSound(UUID clipUUID){
        Clip clip = playingClips.remove(clipUUID);
        if (clip != null){
            clip.stop();
            clip.flush();
            clip.close();
        }
    }

    public void pauseClip(UUID clipUUID){
        Clip clip = playingClips.get(clipUUID);
        if (clip != null) clip.stop();
    }

    public void resumeClip(UUID clipUUID){
        Clip clip = playingClips.get(clipUUID);
        if (clip != null) clip.start();
    }

    public void changeClipVolume(UUID clipUUID, double volume){
        Clip clip = playingClips.get(clipUUID);
        if (clip != null) changeVolume(clip,volume);
    }

    /**
     * Hiermit wird die Lautstaerke aller aktuellen Clips auf die neue Lautstaerke gesetzt.
     * @param volumeLevel die neue Lautstaerke
     */
    private void changeVolumeForAll(double volumeLevel)
    {
        for (Clip c : playingClips.values())
        {
            changeVolume(c, volumeLevel);
        }
    }

    private void changeVolume(Clip clip,double volumeLevel)
    {
        FloatControl volume;
        volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        volume.setValue(20f * (float) Math.log10(volumeLevel/100));
    }

    /*-----------------------------------------GETTER AND SETTER------------------------------------------------------*/

    public DoubleProperty getVolume()
    {
        return volume;
    }

    public static AudioManager getInstance()
    {
        return instance;
    }

}
