package de.prog2.dungeontop.control.manager;

import de.prog2.dungeontop.DungeonTop;
import de.prog2.dungeontop.control.controller.EntityViewController;
import de.prog2.dungeontop.model.entities.Entity;
import de.prog2.dungeontop.model.game.EntityCard;
import de.prog2.dungeontop.model.game.Player;
import de.prog2.dungeontop.model.world.Coordinate;
import de.prog2.dungeontop.resources.TestConstants;
import de.prog2.dungeontop.view.ArenaController;
import de.prog2.dungeontop.view.HellView;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BattleManager2 {

    private Scene scene;
    private ArenaController arenaController;

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

    public void startBattle(Player player1, Player player2){
        Platform.runLater(() -> {
            DungeonTop.getStage().setScene(scene);
            List<Entity> entities = TestConstants.getTestEntities();
            entities.addAll(TestConstants.getTestEntities());
            arenaController.placeCardFriendly(new EntityCard(entities.get(0),0,0,0,0, 0), new Coordinate(0,0));
            arenaController.placeCardOpponent(new EntityCard(entities.get(1),0,0,0,0, 0), new Coordinate(2,1));
        });
    }

    public void endBattle(){
        Platform.runLater(() -> DungeonTop.getStage().setScene(HellView.getCurrHellView()));
    }

    public void nextRound(){

    }

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

    public List<EntityCard> battle(EntityCard card1, EntityCard card2){
        Entity entity1 = card1.getEntity();
        Entity entity2 = card2.getEntity();
        entity2.setHp(entity2.getHp()-entity1.getAttackDamage());
        List<EntityCard> cardList = new ArrayList<>();
        if (entity1.getHp()>0){
            cardList.add(card1);
        }
        if (entity2.getHp()>0){
            cardList.add(card2);
        }
        return cardList;
    }

    private enum BattlePhase{
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

    public static BattleManager2 getInstance() {
        return instance;
    }

    public void setArenaController(ArenaController arenaController) {
        this.arenaController = arenaController;
    }

}
