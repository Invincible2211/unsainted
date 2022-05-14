package de.prog2.dungeontop.model.savegame;

import de.prog2.dungeontop.model.world.Coordinate;
import de.prog2.dungeontop.model.world.Room;

import java.util.Collection;

public record GameSaveData(int soulsInCurrentRun, Coordinate playerPosition, int hellLevel, Collection<Room> rooms) {
}
