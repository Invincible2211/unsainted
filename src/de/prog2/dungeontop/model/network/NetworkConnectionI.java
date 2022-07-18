package de.prog2.dungeontop.model.network;

import java.io.InputStream;
import java.io.OutputStream;

public interface NetworkConnectionI {

    InputStream getInputStream();

    OutputStream getOutputStream();

    boolean isConnected();

    void close();
}
