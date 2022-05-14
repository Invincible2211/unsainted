package de.prog2.dungeontop.model.world;

import de.prog2.dungeontop.resources.WorldConstants;

public class WallCorner extends HellComponent
{
    public WallCorner(Rotation rotation)
    {
        super(WorldConstants.RoomAssetIds.WALL_CORNER_ASSET_ID, rotation);
    }
}
