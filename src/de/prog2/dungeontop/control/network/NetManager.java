package de.prog2.dungeontop.control.network;

import de.prog2.dungeontop.control.manager.GameManager;
import de.prog2.dungeontop.model.network.NetworkConnectionI;

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
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("waiting for connection");
        }
        System.out.println("connected");
        networkAPI = new NetworkAPI(connection.getOutputStream());
        networkInterpreter = new NetworkInterpreter(connection.getInputStream());
        networkInterpreter.start();
        if (connection instanceof SessionHost){
            networkAPI.sendHellData(GameManager.getInstance().getGameWorld().getCurrentHell());
            System.out.println("sendet");
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
