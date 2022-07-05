package de.prog2.dungeontop.control.network;

import de.prog2.dungeontop.model.network.Package;
import de.prog2.dungeontop.model.network.packages.HellPackage;
import de.prog2.dungeontop.model.world.Hell;
import de.prog2.dungeontop.resources.NetworkingConstants;
import de.prog2.dungeontop.utils.GlobalLogger;

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
        System.arraycopy(identifier, 0, dataToSend, 0, identifier.length);
        for (int i = identifier.length; i < dataToSend.length; i++) {
            dataToSend[i] = content[i - identifier.length];
        }
        try {
            GlobalLogger.log(String.format(NetworkingConstants.SENDING, dataToSend.length));
            System.out.println(dataToSend.length);
            outStream.write(dataToSend);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
