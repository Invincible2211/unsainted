package de.prog2.dungeontop.resources.items;

import de.prog2.dungeontop.model.items.Consumables.HealingConsumable;
import de.prog2.dungeontop.model.items.Item;
import de.prog2.dungeontop.model.items.Weapon;
import de.prog2.dungeontop.model.items.artifacts.ExtraSoulsArtifact;
import de.prog2.dungeontop.resources.AssetIds;

public interface ItemConstants
{
    //Consumables
    Item minorPotion = new HealingConsumable("Gesundheitstrank", "heilt den Spieler um %d Lebenspunkte", 5 ,50, AssetIds.HEALTH_POTION);
    Item bread = new HealingConsumable("Brot", "heilt den Spieler um %d Lebenspunkte", 1 ,5, AssetIds.BREAD);

    //Artifacts
    Item necklace = new ExtraSoulsArtifact("Halskette", "Wenn sie ausgerüstet ist, erhält der Spieler +2 Lebenspunkte", 2, 150, AssetIds.NECKLACE);
    Item bracelet = new ExtraSoulsArtifact("Armband", "Wenn sie ausgerüstet ist, erhält der Spieler +1 Lebenspunkte", 1, 200, AssetIds.BRACELET);

    //Weapon
    Item sword = new Weapon("Schwert", "Wenn sie ausgerüstet ist, erhält der Spieler +3 Angriff", 3, 500, AssetIds.SWORD);
}
