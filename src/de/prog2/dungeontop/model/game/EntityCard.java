package de.prog2.dungeontop.model.game;

import de.prog2.dungeontop.model.entities.Entity;

public class EntityCard extends Card
{
    private final Entity entity;

    public EntityCard(Entity entity, int maxRank, int price, int rank)
    {
        super(maxRank, price, rank);
        this.entity = entity;
    }

    public Entity getEntity()
    {
        return entity;
    }
}
