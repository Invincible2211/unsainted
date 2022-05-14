package de.prog2.dungeontop.control.manager;

import de.prog2.dungeontop.model.entities.Entity;
import de.prog2.dungeontop.model.game.DungeonMaster;
import de.prog2.dungeontop.model.game.Player;
import de.prog2.dungeontop.model.world.Coordinate;
import de.prog2.dungeontop.model.world.arena.Arena;
import de.prog2.dungeontop.resources.ExceptionMessagesKeys;
import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.utils.GlobalLogger;

import java.util.concurrent.ThreadLocalRandom;

public class BattleManager
{

    private Player player = null;
    private DungeonMaster dm = null;
    private Arena arena = null;
    private BattlePhase currentPhase = BattlePhase.START;
    private boolean isPlayerfirst = false;


    public BattleManager (Player player, DungeonMaster dm, Arena arena)
    {
        this.player = player;
        this.dm = dm;
        this.arena = arena;
    }

    /**
     * coinflip for who can choose who is first.
     */
    public void startBattle ()
    {
        GlobalLogger.log(LoggerStringValues.START_BATTLE);
        if (ThreadLocalRandom.current().nextBoolean() == true){
            GlobalLogger.log(LoggerStringValues.PLAYER_CAN_CHOOSE_WHO_IS_FIRST);
            // HIER DARF SICH DER SPIELER AUSSUCHEN WER ZUERST ZIEHT... MIT EVENT?
            setCurrentPhase(getNextPhaseInCycle());
        } else {
            GlobalLogger.log(LoggerStringValues.DM_CAN_CHOOSE_WHO_IS_FIRST);
            // HIER DARF SICH DER DM AUSSUCHEN WER ZUERST ZIEHT... MIT EVENT?
            //Wait for choice
            setCurrentPhase(getNextPhaseInCycle());
        }
    }


    /**
     * Legt die Reihnfolge des Battles fest
     * Fuer die Moeglichkeit bei Phases mehr Logik zu implimentieren werden Phasen als Enum genommen anstatt extern in properties zu stehen.
     * @return die naechste Phase in einem Battle die wiederholbar ist. Kein Start und kein End
     */
    public BattlePhase getNextPhaseInCycle ()
    {
        switch(this.getCurrentPhase()){
            case START:
            case SECOND_DUELLIST_SECOND_PLACE_CARDS:
                GlobalLogger.log(LoggerStringValues.GOT_NEXT_BATTLEPHASE);
                return BattlePhase.DRAW_CARDS;
            case DRAW_CARDS:
                GlobalLogger.log(LoggerStringValues.GOT_NEXT_BATTLEPHASE);
                return BattlePhase.FIRST_DUELLIST_PLACE_CARDS;
            case FIRST_DUELLIST_PLACE_CARDS:
                GlobalLogger.log(LoggerStringValues.GOT_NEXT_BATTLEPHASE);
                return BattlePhase.SECOND_DUELLIST_PLACE_CARDS;
            case SECOND_DUELLIST_PLACE_CARDS:
                GlobalLogger.log(LoggerStringValues.GOT_NEXT_BATTLEPHASE);
                return BattlePhase.FIRST_DUELLIST_MINION_ACT;
            case FIRST_DUELLIST_MINION_ACT:
                GlobalLogger.log(LoggerStringValues.GOT_NEXT_BATTLEPHASE);
                return BattlePhase.SECOND_DUELLIST_MINION_ACT;
            case SECOND_DUELLIST_MINION_ACT:
                GlobalLogger.log(LoggerStringValues.GOT_NEXT_BATTLEPHASE);
                return BattlePhase.FIRST_DUELLIST_SECOND_PLACE_CARDS;
            case FIRST_DUELLIST_SECOND_PLACE_CARDS:
                GlobalLogger.log(LoggerStringValues.GOT_NEXT_BATTLEPHASE);
                return BattlePhase.SECOND_DUELLIST_SECOND_PLACE_CARDS;
            case END:
                GlobalLogger.warning(LoggerStringValues.TRY_TO_GET_PHASE_AFTER_END);
                return BattlePhase.END;
            default:
                GlobalLogger.warning(ExceptionMessagesKeys.CAN_NOT_FIND_BATTLESTATE);
                return null;
        }
    }

    public void endBattle (boolean victory)
    {
        setCurrentPhase(BattlePhase.END);
    }


    /**
     * Places card on Arena tile,
     * @param player who controlls the card
     * @param cordinate where to place new minion
     */
    public void placeCard (Player player, Coordinate cordinate)
    {

    }

    public void placeCard (DungeonMaster dungeonMaster)
    {

    }

    public void attack (Player player)
    {

    }

    public void attack (DungeonMaster dungeonMaster)
    {

    }

    /**
     * End the Battle and gives Souls and Skulls
     */
    public void endRound ()
    {

    }

    private void updateStats (Entity[] aliveMinions)
    {

    }

    private void updatePerks (Entity[] aliveMinions)
    {

    }

    public void updateBattlefield ()
    {
        updatePerks(arena.getAllMinions());
        updateStats(arena.getAllMinions());
    }

    public void moveUnit (Entity mover, Coordinate coordinate)
    {

    }

    public boolean isAllowedMove (Entity mover, Coordinate coordinate)
    {
        return false; // TODO
    }
    public void attackUnit (Entity attacker, Entity attecked)
    {

    }

    /**
     * Battlephases, Start und end sind nicht teil des spielcycles.
     */
    private enum BattlePhase
    {
        START, //at threading maybe wait for connections, Decide Beginner
        DRAW_CARDS, //where duellists get to draw to handcardlimit
        FIRST_DUELLIST_PLACE_CARDS, //first duellist can place cards on arena
        SECOND_DUELLIST_PLACE_CARDS, //second duellist can place cards on arena
        FIRST_DUELLIST_MINION_ACT, // first duellist can move and attack minions on field
        SECOND_DUELLIST_MINION_ACT, // second duellist can move and attack minions on field
        FIRST_DUELLIST_SECOND_PLACE_CARDS, // first duellist can place cards with leftover mana, minions placed here cant attck this round
        SECOND_DUELLIST_SECOND_PLACE_CARDS, //second duellist can place cards with leftover mana, minions placed here cant attck this round
        END // choose treasure, get exp, skulls, etc. and go back to overworld
    }

    /**
     * auch Battlephases aber nicht Teil des cycles der Runden
     */
    private enum BattlePhaseSpecials
    {
        START, //at threading maybe wait for connections, Decide Beginner
        END // choose treasure, get exp, skulls, etc. and go back to overworld
    }


    public Player getPlayer ()
    {
        return player;
    }

    public void setPlayer (Player player)
    {
        this.player = player;
    }

    public DungeonMaster getDm ()
    {
        return dm;
    }

    public void setDm (DungeonMaster dm)
    {
        this.dm = dm;
    }

    public Arena getArena ()
    {
        return arena;
    }

    public void setArena (Arena arena)
    {
        this.arena = arena;
    }

    public BattlePhase getCurrentPhase ()
    {
        return currentPhase;
    }

    public void setCurrentPhase (BattlePhase currentPhase)
    {
        this.currentPhase = currentPhase;
    }

    public boolean isPlayerfirst ()
    {
        return isPlayerfirst;
    }

    public void setPlayerfirst (boolean playerfirst)
    {
        isPlayerfirst = playerfirst;
    }
}
