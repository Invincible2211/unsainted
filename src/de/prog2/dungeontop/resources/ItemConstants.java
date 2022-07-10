package de.prog2.dungeontop.resources;

import de.prog2.dungeontop.model.items.Item;

public interface ItemConstants
{
    Item minorPotion = new Item("Minor Health Potion", "Heals player for 5 Health", 5 ,20, AssetIds.HEALTH_POTION);
    Item bread = new Item("Bread", "Heals Player for 1 Health", 1 ,1, AssetIds.BREAD);
}
