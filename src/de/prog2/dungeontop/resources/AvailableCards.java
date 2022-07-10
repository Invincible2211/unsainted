package de.prog2.dungeontop.resources;

import de.prog2.dungeontop.model.game.Card;

import java.util.Arrays;
import java.util.HashSet;

public interface AvailableCards
{
    HashSet<Card> AVAILABLE_CARDS = new HashSet<>();

    public static void INIT_AVAILABLE_CARDS ()
    {
        Arrays.asList(EntityCardEnum.values()).forEach(e -> AvailableCards.AVAILABLE_CARDS.add(e.getValue()));
    }
}
