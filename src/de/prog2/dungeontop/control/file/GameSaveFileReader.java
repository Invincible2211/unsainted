package de.prog2.dungeontop.control.file;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import de.prog2.dungeontop.model.savegame.GameSaveData;
import de.prog2.dungeontop.model.savegame.PlayerSaveData;
import de.prog2.dungeontop.resources.FilePaths;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class GameSaveFileReader {

    private final static GameSaveFileReader instance = new GameSaveFileReader();

    private GameSaveData gameSaveData;
    private PlayerSaveData playerSaveData;

    private GameSaveFileReader(){
        if (this.saveFileExists()){
            this.readSaveFile();
        } else {
            this.initSaveFile();
        }
    }

    private boolean saveFileExists(){
        return false; //TODO
    }
    private void initSaveFile(){

    }

    private void readSaveFile(){
        JsonParser jsonParser = new JsonParser();
        try {
            JsonElement jsonFile = jsonParser.parse(new FileReader(new File(FilePaths.SAVE_FILE_PATH)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        //TODO json -> GamesaveData + PlayerSaveData
    }

    public static GameSaveFileReader getInstance() {
        return instance;
    }

    public GameSaveData getGameSaveData() {
        return gameSaveData;
    }

    public PlayerSaveData getPlayerSaveData() {
        return playerSaveData;
    }

}
