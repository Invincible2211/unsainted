package de.prog2.dungeontop.control.network;

import de.prog2.dungeontop.DungeonTop;
import de.prog2.dungeontop.control.manager.BattleManager2;
import de.prog2.dungeontop.control.manager.GameManager;
import de.prog2.dungeontop.control.manager.PlayerManager;
import de.prog2.dungeontop.model.network.Package;
import de.prog2.dungeontop.model.network.packages.*;
import de.prog2.dungeontop.model.world.Coordinate;
import de.prog2.dungeontop.model.world.Hell;
import de.prog2.dungeontop.resources.NetworkingConstants;
import de.prog2.dungeontop.utils.GlobalLogger;
import de.prog2.dungeontop.view.ArenaController;
import de.prog2.dungeontop.view.HellView;
import de.prog2.dungeontop.view.handViews.EnemyHandView;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class NetworkInterpreter extends Thread{

    private InputStream inStream;

    protected NetworkInterpreter(InputStream inStream){
        this.inStream = inStream;
    }

    @Override
    public void run() {
        GlobalLogger.log(NetworkingConstants.START_INTERPRETING);
        byte[] recievedData = new byte[0];
        while (true){
            GlobalLogger.log(NetworkingConstants.WAITING_FOR_DATA);
            try {
                if (inStream != null){
                    byte[] data = new byte[16000];
                    int count = inStream.read(data);
                    GlobalLogger.log(String.format(NetworkingConstants.BYTES_TO_READ, count));
                    recievedData = Arrays.copyOfRange(data,0,count);
                    GlobalLogger.log(String.format(NetworkingConstants.RECEIVED_DATA, recievedData.length));
                }
            } catch (IOException e) {
                GlobalLogger.warning(e.getMessage());
            }
            if (recievedData.length != 0){
                GlobalLogger.log(NetworkingConstants.INTERPRETING_DATA);
                this.interpret(recievedData);
            }
        }
    }

    private void interpret(byte[] data){
        Package dataPackage = (Package) SerializationUtils.deserialize(data);
        GlobalLogger.log(String.format(NetworkingConstants.BYTES_TO_INTERPRET, data.length));
        if (dataPackage instanceof HellPackage){
            HellPackage hellPackage = (HellPackage) dataPackage;
            // get hell and set current player room
            // this is needed to prevent asynch behaviour
            Coordinate playerCoordinate = hellPackage.getPlayerCoordinate();
            Hell hell = hellPackage.getHell();
            PlayerManager.getInstance().getPlayer().setCurrentRoom(hell.getRoomByCoordinate(playerCoordinate));
            // create and set HellView
            HellView view = new HellView();
            Scene hellView = view.initHellView(hell);
            HellView.setCurrHellView(hellView);
            Platform.runLater(() -> DungeonTop.getStage().setScene(hellView));
        } else if (dataPackage instanceof PlayerMovementPackage){
            PlayerMovementPackage playerMovementPackage = (PlayerMovementPackage) dataPackage;
            KeyCode keyCode = playerMovementPackage.getKeyCode();
            HellView viewInstance = new HellView();
            viewInstance.movePlayer(keyCode);
        } else if (dataPackage instanceof PlayerPackage){
            PlayerPackage playerPackage = (PlayerPackage) dataPackage;
            GameManager.getInstance().setOpponentPlayer(playerPackage.getPlayer());
        } else if (dataPackage instanceof OpenArenaPackage){
            GameManager.getInstance().beginBattle(((OpenArenaPackage)dataPackage).getArena());
        } else if (dataPackage instanceof EndBattlePackage){
            Platform.runLater(() -> BattleManager2.getInstance().endBattle(((EndBattlePackage)dataPackage).isPlayerWins()));
        } else if (dataPackage instanceof PlaceEntityPackage){
            PlaceEntityPackage placeEntityPackage = (PlaceEntityPackage) dataPackage;
            Platform.runLater(() -> BattleManager2.getInstance().spawnOpponent(placeEntityPackage.getEntity(), placeEntityPackage.getCoordinate()));
        } else if (dataPackage instanceof MoveEntityPackage){
            MoveEntityPackage moveEntityPackage = (MoveEntityPackage) dataPackage;
            Platform.runLater(() -> BattleManager2.getInstance().move(moveEntityPackage.getStart(),moveEntityPackage.getTarget()));
        } else if (dataPackage instanceof RemoveEntityPackage){
            RemoveEntityPackage removeEntityPackage = (RemoveEntityPackage) dataPackage;
            Platform.runLater(() -> BattleManager2.getInstance().remove(removeEntityPackage.getCoordinate()));
        } else if (dataPackage instanceof EgopointsChangePackage){
            GameManager.getInstance().getOpponentPlayer().currentEgoPointsProperty().set(
                    GameManager.getInstance().getOpponentPlayer().currentEgoPointsProperty().get() + ((EgopointsChangePackage)dataPackage).getAmount());
        } else if (dataPackage instanceof  HandCardReducePackage){
            Platform.runLater(() -> BattleManager2.getInstance().getArenaController().getEnemyCardView().removeOne());
        } else if (dataPackage instanceof NextPhasePackage){
            Platform.runLater(() -> BattleManager2.getInstance().nextPhase());
        } else if (dataPackage instanceof PlayerBeginnPackage){
            BattleManager2.getInstance().setStarting(!((PlayerBeginnPackage)dataPackage).isPlayerStarts());
        }else if (dataPackage instanceof EgoPointsSetPackage){
            GameManager.getInstance().getOpponentPlayer().currentEgoPointsProperty().set(((EgoPointsSetPackage)dataPackage).getAmount());
        } else if (dataPackage instanceof AttackPackage){
            AttackPackage attackPackage = (AttackPackage) dataPackage;
            Platform.runLater(() -> BattleManager2.getInstance().battle(attackPackage.getAttack(), BattleManager2.getInstance().getEntityAtPosition(attackPackage.getTarget())));
        }
    }

}
