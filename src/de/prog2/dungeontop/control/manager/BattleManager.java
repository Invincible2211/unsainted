package de.prog2.dungeontop.control.manager;

import de.prog2.dungeontop.model.entities.Entity;
import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.model.game.DungeonMaster;
import de.prog2.dungeontop.model.game.Player;
import de.prog2.dungeontop.model.world.Coordinate;
import de.prog2.dungeontop.model.world.arena.Arena;
import de.prog2.dungeontop.resources.ExceptionMessagesKeys;
import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.utils.CoordinateDirections;
import de.prog2.dungeontop.utils.GlobalLogger;

import java.util.ArrayList;
import java.util.Random;

public class BattleManager
{
    private Player player = null;
    private DungeonMaster dm = null;
    private Arena arena = null;
    private BattlePhase currentPhase = BattlePhase.START;
    private ArrayList<Card> firstDuellistHand = new ArrayList<Card>();
    private ArrayList<Card> secondDuellistHand = new ArrayList<Card>();
    private ArrayList<Entity> firstDuellistEntitiesWhoCanAttack = new ArrayList<>();
    private ArrayList<Entity> secondDuellistEntitiesWhoCanAttack = new ArrayList<>();
    private ArrayList<Entity> firstDuellistEntitiesWhoCanMove = new ArrayList<>();
    private ArrayList<Entity> secondDuellistEntitiesWhoCanMove = new ArrayList<>();
    private ArrayList<Entity> firstDuellistEntities = new ArrayList<>();
    private ArrayList<Entity> secondDuellistEntities = new ArrayList<>();
    private Player firstduellist = null;
    private Player secondduellist = null;
    private Player currentActiveDuellist = null;


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
        Random random = new Random();
        if (random.nextBoolean() == true){
            GlobalLogger.log(LoggerStringValues.PLAYER_CAN_CHOOSE_WHO_IS_FIRST);
            // HIER DARF SICH DER SPIELER AUSSUCHEN WER ZUERST ZIEHT... MIT EVENT?
            //Wait for choice of another Thread when we are allowed to
            setPlayerAsFirstDuellist(true); //TODO hier das TRUE aendern mit dem Ergebniss der choice wer zuerst darf
            setCurrentPhase(getNextPhaseInCycle());
        } else {
            GlobalLogger.log(LoggerStringValues.DM_CAN_CHOOSE_WHO_IS_FIRST);
            // HIER DARF SICH DER DM AUSSUCHEN WER ZUERST ZIEHT... MIT EVENT?
            //Wait for choice of another Thread when we are allowed to
            setPlayerAsFirstDuellist(true); //TODO hier das TRUE aendern mit dem Ergebniss der choice wer zuerst darf
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
        switch(this.getCurrentPhase())
        {
            case START:
            case SECOND_DUELLIST_SECOND_PLACE_CARDS:
                GlobalLogger.log(LoggerStringValues.GOT_NEXT_BATTLEPHASE);
                return BattlePhase.FIRSTDUELLIST_DRAW;

            case FIRSTDUELLIST_DRAW:
                GlobalLogger.log(LoggerStringValues.GOT_NEXT_BATTLEPHASE);
                return BattlePhase.FIRST_DUELLIST_PLACE_CARDS;

            case FIRST_DUELLIST_PLACE_CARDS:
                GlobalLogger.log(LoggerStringValues.GOT_NEXT_BATTLEPHASE);
                return BattlePhase.SECOND_DUELLIST_DRAW;

            case SECOND_DUELLIST_DRAW:
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
        if (victory == true) {
            GlobalLogger.log(LoggerStringValues.DUELL_HAS_ENDED_VICTORIOUS);
        } else {
            GlobalLogger.log(LoggerStringValues.DUELL_HAS_ENDED_IN_DEFEAT);
        }
    }


    /**
     * Places card on Arena tile,
     * @param player who controlls the card
     * @param coordinate where to place new minion
     */
    public void placeCard (Player player, Coordinate coordinate, Card card)
    {
                //remove from hand
        //put in list of units who cant move
        //put in list of units who cant attack
    }




    public void attack (Player owner, Entity attacker, Entity attacked)
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
        //check if unit can move there
        if (isValidMove(mover, coordinate))
        {

        //remmove entity form list of units who can move
        //set posi of Unit to new coordinates

        }
    }

    public boolean isAllowedToMove (Entity mover)
    {
        for (Entity entity :
                firstDuellistEntitiesWhoCanMove) {
            if (entity == mover){
                GlobalLogger.log(LoggerStringValues.ENTITY_CAN_MOVE);
                return true;
            }
        }
        for (Entity entity :
                secondDuellistEntitiesWhoCanMove) {
            if (entity == mover){
                GlobalLogger.log(LoggerStringValues.ENTITY_CAN_MOVE);
                return true;
            }
        }
        GlobalLogger.log(LoggerStringValues.ENTITY_CANT_MOVE);
        return false;
    }

    public boolean isValidMove (Entity entity, Coordinate moveTo)
    {
        //When Entity can still move, when target coordinate is not occupied
        if (isAllowedToMove(entity) == true &&
                getArena().getArenaComponent(moveTo).isOccupied() == false)
        {
            return isDirectNeighbour(entity ,moveTo);
        }
        return false;
    }

    //if coordinate is neighbour of moving Entity
    private boolean isDirectNeighbour(Entity entity, Coordinate targetCoordinates)
    {
        for (Coordinate coordinate :
                CoordinateDirections.getStraightNeighbours(entity.getPosition())) {
            if (coordinate == targetCoordinates) {
                return true;
            }
        }
        return false;

    }

