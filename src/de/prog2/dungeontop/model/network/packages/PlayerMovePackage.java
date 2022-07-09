package de.prog2.dungeontop.model.network.packages;

import de.prog2.dungeontop.model.game.MoveDirection;
import de.prog2.dungeontop.model.network.Package;
import org.apache.commons.lang3.SerializationUtils;

public class PlayerMovePackage extends Package {

    private MoveDirection moveDirection;

    public PlayerMovePackage(MoveDirection moveDirection){
        super(new byte[]{0,0,1,0});
        this.moveDirection = moveDirection;
    }

    @Override
    public byte[] getContentAsByteArray() {
        return SerializationUtils.serialize(moveDirection);
    }
}
