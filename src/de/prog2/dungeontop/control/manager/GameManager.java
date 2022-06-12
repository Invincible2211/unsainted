package de.prog2.dungeontop.control.manager;

import de.prog2.dungeontop.model.game.GameState;

public class GameManager {

    /*----------------------------------------------ATTRIBUTE---------------------------------------------------------*/

    private final static GameManager instance = new GameManager();

    private GameState currentState = GameState.MAIN_MENU;

    /*--------------------------------------------KONSTRUKTOREN-------------------------------------------------------*/

    /**
     * Der Konstruktor ist private, da der GameManager ein Singelton ist
     */
    private GameManager()
    {

    }

    /*----------------------------------------------METHODEN----------------------------------------------------------*/

    /**
     * Das Spiel wird in den Zustand des gestarteten Spiels gesetzt.
     */
    public void startGame()
    {
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
    public void Pause()
    {
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

}
