package de.prog2.dungeontop.control.manager;

import de.prog2.dungeontop.model.entities.Hero;
import de.prog2.dungeontop.model.game.Player;
import de.prog2.dungeontop.model.items.Inventory;
import de.prog2.dungeontop.model.items.Item;
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
        player.setSouls((player.getSouls() + amount + (int) Math.floor((float)(amount*player.getSoulArtBonus()/100))));
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
        player.setHp(player.getHp() - (amount - (int)Math.floor((float)(amount*player.getDefArtBonus()/100))));
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
    public static void removeItem (Item item)
    {
        instance.getPlayerInventory().removeItem(item);
    }
    public static void addItem (Item item)
    {
        instance.getPlayerInventory().addItem(item);
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




    /*-----------------------------------------Item-Equip-Methoden------------------------------------------------------*/
    public void addEquipAttackBonus()
    {
        player.getHero().setAttackDamage(player.getHero().getAttackDamage() + player.getWeaponSlot().get(0).getValue());
    }
    public void revertEquipAttackBonus()
    {
        player.getHero().setAttackDamage(player.getHero().getAttackDamage() - player.getWeaponSlot().get(0).getValue());
    }

    public void addArtAttackBonus1()
    {
        player.getHero().setAttackDamage(player.getHero().getAttackDamage() + player.getArtifactSlot1().get(0).getValue());
    }
    public void revertArtAttackBonus1()
    {
        player.getHero().setAttackDamage(player.getHero().getAttackDamage() - player.getArtifactSlot1().get(0).getValue());
    }
    public void addArtSoulsBonus1()
    {
        player.setSoulArtBonus(player.getSoulArtBonus() + player.getArtifactSlot1().get(0).getValue());
    }
    public void revertArtSoulsBonus1()
    {
        player.setSoulArtBonus(player.getSoulArtBonus() + player.getArtifactSlot1().get(0).getValue());
    }
    public void addArtDefenseBonus1()
    {
        player.setDefArtBonus(player.getDefArtBonus() + player.getArtifactSlot1().get(0).getValue());
    }
    public void revertArtDefenseBonus1()
    {
        player.setDefArtBonus(player.getDefArtBonus() + player.getArtifactSlot1().get(0).getValue());
    }


    public void addArtAttackBonus2()
    {
        player.getHero().setAttackDamage(player.getHero().getAttackDamage() + player.getArtifactSlot2().get(0).getValue());
    }
    public void revertArtAttackBonus2()
    {
        player.getHero().setAttackDamage(player.getHero().getAttackDamage() - player.getArtifactSlot2().get(0).getValue());
    }
    public void addArtSoulsBonus2()
    {
        player.setSoulArtBonus(player.getSoulArtBonus() + player.getArtifactSlot2().get(0).getValue());
    }
    public void revertArtSoulsBonus2()
    {
        player.setSoulArtBonus(player.getSoulArtBonus() + player.getArtifactSlot2().get(0).getValue());
    }
    public void addArtDefenseBonus2()
    {
        player.setDefArtBonus(player.getDefArtBonus() + player.getArtifactSlot2().get(0).getValue());
    }
    public void revertArtDefenseBonus2()
    {
        player.setDefArtBonus(player.getDefArtBonus() + player.getArtifactSlot2().get(0).getValue());
    }
}
