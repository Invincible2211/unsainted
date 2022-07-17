package de.prog2.dungeontop.view;

import de.prog2.dungeontop.DungeonTop;
import de.prog2.dungeontop.control.controller.DeckController;
import de.prog2.dungeontop.control.manager.AudioManager;
import de.prog2.dungeontop.control.manager.GameManager;
import de.prog2.dungeontop.control.manager.PlayerManager;
import de.prog2.dungeontop.model.entities.Hero;
import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.model.world.World;
import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.resources.SelectHeroConstants;
import de.prog2.dungeontop.resources.TestConstants;
import de.prog2.dungeontop.resources.WorldConstants;
import de.prog2.dungeontop.resources.views.ViewStrings;
import de.prog2.dungeontop.utils.GlobalLogger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.UUID;

public class SelectHeroView
{
    @FXML
    private Text heroClass;
    @FXML
    private Text heroHealth;
    @FXML
    private Text heroAttack;
    @FXML
    private Text heroDefense;
    @FXML
    private Text heroMaxMoves;
    @FXML
    private Text heroTalent;

    /**
     * Dies sind die Eventmethoden, welche ausgefuehrt werden, wenn auf einen der Hero-Button gedrueckt wird.
     * Anzeige der Hero-Werte(Angriff, usw.) wird in der aktuellen Ansicht aktualisiert.
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
     * Dies ist die Eventmethode, welche ausgefuehrt wird, wenn auf den Confirm-Button gedrueckt wird.
     * Helden-Auswahl wird bestaetigt und der Spieler wechselt zur naechsten Szene (Hell)
     */
    @FXML
    private void onConfirmButtonClicked()
    {
        // get the player's hero and make sure it is not null
        Hero playerHero = PlayerManager.getInstance().getPlayerHero();
        if (playerHero == null)
        {
            GlobalLogger.warning(LoggerStringValues.NO_CHAR_SELECTED);
            return;
        }
        
        PlayerManager.getInstance().getPlayer().setDeck(DeckController.getRandomDeck(false));
        PlayerManager.getInstance().getPlayer().setHandCardLimit(SelectHeroConstants.PLAYER_HANDCARDLIMIT);
        // initialize a new game world and go to the HellView
        if (GameManager.getInstance().getGameWorld() == null)
            GameManager.getInstance().setGameWorld(new World(WorldConstants.HELL_COUNT));
        GameManager.getInstance().getGameWorld().initWorld();
        HellView.resumeHellViewBgMusic();
        DungeonTop.getStage().setScene(HellView.getCurrHellView());

        // bind the hp of the Player to it's Hero Entity
        PlayerManager.getInstance().getPlayerHpProperty().bindBidirectional(PlayerManager.getInstance().getPlayerHero().getHpProperty());

        // set the gameworld that will be saved upon exiting the game
        GameManager.getInstance().getSaveGame().setGameWorld(GameManager.getInstance().getGameWorld());
        AudioManager.getInstance().stopSound(MainMenueController.getHeroSelectionSoundUUID());
    }

    /**
     * Dies ist die Eventmethode, welche ausgefuehrt wird, wenn auf den Return-Button gedrueckt wird.
     * Der Spieler kehrt zum Hauptmenue zurück.
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
     * Sets the description text of a selected hero.
     * This includes his base stats, talent and name.
     *
     * @param hero Hero for whom the description shall be shown.
     */
    public void selectHeroFillText(Hero hero)
    {
        getHeroClass().setText(SelectHeroConstants.PLAYER_CLASS + hero.getName());
        getHeroHealth().setText(SelectHeroConstants.PLAYER_HP + hero.getHp());
        getHeroAttack().setText(SelectHeroConstants.PLAYER_ATK + hero.getAttackDamage());
        getHeroDefense().setText(SelectHeroConstants.PLAYER_DEF + hero.getDefense());
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

    public Text getHeroDefense()
    {
        return heroDefense;
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