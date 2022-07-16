package de.prog2.dungeontop.model.network.packages;

import de.prog2.dungeontop.model.network.Package;
import de.prog2.dungeontop.model.world.arena.Arena;

public class OpenArenaPackage extends Package {

    private final Arena arena;

    public OpenArenaPackage(Arena arena){
        this.arena = arena;
    }

    public Arena getArena() {
        return arena;
    }

}
