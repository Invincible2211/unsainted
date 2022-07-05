package de.prog2.dungeontop.control.manager;

import de.prog2.dungeontop.control.controller.*;
import de.prog2.dungeontop.model.game.*;
import de.prog2.dungeontop.model.world.Coordinate;
import de.prog2.dungeontop.model.world.arena.Arena;
import de.prog2.dungeontop.resources.ExceptionMessagesKeys;
import de.prog2.dungeontop.resources.GameConstants;
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
    private ArenaBaseView myArenaBaseView = null;

    //BattleManager ist ein Singleton.
    private final static BattleManager instance = new BattleManager();

    public static BattleManager getInstance ()
    {
        return instance;
    }

    // BattleManager ist ein Singleton
    private BattleManager ()
    {
    }

    /**
     * @param firstPlayer      Player or DM who will draw and play first. Decide before Battle
     * @param secondplayer     Player or DM will go second
     * @param firstplyerDeck   COPY playerDeck or the DMHerodeck, depending who is who.
     * @param secondplayerDeck COPY playerDeck or the DMHerodeck, depending who is who
     * @param arena            The instance of the Arena
     * @param arenaBaseView    the View that it will draw on
     */
    public void startBattle (Player firstPlayer, Player secondplayer, Deck firstplyerDeck, Deck secondplayerDeck,
                             Arena arena, ArenaBaseView arenaBaseView)
    {
        this.firstDuellist = new Duellist(firstPlayer, firstplyerDeck);
        this.secondDuellist = new Duellist(secondplayer, secondplayerDeck);
        this.arena = arena;
        this.myArenaBaseView = arenaBaseView;
        statinitialiser();
    }

    private void statinitialiser ()
    {
        ArenaBaseController.init(this.myArenaBaseView);
        this.getFirstDuellist().drawNewDuellistHand();
        this.getSecondDuellist().drawNewDuellistHand();
        ArenaBaseController.updatePlayerHands(
                this.getFirstDuellist().getHand(),
                this.getSecondDuellist().getHand());
        this.currentPhase = BattlePhase.START;
        ArenaBaseController.updateEgoPoints(
                this.getFirstDuellist().currentEgoPoints,
                this.getSecondDuellist().getCurrentEgoPoints());
    }

    public BattlePhase getNextPhaseInCycle ()
    {
        switch (this.getCurrentPhase()) {
            case START, SECOND_DUELLIST_SECOND_PLACE_CARDS -> {
                GlobalLogger.log(LoggerStringValues.GOT_NEXT_BATTLEPHASE);
                return BattlePhase.FIRST_DUELLIST_DRAW;
            }
            case FIRST_DUELLIST_DRAW -> {
                GlobalLogger.log(LoggerStringValues.GOT_NEXT_BATTLEPHASE);
                return BattlePhase.FIRST_DUELLIST_PLACE_CARDS;
            }
            case FIRST_DUELLIST_PLACE_CARDS -> {
                GlobalLogger.log(LoggerStringValues.GOT_NEXT_BATTLEPHASE);
                return BattlePhase.SECOND_DUELLIST_DRAW;
            }
            case SECOND_DUELLIST_DRAW -> {
                GlobalLogger.log(LoggerStringValues.GOT_NEXT_BATTLEPHASE);
                return BattlePhase.SECOND_DUELLIST_PLACE_CARDS;
            }
            case SECOND_DUELLIST_PLACE_CARDS -> {
                GlobalLogger.log(LoggerStringValues.GOT_NEXT_BATTLEPHASE);
                return BattlePhase.FIRST_DUELLIST_MINION_ACT;
            }
            case FIRST_DUELLIST_MINION_ACT -> {
                GlobalLogger.log(LoggerStringValues.GOT_NEXT_BATTLEPHASE);
                return BattlePhase.SECOND_DUELLIST_MINION_ACT;
            }
            case SECOND_DUELLIST_MINION_ACT -> {
                GlobalLogger.log(LoggerStringValues.GOT_NEXT_BATTLEPHASE);
                return BattlePhase.FIRST_DUELLIST_SECOND_PLACE_CARDS;
            }
            case FIRST_DUELLIST_SECOND_PLACE_CARDS -> {
                GlobalLogger.log(LoggerStringValues.GOT_NEXT_BATTLEPHASE);
                return BattlePhase.SECOND_DUELLIST_SECOND_PLACE_CARDS;
            }
            case END -> {
                GlobalLogger.warning(LoggerStringValues.TRY_TO_GET_PHASE_AFTER_END);
                return BattlePhase.END;
            }
            default -> {
                GlobalLogger.warning(ExceptionMessagesKeys.CAN_NOT_FIND_BATTLESTATE);
                return null;
            }
        }
    }

    /**
     * @param gewinner
     * @param damageAnVerlierer
     * @return
     */
    private BattleOutCome endBattle (Player gewinner, int damageAnVerlierer)
    {
        GlobalLogger.log(LoggerStringValues.BATTLE_HAS_ENDED);
        setCurrentPhase(BattlePhase.END);
        return new BattleOutCome(gewinner, damageAnVerlierer);
    }

    public void endAPhase ()
    {
        //IF new reound both players get more egopointsmax and current to max
        if (getCurrentPhase() == BattlePhase.SECOND_DUELLIST_SECOND_PLACE_CARDS) {
            GlobalLogger.log(LoggerStringValues.NEXT_ROUND_BOTH_PLAYERS_GET_EGOPOINTS);
            this.getFirstDuellist().egoPointsMax += GameConstants.EGOPOINTS_PER_ROUND_INCREMENT;
            this.getSecondDuellist().egoPointsMax += GameConstants.EGOPOINTS_PER_ROUND_INCREMENT;
            this.getFirstDuellist().currentEgoPoints = this.getFirstDuellist().egoPointsMax;
            this.getSecondDuellist().currentEgoPoints = this.getSecondDuellist().egoPointsMax;
        }
        //for tests
        ArenaBaseController.updateBattlefield(this.getArena());
        //set next phase
        setCurrentPhase(getNextPhaseInCycle());
        //TODO check if match is over
        GlobalLogger.log(LoggerStringValues.CURRENTPHASE_IS_NOW + getCurrentPhase());
    }

    /**
     * Places card on Arena tile, then updates it on the View.
     *
     * @param duellist   who controlls the card
     * @param coordinate where to place new minion
     * @param entityCard the card to place
     * @return true if successful, false if not
     */
    public boolean tryPlaceEntity(Duellist duellist, Coordinate coordinate, EntityCard entityCard)
    {
        // Check if the duellist has enough EgoPoints to place the card.
        if (entityCard.getPrice() > duellist.getCurrentEgoPoints())
        {
            GlobalLogger.warning(LoggerStringValues.NOT_ENOUGH_EGOPOINTS);
            return false;
        }
        // If the tile is empty, place the card on it.
        if (!EntityCardController.tryInstantiate(entityCard, arena, coordinate))
        {
            GlobalLogger.warning(LoggerStringValues.ALREADY_OCCUPIED);
            return false;
        }
        // Update duellist and arena.
        duellist.reduceEgoPoints(entityCard.getPrice());
        duellist.removeCardFromHand(entityCard);
        ArenaBaseController.updateBattlefield(arena);
        ArenaBaseController.updateEgoPoints(firstDuellist.currentEgoPoints, secondDuellist.currentEgoPoints);
        GlobalLogger.log(LoggerStringValues.PLACED_CARD_IN_ARENA);
        return true;
    }

    private void attack (Coordinate coordinateOfAttackedEntity)
    {
        this.arena = EntityController.attack(this.getArena().getSelectedEntity(), coordinateOfAttackedEntity, this.arena);
        ArenaBaseController.updateBattlefield(this.getArena());
    }


    private void moveUnit (MoveDirection direction)
    {
        EntityController.tryMoveTowards(this.arena, this.arena.getSelectedEntity(), direction);
        ArenaBaseController.updateBattlefield(this.getArena());

    }

    public void testPlaceCard()
    {
        this.getFirstDuellist().setCurrentEgoPoints(10);
        ArenaBaseController.updateEgoPoints(this.getFirstDuellist().currentEgoPoints, this.getSecondDuellist().currentEgoPoints);
        this.tryPlaceEntity(this.getFirstDuellist(), new Coordinate(0, 0), (EntityCard) this.getFirstDuellist().getHand().get(0));
        ArenaBaseController.updateBattlefield(this.getArena());
    }

    public void arenaTilePressed (Coordinate coordinate)
    {
        //if no entity has been selected yet, try to select one
        if (!this.getArena().hasSelectedUnit())
        {
            //select a unit if one is on the tile
            if (this.getArena().getEntity(coordinate) != null)
            {
                this.getArena().selectUnit(this.arena.getEntity(coordinate));
                GlobalLogger.log(LoggerStringValues.BATTLEMANAGER_SELECT_A_UNIT);

            } else {
                GlobalLogger.warning(LoggerStringValues.NO_UNIT_ON_TILE);
            }
        }
        //if a unit has been selected, either move or attack
        else
        {
            //if the tile is not empty, attack the unit
            if (this.getArena().getEntity(coordinate) != null)
            {
                this.attack(coordinate);
                return;
            }
            //if the tile is empty, move the unit

            //this is where bugs will happen
            if (this.getArena().getSelectedEntity().getPosition().getX() == coordinate.getX())
            {
                if (this.getArena().getSelectedEntity().getPosition().getY() < coordinate.getY())
                {
                    moveUnit(MoveDirection.DOWN);
                } else {
                    moveUnit(MoveDirection.UP);
                }
            } else {
                if (this.getArena().getSelectedEntity().getPosition().getX() < coordinate.getX())
                {
                    moveUnit(MoveDirection.RIGHT);
                } else {
                    moveUnit(MoveDirection.LEFT);
                }
            }

        }
    }


    public void arenaTileReleased (Coordinate coordinate)
    {
        //Logik und ueberpruefung ob darf und was passiert
    }

    public Player getCurrentActivePlayer ()
    {
        if (getCurrentPhase() == BattlePhase.FIRST_DUELLIST_DRAW ||
                getCurrentPhase() == BattlePhase.FIRST_DUELLIST_PLACE_CARDS ||
                getCurrentPhase() == BattlePhase.FIRST_DUELLIST_MINION_ACT ||
                getCurrentPhase() == BattlePhase.FIRST_DUELLIST_SECOND_PLACE_CARDS) {
            GlobalLogger.log(LoggerStringValues.CURRENT_ACTIVE_PLAYER_ONE);
            return this.getFirstDuellist().getPlayer();

        } else if (getCurrentPhase() == BattlePhase.SECOND_DUELLIST_DRAW ||
                getCurrentPhase() == BattlePhase.SECOND_DUELLIST_PLACE_CARDS ||
                getCurrentPhase() == BattlePhase.SECOND_DUELLIST_MINION_ACT ||
                getCurrentPhase() == BattlePhase.SECOND_DUELLIST_SECOND_PLACE_CARDS) {
            GlobalLogger.log(LoggerStringValues.CURRENT_ACTIVE_PLAYER_TWO);
            return this.getSecondDuellist().getPlayer();
        }
        return null;
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
        private void drawNewDuellistHand ()
        {
            if (getDeck().getCards().size() < handLimit) {
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

        private void removeCardFromHand (Card card)
        {
            getHand().remove(card);
            GlobalLogger.log(LoggerStringValues.CARD_REMOVED_FROM_HAND);
        }

        protected void reduceEgoPoints (int amount)
        {
            this.currentEgoPoints -= amount;
            GlobalLogger.log(LoggerStringValues.REDUCED_EGOPOINTS);
        }
        //technically not a try as it is not a boolean
        private void tryReduceEgoPoints ()
        {
            if (currentEgoPoints > 0) {
                GlobalLogger.log(LoggerStringValues.REDUCED_EGOPOINTS);
                currentEgoPoints--;
                return;
            }
            GlobalLogger.warning(LoggerStringValues.NO_EGO_TO_REDUCE);
        }

        private void resetEgoPoints ()
        {
            this.currentEgoPoints = this.egoPointsMax;
        }

        /**
         * this should only be done if there are not enough cards in the Deck to draw a new hand
         */
        private void reStackDeckFromDiscard ()
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
