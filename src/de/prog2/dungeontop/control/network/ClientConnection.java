package de.prog2.dungeontop.control.network;

import de.prog2.dungeontop.model.network.NetworkConnectionI;
import de.prog2.dungeontop.resources.NetworkData;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientConnection extends Thread implements NetworkConnectionI {

    private String ip;
    private InputStream inputStream;
    private OutputStream outputStream;

    protected ClientConnection(String ip){
        this.ip = ip;
    }

    @Override
    public void run() {
        System.out.println("connecting");
        try {
            Socket socket = new Socket(ip, NetworkData.DEFAULT_PORT);
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
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
