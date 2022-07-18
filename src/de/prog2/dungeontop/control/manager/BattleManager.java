package de.prog2.dungeontop.control.manager;

import com.rits.cloning.Cloner;
import de.prog2.dungeontop.DungeonTop;
import de.prog2.dungeontop.control.controller.*;
import de.prog2.dungeontop.model.entities.Entity;
import de.prog2.dungeontop.model.game.*;
import de.prog2.dungeontop.model.world.Coordinate;
import de.prog2.dungeontop.model.world.arena.Arena;
import de.prog2.dungeontop.resources.ExceptionMessagesKeys;
import de.prog2.dungeontop.resources.GameConstants;
import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.utils.GlobalLogger;
import de.prog2.dungeontop.view.ArenaBaseView;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

@Deprecated
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
        instantiateHeroes();
        updateValues();
        Platform.runLater(() -> DungeonTop.getStage().setScene(new Scene(arenaBaseView.getBackGroundAnchorPane())));
    }

    /**
     * will instantiate startvalues before the first turn took place
     */
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
                this.getFirstDuellist().getCurrentEgoPoints(),
                this.getSecondDuellist().getCurrentEgoPoints());
    }

    /**
     * it sets the order of battle phases.
     * Currently there are too many phases as the planes for the game were big.
     * @return the next battlephase
     */
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
     * zu spielbeginn werden die Helden der Spieler in die Ecken gepackt.
     */
    private void instantiateHeroes()
    {
        Coordinate cornerBottemRight = new Coordinate(getArena().getWidth()-1, getArena().getHeight()-1);
        Coordinate cornerTopLeft = new Coordinate(0, 0);
        Cloner cloner = new Cloner();
        Entity hero = cloner.deepClone(getFirstDuellist().getPlayer().getHero());
        Entity hero2 = cloner.deepClone(getSecondDuellist().getPlayer().getHero());
        arena.insertEntity(cornerBottemRight, hero);
        arena.insertEntity(cornerTopLeft, hero2);
    }

    /**
     * @param gewinner
     * @param hpGewinnerLeftOver
     * @return
     */
    public BattleOutCome endBattle (Player gewinner, int hpGewinnerLeftOver)
    {
        GlobalLogger.log(LoggerStringValues.BATTLE_HAS_ENDED);
        setCurrentPhase(BattlePhase.END);
        return new BattleOutCome(gewinner, hpGewinnerLeftOver);
    }

    /**
     * will refresh values that get reset on a new round
     * updates the view with fresh values
     */
    public void endAPhase ()
    {
        //IF new round both players get more egopointsmax and current to max
        if (getCurrentPhase() == BattlePhase.SECOND_DUELLIST_SECOND_PLACE_CARDS) {
            newRound();
        }
        setCurrentPhase(getNextPhaseInCycle());
        GlobalLogger.log(LoggerStringValues.CURRENTPHASE_IS_NOW + getCurrentPhase());

        deselectHandCards();
        updateValues();
    }

    /**
     * helpmethode to deselct all cards
     */
    private void deselectHandCards ()
    {
        if(firstDuellist.hasSelectedCard()) {
            firstDuellist.getSelectedCard().setSelected(false);
        }
        if(secondDuellist.hasSelectedCard()) {
            secondDuellist.getSelectedCard().setSelected(false);
        }
    }

    /**
     * gives the current ArenaBaseController updated values
     */
    private void updateValues()
    {
        ArenaBaseController.updateBattlefield(this.getArena());
        ArenaBaseController.updatePhaseDisplay();
        ArenaBaseController.updateEgoPoints(
                this.getFirstDuellist().getCurrentEgoPoints(),
                this.getSecondDuellist().getCurrentEgoPoints());
        ArenaBaseController.updatePlayerHands(getFirstDuellist().getHand(), getSecondDuellist().getHand());
    }

    /**
     * increases the current egopoints of both players by EGOPOINTS_PER_ROUND_INCEREMENT
     * and sets the current egopoints to max egopoints
     * Calls resetEntityMovement
     */
    private void newRound()
    {
        GlobalLogger.log(LoggerStringValues.NEXT_ROUND_BOTH_PLAYERS_GET_EGOPOINTS);
        this.getFirstDuellist().egoPointsMax += GameConstants.EGOPOINTS_PER_ROUND_INCREMENT;
        this.getSecondDuellist().egoPointsMax += GameConstants.EGOPOINTS_PER_ROUND_INCREMENT;
        this.getFirstDuellist().setCurrentEgoPoints(this.getFirstDuellist().egoPointsMax);
        this.getSecondDuellist().setCurrentEgoPoints(this.getSecondDuellist().egoPointsMax);
        ArenaBaseController.updateEgoPoints(
                this.getFirstDuellist().getCurrentEgoPoints(),
                this.getSecondDuellist().getCurrentEgoPoints());
        this.getFirstDuellist().drawNewDuellistHand();
        this.getSecondDuellist().drawNewDuellistHand();
        resetEntityMovement();
    }

    /**
     * HelpMethod to reset the movement of all entities at a new round
     */
    private void resetEntityMovement ()
    {

        arena.getArenaHashmap().forEach((key, value) -> {
            value.setMovement(value.getMaxMovement());
        });

    }

    /**
     * Places card on Arena tile, then updates it on the View.
     * only part of the summoning process as it is modulised for the first and second duellist.
     * @param duellist   who controlls the card
     * @param coordinate where to place new minion
     * @param entityCard the card to place
     * @return true if successful, false if not
     */
    public boolean tryPlaceEntity(Duellist duellist, Coordinate coordinate, EntityCard entityCard)
    {
        // Check if the duellist has enough EgoPoints to place the card.
        if (entityCard.getSummonCost() > duellist.getCurrentEgoPoints())
        {
            GlobalLogger.warning(LoggerStringValues.NOT_ENOUGH_EGOPOINTS);

            return false;
        }
        // If the tile is empty, place the card on it.
        if (summonCardHelp(duellist, coordinate, entityCard)) return false;
        deselectHandCards();
        GlobalLogger.log(LoggerStringValues.PLACED_CARD_IN_ARENA);
        updateValues();
        return true;
    }

    /**
     * Hilfsmethode beim Ablauf vom spielen einer Karte.
     * @param duellist
     * @param coordinate
     * @param entityCard
     * @return
     */
    private boolean summonCardHelp (Duellist duellist, Coordinate coordinate, EntityCard entityCard)
    {
        //IMPORTANT: tryInstantiate will summon the Minions as sideeffect if it doesnt give false back.
        if (!EntityCardController.tryInstantiate(entityCard, arena, coordinate))
        {
            GlobalLogger.warning(LoggerStringValues.ALREADY_OCCUPIED);
            deselectHandCards();
            return false;
        }

        duellist.reduceEgoPoints(entityCard.getSummonCost());
        duellist.removeCardFromHand(entityCard);
        updateValues();
        return true;
    }

    /**
     * HelpMethod
     * makes the Entity Attack the selexted other
     * @param coordinateOfAttackedEntity
     */
    private void attack (Coordinate coordinateOfAttackedEntity)
    {
        this.arena = EntityController.attack(this.getArena().getSelectedEntity(), coordinateOfAttackedEntity, this.arena);
        this.getArena().setSelectedEntity(null);
        ArenaBaseController.updateBattlefield(this.getArena());
    }

    /**
     * HelpMethod zur Uebersichtlichkeit beim bewegen einer Entity
     * @param direction
     */
    private void moveUnit (MoveDirection direction)
    {
        EntityController.tryMoveTowards(this.arena, this.arena.getSelectedEntity(), direction);
        this.getArena().setSelectedEntity(null);
        ArenaBaseController.updateBattlefield(this.getArena());
    }


    /**
     * Hauptmethode fuer die Funktion in der Arena
     * @param coordinate the clicked tile in the arena.
     *                   Behavior is dependent on static variables like phase and selectedEntity.
     */
    public void arenaTilePressed (Coordinate coordinate)
    {
        //if current player has selected a handcard
        if (getCurrentActivePlayer() == getFirstDuellist() && getFirstDuellist().hasSelectedCard())
        {
            tryPlaceHandCard(coordinate, getFirstDuellist());
            return;
        }
        if (getCurrentActivePlayer() == getSecondDuellist() && getSecondDuellist().hasSelectedCard())
        {
            tryPlaceHandCard(coordinate, getSecondDuellist());
            return;
        }
        //if arena has a selected unit
        if (!this.getArena().hasSelectedUnit())
        {
            trySelectEntity(coordinate);
            return;
        }
        //if arena has a selected unit
        if (this.getArena().hasSelectedUnit())
        {
            entityAction(coordinate);
            return;
        }

    }

    /**
     * HelpMethod zur Uebersicht, entscheidet ob ein angriff oder eine Bewegung ausgefuehrt werden soll.
     * @param coordinate clicked tile in the arena.
     */
    private void entityAction (Coordinate coordinate)
    {
        //if the tile is not empty, attack the unit
        if (this.getArena().getEntity(coordinate) != null)
        {
            attack(coordinate);
            return;
        }

        //if the tile is empty, move the unit
        if (this.getArena().getEntity(coordinate) == null)
        {
        moveAction(coordinate);
            return;
        }
    }

    /**
     * HelpMethod zu Uebesicht, Uebernimmt die entscheidung ob eine Entity selected wird.
     * @param coordinate clicked tile in the arena.
     */
    private void trySelectEntity (Coordinate coordinate)
    {
        if (this.getArena().getEntity(coordinate) != null)
        {
            this.getArena().selectUnit(this.arena.getEntity(coordinate));
            GlobalLogger.log(LoggerStringValues.BATTLEMANAGER_SELECT_A_UNIT);

        } else {
            GlobalLogger.warning(LoggerStringValues.NO_UNIT_ON_TILE);
        }
    }

    private void tryPlaceHandCard (Coordinate coordinate, Duellist duellist)
    {
        if (duellist.hasSelectedCard())
        {
            if (duellist.getSelectedCard() instanceof EntityCard)
            {
                tryPlaceEntity(duellist, coordinate, (EntityCard) duellist.getSelectedCard());
            }
        }
    }

    /**
     * translates coordinates to a direction and moves the unit
     * @param coordinate the coordinate to move to
     */
    private void moveAction (Coordinate coordinate)
    {
        if (this.getArena().getSelectedEntity().getPosition().getX() == coordinate.getX())
        {
            //if click on itself, deselect unit
            if (this.getArena().getSelectedEntity().getPosition().getY() == coordinate.getY())
            {
                this.getArena().setSelectedEntity(null);
            }

            //X ist gleich Y ist unterschiedlich
            if (this.getArena().getSelectedEntity().getPosition().getY() < coordinate.getY())
            {
                moveUnit(MoveDirection.DOWN);
            } else {
                moveUnit(MoveDirection.UP);
            }
        } else {
            //X ist unterschiedlich Y ist gleich
            if (this.getArena().getSelectedEntity().getPosition().getX() < coordinate.getX())
            {
                moveUnit(MoveDirection.RIGHT);
            } else {
                moveUnit(MoveDirection.LEFT);
            }
        }
    }

    @Deprecated
    public void arenaTileReleased (Coordinate coordinate)
    {
        //Logik und ueberpruefung ob darf und was passiert
    }

    /**
     * @version 1.0 the only place where a player might get restricted from actions.
     * @return the currently active player.
     */
    public Duellist getCurrentActivePlayer ()
    {
        if (getCurrentPhase() == BattlePhase.FIRST_DUELLIST_DRAW ||
                getCurrentPhase() == BattlePhase.FIRST_DUELLIST_PLACE_CARDS ||
                getCurrentPhase() == BattlePhase.FIRST_DUELLIST_MINION_ACT ||
                getCurrentPhase() == BattlePhase.FIRST_DUELLIST_SECOND_PLACE_CARDS) {
            GlobalLogger.log(LoggerStringValues.CURRENT_ACTIVE_PLAYER_ONE);
            return this.getFirstDuellist();

        } else if (getCurrentPhase() == BattlePhase.SECOND_DUELLIST_DRAW ||
                getCurrentPhase() == BattlePhase.SECOND_DUELLIST_PLACE_CARDS ||
                getCurrentPhase() == BattlePhase.SECOND_DUELLIST_MINION_ACT ||
                getCurrentPhase() == BattlePhase.SECOND_DUELLIST_SECOND_PLACE_CARDS) {
            GlobalLogger.log(LoggerStringValues.CURRENT_ACTIVE_PLAYER_TWO);
            return this.getSecondDuellist();
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

    public String getCurrentPhaseAsString()
    {
        return currentPhase.toString();
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
            this.currentEgoPoints = GameConstants.START_EGOPOINTS;
            this.egoPointsMax = player.getMax_ego_points();
            this.drawNewDuellistHand();
        }

        /**
         * will shuffle deck if there are not enough cards to draw
         */
        protected void drawNewDuellistHand ()
        {
            if (getDeck().getCards().size() < this.handLimit) {
                reStackDeckFromDiscard();
                DeckController.shuffleDeck(getDeck());
            }
            discardHand();
            DeckController.shuffleDeck(getDeck());
            for (int i = 0; i < this.handLimit; i++) {
                Card drawenCard = deck.popCard();
                this.getHand().add(drawenCard);
                GlobalLogger.log(LoggerStringValues.DREW_NEW_CARD + i + "out of" + handLimit);
            }
        }

        private void discardHand()
        {
            for (int i = 0; i < hand.size(); i++) {
                removeCardFromHand(hand.get(i));
            }
        }

        private void removeCardFromHand (Card card)
        {
            getDiscardPile().pushCard(card);
            getHand().remove(card);
            GlobalLogger.log(LoggerStringValues.CARD_REMOVED_FROM_HAND);
        }

        protected void reduceEgoPoints (int amount)
        {
            if (getCurrentEgoPoints() - amount >= 0) {
                setCurrentEgoPoints(getCurrentEgoPoints() - amount);
            GlobalLogger.log(LoggerStringValues.REDUCED_EGOPOINTS);
            } else {
                setCurrentEgoPoints(0);
                GlobalLogger.log(LoggerStringValues.NOT_ENOUGH_EGOPOINTS);
            }
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

        public boolean hasSelectedCard ()
        {
            for (Card card : this.getHand()) {
                if (card.isSelected()) {
                    return true;
                }
            }
            return false;
        }

        public Card getSelectedCard ()
        {
            for (Card card : this.getHand()) {
                if (card.isSelected()) {
                    return card;
                }
            }
            return null;
        }

        public int getCurrentEgoPoints ()
        {
            return currentEgoPoints;
        }

        public void setCurrentEgoPoints (int amount)
        {
            this.currentEgoPoints = amount;
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
