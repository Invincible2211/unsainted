package de.prog2.dungeontop.resources;

import de.prog2.dungeontop.model.world.hellComponents.Rotation;

import java.util.UUID;

public interface WorldConstants
{
    /**
     * Jeder Raum ist ROOM_SIZExROOM_SIZE gross.
     */
    int ROOM_SIZE = 3;
    int HELL_SIZE = 7;
    int LOWEST_COORDINATE = 0;
    int RANDOMIZER_LIMIT = 6;
    int MONSTER_ROOM_RATIO = 60;
    int ONE_HUNDRED = 100;
    int MINIMUM_ROOM_COUNT = 25;
    int MAXIMUM_ROOM_COUNT = MINIMUM_ROOM_COUNT + 5;

    /**
     * Holds alle the AssetIds for the HellComponents
     */
    interface HellComponentAssetIds
    {
        UUID WALL_ASSET_ID = UUID.randomUUID();
        UUID WALL_CORNER_ASSET_ID = UUID.randomUUID();
        UUID PASSAGE_ASSET_ID = UUID.randomUUID();
        UUID ROOM_CENTER_ASSET_ID = UUID.randomUUID();
    }

    /**
     * Holds all the different Rotations for the HellComponents
     */
    interface HellComponentRotations
    {
        // Passage and Walls
        Rotation HORIZONTAL_TOP = Rotation.UP;
        Rotation HORIZONTAL_BOTTOM = Rotation.DOWN;
        Rotation VERTICAL_LEFT = Rotation.LEFT;
        Rotation VERTICAL_RIGHT = Rotation.RIGHT;

        Rotation TOP_LEFT = Rotation.UP;
        Rotation TOP_RIGHT = Rotation.RIGHT;
        Rotation BOTTOM_LEFT = Rotation.LEFT;
        Rotation BOTTOM_RIGHT = Rotation.DOWN;
    }
}
