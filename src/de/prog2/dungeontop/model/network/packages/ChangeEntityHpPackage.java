package de.prog2.dungeontop.model.network.packages;

import de.prog2.dungeontop.model.entities.Entity;
import de.prog2.dungeontop.model.network.Package;

public class ChangeEntityHpPackage extends Package
{
    private Entity entity;
    private int amount;

    public ChangeEntityHpPackage(Entity entity, int amount)
    {
        this.entity = entity;
        this.amount = amount;
    }

    public Entity getEntity()
    {
        return entity;
    }

    public int getAmount ()
    {
        return amount;
    }

}
