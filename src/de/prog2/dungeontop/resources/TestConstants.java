package de.prog2.dungeontop.resources;

import de.prog2.dungeontop.model.entities.Entity;
import de.prog2.dungeontop.model.entities.Minion;
import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.model.game.EntityCard;

import java.util.ArrayList;
import java.util.List;

public class TestConstants
{
    public static List<Card> getTestCards()
    {
        ArrayList<Card> cards = new ArrayList<>();
        Entity harald = new Minion("Harald", 12, 5, 2, 45);
        cards.add(new EntityCard(harald, 6, 99, 5, 3));
        Entity james = new Minion("James", 4, 9, 3, 42);
        cards.add(new EntityCard(james, 6, 120, 2, 1));
        Entity dieter = new Minion("Dieter", 6, 7, 4, 43);
        cards.add(new EntityCard(dieter, 6, 350, 6, 5));
        Entity johann = new Minion("Johann", 8, 5, 5, 44);
        cards.add(new EntityCard(johann, 6, 30, 3, 10));
        Entity josef = new Minion("Josef", 10, 3, 6, 46);
        cards.add(new EntityCard(josef, 6, 70, 4, 2));
        return cards;
    }
    public static List<Entity> getTestEntities()
    {
        ArrayList<Entity> entities = new ArrayList<>();
        Entity harald = new Minion("Harald", 12, 5, 2, 45);
        entities.add(harald);
        Entity james = new Minion("James", 4, 9, 3, 42);
        entities.add(james);
        Entity dieter = new Minion("Dieter", 6, 7, 4, 43);
        entities.add(dieter);
        Entity johann = new Minion("Johann", 8, 5, 5, 44);
        entities.add(johann);
        Entity josef = new Minion("Josef", 10, 3, 6, 46);
        entities.add(josef);
        return entities;
    }
}
