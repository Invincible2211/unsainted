package de.prog2.dungeontop.control.manager;

import de.prog2.dungeontop.DungeonTop;
import de.prog2.dungeontop.control.controller.EntityCardController;
import de.prog2.dungeontop.control.controller.EntityController;
import de.prog2.dungeontop.control.controller.EntityViewController;
import de.prog2.dungeontop.control.network.NetManager;
import de.prog2.dungeontop.model.entities.Entity;
import de.prog2.dungeontop.model.entities.Minion;
import de.prog2.dungeontop.model.game.EntityCard;
import de.prog2.dungeontop.model.game.Player;
import de.prog2.dungeontop.model.world.Coordinate;
import de.prog2.dungeontop.model.world.arena.Arena;
import de.prog2.dungeontop.resources.TestConstants;
import de.prog2.dungeontop.view.ArenaController;
import de.prog2.dungeontop.view.HellView;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BattleManager2 {

    private Scene scene;

    private Player player1;
    private Player player2;
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

    public void startBattle(Player player1, Player player2, Arena arena){
        Platform.runLater(() -> {
            this.player1 = player1;
            this.player2 = player2;
            arenaController.initBattle(arena);
            DungeonTop.getStage().setScene(scene);
            List<Entity> entities = TestConstants.getTestEntities();
            entities.addAll(TestConstants.getTestEntities());
            //TODO Hero und DungeonMaster spawnen
            if (!GameManager.getInstance().isDM()){
                arenaController.placeCardFriendly(new EntityCard(new Minion("Harald",10,4,4,410),10,4,4,410,4).getEntity(), new Coordinate(2,1));
            }  else {
                arenaController.placeCardFriendly(new EntityCard(new Minion("Harald",10,4,4,410),10,4,4,410,4).getEntity(), new Coordinate(2,1));
            }
            //arenaController.placeCardOpponent(new EntityCard(entities.get(1),0,0,0,0), new Coordinate(2,1));
        });
    }

    public void endBattle(boolean playerWins){
        Platform.runLater(() -> {
            if (playerWins){
                DungeonTop.getStage().setScene(HellView.getCurrHellView());
            } else {
                GameManager.getInstance().endGame();
            }
            NetManager.getInstance().getNetworkAPI().sendEndBattlePackage(playerWins);
        });
    }

    public void spawnOpponent(Entity card, Coordinate pos){
        arenaController.placeCardOpponent(card, pos);
    }

    public void move(Coordinate pos, Coordinate target){
        arenaController.move(pos,target);
    }

    public void remove(Coordinate pos){
        arenaController.remove(pos);
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

    public List<Entity> battle(Entity entity1, Entity entity2)
    {
        entity2.setHp(entity2.getHp()-entity1.getAttackDamage());
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

    public Scene getScene() {
        return scene;
    }

}
