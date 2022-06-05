package de.prog2.dungeontop.resources;

public interface HellViewConstants
{
    int HORIZONTAL_TILES = WorldConstants.HELL_SIZE * WorldConstants.ROOM_SIZE;
    int VERTICAL_TILES = WorldConstants.HELL_SIZE * WorldConstants.ROOM_SIZE;

    int ROOM_TILE_WIDTH = 200;
    int ROOM_TILE_HEIGHT = 200;

    int ROOM_TILE_FIT_WIDTH = 200;
    int ROOM_TILE_FIT_HEIGHT = 200;

    int TRANSFORM_Y_COORDINATE = WorldConstants.HELL_SIZE * WorldConstants.ROOM_SIZE * ROOM_TILE_HEIGHT;

    double PLAYER_MOVESPEED = ROOM_TILE_FIT_HEIGHT * WorldConstants.ROOM_SIZE;
}
