package de.prog2.dungeontop.resources;

import de.prog2.dungeontop.model.items.*;

public interface ItemConstants
{
    //Consumables
    Item minorPotion = new Consumable("Gesundheitstrank", "heilt den Spieler um 5 Lebenspunkte", 5 ,50, BonusType.HEALTH, AssetIds.HEALTH_POTION);
    Item bread = new Consumable("Brot", "heilt den Spieler um 1 Lebenspunkte", 1 ,5, BonusType.HEALTH, AssetIds.BREAD);

    //Artifacts
    Item necklace = new Artifact("Halskette", "Wenn sie ausgerüstet ist, erhält der Spieler +2 Lebenspunkte", 2, 150, BonusType.SOULS, AssetIds.NECKLACE);
    Item bracelet = new Artifact("Armband", "Wenn sie ausgerüstet ist, erhält der Spieler +1 Lebenspunkte", 1, 200, BonusType.DEFENSE, AssetIds.BRACELET);

    //Weapon
    Item sword = new Weapon("Schwert", "Wenn sie ausgerüstet ist, erhält der Spieler +3 Angriff", 3, 500, BonusType.ATTACK, AssetIds.SWORD);

}
