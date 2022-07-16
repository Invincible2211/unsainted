package de.prog2.dungeontop.control.manager;

import de.prog2.dungeontop.DungeonTop;
import de.prog2.dungeontop.resources.AudioDefaultValues;
import de.prog2.dungeontop.resources.FilePaths;
import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.utils.GlobalLogger;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.WindowEvent;

import javax.sound.sampled.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class AudioManager
{

    /*----------------------------------------------ATTRIBUTE---------------------------------------------------------*/

    private final static AudioManager instance = new AudioManager();
    private HashMap<UUID,Clip> playingClips = new HashMap<>();

    private HashMap<Scene,UUID> sceneSounds = new HashMap<>();
    private DoubleProperty volume = new SimpleDoubleProperty(AudioDefaultValues.DEFAULT_VOLUME);

    /*--------------------------------------------KONSTRUKTOREN-------------------------------------------------------*/

    /**
     * Der Konstruktor ist private, da der Audio ein Singelton ist. Ausserdem wird ein Change-Listener fuer die
     * volume-Property regestriert, welcher die Lautstaerke anpasst, sollte der Wert sich aendern.
     */
    private AudioManager()
    {
        volume.addListener((observable, oldValue, newValue) -> changeVolumeForAll(newValue.doubleValue()));
    }

    /*----------------------------------------------METHODEN----------------------------------------------------------*/

    /**
     * Diese Methode spielt einen Sound ab
     * @param soundID die ID des Assets, welches den Sound beinhaltet
     */
    public UUID playSound(int soundID, boolean loop)
    {
        UUID clipUUID = UUID.randomUUID();
        AudioThread audioThread = new AudioThread(soundID,clipUUID,loop);
        audioThread.start();
        return clipUUID;
    }

    public UUID playSoundOnScene(int soundID, Scene scene, boolean loop){
        UUID uuid = playSound(soundID,loop);
        pauseClip(uuid);
        sceneSounds.put(scene,uuid);
        return uuid;
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

    public void resetClip(UUID clipUUID){
        Clip clip = playingClips.get(clipUUID);
        if (clip != null) clip.setMicrosecondPosition(0);
    }

    public void changeClipVolume(UUID clipUUID, double volume){
        Clip clip = playingClips.get(clipUUID);
        if (clip != null) changeVolume(clip,volume);
    }

    public void changeClipVolumeWhilePlayingSound(int soundID, UUID clipUUID, double volume){
        Clip clip = playingClips.get(clipUUID);
        double oldVolume = getVolume(clip);
        changeVolume(clip,volume);
        UUID uuid = playSound(soundID,false);
        Clip newClip = playingClips.get(uuid);
        while (newClip == null){
            newClip = playingClips.get(uuid);
        }
        newClip.addLineListener(event -> {
            if (event.getType() != LineEvent.Type.STOP)
            {
                return;
            }
            changeVolume(playingClips.get(clipUUID),oldVolume);
        });
    }

    public void playAfter(int soundID, boolean loop, UUID playAfterSoundUUID){
        Clip clip = playingClips.get(playAfterSoundUUID);
        clip.addLineListener(event -> {
            if (event.getType() != LineEvent.Type.CLOSE)
            {
                return;
            }
            playSound(soundID, loop);
        });
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
        volume.shift(20 * (float) Math.log10(getVolume(clip)/100), 20 * (float) Math.log10(volumeLevel/100),50);
    }

    private double getVolume(Clip clip){
        FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        return Math.pow(10f, control.getValue() / 20f)*100;
    }

    public DoubleProperty getVolume()
    {
        return volume;
    }

    public static AudioManager getInstance()
    {
        return instance;
    }

    public void addListener(){
        DungeonTop.getStage().sceneProperty().addListener((observable, oldValue, newValue) -> {
            pauseClip(sceneSounds.get(oldValue));
            resetClip(sceneSounds.get(oldValue));
            resumeClip(sceneSounds.get(newValue));
            changeVolumeForAll(volume.get());
        });
    }

    private class AudioThread extends Thread{

        private int soundID;
        private UUID soundUUID;

        private boolean loop;

        public AudioThread(int soundID, UUID soundUUID, boolean loop){
            this.soundID = soundID;
            this.soundUUID = soundUUID;
            this.loop = loop;
        }

        @Override
        public void run() {
            GlobalLogger.log(String.format(LoggerStringValues.PLAY_SOUND, soundID), GlobalLogger.LoggerLevel.DEBUG);
            File soundFile = AssetsManager.getAssetById(soundID);
            try
            {
                final AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                LineListener listener = event ->
                {
                    if (event.getType() != LineEvent.Type.CLOSE)
                    {
                        return;
                    }
                    playingClips.remove(soundUUID);
                };
                clip.addLineListener(listener );
                if (loop){
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                }
                clip.start();
                playingClips.put(soundUUID,clip);
                changeVolume(clip,volume.get());
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e)
            {
                GlobalLogger.warning(e.getMessage());
            }
        }

    }

}
