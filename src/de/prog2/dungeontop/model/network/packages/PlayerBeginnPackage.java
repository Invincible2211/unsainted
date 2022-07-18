package de.prog2.dungeontop.model.network.packages;

import de.prog2.dungeontop.model.network.Package;

public class PlayerBeginnPackage extends Package {

    private final boolean playerStarts;

    public PlayerBeginnPackage(boolean playerStarts) {
        this.playerStarts = playerStarts;
    }

    public boolean isPlayerStarts() {
        return playerStarts;
    }
}

