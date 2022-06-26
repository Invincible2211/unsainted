package de.prog2.dungeontop.control.network;

import de.prog2.dungeontop.resources.NetworkData;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import java.io.*;

public class LobbyController extends Thread{

    private final String partnerIP;

    public LobbyController(String partnerIP){
        this.partnerIP = partnerIP;
        this.run();
    }

    //private final MySQLConnector sqlConnector = new MySQLConnector();

    private InputStream inStream;
    private OutputStream outStream;

    @Override
    public void run() {
        /**
        String hostIP = sqlConnector.getIPHost();
        if (hostIP == null){
            info.setText("Erstelle Lobby...");
            sqlConnector.createLobby(IPChecker.getIPAdress());
            info.setText("Warte auf zweiten Spieler...");
        } else {
            info.setText("Lobby gefunden, trete bei...");
            sqlConnector.joinLobby(IPChecker.getIPAdress());
        }
        progressBar.setProgress(0.3);
        String otherIP;
        while (true){
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            otherIP = hostIP == null ? sqlConnector.getIPClient() : sqlConnector.getIPHost();
            if (otherIP != null){
                break;
            }
        }
        info.setText("Spiel gefunden...");
        progressBar.setProgress(0.6);
         **/
        TCPTunnel tcpTunnel = new TCPTunnel(NetworkData.DEFAULT_PORT, partnerIP, NetworkData.DEFAULT_PORT);
        while (tcpTunnel.getInputStream()==null){
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        inStream = tcpTunnel.getInputStream();
        outStream = tcpTunnel.getOutputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
        while (true){
            String s = null;
            try {
                s = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (s!=null){
                System.out.println(s);
            }
        }
    }

    public InputStream getInStream() {
        return inStream;
    }

    public OutputStream getOutStream() {
        return outStream;
    }

}
