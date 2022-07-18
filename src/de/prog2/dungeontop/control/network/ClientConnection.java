package de.prog2.dungeontop.control.network;

import de.prog2.dungeontop.model.network.NetworkConnectionI;
import de.prog2.dungeontop.resources.NetworkData;
import de.prog2.dungeontop.resources.NetworkingConstants;
import de.prog2.dungeontop.utils.GlobalLogger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientConnection extends Thread implements NetworkConnectionI
{
    private final String ip;
    private InputStream inputStream;
    private OutputStream outputStream;

    Socket socket = null;

    protected ClientConnection(String ip){
        this.ip = ip;
    }

    @Override
    public void run() {
        GlobalLogger.log(String.format(NetworkingConstants.NETWORK_CONNECTING_TO, ip));
        while (!isConnected()){
            try {
                socket = new Socket(ip, NetworkData.DEFAULT_PORT);
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();
            } catch (IOException e) {
                GlobalLogger.warning(e.getMessage());
            }
            try {
                sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
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
            socket.close();
        } catch (IOException | NullPointerException e) {

        }
    }

}
