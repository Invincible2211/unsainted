package de.prog2.dungeontop.control.file;

import de.prog2.dungeontop.model.game.SaveGame;
import org.apache.commons.lang3.SerializationUtils;

import java.io.*;

public class GameSaveFileWriter {

    private final static GameSaveFileWriter instance = new GameSaveFileWriter();

    private GameSaveFileWriter(){

    }

    public static GameSaveFileWriter getInstance() {
        return instance;
    }

    public void saveGame(SaveGame game){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(game);
            OutputStream outputStream = new FileOutputStream(new File("rsc/config/savegame.unsainted"));
            byteArrayOutputStream.writeTo(outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
