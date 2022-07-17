package de.prog2.dungeontop.model.network.packages;

import de.prog2.dungeontop.model.network.Package;

public class EgoPointsSetPackage extends Package
{

    private final int amount;

    public EgoPointsSetPackage (int amount)
    {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }


}
