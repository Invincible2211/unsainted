package de.prog2.dungeontop.resources;

import de.prog2.dungeontop.model.entities.Entity;
import de.prog2.dungeontop.model.entities.Minion;
import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.model.game.Deck;
import de.prog2.dungeontop.model.game.EntityCard;
import de.prog2.dungeontop.model.game.SpellCard;
import de.prog2.dungeontop.model.spells.DamageSpell;
import de.prog2.dungeontop.model.spells.HealingSpell;
import de.prog2.dungeontop.model.spells.Spell;

import java.util.ArrayList;
import java.util.List;

public interface TestConstants
{

    static List<Card> getTestCards()
    {
        ArrayList<Card> cards = new ArrayList<>();
        Entity harald = new Minion("Harald", 12, 5, 2, 1, 2, 450);
        cards.add(new EntityCard(harald, 6, 99, 5, 3, 0));
        Entity james = new Minion("James", 4, 9, 1, 1, 3, 420);
        cards.add(new EntityCard(james, 6, 120, 2, 1, 0));
        HealingSpell healingSpell = new HealingSpell(SpellValues.HEALING_SPELL_NAME,
                String.format(SpellValues.HEALING_SPELL_DESCRIPTION, SpellValues.HEALING_SPELL_RADIUS, SpellValues.HEALING_SPELL_HEAL),
                SpellValues.HEALING_SPELL_ASSET_ID, SpellValues.HEALING_SPELL_HEAL, 1);
        cards.add(new SpellCard(healingSpell, 6, 350, 6, 5, 0));
        Spell testSpell2 = new DamageSpell(SpellValues.METEOR_SPELL_NAME,
                String.format(SpellValues.METEOR_SPELL_DESCRIPTION, SpellValues.METEOR_SPELL_RADIUS, SpellValues.METEOR_SPELL_DAMAGE),
                SpellValues.METEOR_SPELL_ASSET_ID, SpellValues.METEOR_SPELL_DAMAGE, 1);
        cards.add(new SpellCard(testSpell2, 6, 30, 3, 10, 0));
        Entity josef = new Minion("Josef", 10, 3, 0, 1, 6, 460);
        cards.add(new EntityCard(josef, 6, 70, 4, 2, 0));
        return cards;
    }
    static List<Entity> getTestEntities()
    {
        ArrayList<Entity> entities = new ArrayList<>();
        Entity harald = new Minion("Harald", 12, 5, 3, 1, 2, 450);
        entities.add(harald);
        Entity james = new Minion("James", 4, 9, 1, 1, 3, 420);
        entities.add(james);
        Entity dieter = new Minion("Dieter", 6, 7, 0, 1, 4, 430);
        entities.add(dieter);
        Entity johann = new Minion("Johann", 8, 5, 2, 1, 5, 440);
        entities.add(johann);
        Entity josef = new Minion("Josef", 10, 3, 1, 1, 6, 460);
        entities.add(josef);
        return entities;
    }
}
