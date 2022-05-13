package de.prog2.dungeontop.control.controller;

import de.prog2.dungeontop.model.world.*;
import de.prog2.dungeontop.utils.CoordinateDirections;
import de.prog2.dungeontop.utils.GlobalLogger;

public class HellController
{
    public static void generateHell(Hell hell)
    {
        hell.insertRoom(new Coordinate(0, 0), null);

        initHellComponentHashMap(hell);
    }

    public static void initHellComponentHashMap(Hell hell)
    {
        for (var entry : hell.getRoomHashMap().entrySet())
        {
            // get coordinates
            Coordinate coordLeftTop = CoordinateDirections.getLeftTop(entry.getKey());
            Coordinate coordRightTop = CoordinateDirections.getRightTop(entry.getKey());
            Coordinate coordLeftBottom = CoordinateDirections.getLeftBottom(entry.getKey());
            Coordinate coordRightBottom = CoordinateDirections.getRightBottom(entry.getKey());

            Coordinate coordTop = CoordinateDirections.getTop(entry.getKey());
            Coordinate coordBottom = CoordinateDirections.getBottom(entry.getKey());
            Coordinate coordLeft = CoordinateDirections.getLeft(entry.getKey());
            Coordinate coordRight = CoordinateDirections.getRight(entry.getKey());

            // add roomCenter
            hell.insertHellComponent(entry.getKey(), new RoomCenter(entry.getValue()));

            // add the 4 corners
            hell.insertHellComponent(coordLeftTop, new WallCorner());
            hell.insertHellComponent(coordRightTop, new WallCorner());
            hell.insertHellComponent(coordLeftBottom, new WallCorner());
            hell.insertHellComponent(coordRightBottom, new WallCorner());

            // add passages and walls
            // top
            if(entry.getValue().getTopRoom() != null)
                hell.insertHellComponent(coordTop, new Passage());
            else
                hell.insertHellComponent(coordTop, new Wall());
            // bottom
            if(entry.getValue().getBottomRoom() != null)
                hell.insertHellComponent(coordBottom, new Passage());
            else
                hell.insertHellComponent(coordBottom, new Wall());
            // left
            if(entry.getValue().getLeftRoom() != null)
                hell.insertHellComponent(coordLeft, new Passage());
            else
                hell.insertHellComponent(coordLeft, new Wall());
            // right
            if(entry.getValue().getRightRoom() != null)
                hell.insertHellComponent(coordRight, new Passage());
            else
                hell.insertHellComponent(coordRight, new Wall());
        }
    }
}
