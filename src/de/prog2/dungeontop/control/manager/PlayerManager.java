package de.prog2.dungeontop.control.manager;

import de.prog2.dungeontop.model.entities.Hero;
import de.prog2.dungeontop.model.game.Player;
import de.prog2.dungeontop.model.items.Inventory;
import de.prog2.dungeontop.model.items.Item;
import de.prog2.dungeontop.resources.GameConstants;
import javafx.beans.property.SimpleIntegerProperty;

public class PlayerManager
{
    private final static PlayerManager instance = new PlayerManager();

    private Player player = new Player();

    private PlayerManager(){
        initPlayerData();
    }

    public void addSouls(int amount)
    {
        player.setSouls((player.getSouls() + (amount+((int) Math.floor((float)amount*player.getSoulArtBonus()/GameConstants.LEVEL_1_EXP_CAP)))));
    }

    public void removeSouls(int amount)
    {
        player.setSouls(player.getSouls() - amount);
    }

    public int getPlayerSouls(){
        return player.getSouls();
    }

    public int getPlayerHp ()
    {
        return player.getHp();
    }
    public Hero getPlayerHero ()
    {
        return player.getHero();
    }

    public void addHp (int amount)
    {
        player.setHp(player.getHp() + amount);
    }

    public void removeHp (int amount)
    {
        player.setHp(player.getHp() - amount);
    }

    public SimpleIntegerProperty getPlayerHpProperty ()
    {
        return player.getHpProperty();
    }

    public SimpleIntegerProperty getPlayerSoulsProperty ()
    {
        return player.getSoulsProperty();
    }

    public void addEgoPoints(int amount)
    {
        player.setMax_ego_points(player.getMax_ego_points() + amount);
    }
    public void removeEgoPoints(int amount)
    {
        player.setMax_ego_points(player.getMax_ego_points() - amount);
    }
    public int getPlayerEgoPoints()
    {
        return player.getMax_ego_points();
    }




    private void playerDied()
    {

    }

    private void savePlayerDataToFile()
    {

    }

    private void initPlayerData()
    {
        //HashMap<String,String> playerData = GameSaveFileReader.getSaveFile().getPlayerData();
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

    public Inventory getPlayerInventory () { return this.getPlayer().getInventory(); }

    public void setPlayer(Player player)
    {
        this.player = player;
    }

    public void checkLevelUp()
    {
        if (player.getExperiencePoints() >= player.getExpCap())
        {
            player.levelUp();
        }
    }
}
