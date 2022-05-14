package de.prog2.dungeontop.control.manager;

import de.prog2.dungeontop.control.file.GameSaveFileReader;
import de.prog2.dungeontop.model.game.Player;

import java.util.HashMap;

public class PlayerManager {

    private final static PlayerManager instance = new PlayerManager();

    private final Player player = new Player();

    private PlayerManager(){
        initPlayerData();
    }

    public void addSouls(int amount){
        player.setSouls(player.getSouls() + amount);
    }

    public void removeSouls(int amount)
    {
        player.setSouls(player.getSouls() - amount);
    }

    public int getPlayerSouls(){
        return player.getSouls();
    }

    public void addEgoPoints(int amount)
    {
        player.setEgo_points(player.getEgo_points() + amount);
    }
    public void removeEgoPoints(int amount)
    {
        player.setEgo_points(player.getEgo_points() - amount);
    }
    public int getPlayerEgoPoints()
    {
        return player.getEgo_points();
    }

    private void playerDied()
    {

    }

    private void savePlayerDataToFile()
    {

    }

    private void initPlayerData()
    {
        HashMap<String,String> playerData = GameSaveFileReader.getSaveFile().getPlayerData();
    }

    public static PlayerManager getInstance() {
        return instance;
    }

    public Player getPlayer() {
        return player;
    }

    public void loadDeck()
    {

    }

}
