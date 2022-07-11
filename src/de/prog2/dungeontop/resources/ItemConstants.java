package de.prog2.dungeontop.resources;

import de.prog2.dungeontop.model.items.Item;
import de.prog2.dungeontop.model.items.ItemType;

public interface ItemConstants
{
    //Consumables
    Item minorPotion = new Item("Gesundheitstrank", "heilt den Spieler um 5 Lebenspunkte", 5 ,50, ItemType.VERBRAUCHSGÜTER, AssetIds.HEALTH_POTION);
    Item bread = new Item("Brot", "heilt den Spieler um 1 Lebenspunkte", 1 ,5, ItemType.VERBRAUCHSGÜTER, AssetIds.BREAD);

    //Artifacts
    Item necklace = new Item("Halskette", "Wenn sie ausgerüstet ist, erhält der Spieler +2 Lebenspunkte", 2, 150, ItemType.ARTEFAKT, AssetIds.NECKLACE);
    Item bracelet = new Item("Armband", "Wenn sie ausgerüstet ist, erhält der Spieler +1 Lebenspunkte", 1, 200, ItemType.ARTEFAKT, AssetIds.BRACELET);

    //Weapon
    Item sword = new Item("Schwert", "Wenn sie ausgerüstet ist, erhält der Spieler +3 Angriff", 3, 500, ItemType.WAFFE, AssetIds.SWORD);

}
