package de.prog2.dungeontop.model.network;

import java.io.Serializable;

public abstract class Package implements Serializable
{

    private byte[] identifier;

    public Package(byte[] identifier){
        this.identifier = identifier;
    }

    public abstract byte[] getContentAsByteArray();

    public byte[] getIdentifier() {
        return identifier;
    }

}
