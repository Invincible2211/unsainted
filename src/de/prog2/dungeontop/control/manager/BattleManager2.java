package de.prog2.dungeontop.control.manager;

import de.prog2.dungeontop.DungeonTop;
import de.prog2.dungeontop.control.controller.ArenaBaseController;
import de.prog2.dungeontop.control.controller.DeckController;
import de.prog2.dungeontop.control.controller.EntityController;
import de.prog2.dungeontop.control.network.NetManager;
import de.prog2.dungeontop.model.entities.Entity;
import de.prog2.dungeontop.model.entities.Minion;
import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.model.game.EntityCard;
import de.prog2.dungeontop.model.game.Player;
import de.prog2.dungeontop.model.world.Coordinate;
import de.prog2.dungeontop.model.world.arena.Arena;
import de.prog2.dungeontop.model.world.rooms.ArenaRoom;
import de.prog2.dungeontop.resources.GameConstants;
import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.resources.TestConstants;
import de.prog2.dungeontop.utils.GlobalLogger;
import de.prog2.dungeontop.view.ArenaController;
import de.prog2.dungeontop.view.HellView;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.*;

public class BattleManager2 {

    private Scene scene;

    private Player player1;
    private Player player2;
    private ArenaController arenaController;

    private boolean isStarting;

    private UUID musicId;

    private final static BattleManager2 instance = new BattleManager2();

    private BattlePhase battlePhase;

    private final HashMap<Coordinate, EntityCard> entityCoordinateHashMap = new HashMap<>();

