package de.prog2.dungeontop.model.network;

import java.io.InputStream;
import java.io.OutputStream;

public interface NetworkConnectionI {

    public InputStream getInputStream();

    public OutputStream getOutputStream();

    public boolean isConnected();

}
