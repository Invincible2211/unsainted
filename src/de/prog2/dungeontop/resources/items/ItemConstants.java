package de.prog2.dungeontop.resources.items;

import de.prog2.dungeontop.model.items.Artifact;
import de.prog2.dungeontop.model.items.Consumable;
import de.prog2.dungeontop.model.items.Consumables.HealingConsumable;
import de.prog2.dungeontop.model.items.Item;
import de.prog2.dungeontop.model.items.Weapon;
import de.prog2.dungeontop.model.items.artifacts.ExtraSoulsArtifact;
import de.prog2.dungeontop.resources.AssetIds;

public interface ItemConstants
{
    //Consumables
    Consumable MINOR_POTION = new HealingConsumable("Gesundheitstrank", "heilt den Spieler um %d Lebenspunkte", 5 ,50, AssetIds.HEALTH_POTION);
    Consumable BREAD = new HealingConsumable("Brot", "heilt den Spieler um %d Lebenspunkte", 1 ,5, AssetIds.BREAD);
    Consumable CHEESE = new HealingConsumable("Käse", "heilt den Spieler um %d Lebenspunkte", 1 ,5, AssetIds.CHEESE);

    //Artifacts
    // TODO change to new Artifact classes
    Artifact NECKLACE = new ExtraSoulsArtifact("Halskette", "Wenn sie ausgerüstet ist, erhält der Spieler +2 Lebenspunkte", 2, 150, AssetIds.NECKLACE);
    Artifact BRACELET = new ExtraSoulsArtifact("Armband", "Wenn sie ausgerüstet ist, erhält der Spieler +1 Lebenspunkte", 1, 200, AssetIds.BRACELET);
    Artifact RING = new ExtraSoulsArtifact("Ring", "Wenn sie ausgerüstet ist, erhält der Spieler +1 Lebenspunkte", 1, 200, AssetIds.RING);
    Artifact HELMET = new ExtraSoulsArtifact("Helm", "Wenn sie ausgerüstet ist, erhält der Spieler +1 Lebenspunkte", 1, 200, AssetIds.HELMET);
    Artifact SHIELD = new ExtraSoulsArtifact("Schild", "Wenn sie ausgerüstet ist, erhält der Spieler +1 Lebenspunkte", 1, 200, AssetIds.SHIELD);

    //Weapon
    Weapon SWORD = new Weapon("Schwert", "Wenn sie ausgerüstet ist, erhält der Spieler +3 Angriff", 5, AssetIds.SWORD, 3, 1);
    Weapon BOW = new Weapon("Bogen", "Wenn sie ausgerüstet ist, erhält der Spieler +2 Angriff", 5, AssetIds.BOW, 2, 3);
    Weapon CROSSBOW = new Weapon("Armageddon", "Wenn sie ausgerüstet ist, erhält der Spieler +4 Angriff", 5, AssetIds.CROSSBOW, 3, 2);
    Weapon AXE = new Weapon("Axt", "Wenn sie ausgerüstet ist, erhält der Spieler +3 Angriff", 5, AssetIds.AXE, 3, 1);
    Weapon WAND = new Weapon("Zauberstab", "Wenn sie ausgerüstet ist, erhält der Spieler +2 Angriff", 5, AssetIds.WAND, 2, 2);
    Weapon CLAW = new Weapon("Klaue", "Wenn sie ausgerüstet ist, erhält der Spieler +1 Angriff", 5, AssetIds.CLAW, 1, 1);
}
