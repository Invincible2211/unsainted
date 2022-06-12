package de.prog2.dungeontop.control.manager;

import de.prog2.dungeontop.control.controller.*;
import de.prog2.dungeontop.model.entities.Entity;
import de.prog2.dungeontop.model.game.*;
import de.prog2.dungeontop.model.world.Coordinate;
import de.prog2.dungeontop.model.world.arena.Arena;
import de.prog2.dungeontop.resources.ExceptionMessagesKeys;
import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.utils.GlobalLogger;
import de.prog2.dungeontop.view.ArenaBaseView;

import java.util.ArrayList;
import java.util.List;

public class BattleManager
{
    private Arena arena;
    private BattlePhase currentPhase = BattlePhase.START;
    private Duellist firstDuellist = null;
    private Duellist secondDuellist = null;

    //BattleManager ist ein Singleton.
    private final static BattleManager instance = new BattleManager();

    public static BattleManager getInstance ()
    {
        GlobalLogger.log(LoggerStringValues.BATTLEMANAGER_GET);
        return instance;
    }
    // BattleManager ist ein Singleton
    private BattleManager ()
    {
    }

    /**
     * @param firstPlayer Player or DM who will draw and play first. Decide before Battle
     * @param secondplayer Player or DM will go second
     * @param firstplyerDeck COPY playerDeck or the DMHerodeck, depending who is who.
     * @param secondplayerDeck COPY playerDeck or the DMHerodeck, depending who is who
     * @param arena The instance of the Arena
     * @param arenaBaseView the View that it will draw on
     */
    public void startBattle(Player firstPlayer, Player secondplayer, Deck firstplyerDeck, Deck secondplayerDeck, Arena arena, ArenaBaseView arenaBaseView)
    {
        this.firstDuellist = new Duellist(firstPlayer,firstplyerDeck);
        this.secondDuellist = new Duellist(secondplayer,secondplayerDeck);
        this.arena = arena;
        statinitialiser(arenaBaseView);
    }

    /**
     * @param arenaBaseView the View that it will draw on
     */
    private void statinitialiser(ArenaBaseView arenaBaseView)
    {
        ArenaBaseController.init(arenaBaseView);
        this.getFirstDuellist().drawNewDuellistHand();
        this.getSecondDuellist().drawNewDuellistHand();
        ArenaBaseController.updatePlayerHands(arenaBaseView,
                this.getFirstDuellist().getHand(),
                this.getSecondDuellist().getHand());
        this.currentPhase = BattlePhase.START;
        ArenaBaseController.updateEgoPoints(arenaBaseView,
                this.getFirstDuellist().currentEgoPoints,
                this.getSecondDuellist().getCurrentEgoPoints());
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
    private BattleOutCome endBattle (Player gewinner, int damageAnVerlierer)
    {
        GlobalLogger.log(LoggerStringValues.BATTLE_HAS_ENDED);
        setCurrentPhase(BattlePhase.END);
        return new BattleOutCome(gewinner, damageAnVerlierer);
    }

    public void endAPhase ()
    {
        setCurrentPhase(getNextPhaseInCycle());
        GlobalLogger.log(LoggerStringValues.CURRENTPHASE_IS_NOW + getCurrentPhase());
    }

    /**
     * Places card on Arena tile,
     * @param duellist who controlls the card
     * @param coordinate where to place new minion
     */
    public void placeEntity (Duellist duellist, Coordinate coordinate, EntityCard entityCard)
    {
        if (entityCard.getPrice() <= duellist.getCurrentEgoPoints())
        {
            EntityCardController.tryInstantiate(entityCard, arena, coordinate);
            //Reduce egopoints by one for each cost of card
            for (int cost = 0; cost < entityCard.getPrice(); cost++)
            {
                duellist.tryReduceEgoPoints();
            }
            duellist.removeCardFromHand(entityCard);
            GlobalLogger.log(LoggerStringValues.PLACED_CARD_IN_ARENA);
        } else {
            GlobalLogger.warning(LoggerStringValues.NOT_ENOUGH_EGOPOINTS);
        }
    }

    private void attack (Entity attacker, Entity attacked)
    {
        this.arena = EntityController.attack(attacker, attacked.getPosition(), this.arena);
    }


    private void moveUnit (Entity mover, MoveDirection direction)
    {
        EntityController.tryMoveTowards(this.arena, mover, direction);
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

    public Arena getArena ()
    {
        return arena;
    }

    public Duellist getFirstDuellist ()
    {
        return firstDuellist;
    }

    public Duellist getSecondDuellist ()
    {
        return secondDuellist;
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

        private List<Card> hand = new ArrayList<>();
        private int handLimit = 0;
        private int currentEgoPoints = 0;
        private int egoPointsMax = 0;

        public Duellist (Player player, Deck deck)
        {
            this.player = player;
            this.deck = deck;
            this.discardPile = new Deck();

            this.handLimit = player.getHandCardLimit();
            this.currentEgoPoints = player.getEgo_points();
            this.egoPointsMax = player.getEgo_points();
            this.drawNewDuellistHand();

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
                DeckController.shuffleDeck(getDeck());
            }
            getHand().clear();
            DeckController.shuffleDeck(getDeck());
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

        //technically not a try as it is not a boolean
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
            DeckController.shuffleDeck(getDeck());
        }

        public int getCurrentEgoPoints ()
        {
            return currentEgoPoints;
        }

        public void setCurrentEgoPoints (int currentEgoPoints)
        {
            this.currentEgoPoints = currentEgoPoints;
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

    }
}
