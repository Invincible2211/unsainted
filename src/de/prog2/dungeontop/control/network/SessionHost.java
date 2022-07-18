package de.prog2.dungeontop.control.network;

import de.prog2.dungeontop.model.network.NetworkConnectionI;
import de.prog2.dungeontop.resources.NetworkData;
import de.prog2.dungeontop.resources.NetworkingConstants;
import de.prog2.dungeontop.utils.GlobalLogger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SessionHost extends Thread implements NetworkConnectionI {

    private InputStream inputStream;
    private OutputStream outputStream;

    ServerSocket serverSocket;
    Socket clientConnection;

    protected SessionHost(){
        GlobalLogger.log(NetworkingConstants.WAITTNG);
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket();
            serverSocket.setReuseAddress(true);
            serverSocket.bind(new InetSocketAddress(NetworkData.DEFAULT_PORT));
            clientConnection = serverSocket.accept();
            inputStream = clientConnection.getInputStream();
            outputStream = clientConnection.getOutputStream();
        } catch (IOException e) {
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

    @Override
    public void close(){
        try {
            serverSocket.close();
            clientConnection.close();
        } catch (IOException | NullPointerException e) {

        }
    }

}