    private BattleManager2(){
        FXMLLoader loader = new FXMLLoader();
        Parent viewRoot = null;
        try {
            viewRoot = loader.load(DungeonTop.class.getClassLoader().getResourceAsStream("view/arena.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        arenaController = loader.getController();
        scene = new Scene(viewRoot);
    }

    public void startBattle(Arena arena){
        Platform.runLater(() -> {
            this.player1 = PlayerManager.getInstance().getPlayer();
            this.player2 = GameManager.getInstance().getOpponentPlayer();
            Double random = Math.random();
            musicId = AudioManager.getInstance().playSound(random < 0.25 ? 984 : random < 0.5 ? 983 : random < 0.75 ? 982 : 985, true);
            if (!GameManager.getInstance().isDM()){
                if (random>0.5){
                    isStarting = true;
                }
                NetManager.getInstance().getNetworkAPI().sendStartBattle(isStarting);
            }
            arenaController.initBattle(arena);
            drawNewHand();
            DungeonTop.getStage().setScene(scene);
            List<Entity> entities = TestConstants.getTestEntities();
            entities.addAll(TestConstants.getTestEntities());
            //TODO Hero und DungeonMaster spawnen
            Entity peter = new Minion("Peter",10,4,4, 1,410, 211);
            EntityCard petercard = new EntityCard(peter,10,4,4,410,4);
            peter.setCard(petercard);
            Coordinate cord = new Coordinate(0, arena.getHeight()-1);
            arenaController.placeEntityFriendly(peter,cord);
            battlePhase = BattlePhase.FIRST_DUELLIST_DRAW;
            processButton();
            processLabel();
            instantiateHeroes(arena);
        });
    }

    private void instantiateHeroes (Arena arena)
    {
        Coordinate cornerBottemRight = new Coordinate(arena.getWidth()-1, arena.getHeight()-1);
        Entity hero = player1.getHero();
        arenaController.placeEntityFriendly(hero, cornerBottemRight);
    }

    private void processButton(){
        arenaController.getNextPhaseButton().setDisable(!isTurn());
    }

    private void processLabel(){
        if (!isStarting){
            switch (battlePhase){
                case FIRST_DUELLIST_DRAW -> {
                    arenaController.setPhaseLabel("Dein Gegner zieht seine Karten");
                }
                case SECOND_DUELLIST_DRAW -> {
                    arenaController.setPhaseLabel("Du kannst deine Karten platzieren");
                }
                case FIRST_DUELLIST_PLACE_CARDS -> {
                    arenaController.setPhaseLabel("Dein Gegner kann seine Karten platzieren");
                }
                case SECOND_DUELLIST_PLACE_CARDS -> {
                    arenaController.setPhaseLabel("Du kannst deine Minions bewegen und mit ihnen angreifen");
                }
                case FIRST_DUELLIST_MINION_ACT -> {
                    arenaController.setPhaseLabel("Dein Gegner kann seine Minions bewegen und mit ihnen angreifen");
                }
                case SECOND_DUELLIST_MINION_ACT -> {
                    arenaController.setPhaseLabel("Du kannst deine Karten platzieren");
                }
                case FIRST_DUELLIST_SECOND_PLACE_CARDS -> {
                    arenaController.setPhaseLabel("Dein Gegner kann seine Karten platzieren");
                }
                case SECOND_DUELLIST_SECOND_PLACE_CARDS -> {
                    arenaController.setPhaseLabel("Du ziehst deine Karten");
                }
            }
        } else {
            switch (battlePhase){
                case FIRST_DUELLIST_DRAW -> {
                    arenaController.setPhaseLabel("Du ziehst deine Karten");
                }
                case SECOND_DUELLIST_DRAW -> {
                    arenaController.setPhaseLabel("Dein Gegner kann seine Karten platzieren");
                }
                case FIRST_DUELLIST_PLACE_CARDS -> {
                    arenaController.setPhaseLabel("Du kannst deine Karten platzieren");
                }
                case SECOND_DUELLIST_PLACE_CARDS -> {
                    arenaController.setPhaseLabel("Dein Gegner kann seine Minions bewegen und mit ihnen angreifen");
                }
                case FIRST_DUELLIST_MINION_ACT -> {
                    arenaController.setPhaseLabel("Du kannst deine Minions bewegen und mit ihnen angreifen");
                }
                case SECOND_DUELLIST_MINION_ACT -> {
                    arenaController.setPhaseLabel("Dein Gegner kann seine Karten platzieren");
                }
                case FIRST_DUELLIST_SECOND_PLACE_CARDS -> {
                    arenaController.setPhaseLabel("Du kannst deine Karten platzieren");
                }
                case SECOND_DUELLIST_SECOND_PLACE_CARDS -> {
                    arenaController.setPhaseLabel("Dein Gegner zieht seine Karten");
                }
            }
        }
    }

    public void endBattle(boolean playerWins){
        Platform.runLater(() -> {
            if (playerWins){
                if (((ArenaRoom)PlayerManager.getInstance().getPlayer().getCurrentRoom()).isBoss())
                {
                    GameManager.getInstance().getGameWorld().getNextHell();
                    HellView.restartHellViewBgMusic();
                }
                else
                {
                    HellView.resumeHellViewBgMusic();
                }
                DungeonTop.getStage().setScene(HellView.getCurrHellView());
            } else {
                GameManager.getInstance().endGame();
            }
            AudioManager.getInstance().stopSound(musicId);
            NetManager.getInstance().getNetworkAPI().sendEndBattlePackage(playerWins);
        });
    }

    public void spawnOpponent(Entity card, Coordinate pos){
        arenaController.placeEntityOpponent(card, pos);
    }

    public void move(Coordinate pos, Coordinate target){
        arenaController.move(pos,target);
    }

    public void remove(Coordinate pos){
        arenaController.remove(pos);
    }

    public void nextPhase(){
        switch (battlePhase){
            case FIRST_DUELLIST_DRAW -> {
                battlePhase = BattlePhase.SECOND_DUELLIST_DRAW;
            }
            case SECOND_DUELLIST_DRAW -> {
                battlePhase = BattlePhase.FIRST_DUELLIST_PLACE_CARDS;
            }
            case FIRST_DUELLIST_PLACE_CARDS -> {
                battlePhase = BattlePhase.SECOND_DUELLIST_PLACE_CARDS;
            }
            case SECOND_DUELLIST_PLACE_CARDS -> {
                battlePhase = BattlePhase.FIRST_DUELLIST_MINION_ACT;
            }
            case FIRST_DUELLIST_MINION_ACT -> {
                battlePhase = BattlePhase.SECOND_DUELLIST_MINION_ACT;
            }
            case SECOND_DUELLIST_MINION_ACT -> {
                battlePhase = BattlePhase.FIRST_DUELLIST_SECOND_PLACE_CARDS;
            }
            case FIRST_DUELLIST_SECOND_PLACE_CARDS -> {
                battlePhase = BattlePhase.SECOND_DUELLIST_SECOND_PLACE_CARDS;
            }
            case SECOND_DUELLIST_SECOND_PLACE_CARDS -> {
                battlePhase = BattlePhase.FIRST_DUELLIST_DRAW;
                newRound();
            }
        }
        processButton();
        processLabel();
    }
    
    private void newRound(){
        GlobalLogger.log(LoggerStringValues.NEXT_ROUND_BOTH_PLAYERS_GET_EGOPOINTS);
        player1.setMax_ego_points(player1.getMax_ego_points() + GameConstants.EGOPOINTS_PER_ROUND_INCREMENT);
        player2.setMax_ego_points(player2.getMax_ego_points() + GameConstants.EGOPOINTS_PER_ROUND_INCREMENT);
        player1.currentEgoPointsProperty().setNewValue(player1.getMax_ego_points());
        player2.currentEgoPointsProperty().setNewValue(player2.getMax_ego_points());
        drawNewHand();
        resetEntityMovement();
    }

    private void resetEntityMovement() {
    }

    @Deprecated
    public boolean entityAttack(){
        return false; //TODO
    }

    public boolean placeCard(Coordinate coordinate, EntityCard entityCard){
        if (entityCoordinateHashMap.containsKey(coordinate)){
            return false;
        } else {
            entityCoordinateHashMap.put(coordinate, entityCard);
            return true;
        }
    }

    public List<Entity> battle(Entity entity1, Entity entity2)
    {
        EntityController.applyDamage(entity2, entity1.getAttackDamage());
        List<Entity> cardList = new ArrayList<>();
        if (entity2.getHp()>0){
            cardList.add(entity2);
        }
        if (player1.getHp()<=0){
            endBattle(GameManager.getInstance().isDM());
        } else if (player2.getHp() <= 0){
            endBattle(!GameManager.getInstance().isDM());
        }
        return cardList;
    }

    public void drawNewHand()
    {
        if (player1.getDeck().getCards().size() < player1.getHandCardLimit()) {
                reStackDeckFromDiscard();
                DeckController.shuffleDeck(player1.getDeck());
            }
            discardHand();
            DeckController.shuffleDeck(player1.getDeck());
            List<Card> hand = new LinkedList<>();
            for (int i = 0; i < player1.getHandCardLimit(); i++) {
                //hand.add(DeckController.drawCard(player1.getDeck());

                Card drawenCard = DeckController.drawCard(player1.getDeck());
                //TODO sound of get
                player1.getHandCards().add(drawenCard);
                GlobalLogger.log(LoggerStringValues.DREW_NEW_CARD + i + "out of" + player1.getHandCardLimit());
            }

    }

    //Karten muessen in den discard pile und nicht einfach weggelegt werden
    public void discardHand ()
    {
        player1.getHandCards().clear();
    }

    public void removeCardFromHand (Card card)
    {
        //TODO SOUND einspielen
        player1.getDiscardPile().pushCard(card);
        player1.getHandCards().remove(card);
        NetManager.getInstance().getNetworkAPI().sendHandCardReducePackage();
        GlobalLogger.log(LoggerStringValues.CARD_REMOVED_FROM_HAND);
    }

    public void reduceEgoPoints (int amount)
    {
        int egopoints = player1.currentEgoPointsProperty().get();
        if (egopoints - amount >= 0) {
            player1.currentEgoPointsProperty().set(egopoints - amount);
            GlobalLogger.log(LoggerStringValues.REDUCED_EGOPOINTS + amount);
            NetManager.getInstance().getNetworkAPI().sendEgopointsChangePackage(Math.negateExact(amount));
        } else {
            GlobalLogger.log(LoggerStringValues.NOT_ENOUGH_EGOPOINTS);
        }
    }

    public void reStackDeckFromDiscard ()
    {
        //TODO sound abspielen
        for (Card card : player1.getDiscardPile().getCards()) {
            player1.getDeck().getCards().push(player1.getDiscardPile().popCard());
        }
        DeckController.shuffleDeck(player1.getDeck());
    }

    public Card getSelectedHandCard()
    {
        for (Card card :player1.getHandCards()) {
            if (card.isSelected())
            {
                return card;
            }
        }
        return null;
    }

    public boolean isTurn() {
        if (isStarting && (battlePhase == BattlePhase.FIRST_DUELLIST_DRAW || battlePhase == BattlePhase.FIRST_DUELLIST_PLACE_CARDS || battlePhase == BattlePhase.FIRST_DUELLIST_MINION_ACT|| battlePhase == BattlePhase.FIRST_DUELLIST_SECOND_PLACE_CARDS)){
            return true;
        } else if (!isStarting && (battlePhase == BattlePhase.SECOND_DUELLIST_DRAW || battlePhase == BattlePhase.SECOND_DUELLIST_PLACE_CARDS || battlePhase == BattlePhase.SECOND_DUELLIST_MINION_ACT|| battlePhase == BattlePhase.SECOND_DUELLIST_SECOND_PLACE_CARDS)){
            return true;
        } else {
            return false;
        }
    }

    private enum BattlePhase{
        FIRST_DUELLIST_DRAW, //where first duellist draws cards
        FIRST_DUELLIST_PLACE_CARDS, //first duellist can place cards on arena
        SECOND_DUELLIST_DRAW, //where second duellist draws cards
        SECOND_DUELLIST_PLACE_CARDS, //second duellist can place cards on arena
        FIRST_DUELLIST_MINION_ACT, // first duellist can move and attack minions on field
        SECOND_DUELLIST_MINION_ACT, // second duellist can move and attack minions on field
        FIRST_DUELLIST_SECOND_PLACE_CARDS, // first duellist can place cards with leftover mana, minions placed here cant attck this round
        SECOND_DUELLIST_SECOND_PLACE_CARDS; //second duellist can place cards with leftover mana, minions placed here cant attck this round

    }

    public static BattleManager2 getInstance() {
        return instance;
    }

    public void setArenaController(ArenaController arenaController) {
        this.arenaController = arenaController;
    }

    public Scene getScene() {
        return scene;
    }

    public AnchorPane getCardDetailViewContainer() {
        return arenaController.getCardDetailViewContainer();
    }

    public void setStarting(boolean starting) {
        isStarting = starting;
    }
}
