package de.prog2.dungeontop.control.network;

public class NetController {

    public static void enable(String host){
        new NetManager(host).start();
    }

}
