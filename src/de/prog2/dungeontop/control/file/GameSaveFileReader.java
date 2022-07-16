package de.prog2.dungeontop.control.file;

import de.prog2.dungeontop.DungeonTop;
import de.prog2.dungeontop.model.game.SaveGame;
import de.prog2.dungeontop.resources.FilePaths;
import org.apache.commons.lang3.SerializationException;
import org.apache.commons.lang3.SerializationUtils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class GameSaveFileReader {

    private final static GameSaveFileReader instance = new GameSaveFileReader();
    private SaveGame saveGame;

    private GameSaveFileReader(){
        if (this.saveFileExists()){
            this.readSaveFile();
        }
    }

    private boolean saveFileExists(){
        return Files.exists(Path.of(FilePaths.SAVE_FILE_PATH));
    }

    private void readSaveFile(){
        try {
            saveGame = (SaveGame) SerializationUtils.deserialize(DungeonTop.class.getClassLoader().getResourceAsStream(FilePaths.SAVE_FILE_PATH_FOR_CLASSLOADER));
        } catch (SerializationException e){
            File file = new File(FilePaths.SAVE_FILE_PATH_FOR_CLASSLOADER);
            file.delete();
        }
    }

    public static GameSaveFileReader getInstance() {
        return instance;
    }

    public SaveGame getSaveGame() {
        return saveGame;
    }

}
