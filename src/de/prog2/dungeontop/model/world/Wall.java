package de.prog2.dungeontop.model.world;

import de.prog2.dungeontop.resources.WorldConstants;

public class Wall extends HellComponent
{
    public Wall(Rotation rotation)
    {
        super(WorldConstants.RoomAssetIds.WALL_ASSET_ID, rotation);
    }
}
