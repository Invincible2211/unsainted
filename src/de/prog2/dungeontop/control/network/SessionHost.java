package de.prog2.dungeontop.control.network;

import de.prog2.dungeontop.model.network.NetworkConnectionI;
import de.prog2.dungeontop.resources.NetworkData;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SessionHost extends Thread implements NetworkConnectionI {

    private InputStream inputStream;
    private OutputStream outputStream;

    protected SessionHost(){
        System.out.println("waiting");
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(NetworkData.DEFAULT_PORT);
            Socket clientConnection = serverSocket.accept();
            inputStream = clientConnection.getInputStream();
            outputStream = clientConnection.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
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
