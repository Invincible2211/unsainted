package de.prog2.dungeontop.resources;

import de.prog2.dungeontop.model.entities.Entity;
import de.prog2.dungeontop.model.entities.Minion;
import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.model.game.EntityCard;
import de.prog2.dungeontop.model.game.SpellCard;
import de.prog2.dungeontop.model.spells.Spell;
import de.prog2.dungeontop.model.spells.TestSpell;

import java.util.ArrayList;
import java.util.List;

public interface TestConstants
{
    static List<Card> getTestCards()
    {
        ArrayList<Card> cards = new ArrayList<>();
        Entity harald = new Minion("Harald", 12, 5, 2, 2, 450);
        cards.add(new EntityCard(harald, 6, 99, 5, 3, 0));
        Entity james = new Minion("James", 4, 9, 1, 3, 420);
        cards.add(new EntityCard(james, 6, 120, 2, 1, 0));
        Spell testSpell = new TestSpell();
        cards.add(new SpellCard(testSpell, 6, 350, 6, 5, 0));
        Spell testSpell2 = new TestSpell();
        cards.add(new SpellCard(testSpell2, 6, 30, 3, 10, 0));
        Entity josef = new Minion("Josef", 10, 3, 0, 6, 460);
        cards.add(new EntityCard(josef, 6, 70, 4, 2, 0));
        return cards;
    }
    static List<Entity> getTestEntities()
    {
        ArrayList<Entity> entities = new ArrayList<>();
        Entity harald = new Minion("Harald", 12, 5, 3, 2, 450);
        entities.add(harald);
        Entity james = new Minion("James", 4, 9, 1, 3, 420);
        entities.add(james);
        Entity dieter = new Minion("Dieter", 6, 7, 0, 4, 430);
        entities.add(dieter);
        Entity johann = new Minion("Johann", 8, 5, 2, 5, 440);
        entities.add(johann);
        Entity josef = new Minion("Josef", 10, 3, 1, 6, 460);
        entities.add(josef);
        return entities;
    }
}
