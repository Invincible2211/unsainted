package de.prog2.dungeontop.model.game;

import de.prog2.dungeontop.model.entities.Entity;
import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.utils.GlobalLogger;

public class EntityCard extends Card
{
    private Entity entity;

    public EntityCard(Entity entity, int maxRank, int price, int rank, int summonCost, int ID)
    {
        super(maxRank, price, rank, summonCost, ID);
        this.entity = entity;
        entity.setCard(this);
        GlobalLogger.log(LoggerStringValues.ENTITYCARD_CREATED);
    }

    public Entity getEntity()
    {
        return entity;
    }

    public int getID ()
    {
        return super.getID();
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append(this.getEntity().getName());
        builder.append(super.toString());
        return  builder.toString();
    }
}
