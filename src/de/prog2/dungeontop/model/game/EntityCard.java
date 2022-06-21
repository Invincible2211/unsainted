package de.prog2.dungeontop.model.game;

import de.prog2.dungeontop.model.entities.Entity;
import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.utils.GlobalLogger;

public class EntityCard extends Card
{
    private final Entity entity;

    public EntityCard(Entity entity, int maxRank, int price, int rank)
    {
        super(maxRank, price, rank);
        this.entity = entity;
        GlobalLogger.log(LoggerStringValues.ENTITYCARD_CREATED);
    }

    public Entity getEntity()
    {
        return entity;
    }
}
