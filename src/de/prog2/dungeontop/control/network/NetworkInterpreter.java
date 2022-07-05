package de.prog2.dungeontop.control.network;

import de.prog2.dungeontop.DungeonTop;
import de.prog2.dungeontop.model.world.Hell;
import de.prog2.dungeontop.resources.NetworkingConstants;
import de.prog2.dungeontop.utils.GlobalLogger;
import de.prog2.dungeontop.view.HellView;
import javafx.scene.Scene;
import org.apache.commons.lang3.SerializationUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class NetworkInterpreter extends Thread{

    private InputStream inStream;

    protected NetworkInterpreter(InputStream inStream){
        this.inStream = inStream;
    }

    @Override
    public void run() {
        GlobalLogger.log(NetworkingConstants.START_INTERPRETING);
        byte[] recievedData = new byte[0];
        while (true){
            GlobalLogger.log(NetworkingConstants.WAITING_FOR_DATA);
            try {
                if (inStream != null){
                    byte[] data = new byte[16000];
                    int count = inStream.read(data);
                    GlobalLogger.log(String.format(NetworkingConstants.BYTES_TO_READ, count));
                    recievedData = Arrays.copyOfRange(data,0,count);
                    GlobalLogger.log(String.format(NetworkingConstants.RECEIVED_DATA, recievedData.length));
                }
            } catch (IOException e) {
                GlobalLogger.warning(e.getMessage());
            }
            if (recievedData.length != 0){
                GlobalLogger.log(NetworkingConstants.INTERPRETING_DATA);
                this.interpret(recievedData);
            }
        }
    }

    private void interpret(byte[] data){
        GlobalLogger.log(String.format(NetworkingConstants.BYTES_TO_INTERPRET, data.length));
        byte[] identifier = new byte[]{data[0],data[1],data[2],data[3]};
        byte[] content = new byte[data.length - 4];
        for (int i = 4; i < data.length; i++) {
            content[i-4] = data[i];
        }
        StringBuilder builder = new StringBuilder();
        builder.append(identifier[0]);
        builder.append(identifier[1]);
        builder.append(identifier[2]);
        builder.append(identifier[3]);
        String identifierAsString = builder.toString();
        GlobalLogger.log(identifierAsString);

        // TODO : Remove magic?
        switch (identifierAsString){
            case "0001":
                System.out.println("getHell");
                Hell hell = (Hell) SerializationUtils.deserialize(content);
                HellView view = new HellView();
                Scene hellView = view.initHellView(hell);
                DungeonTop.getStage().setScene(hellView);
        }
    }

}
