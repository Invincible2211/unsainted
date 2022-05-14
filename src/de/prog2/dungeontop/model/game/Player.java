package de.prog2.dungeontop.model.game;

import de.prog2.dungeontop.utils.GlobalLogger;

import de.prog2.dungeontop.resources.LoggerStringValues;

public class Player{
    private int souls;

    public Player (){
        this.souls = 0;
    }
    public Player (int souls){
        this.souls = souls;
    }

    public int getSouls(){
        GlobalLogger.log(LoggerStringValues.PLAYERSOULS_GET);
        return this.souls;
    }

    public void setSouls(int souls){
        GlobalLogger.log(LoggerStringValues.PLAYERSOULS_SET + souls + LoggerStringValues.SET);
        this.souls = souls;
    }
}
