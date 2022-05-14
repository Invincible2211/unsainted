package de.prog2.dungeontop.control.file;

public class GameSaveFileWriter {

    private final static GameSaveFileWriter instance = new GameSaveFileWriter();

    private GameSaveFileWriter(){

    }

    public static GameSaveFileWriter getInstance() {
        return instance;
    }

}
