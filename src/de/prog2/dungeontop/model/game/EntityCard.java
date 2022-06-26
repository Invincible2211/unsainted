package de.prog2.dungeontop.model.game;

import de.prog2.dungeontop.model.entities.Entity;
import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.utils.GlobalLogger;

import java.util.List;

public class EntityCard extends Card
{
    private Entity entity;

    public EntityCard(Entity entity, int maxRank, int price, int rank, int summonCost, List... assets)
    {
        super(maxRank, price, rank, summonCost, assets);
        this.entity = entity;
        GlobalLogger.log(LoggerStringValues.ENTITYCARD_CREATED);
    }

    public Entity getEntity()
    {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }
}
