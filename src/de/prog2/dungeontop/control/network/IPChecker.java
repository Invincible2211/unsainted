package de.prog2.dungeontop.control.network;

import de.prog2.dungeontop.resources.NetworkingConstants;
import de.prog2.dungeontop.utils.GlobalLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

/**
 * This class is used to get the local of public IP address.
 */
public class IPChecker {

    /**
     * This method is used to get the public IP address.
     */
    public static String getPublicIPAdress(){
        String ip = "";
        try {
            URL ipAdressURL = new URL(NetworkingConstants.TEST_PUBLIC_ADDRESS_URL);
            BufferedReader in = new BufferedReader(new InputStreamReader(ipAdressURL.openStream()));
            ip = in.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ip;
    }

    /**
     * This method is used to get the local IP address.
     */
    public static String getLocalIPAdress(){
        String ip = null;
        try(final DatagramSocket socket = new DatagramSocket()){
            socket.connect(InetAddress.getByName(NetworkingConstants.TEST_PUBLIC_DNS_IP), NetworkingConstants.TEST_PUBLIC_DNS_PORT);
            ip = socket.getLocalAddress().getHostAddress();
        } catch (UnknownHostException | SocketException e) {
            GlobalLogger.warning(e.getMessage());
        }
        return ip;
    }
}
