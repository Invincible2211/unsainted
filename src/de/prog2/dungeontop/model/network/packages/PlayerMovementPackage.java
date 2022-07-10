package de.prog2.dungeontop.model.network.packages;

import de.prog2.dungeontop.model.network.Package;
import javafx.scene.input.KeyCode;
import org.apache.commons.lang3.SerializationUtils;

public class PlayerMovementPackage extends Package
{
    private KeyCode keyCode;

    public PlayerMovementPackage (KeyCode keyCode)
    {
        this.keyCode = keyCode;
    }

    public KeyCode getKeyCode() {
        return keyCode;
    }

}
