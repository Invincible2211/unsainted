package de.prog2.dungeontop.model.world.hellComponents;

import de.prog2.dungeontop.model.world.rooms.Room;
import de.prog2.dungeontop.resources.WorldConstants;

/**
 * Represents the center of a room.
 */
public class RoomCenter extends HellComponent
{
    private final Room room;
    /**
     * Creates a center of a room.
     */
    public RoomCenter(Room room)
    {
        super(WorldConstants.HellComponentAssetIds.ROOM_CENTER_ASSET_ID);
        this.room = room;
    }

    public Room getRoom()
    {
        return room;
    }
}
