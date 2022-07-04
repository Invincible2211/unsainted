package de.prog2.dungeontop.model.network.packages;

import de.prog2.dungeontop.model.network.Package;
import de.prog2.dungeontop.model.world.Hell;
import org.apache.commons.lang3.SerializationUtils;

public class HellPackage extends Package {

    private Hell hell;

    public HellPackage(Hell hell) {
        super(new byte[]{0,0,0,1});
        this.hell = hell;
    }

    @Override
    public byte[] getContentAsByteArray() {
        return SerializationUtils.serialize(hell);
    }

}
