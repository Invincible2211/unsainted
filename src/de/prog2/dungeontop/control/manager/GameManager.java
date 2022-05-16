package de.prog2.dungeontop.control.manager;

import de.prog2.dungeontop.model.game.GameState;

public class GameManager {

    private GameState currentState = GameState.MAIN_MENU;

    public void startGame(){
        this.currentState = GameState.RUNNING;
    }

    public void endGame(){
        this.currentState = GameState.END;
    }

    public void Pause(){
        this.currentState = GameState.PAUSE;
    }

    public void beginBattle()
    {
        this.currentState = GameState.BATTLE;
    }

    public void loseRun()
    {
        this.currentState = GameState.LOSE;
    }

    public void openShop()
    {
        this.currentState = GameState.SHOP;
    }

    public void exploringHell()
    {
        this.currentState = GameState.EXPLORING;
    }

    public GameState getCurrentState() {
        return currentState;
    }
}
