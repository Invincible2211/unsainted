package de.prog2.dungeontop.control.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class IPChecker {

    public static String getPublicIPAdress(){
        String ip = "";
        try {
            URL ipAdressURL = new URL("http://checkip.amazonaws.com");
            BufferedReader in = new BufferedReader(new InputStreamReader(ipAdressURL.openStream()));
            ip = in.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ip;
    }

    public static String getLocalIPAdress(){
        String ip = null;
        try(final DatagramSocket socket = new DatagramSocket()){
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            ip = socket.getLocalAddress().getHostAddress();
        } catch (UnknownHostException | SocketException e) {
            e.printStackTrace();
        }
        return ip;
    }



}
