package de.prog2.dungeontop.model.savegame;

import java.util.Collection;

public record PlayerSaveData(int level, int exp, int souls, Collection<String> unlockedCards) {
}
