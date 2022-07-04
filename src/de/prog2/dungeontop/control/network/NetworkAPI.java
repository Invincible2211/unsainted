package de.prog2.dungeontop.control.network;

import de.prog2.dungeontop.model.network.Package;
import de.prog2.dungeontop.model.network.packages.HellPackage;
import de.prog2.dungeontop.model.world.Hell;

import java.io.IOException;
import java.io.OutputStream;

public class NetworkAPI {

    private OutputStream outStream;

    protected NetworkAPI(OutputStream outStream){
        this.outStream = outStream;
    }

    public void sendHellData(Hell hell){
        this.sendData(new HellPackage(hell));
    }

    private void sendData(Package data){
        byte[] identifier = data.getIdentifier();
        byte[] content = data.getContentAsByteArray();
        byte dataToSend[] = new byte[identifier.length + content.length];
        for (int i = 0; i < identifier.length; i++) {
            dataToSend[i] = identifier[i];
        }
        for (int i = identifier.length; i < dataToSend.length; i++) {
            dataToSend[i] = content[i - identifier.length];
        }
        try {
            System.out.println(dataToSend.length);
            outStream.write(dataToSend);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
