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

    public void beginnBattle(){
        this.currentState = GameState.BATTLE;
    }

    public GameState getCurrentState() {
        return currentState;
    }

}
