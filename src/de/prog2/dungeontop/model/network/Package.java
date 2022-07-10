package de.prog2.dungeontop.model.network;

import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;

public abstract class Package implements Serializable
{

    public byte[] toByteArray() {
        return SerializationUtils.serialize(this);
    }

}
