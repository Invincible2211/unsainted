package de.prog2.dungeontop.model.world;

import de.prog2.dungeontop.resources.WorldConstants;

public class RoomCenter extends HellComponent
{
    private Room room;
    public RoomCenter(Room room)
    {
        super(WorldConstants.RoomAssetIds.ROOM_CENTER_ASSET_ID);
        this.room = room;
    }
}
