package de.prog2.dungeontop.control.manager;

import de.prog2.dungeontop.control.controller.EntityController;
import de.prog2.dungeontop.model.entities.Entity;
import de.prog2.dungeontop.model.entities.EntityClass;
import de.prog2.dungeontop.model.game.*;
import de.prog2.dungeontop.model.world.Coordinate;
import de.prog2.dungeontop.model.world.arena.Arena;
import de.prog2.dungeontop.resources.ExceptionMessagesKeys;
import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.utils.GlobalLogger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BattleManager
{
    private final Arena arena;
    private BattlePhase currentPhase = BattlePhase.START;
    private Duellist firstDuellist = null;
    private Duellist secondDuellist = null;

    /**
     *
     * @param firstPlayer Player or DM who will draw and play first. Decide before Battle
     * @param secondplayer Player or DM will go second
     * @param firstplyerDeck COPY playerDeck or the DMHerodeck, depending who is who.
     * @param secondplayerDeck COPY playerDeck or the DMHerodeck, depending who is who
     * @param arena The instance of the Arena
     */
    public BattleManager (Player firstPlayer, Player secondplayer, Deck firstplyerDeck, Deck secondplayerDeck, Arena arena)
    {
        this.firstDuellist = new Duellist(firstPlayer,firstplyerDeck,new Deck(),new ArrayList<EntityClass>());
        this.secondDuellist = new Duellist(secondplayer,secondplayerDeck,new Deck(),new ArrayList<EntityClass>());
        this.arena = arena;
    }


    /**
     * Legt die Reihenfolge des Battles fest
     * Fuer die Moeglichkeit bei Phases mehr Logik zu implementieren werden Phasen als Enum genommen, anstatt extern in properties zu stehen.
     * @return die naechste Phase in einem Battle die wiederholbar ist. Kein Start und kein End
     */
    public void startBattle()
    {

    }

    public BattlePhase getNextPhaseInCycle ()
    {
        switch (this.getCurrentPhase())
        {
            case START, SECOND_DUELLIST_SECOND_PLACE_CARDS ->
                    {
                        GlobalLogger.log(LoggerStringValues.GOT_NEXT_BATTLEPHASE);
                        return BattlePhase.FIRST_DUELLIST_DRAW;
                    }
            case FIRST_DUELLIST_DRAW ->
                    {
                        GlobalLogger.log(LoggerStringValues.GOT_NEXT_BATTLEPHASE);
                        return BattlePhase.FIRST_DUELLIST_PLACE_CARDS;
                    }
            case FIRST_DUELLIST_PLACE_CARDS ->
                    {
                        GlobalLogger.log(LoggerStringValues.GOT_NEXT_BATTLEPHASE);
                        return BattlePhase.SECOND_DUELLIST_DRAW;
                    }
            case SECOND_DUELLIST_DRAW ->
                    {
                        GlobalLogger.log(LoggerStringValues.GOT_NEXT_BATTLEPHASE);
                        return BattlePhase.SECOND_DUELLIST_PLACE_CARDS;
                    }
            case SECOND_DUELLIST_PLACE_CARDS ->
                    {
                        GlobalLogger.log(LoggerStringValues.GOT_NEXT_BATTLEPHASE);
                        return BattlePhase.FIRST_DUELLIST_MINION_ACT;
                    }
            case FIRST_DUELLIST_MINION_ACT ->
                    {
                        GlobalLogger.log(LoggerStringValues.GOT_NEXT_BATTLEPHASE);
                        return BattlePhase.SECOND_DUELLIST_MINION_ACT;
                    }
            case SECOND_DUELLIST_MINION_ACT ->
                    {
                        GlobalLogger.log(LoggerStringValues.GOT_NEXT_BATTLEPHASE);
                        return BattlePhase.FIRST_DUELLIST_SECOND_PLACE_CARDS;
                    }
            case FIRST_DUELLIST_SECOND_PLACE_CARDS ->
                    {
                        GlobalLogger.log(LoggerStringValues.GOT_NEXT_BATTLEPHASE);
                        return BattlePhase.SECOND_DUELLIST_SECOND_PLACE_CARDS;
                    }
            case END ->
                    {
                        GlobalLogger.warning(LoggerStringValues.TRY_TO_GET_PHASE_AFTER_END);
                        return BattlePhase.END;
                    }
            default ->
                    {
                        GlobalLogger.warning(ExceptionMessagesKeys.CAN_NOT_FIND_BATTLESTATE);
                        return null;
                    }
        }
    }

    //TODO Event handlen und aufrufen welches das outcome handled und Spieler Belohnt oder run beendet.
    public BattleOutCome endBattle (Player gewinner, int damageAnVerlierer)
    {
        GlobalLogger.log(LoggerStringValues.BATTLE_HAS_ENDED);
        setCurrentPhase(BattlePhase.END);
        return new BattleOutCome(gewinner, damageAnVerlierer);
    }

    /**
     * Places card on Arena tile,
     * @param duellist who controlls the card
     * @param coordinate where to place new minion
     */
    public void placeCard (Duellist duellist, Coordinate coordinate, Card card)
    {

        //remove from hand
        //put in list of units who cant move
        //put in list of units who cant attack
    }

    public void attack (Player owner, Entity attacker, Entity attacked)
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

    }

    public void moveUnit (Entity mover, MoveDirection direction)
    {
        EntityController.tryMoveTowards(this.arena, mover, direction);
    }

    private boolean isInRangeToAttack (Entity attacker, Entity attacked)
    {
        // TODO: EntityController @FynnK @Jesse
//        Coordinate coordinateOfAttacker = attacker.getPosition();
//        for (Coordinate coordinate : CoordinateDirections.getStraightNeighbours(coordinateOfAttacker))
//        {
//            if (coordinate == attacked.getPosition())
//            {
//                GlobalLogger.log(LoggerStringValues.ENTITY_HAS_RANGE_TO_ATTACK);
//                return true;
//            }
//        }
//        GlobalLogger.log(LoggerStringValues.ENTIT_DOES_NOT_HAVE_TARGET_IN_RANGE);
        return false;
    }

    /**
     * Battlephases, Start und end sind nicht teil des spielcycles.
     */
    private enum BattlePhase
    {
        START, //at threading maybe wait for connections, Decide Beginner
        FIRST_DUELLIST_DRAW, //where first duellist draws cards
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

    /*------------------------------Getter und Setter---------------------------------*/

    public BattlePhase getCurrentPhase ()
    {
        return currentPhase;
    }
    public void setCurrentPhase (BattlePhase currentPhase)
    {
        this.currentPhase = currentPhase;
    }
    /*--------------------Hilfsklassen und ControllerMethodik------------------------*/


    /**
     * Duellist wird ausschließlich in einem Battle benutzt um zu vermeiden durch Nebeneffekte die Felder der Spieler zu verändern
     * Fuer ein Model hat es zwar zuviel Logik aber eine extra Controllerklasse fuer eine Innere Klasse wollten wir nicht.
     */
    private class Duellist
    {

        private Player player = null;
        private Deck deck = null;
        private Deck discardPile = new Deck();
        private List<EntityClass> activePerks = new ArrayList<>();
        private List<Card> hand = new ArrayList<>();
        private int handLimit = 0;
        private int currentEgoPoints = 0;
        private int egoPointsMax = 0;

        public Duellist (Player player, Deck deck, Deck discardPile,
                         List<EntityClass> activePerks)
        {
            this.player = player;
            this.deck = deck;
            this.discardPile = discardPile;
            this.activePerks = activePerks;
            this.handLimit = player.getHandCardLimit();
            this.currentEgoPoints = player.getEgo_points();
            this.egoPointsMax = player.getEgo_points();
        }

        /**
         * will shuffle deck if there are not enough cards to draw
         * TODO make shure that deck always has at least X amount of cards.
         */
        private void drawNewDuellistHand()
        {
            if (getDeck().getCards().size() < handLimit)
            {
                reStackDeckFromDiscard();
                Collections.shuffle(getDeck().getCards());
            }
            getHand().clear();
            Collections.shuffle(getDeck().getCards());
            for (int i = 0; i < handLimit; i++) {
                Card drawedCard = deck.popCard();
                hand.add(drawedCard);
                discardPile.pushCard(drawedCard);
                GlobalLogger.log(LoggerStringValues.DREW_NEW_HAND);
            }
        }

        private void removeCardFromHand(Card card)
        {
            getHand().remove(card);
            GlobalLogger.log(LoggerStringValues.CARD_REMOVED_FROM_HAND);
        }

        private void tryReduceEgoPoints()
        {
            if (currentEgoPoints > 0){
                GlobalLogger.log(LoggerStringValues.REDUCED_EGOPOINTS);
                currentEgoPoints--;
                return;
            }
            GlobalLogger.warning(LoggerStringValues.NO_EGO_TO_REDUCE);
        }

        private void resetEgoPoints()
        {
            this.currentEgoPoints = this.egoPointsMax;
        }

        /**
         * this should only be done if there are not enough cards in the Deck to draw a new hand
         */
        private void reStackDeckFromDiscard()
        {
            for (Card card : getDiscardPile().getCards()) {
                getDeck().getCards().push(card);
            }
            Collections.shuffle(getDeck().getCards());
        }


        public List<Card> getHand ()
        {
            return hand;
        }

        public void setHand (List<Card> hand)
        {
            this.hand = hand;
        }

        public Player getPlayer ()
        {
            return player;
        }

        public void setPlayer (Player player)
        {
            this.player = player;
        }

        public Deck getDeck ()
        {
            return deck;
        }

        public void setDeck (Deck deck)
        {
            this.deck = deck;
        }

        public Deck getDiscardPile ()
        {
            return discardPile;
        }

        public void setDiscardPile (Deck discardPile)
        {
            this.discardPile = discardPile;
        }

        public List<EntityClass> getActivePerks ()
        {
            return activePerks;
        }

        public void setActivePerks (List<EntityClass> activePerks)
        {
            this.activePerks = activePerks;
        }
    }
}
