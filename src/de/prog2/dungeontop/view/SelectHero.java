package de.prog2.dungeontop.view;

import de.prog2.dungeontop.DungeonTop;
import de.prog2.dungeontop.control.manager.GameManager;
import de.prog2.dungeontop.control.manager.PlayerManager;
import de.prog2.dungeontop.model.entities.Hero;
import de.prog2.dungeontop.model.game.Player;
import de.prog2.dungeontop.resources.AssetIds;
import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.resources.ViewStrings;
import de.prog2.dungeontop.utils.GlobalLogger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;

public class SelectHero
{
    private Hero hero;
    private Hero warrior;
    private Hero mage;
    private Hero rogue;

    @FXML
    private VBox heroText;
    @FXML
    private Text heroClass;
    @FXML
    private Text heroHealth;
    @FXML
    private Text heroAttack;
    @FXML
    private Text heroTalent;


    /**
     * Diese sind die Eventmethode, welche ausgefuehrt wird, wenn auf den Hero-Button gedrueckt wird.
     * Hero's Werte(Angriffe, usw.) wird jetzt gezeigt.
     */
    @FXML
    private void onHero1ButtonClicked()
    {
        heroText.setVisible(false);
        selectHeroFillText(warrior);
        hero = warrior;
        heroText.setVisible(true);
        PlayerManager.getInstance().getPlayer().setHero(hero);
    }

    @FXML
    private void onHero2ButtonClicked()
    {
        heroText.setVisible(false);
        selectHeroFillText(mage);
        hero = mage;
        heroText.setVisible(true);
        PlayerManager.getInstance().getPlayer().setHero(hero);
    }

    @FXML
    private void onHero3ButtonClicked()
    {
        heroText.setVisible(false);
        selectHeroFillText(rogue);
        hero = rogue;
        heroText.setVisible(true);
        PlayerManager.getInstance().getPlayer().setHero(hero);
    }

    /**
     * Diese sind die Eventmethode, welche ausgefuehrt wird, wenn auf den Confirm-Button gedrueckt wird.
     * Held-Auswahl wird bestätigt und der Spieler geht zur nächsten Szene (Hell) über
     */
    @FXML
    private void onConfirmButtonClicked()
    {
        // TODO: Possibility to choose new game or load game
        if (hero == null)
        {
            GlobalLogger.warning(LoggerStringValues.NO_CHAR_SELECTED);
            return;
        }

        if (hero == warrior)
            HellView.setPlayerAssetId(AssetIds.WARRIOR);
        else if (hero == mage)
            HellView.setPlayerAssetId(AssetIds.MAGICIAN);
        else if (hero == rogue)
            HellView.setPlayerAssetId(AssetIds.ROGUE);

        GameManager.getInstance().getGameWorld().initWorld();
        DungeonTop.getStage().setScene(HellView.getCurrHellView());
    }

    /**
     * Diese sind die Eventmethode, welche ausgefuehrt wird, wenn auf den Return-Button gedrueckt wird.
     * Der Spieler kehrt zum Hauptmenü zurück.
     */
    @FXML
    private void onReturnButtonClicked() throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(DungeonTop.class.getClassLoader().getResourceAsStream(ViewStrings.MAIN_MENUE_FXML));
        Scene scene = new Scene(root);
        DungeonTop.getStage().setScene(scene);
    }

    /**
     * Diese sind die Eventmethode, welche ausgefuehrt wird, wenn auf den Shop-Button gedrueckt wird.
     * Der Spieler betritt das Shop.
     */
    @FXML
    private void onOpenShopButtonClicked() throws  IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(DungeonTop.class.getClassLoader().getResourceAsStream(ViewStrings.SHOP_VIEW_FXML));
        Scene scene = new Scene(root);
        DungeonTop.getStage().setScene(scene);
    }

    public void selectHeroFillText(Hero hero)
    {
        getHeroClass().setText("Class: " + hero.getName());
        getHeroHealth().setText("Health: " + hero.getHp());
        getHeroAttack().setText("Attack: " + hero.getAttackDamage());
        getHeroTalent().setText("Talent: " + hero.getTalent());
    }

    //Get- und Setters
    public Text getHeroClass()
    {
        return heroClass;
    }

    public Text getHeroHealth()
    {
        return heroHealth;
    }

    public Text getHeroAttack()
    {
        return heroAttack;
    }
    public Text getHeroTalent()
    {
        return heroTalent;
    }

    public Hero getWarrior()
    {
        return warrior;
    }

    public Hero getMage()
    {
        return mage;
    }

    public Hero getRogue()
    {
        return rogue;
    }

    public void setWarrior(Hero warrior)
    {
        this.warrior = warrior;
    }

    public void setMage(Hero mage)
    {
        this.mage = mage;
    }

    public void setRogue(Hero rogue)
    {
        this.rogue = rogue;
    }
}