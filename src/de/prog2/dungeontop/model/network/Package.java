package de.prog2.dungeontop.model.network;

import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;

public abstract class Package implements Serializable
{

    private byte[] identifier;

    public Package(byte[] identifier){
        this.identifier = identifier;
    }

    public byte[] toByteArray() {
        return SerializationUtils.serialize(this);
    }

    public byte[] getIdentifier() {
        return identifier;
    }

}
