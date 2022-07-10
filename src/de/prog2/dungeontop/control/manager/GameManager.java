package de.prog2.dungeontop.control.manager;

import de.prog2.dungeontop.DungeonTop;
import de.prog2.dungeontop.control.file.GameSaveFileReader;
import de.prog2.dungeontop.model.entities.Hero;
import de.prog2.dungeontop.model.game.GameState;
import de.prog2.dungeontop.model.game.SaveGame;
import de.prog2.dungeontop.model.world.World;
import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.resources.ViewStrings;
import de.prog2.dungeontop.resources.WorldConstants;
import de.prog2.dungeontop.utils.GlobalLogger;
import de.prog2.dungeontop.view.SettingsController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class GameManager {

    /*----------------------------------------------ATTRIBUTE---------------------------------------------------------*/

    private final static GameManager instance = new GameManager();

    private GameState currentState = GameState.MAIN_MENU;
    private World gameWorld = new World(WorldConstants.HELL_COUNT);
    private boolean isDM = false;

    private SaveGame saveGame = GameSaveFileReader.getInstance().getSaveGame();

    /*--------------------------------------------KONSTRUKTOREN-------------------------------------------------------*/

    /**
     * Der Konstruktor ist private, da der GameManager ein Singelton ist
     */
    private GameManager()
    {
        //this.gameWorld.initWorld();
    }

    /*----------------------------------------------METHODEN----------------------------------------------------------*/

    /**
     * Das Spiel wird in den Zustand des gestarteten Spiels gesetzt.
     */
    public void startGame()
    {
        SaveGame saveGame = GameSaveFileReader.getInstance().getSaveGame();
        if (saveGame == null)
        {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = null;
            try
            {
                root = fxmlLoader.load(DungeonTop.class.getClassLoader().getResourceAsStream(ViewStrings.SELECT_HERO_FXML));
            } catch (IOException e)
            {
                GlobalLogger.warning(LoggerStringValues.FXML_LOAD_ERROR);
            }
            Scene scene = new Scene(root);
            DungeonTop.getStage().setScene(scene);
        }
        else
        {
            setGameWorld(saveGame.getGameWorld());
        }
        this.currentState = GameState.RUNNING;
    }

    /**
     * Das Spiel wird in den Zustand des beendeten Spiels gesetzt.
     */
    public void endGame()
    {
        this.currentState = GameState.END;
    }

    /**
     * Das Spiel wird in den Zustand des pausierten Spiels gesetzt.
     */
    public void pause()
    {
        SettingsController.showSettings();
        this.currentState = GameState.PAUSE;
    }

    /**
     * Das Spiel wird in den Zustand des gestarteten Kampfes gesetzt.
     */
    public void beginBattle()
    {
        this.currentState = GameState.BATTLE;
    }

    /**
     * Das Spiel wird in den Zustand eines gescheiterten Spieldurchlaufs gesetzt.
     */
    public void loseRun()
    {
        this.currentState = GameState.LOSE;
    }

    /**
     * Das Spiel wird in den Zustand eines geoeffneten Shops gesetzt.
     */
    public void openShop()
    {
        this.currentState = GameState.SHOP;
    }

    /**
     * Das Spiel wird in den Zustand der Erkundung der Hell gesetzt.
     */
    public void exploringHell()
    {
        this.currentState = GameState.EXPLORING;
    }

    /*-----------------------------------------GETTER AND SETTER------------------------------------------------------*/
    public GameState getCurrentState()
    {
        return currentState;
    }
    public static GameManager getInstance()
    {
        return instance;
    }
    public World getGameWorld() { return this.gameWorld; }
    public void setDM ()
    {
        this.isDM = true;
    }
    public boolean isDM()
    {
        return this.isDM;
    }
    public SaveGame getSaveGame() {
        return saveGame;
    }
    public void setGameWorld (World newWorld)
    {
        this.gameWorld = newWorld;
    }
}
