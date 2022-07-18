package de.prog2.dungeontop.control.network;

import de.prog2.dungeontop.control.manager.GameManager;
import de.prog2.dungeontop.control.manager.PlayerManager;
import de.prog2.dungeontop.model.game.Player;
import de.prog2.dungeontop.model.network.NetworkConnectionI;
import de.prog2.dungeontop.resources.GameConstants;
import de.prog2.dungeontop.resources.NetworkingConstants;
import de.prog2.dungeontop.resources.SelectHeroConstants;
import de.prog2.dungeontop.utils.GlobalLogger;

import java.io.IOException;
import java.io.OutputStream;

/**
 * This class is responsible for managing all the networking.
 */
public class NetManager extends Thread
{
    private NetworkConnectionI connection;
    private NetworkAPI networkAPI = new NetworkAPI(new OutputStream() {
        @Override
        public void write(int b) throws IOException {

        }
    });
    private NetworkInterpreter networkInterpreter;

    private static NetManager instance = new NetManager();

    protected NetManager(String host){
        connection = host == null ? new SessionHost() : new ClientConnection(host);
        ((Thread) connection).start();
        instance = this;
    }

    private NetManager(){

    }

    @Override
    public void run() {
        while (!connection.isConnected()){
            try {
                sleep(NetworkingConstants.WAITING_TIME_FOR_CONNECTION);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            GlobalLogger.log(NetworkingConstants.WAITING_FOR_CONNECTION);
        }
        GlobalLogger.log(NetworkingConstants.CONNECTED);
        networkAPI = new NetworkAPI(connection.getOutputStream());
        networkInterpreter = new NetworkInterpreter(connection.getInputStream());
        networkInterpreter.start();

        if (GameManager.getInstance().isDM())
        {
            PlayerManager.getInstance().getPlayer().setHero(SelectHeroConstants.DUNGEON_MASTER);
            PlayerManager.getInstance().getPlayer().getHpProperty().bindBidirectional(PlayerManager.getInstance().getPlayerHero().getHpProperty());
        }

        if (connection instanceof SessionHost)
        {
            networkAPI.sendHellData(GameManager.getInstance().getGameWorld().getCurrentHell(),
                    PlayerManager.getInstance().getPlayer().getCurrentRoom().getCoordinate());
            GlobalLogger.log(NetworkingConstants.SENDS_HELL_DATA);
        }
        try {
            sleep(GameConstants.SLEEPTIMER_TIME_IN_MILLISECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Player player = PlayerManager.getInstance().getPlayer();
        networkAPI.sendPlayerData(player);
    }

    public NetworkAPI getNetworkAPI() {
        return networkAPI;
    }

    public boolean isConnected(){
        return networkInterpreter != null;
    }

    public static NetManager getInstance() {
        return instance;
    }

    protected void reset(){
        connection = null;
        if (!GameManager.getInstance().isDM()){
            connection = new SessionHost();
        }
    }

}
