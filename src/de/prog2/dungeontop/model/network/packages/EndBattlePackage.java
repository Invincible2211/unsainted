package de.prog2.dungeontop.model.network.packages;

import de.prog2.dungeontop.model.network.Package;

public class EndBattlePackage extends Package {

    private final boolean playerWins;

    public EndBattlePackage(boolean playerWins){
        this.playerWins = playerWins;
    }

    public boolean isPlayerWins() {
        return playerWins;
    }

}
