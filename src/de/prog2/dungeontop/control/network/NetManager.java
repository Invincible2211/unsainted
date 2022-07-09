package de.prog2.dungeontop.control.network;

import de.prog2.dungeontop.control.manager.GameManager;
import de.prog2.dungeontop.control.manager.PlayerManager;
import de.prog2.dungeontop.model.network.NetworkConnectionI;
import de.prog2.dungeontop.resources.NetworkingConstants;
import de.prog2.dungeontop.utils.GlobalLogger;

public class NetManager extends Thread{

    private NetworkConnectionI connection;
    private NetworkAPI networkAPI;
    private NetworkInterpreter networkInterpreter;

    private static NetManager instance;

    protected NetManager(String host){
        connection = host == null ? new SessionHost() : new ClientConnection(host);
        ((Thread) connection).start();
        instance = this;
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
        if (connection instanceof SessionHost)
        {
            networkAPI.sendHellData(GameManager.getInstance().getGameWorld().getCurrentHell(),
                    PlayerManager.getInstance().getPlayer().getCurrentRoom().getCoordinate());
            GlobalLogger.log(NetworkingConstants.SENDS_HELL_DATA);
        }
    }

    public NetworkAPI getNetworkAPI() {
        return networkAPI;
    }

    public boolean isConnected(){
        return networkAPI != null;
    }

    public static NetManager getInstance() {
        return instance;
    }

}
