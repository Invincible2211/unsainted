package de.prog2.dungeontop.resources;

import de.prog2.dungeontop.control.manager.CardManager;
import de.prog2.dungeontop.model.game.Card;

import java.util.Arrays;
import java.util.HashSet;

public interface AvailableCards
{
    HashSet<Card> AVAILABLE_CARDS = new HashSet<>();

    static void INIT_CARD_DATA()
    {
        Arrays.asList(EntityCardEnum.values()).forEach(e -> AvailableCards.AVAILABLE_CARDS.add(e.getValue()));
        Arrays.asList(SpellCardEnum.values()).forEach(e -> AvailableCards.AVAILABLE_CARDS.add(e.getSpellCard()));

        // init unlocked cards
        if (CardManager.getInstance().getUnlockedCards().size() == 0)
        {
            for (int i = 0; i < (DeckConstants.DEFAULT_UNLOCKED_CARDS * DeckConstants.CARD_MAX_RANK); i++)
            {
                CardManager.getInstance().addUnlockedCard(Arrays.asList(EntityCardEnum.values()).get(i).getValue());
            }
            CardManager.getInstance().addUnlockedCard(SpellCardEnum.HEALING_SPELL_R1_CARD.getSpellCard());
            CardManager.getInstance().addUnlockedCard(SpellCardEnum.HEALING_SPELL_R2_CARD.getSpellCard());
            CardManager.getInstance().addUnlockedCard(SpellCardEnum.HEALING_SPELL_R3_CARD.getSpellCard());

            CardManager.getInstance().addUnlockedCard(SpellCardEnum.METEOR_SPELL_R1_CARD.getSpellCard());
            CardManager.getInstance().addUnlockedCard(SpellCardEnum.METEOR_SPELL_R2_CARD.getSpellCard());
            CardManager.getInstance().addUnlockedCard(SpellCardEnum.METEOR_SPELL_R3_CARD.getSpellCard());
        }

        // init locked cards
        if (CardManager.getInstance().getLockedCards().size() == 0)
        {
            for (int n = 0; n < (AvailableCards.AVAILABLE_CARDS.size() / DeckConstants.CARD_MAX_RANK -
                    DeckConstants.DEFAULT_UNLOCKED_CARDS); n++)
            {
                CardManager.getInstance().getLockedCards().add(Arrays.asList(EntityCardEnum.values()).
                        get(n * DeckConstants.CARD_MAX_RANK).getValue());
            }
        }
    }

}
