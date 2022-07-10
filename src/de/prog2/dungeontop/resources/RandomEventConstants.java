package de.prog2.dungeontop.resources;

public interface RandomEventConstants
{
    boolean FINISHED = true;

    int RNG_MAX = 100;
    int RNG_CHANGE_HP_POS = 50;
    int RNG_CHANGE_SOULS_POS = 50;

    // change souls value event
    int CHANGE_SOULS = 0;
    int ADDED_SOULS_AMOUNT = 100;
    int REMOVED_SOULS_AMOUNT = 50;
    int MIN_SOULS_AFTER_CHANGE = 0;

    // change hp value event
    int CHANGE_HP = 1;
    int ADDED_HP_AMOUNT = 4;
    int REMOVED_HP_AMOUNT = 2;
    int MIN_HP_AFTER_CHANGE = 1;
}
