package de.prog2.dungeontop.view;

import de.prog2.dungeontop.DungeonTop;
import de.prog2.dungeontop.control.file.GameSaveFileReader;
import de.prog2.dungeontop.control.manager.GameManager;
import de.prog2.dungeontop.control.manager.PlayerManager;
import de.prog2.dungeontop.model.entities.Hero;
import de.prog2.dungeontop.resources.AssetIds;
import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.resources.SelectHeroConstants;
import de.prog2.dungeontop.resources.ViewStrings;
import de.prog2.dungeontop.utils.GlobalLogger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;

// TODO Bitte Magic entfernen, was ist das hier?
public class SelectHero
{
    private final Hero warrior = new Hero(SelectHeroConstants.WARRIOR_NAME, SelectHeroConstants.WARRIOR_HP,
            SelectHeroConstants.WARRIOR_ATK, SelectHeroConstants.ROGUE_TALENT);
    private final Hero mage = new Hero(SelectHeroConstants.MAGE_NAME, SelectHeroConstants.MAGE_HP,
            SelectHeroConstants.MAGE_ATK, SelectHeroConstants.MAGE_TALENT);
    private final Hero rogue = new Hero(SelectHeroConstants.ROGUE_NAME, SelectHeroConstants.ROGUE_HP,
            SelectHeroConstants.ROGUE_ATK, SelectHeroConstants.ROGUE_TALENT);

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
        selectHeroFillText(warrior);
        PlayerManager.getInstance().getPlayer().setHero(warrior);
    }

    @FXML
    private void onHero2ButtonClicked()
    {
        selectHeroFillText(mage);
        PlayerManager.getInstance().getPlayer().setHero(mage);
    }

    @FXML
    private void onHero3ButtonClicked()
    {
        selectHeroFillText(rogue);
        PlayerManager.getInstance().getPlayer().setHero(rogue);
    }

    /**
     * Diese sind die Eventmethode, welche ausgefuehrt wird, wenn auf den Confirm-Button gedrueckt wird.
     * Held-Auswahl wird bestätigt und der Spieler geht zur nächsten Szene (Hell) über
     */
    @FXML
    private void onConfirmButtonClicked()
    {
        Hero playerHero = PlayerManager.getInstance().getPlayerHero();
        if (playerHero == null)
        {
            GlobalLogger.warning(LoggerStringValues.NO_CHAR_SELECTED);
            return;
        }

        if (playerHero == warrior)
            HellView.setPlayerAssetId(AssetIds.WARRIOR);
        else if (playerHero == mage)
            HellView.setPlayerAssetId(AssetIds.MAGICIAN);
        else if (playerHero == rogue)
            HellView.setPlayerAssetId(AssetIds.ROGUE);

        GameManager.getInstance().getGameWorld().initWorld();
        DungeonTop.getStage().setScene(HellView.getCurrHellView());

        GameManager.getInstance().getSaveGame().setGameWorld(GameManager.getInstance().getGameWorld());
        GameManager.getInstance().getSaveGame().setPlayer(PlayerManager.getInstance().getPlayer());
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
        getHeroClass().setText(SelectHeroConstants.PLAYER_CLASS + hero.getName());
        getHeroHealth().setText(SelectHeroConstants.PLAYER_HP + hero.getHp());
        getHeroAttack().setText(SelectHeroConstants.PLAYER_ATK + hero.getAttackDamage());
        getHeroTalent().setText(SelectHeroConstants.PLAYER_TALENT + hero.getTalent());
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
}