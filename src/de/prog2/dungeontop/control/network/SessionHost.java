package de.prog2.dungeontop.control.network;

import de.prog2.dungeontop.model.network.NetworkConnectionI;
import de.prog2.dungeontop.resources.NetworkData;
import de.prog2.dungeontop.resources.NetworkingConstants;
import de.prog2.dungeontop.utils.GlobalLogger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SessionHost extends Thread implements NetworkConnectionI {

    private InputStream inputStream;
    private OutputStream outputStream;

    protected SessionHost(){
        GlobalLogger.log(NetworkingConstants.WAITTNG);
    }

    @Override
    public void run() {
        Socket clientConnection = null;
        try {
            ServerSocket serverSocket = new ServerSocket(NetworkData.DEFAULT_PORT);
            clientConnection = serverSocket.accept();
            inputStream = clientConnection.getInputStream();
            outputStream = clientConnection.getOutputStream();
        } catch (IOException e) {
            try {
                clientConnection.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            NetManager.getInstance().reset();
            GlobalLogger.warning(e.getMessage());
        }
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    @Override
    public boolean isConnected() {
        return inputStream != null && outputStream != null;
    }

}
