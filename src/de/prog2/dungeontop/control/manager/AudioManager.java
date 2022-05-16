package de.prog2.dungeontop.control.manager;

import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.utils.GlobalLogger;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AudioManager {

    public static void playSound(int soundID) {
        GlobalLogger.log(String.format(LoggerStringValues.PLAY_SOUND, soundID));
        File soundFile = AssetsManager.getAssetById(soundID);
        try {
            final AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start(); //TODO auf Thread auslagern sobald erlaubt
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

}
