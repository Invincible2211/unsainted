package de.prog2.dungeontop.model.game;

import de.prog2.dungeontop.utils.GlobalLogger;

public class Player{
    private int goldValue;

    public Player (){
        this.goldValue = 0;
    }
    public Player (int goldValue){
        this.goldValue = goldValue;
    }

    public int getGoldValue(){
        GlobalLogger.log("Playergold wurde abgefragt.");
        return this.goldValue;
    }

    public void setGoldValue(int goldValue){
        GlobalLogger.log("Playergold wurde auf " + goldValue + " gesetzt.");
        this.goldValue = this.goldValue;
    }
}
