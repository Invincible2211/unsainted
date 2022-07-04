package de.prog2.dungeontop.model.network;

public abstract class Package {

    private byte[] identifier;

    public Package(byte[] identifier){
        this.identifier = identifier;
    }

    public abstract byte[] getContentAsByteArray();

    public byte[] getIdentifier() {
        return identifier;
    }

}
