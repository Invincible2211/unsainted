package de.prog2.dungeontop.model.network.packages;

import de.prog2.dungeontop.model.game.Player;
import de.prog2.dungeontop.model.network.Package;

public class PlayerPackage extends Package {

    private final Player player;
    public PlayerPackage(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

}
