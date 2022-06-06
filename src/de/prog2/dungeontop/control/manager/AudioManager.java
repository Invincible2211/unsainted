package de.prog2.dungeontop.control.manager;

import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.utils.GlobalLogger;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableDoubleValue;
import javafx.beans.value.ObservableValue;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AudioManager {

    private final static AudioManager instance = new AudioManager();
    private List<Clip> playingClips = new ArrayList<>();
    private DoubleProperty volume = new SimpleDoubleProperty(100.0);

    private AudioManager(){
        volume.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                changeVolume(newValue.doubleValue());
            }
        });
    }

    public void playSound(int soundID) {
        GlobalLogger.log(String.format(LoggerStringValues.PLAY_SOUND, soundID));
        File soundFile = AssetsManager.getAssetById(soundID);
        try {
            final AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            playingClips.add(clip);
            LineListener listener = new LineListener() {
                public void update(LineEvent event) {
                    if (event.getType() != LineEvent.Type.STOP) {
                        return;
                    }
                    System.out.println("removed");
                    playingClips.remove(clip);
                }
            };
            clip.addLineListener(listener );
            clip.start(); //TODO auf Thread auslagern sobald erlaubt
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    private void changeVolume(double volumeLevel){
        FloatControl volume;
        for (Clip c:
             playingClips) {
            volume = (FloatControl) c.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(20f * (float) Math.log10(volumeLevel/100));
        }
    }

    public static AudioManager getInstance() {
        return instance;
    }

    public DoubleProperty getVolume() {
        return volume;
    }
}
