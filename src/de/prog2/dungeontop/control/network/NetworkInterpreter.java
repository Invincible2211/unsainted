package de.prog2.dungeontop.control.network;

import de.prog2.dungeontop.DungeonTop;
import de.prog2.dungeontop.control.controller.EntityController;
import de.prog2.dungeontop.control.manager.BattleManager2;
import de.prog2.dungeontop.control.manager.GameManager;
import de.prog2.dungeontop.control.manager.PlayerManager;
import de.prog2.dungeontop.model.entities.Entity;
import de.prog2.dungeontop.model.network.Package;
import de.prog2.dungeontop.model.network.packages.*;
import de.prog2.dungeontop.model.world.Coordinate;
import de.prog2.dungeontop.model.world.Hell;
import de.prog2.dungeontop.resources.NetworkingConstants;
import de.prog2.dungeontop.utils.GlobalLogger;
import de.prog2.dungeontop.view.HellView;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import org.apache.commons.lang3.SerializationException;
import org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * This class ist responsible for interpreting the packages send by the other Computer.
 */
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
                inStream = new InputStream() {
                    @Override
                    public int read() throws IOException {
                        return 0;
                    }
                };
                String host = NetManager.getInstance().getHost();
                NetManager.getInstance().close();
                NetManager.getInstance().stop();
                NetController.enable(host);
            }
            if (recievedData.length != 0){
                GlobalLogger.log(NetworkingConstants.INTERPRETING_DATA);
                this.interpret(recievedData);
            }
        }
    }

    private void interpret(byte[] data) {
        Package temp = null;
        try {
            temp = (Package) SerializationUtils.deserialize(data);
        } catch (SerializationException e) {

        }
        if (temp == null){
            return;
        }
        Package dataPackage = temp;
        GlobalLogger.log(String.format(NetworkingConstants.BYTES_TO_INTERPRET, data.length));
        if (dataPackage instanceof HellPackage) {
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
        } else if (dataPackage instanceof PlayerMovementPackage playerMovementPackage) {
            KeyCode keyCode = playerMovementPackage.getKeyCode();
            HellView viewInstance = new HellView();
            viewInstance.movePlayer(keyCode);
        } else if (dataPackage instanceof PlayerPackage playerPackage) {
            GameManager.getInstance().setOpponentPlayer(playerPackage.getPlayer());
        } else if (dataPackage instanceof OpenArenaPackage) {
            GameManager.getInstance().beginBattle(((OpenArenaPackage) dataPackage).getArena());
        } else if (dataPackage instanceof EndBattlePackage) {
            Platform.runLater(() -> BattleManager2.getInstance().endBattle(((EndBattlePackage) dataPackage).isPlayerWins(), false));
        } else if (dataPackage instanceof PlaceEntityPackage placeEntityPackage) {
            Platform.runLater(() -> BattleManager2.getInstance().spawnOpponent(placeEntityPackage.getEntity(), placeEntityPackage.getCoordinate()));
        } else if (dataPackage instanceof MoveEntityPackage moveEntityPackage) {
            Platform.runLater(() -> BattleManager2.getInstance().move(moveEntityPackage.getStart(), moveEntityPackage.getTarget()));
        } else if (dataPackage instanceof RemoveEntityPackage removeEntityPackage) {
            Platform.runLater(() -> BattleManager2.getInstance().remove(removeEntityPackage.getCoordinate(), true));
        } else if (dataPackage instanceof EgopointsChangePackage) {
            Platform.runLater(() -> GameManager.getInstance().getOpponentPlayer().currentEgoPointsProperty().set(
                    GameManager.getInstance().getOpponentPlayer().currentEgoPointsProperty().get() + ((EgopointsChangePackage) dataPackage).getAmount()));
        } else if (dataPackage instanceof HandCardReducePackage) {
            Platform.runLater(() -> BattleManager2.getInstance().getArenaController().getEnemyCardView().removeOne());
        } else if (dataPackage instanceof NextPhasePackage) {
            Platform.runLater(() -> BattleManager2.getInstance().nextPhase());
        } else if (dataPackage instanceof PlayerBeginnPackage) {
            BattleManager2.getInstance().setStarting(!((PlayerBeginnPackage) dataPackage).isPlayerStarts());
        } else if (dataPackage instanceof EgoPointsSetPackage) {
            Platform.runLater(() -> GameManager.getInstance().getOpponentPlayer().currentEgoPointsProperty().set(((EgoPointsSetPackage) dataPackage).getAmount()));
        } else if (dataPackage instanceof AttackPackage attackPackage) {
            Platform.runLater(() -> BattleManager2.getInstance().battle(attackPackage.getAttack(), List.of(new Entity[]{BattleManager2.getInstance().getEntityAtPosition(attackPackage.getTarget())})));
        } else if (dataPackage instanceof HandCardIncreasedPackage) {
            Platform.runLater(() -> BattleManager2.getInstance().getArenaController().getEnemyCardView().addOne());
        } else if (dataPackage instanceof ChangeEntityHpByCoordinatePackage changeEntityPackage) {
            Platform.runLater(() ->
            {
                boolean isDamage = changeEntityPackage.getAmount() >= 0;
                Entity entity = BattleManager2.getInstance().getOpponentEntityAtPosition(changeEntityPackage.getCoordinate());
                if (isDamage)
                    EntityController.applyDamage(entity, changeEntityPackage.getAmount());
                else
                    EntityController.applyHeal(entity, -changeEntityPackage.getAmount());
            });

        } else if (dataPackage instanceof GameEndPackage) {
            GameManager.getInstance().endGame(true);
        }
    }

}
