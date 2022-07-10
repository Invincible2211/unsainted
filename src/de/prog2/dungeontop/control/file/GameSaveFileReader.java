package de.prog2.dungeontop.control.file;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import de.prog2.dungeontop.DungeonTop;
import de.prog2.dungeontop.model.game.SaveGame;
import de.prog2.dungeontop.resources.FilePaths;
import org.apache.commons.lang3.SerializationUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
        saveGame = (SaveGame) SerializationUtils.deserialize(DungeonTop.class.getClassLoader().getResourceAsStream(FilePaths.SAVE_FILE_PATH_FOR_CLASSLOADER));
    }

    public static GameSaveFileReader getInstance() {
        return instance;
    }

    public SaveGame getSaveGame() {
        return saveGame;
    }

}