    private boolean isInRangeToAttack (Entity attacker, Entity attacked)
    {
        Coordinate coordinateOfAttacker = attacker.getPosition();
        for (Coordinate coordinate :
                CoordinateDirections.getStraightNeighbours(coordinateOfAttacker)) {
            if (coordinate == attacked.getPosition()){
                GlobalLogger.log(LoggerStringValues.ENTITY_HAS_RANGE_TO_ATTACK);
                return true;
            }
        }
        GlobalLogger.log(LoggerStringValues.ENTIT_DOES_NOT_HAVE_TARGET_IN_RANGE);
        return false;
    }

    /**
     * Battlephases, Start und end sind nicht teil des spielcycles.
     */
    private enum BattlePhase
    {
        START, //at threading maybe wait for connections, Decide Beginner
        FIRSTDUELLIST_DRAW, //where first duellist get to draw to handcardlimit
        FIRST_DUELLIST_PLACE_CARDS, //first duellist can place cards on arena
        SECOND_DUELLIST_DRAW, //where second duellist draws cards
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

    /** ---------------------------------------------------- GETTER UND SETTER----------------------------------------------------**/

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

    public ArrayList<Card> getFirstDuellistHand ()
    {
        return firstDuellistHand;
    }

    public void setFirstDuellistHand (ArrayList<Card> firstDuellistHand)
    {
        this.firstDuellistHand = firstDuellistHand;
    }

    public ArrayList<Card> getSecondDuellistHand ()
    {
        return secondDuellistHand;
    }

    public void setSecondDuellistHand (ArrayList<Card> secondDuellistHand)
    {
        this.secondDuellistHand = secondDuellistHand;
    }

    public ArrayList<Entity> getFirstDuellistEntitiesWhoCanAttack ()
    {
        return firstDuellistEntitiesWhoCanAttack;
    }

    public void setFirstDuellistEntitiesWhoCanAttack (
            ArrayList<Entity> firstDuellistEntitiesWhoCanAttack)
    {
        this.firstDuellistEntitiesWhoCanAttack = firstDuellistEntitiesWhoCanAttack;
    }

    public ArrayList<Entity> getSecondDuellistEntitiesWhoCanAttack ()
    {
        return secondDuellistEntitiesWhoCanAttack;
    }

    public void setSecondDuellistEntitiesWhoCanAttack (
            ArrayList<Entity> secondDuellistEntitiesWhoCanAttack)
    {
        this.secondDuellistEntitiesWhoCanAttack = secondDuellistEntitiesWhoCanAttack;
    }

    public ArrayList<Entity> getFirstDuellistEntitiesWhoCanMove ()
    {
        return firstDuellistEntitiesWhoCanMove;
    }

    public void setFirstDuellistEntitiesWhoCanMove (
            ArrayList<Entity> firstDuellistEntitiesWhoCanMove)
    {
        this.firstDuellistEntitiesWhoCanMove = firstDuellistEntitiesWhoCanMove;
    }

    public ArrayList<Entity> getSecondDuellistEntitiesWhoCanMove ()
    {
        return secondDuellistEntitiesWhoCanMove;
    }

    public void setSecondDuellistEntitiesWhoCanMove (
            ArrayList<Entity> secondDuellistEntitiesWhoCanMove)
    {
        this.secondDuellistEntitiesWhoCanMove = secondDuellistEntitiesWhoCanMove;
    }

    public Player getFirstduellist ()
    {
        return firstduellist;
    }

    public void setFirstduellist (Player firstduellist)
    {
        this.firstduellist = firstduellist;
    }

    public Player getSecondduellist ()
    {
        return secondduellist;
    }

    public void setSecondduellist (Player secondduellist)
    {
        this.secondduellist = secondduellist;
    }

    private void setCurrentActiveDuellist (Player player)
    {
        this.currentActiveDuellist = player;
    }

    private Player getCurrentActiveDuellist ()
    {
        if (this.currentActiveDuellist == this.firstduellist){
            return this.firstduellist;
        } else {
            return this.secondduellist;
        }
    }

    private ArrayList<Card> getCurrentHand ()
    {
        if (this.currentActiveDuellist == this.firstduellist){
            return this.getFirstDuellistHand();
        } else {
            return this.getSecondDuellistHand();
        }
    }

    private ArrayList<Entity> getCurrentEntitiesWhoCanAttack ()
    {
        if (this.currentActiveDuellist == this.firstduellist){
            return this.getFirstDuellistEntitiesWhoCanAttack();
        } else {
            return this.getSecondDuellistEntitiesWhoCanAttack();
        }
    }

    private ArrayList<Entity> getCurrentEntitiesWhoCanMove ()
    {
        if (this.currentActiveDuellist == this.firstduellist){
            return this.getFirstDuellistEntitiesWhoCanMove();
        } else {
            return this.getSecondDuellistEntitiesWhoCanMove();
        }
    }

    private void setPlayerAsFirstDuellist (boolean choice)
    {
        if (choice == true) {
            this.firstduellist = this.player;
            this.secondduellist = this.dm;
            GlobalLogger.log(LoggerStringValues.PLAYER_GOES_FIRST);
        } else {
            this.firstduellist = this.dm;
            this.secondduellist = this.player;
            GlobalLogger.log(LoggerStringValues.DM_GOES_FIRST);
        }
    }
}
