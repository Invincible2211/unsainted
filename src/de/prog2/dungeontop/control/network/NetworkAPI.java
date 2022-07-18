package de.prog2.dungeontop.control.network;

import de.prog2.dungeontop.model.entities.Entity;
import de.prog2.dungeontop.model.game.Player;
import de.prog2.dungeontop.model.network.Package;
import de.prog2.dungeontop.model.network.packages.*;
import de.prog2.dungeontop.model.world.Coordinate;
import de.prog2.dungeontop.model.world.Hell;
import de.prog2.dungeontop.model.world.arena.Arena;
import javafx.scene.input.KeyCode;

import java.io.IOException;
import java.io.OutputStream;

/**
 * This class ist responsible for sending the packages to the other Computer.
 */
public class NetworkAPI {

    private OutputStream outStream;

    protected NetworkAPI(OutputStream outStream){
        this.outStream = outStream;
    }

    public void sendHellData(Hell hell, Coordinate playerCoordinate){
        this.sendData(new HellPackage(hell, playerCoordinate));
    }

    public void sendChangeEntityHpPackage(Coordinate coordinate, int amount){
        this.sendData(new ChangeEntityHpByCoordinatePackage(coordinate, amount));
    }

    public void sendEgopointsAddPackage (int amount)
    {
        this.sendData(new EgopointsChangePackage(amount));
    }

    public void sendPlayerMovementData (KeyCode keyCode)
    {
        this.sendData(new PlayerMovementPackage(keyCode));
    }

    public void sendPlayerData(Player player){
        this.sendData(new PlayerPackage(player));
    }

    public void sendOpenArenaPackage(Arena arena) {
        sendData(new OpenArenaPackage(arena));
    }

    public void sendEndBattlePackage(boolean playerWins){
        this.sendData(new EndBattlePackage(playerWins));
    }

    public void sendGameEndPackage ()
    {
        this.sendData(new GameEndPackage());
    }

    public void sendMoveEntity(Coordinate start, Coordinate target){
        this.sendData(new MoveEntityPackage(start, target));
    }

    public void sendSpawnEntity(Entity card, Coordinate position){
        this.sendData(new PlaceEntityPackage(card,position));
    }

    public void sendRemoveEntity(Coordinate coordinate){
        this.sendData(new RemoveEntityPackage(coordinate));
    }

    private void sendData(Package data){
        try {
            outStream.write(data.toByteArray());
        } catch (IOException e) {

        }
    }

    public void sendHandCardReducePackage() {
        this.sendData(new HandCardReducePackage());
    }

    public void sendNextRoundPackage() {
        this.sendData(new NextPhasePackage());
    }

    public void sendStartBattle(boolean isStarting) {
        this.sendData(new PlayerBeginnPackage(isStarting));
    }

    public void sendAttackPackage(Entity attack,  Coordinate target){
        this.sendData(new AttackPackage(attack,target));
    }

    public void sendEgoPointsSetPackage (int amount) {
        this.sendData(new EgoPointsSetPackage(amount));
    }

    public void sendHandCardIncreasedPackage() {
        sendData(new HandCardIncreasedPackage());
    }

}
