package de.prog2.dungeontop.control.file;

import de.prog2.dungeontop.control.manager.GameManager;
import de.prog2.dungeontop.model.game.SaveGame;
import de.prog2.dungeontop.resources.FilePaths;

import java.io.*;

public class GameSaveFileWriter {

    private final static GameSaveFileWriter instance = new GameSaveFileWriter();

    private GameSaveFileWriter(){

    }

    public static GameSaveFileWriter getInstance() {
        return instance;
    }

    public void saveGame(SaveGame game){
        if (!GameManager.getInstance().isDM()){
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                objectOutputStream.writeObject(game);
                OutputStream outputStream = new FileOutputStream(new File(FilePaths.SAVE_FILE_PATH));
                byteArrayOutputStream.writeTo(outputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
