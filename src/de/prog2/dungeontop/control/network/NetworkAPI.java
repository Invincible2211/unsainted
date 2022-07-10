package de.prog2.dungeontop.control.network;

import de.prog2.dungeontop.model.game.Player;
import de.prog2.dungeontop.model.network.Package;
import de.prog2.dungeontop.model.network.packages.HellPackage;
import de.prog2.dungeontop.model.network.packages.OpenArenaPackage;
import de.prog2.dungeontop.model.network.packages.PlayerMovementPackage;
import de.prog2.dungeontop.model.network.packages.PlayerPackage;
import de.prog2.dungeontop.model.world.Coordinate;
import de.prog2.dungeontop.model.world.Hell;
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

    public void sendPlayerMovementData (KeyCode keyCode)
    {
        this.sendData(new PlayerMovementPackage(keyCode));
    }

    public void sendPlayerData(Player player){
        this.sendData(new PlayerPackage(player));
    }

    public void sendOpenArenaPackage() {
        sendData(new OpenArenaPackage());
    }

    private void sendData(Package data){
        try {
            outStream.write(data.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
