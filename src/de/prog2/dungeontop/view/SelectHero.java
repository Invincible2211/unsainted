package de.prog2.dungeontop.view;

import de.prog2.dungeontop.DungeonTop;
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
import javafx.scene.text.Text;

import java.io.IOException;

public class SelectHero
{
    @FXML
    private Text heroClass;
    @FXML
    private Text heroHealth;
    @FXML
    private Text heroAttack;
    @FXML
    private Text heroMaxMoves;
    @FXML
    private Text heroTalent;


    /**
     * Diese sind die Eventmethode, welche ausgefuehrt wird, wenn auf den Hero-Button gedrueckt wird.
     * Hero's Werte(Angriffe, usw.) wird jetzt gezeigt.
     */
    @FXML
    private void onHero1ButtonClicked()
    {
        selectHeroFillText(SelectHeroConstants.WARRIOR);
        PlayerManager.getInstance().getPlayer().setHero(SelectHeroConstants.WARRIOR);
    }

    @FXML
    private void onHero2ButtonClicked()
    {
        selectHeroFillText(SelectHeroConstants.MAGE);
        PlayerManager.getInstance().getPlayer().setHero(SelectHeroConstants.MAGE);
    }

    @FXML
    private void onHero3ButtonClicked()
    {
        selectHeroFillText(SelectHeroConstants.ROGUE);
        PlayerManager.getInstance().getPlayer().setHero(SelectHeroConstants.ROGUE);
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

        if (playerHero == SelectHeroConstants.WARRIOR)
            HellView.setPlayerAssetId(AssetIds.WARRIOR);
        else if (playerHero == SelectHeroConstants.MAGE)
            HellView.setPlayerAssetId(AssetIds.MAGICIAN);
        else if (playerHero == SelectHeroConstants.ROGUE)
            HellView.setPlayerAssetId(AssetIds.ROGUE);

        GameManager.getInstance().getGameWorld().initWorld();
        DungeonTop.getStage().setScene(HellView.getCurrHellView());


        PlayerManager.getInstance().getPlayerHpProperty().bindBidirectional(PlayerManager.getInstance().getPlayerHero().getHpProperty());

        GameManager.getInstance().getSaveGame().setGameWorld(GameManager.getInstance().getGameWorld());
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

    public void selectHeroFillText(Hero hero)
    {
        getHeroClass().setText(SelectHeroConstants.PLAYER_CLASS + hero.getName());
        getHeroHealth().setText(SelectHeroConstants.PLAYER_HP + hero.getHp());
        getHeroAttack().setText(SelectHeroConstants.PLAYER_ATK + hero.getAttackDamage());
        getHeroMaxMoves().setText(SelectHeroConstants.PLAYER_MAX_MOVES + hero.getMaxMovement());
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

    public Text getHeroMaxMoves ()
    {
        return heroMaxMoves;
    }
}