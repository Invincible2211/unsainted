package de.prog2.dungeontop.model.savegame;

import java.util.Set;

public record PlayerSaveData(int level, int exp, int souls, Set<String> unlockedCards) {
}
