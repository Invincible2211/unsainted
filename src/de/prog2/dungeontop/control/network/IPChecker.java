package de.prog2.dungeontop.control.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class IPChecker {

    public static String getIPAdress(){
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

}
