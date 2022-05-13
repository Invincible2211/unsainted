package de.prog2.dungeontop.model.world;

import de.prog2.dungeontop.resources.WorldConstants;

public class Passage extends HellComponent
{
    public Passage(Rotation rotation)
    {
        super(WorldConstants.RoomAssetIds.PASSAGE_ASSET_ID, rotation);
    }
}
