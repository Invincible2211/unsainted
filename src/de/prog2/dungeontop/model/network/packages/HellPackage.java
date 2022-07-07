package de.prog2.dungeontop.model.network.packages;

import de.prog2.dungeontop.model.network.Package;
import de.prog2.dungeontop.model.world.Coordinate;
import de.prog2.dungeontop.model.world.Hell;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;

public class HellPackage extends Package
{

    private Hell hell;
    private Coordinate playerCoordinate;

    public HellPackage(Hell hell, Coordinate playerCoordinate) {
        super(new byte[]{0,0,0,1});

        this.playerCoordinate = playerCoordinate;
        this.hell = hell;
    }

    @Override
    public byte[] getContentAsByteArray() {
        return SerializationUtils.serialize(this);
    }

    public Hell getHell ()
    {
        return this.hell;
    }

    public Coordinate getPlayerCoordinate ()
    {
        return this.playerCoordinate;
    }
}
