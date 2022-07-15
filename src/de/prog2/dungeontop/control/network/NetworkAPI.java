package de.prog2.dungeontop.control.network;

import de.prog2.dungeontop.model.entities.Entity;
import de.prog2.dungeontop.model.game.EntityCard;
import de.prog2.dungeontop.model.game.Player;
import de.prog2.dungeontop.model.network.Package;
import de.prog2.dungeontop.model.network.packages.*;
import de.prog2.dungeontop.model.world.Coordinate;
import de.prog2.dungeontop.model.world.Hell;
import de.prog2.dungeontop.model.world.arena.Arena;
import javafx.scene.input.KeyCode;

import java.io.IOException;
import java.io.OutputStream;

public class NetworkAPI {

    private OutputStream outStream;

    protected NetworkAPI(OutputStream outStream){
        this.outStream = outStream;
    }

    public void sendHellData(Hell hell, Coordinate playerCoordinate){
        this.sendData(new HellPackage(hell, playerCoordinate));
    }

    public void sendEgopointsChangePackage(int amount)
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
            throw new RuntimeException(e);
        }
    }

    public void sendHandCardReducePackage() {
        this.sendData(new HandCardReducePackage());
    }
}
