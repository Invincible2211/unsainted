package de.prog2.dungeontop.resources.items;

import de.prog2.dungeontop.model.items.Artifact;
import de.prog2.dungeontop.model.items.Consumable;
import de.prog2.dungeontop.model.items.Consumables.HealingConsumable;
import de.prog2.dungeontop.model.items.Item;
import de.prog2.dungeontop.model.items.Weapon;
import de.prog2.dungeontop.model.items.artifacts.DefenseArtifact;
import de.prog2.dungeontop.model.items.artifacts.ExtraSoulsArtifact;
import de.prog2.dungeontop.resources.AssetIds;
import org.apache.commons.lang3.SerializationUtils;

public interface ItemConstants
{
    static Item getRandomItem()
    {
        int random = (int) (Math.random() * 13);
        return switch (random)
        {
            default -> SerializationUtils.clone(MINOR_POTION);
            case 1 -> SerializationUtils.clone(BREAD);
            case 2 -> SerializationUtils.clone(CHEESE);
            case 3 -> SerializationUtils.clone(NECKLACE);
            case 4 -> SerializationUtils.clone(BRACELET);
            case 5 -> SerializationUtils.clone(RING);
            case 6 -> SerializationUtils.clone(HELMET);
            case 7 -> SerializationUtils.clone(SHIELD);
            case 8 -> SerializationUtils.clone(SWORD);
            case 9 -> SerializationUtils.clone(AXE);
            case 10 -> SerializationUtils.clone(BOW);
            case 11 -> SerializationUtils.clone(CLAW);
            case 12 -> SerializationUtils.clone(CROSSBOW);
            case 13 -> SerializationUtils.clone(WAND);
        };
    }

    // ItemController
    int INVENTORY_SLOTS = 28;
    int INVENTORY_COLUMNS = 7;

    // ItemClicked
    String EQUIP_ARTIFACT = "Artefakt ausrüsten";
    String EQUIP_WEAPON = "Waffe ausrüsten";
    String UNEQUIP_ITEM = "Ablegen";

    //Consumables
    Consumable MINOR_POTION = new HealingConsumable("Gesundheitstrank", "heilt den Spieler um %d Lebenspunkte", 5 , AssetIds.HEALTH_POTION);
    Consumable BREAD = new HealingConsumable("Brot", "heilt den Spieler um %d Lebenspunkte", 1 , AssetIds.BREAD);
    Consumable CHEESE = new HealingConsumable("Käse", "heilt den Spieler um %d Lebenspunkte", 1 , AssetIds.CHEESE);

    //Artifacts
    Artifact NECKLACE = new ExtraSoulsArtifact("Halskette", "Wenn sie ausgerüstet ist, erhält der Spieler 2% extra Souls", 2, AssetIds.NECKLACE);
    Artifact BRACELET = new ExtraSoulsArtifact("Armband", "Wenn sie ausgerüstet ist, erhält der Spieler 1% extra Souls", 1, AssetIds.BRACELET);
    Artifact RING = new ExtraSoulsArtifact("Ring", "Wenn sie ausgerüstet ist, erhält der Spieler 1% extra Souls", 1, AssetIds.RING);
    Artifact HELMET = new DefenseArtifact("Helm", "Wenn sie ausgerüstet ist, erhält der Spieler +1 Verteidigungspunkte", 1, AssetIds.HELMET);
    Artifact SHIELD = new DefenseArtifact("Schild", "Wenn sie ausgerüstet ist, erhält der Spieler +1 Verteidigungspunkte", 1, AssetIds.SHIELD);

    //Weapon
    Weapon SWORD = new Weapon("Schwert", "Wenn sie ausgerüstet ist, erhält der Spieler +3 Angriff", AssetIds.SWORD, 3, 1);
    Weapon BOW = new Weapon("Bogen", "Wenn sie ausgerüstet ist, erhält der Spieler +2 Angriff", AssetIds.BOW, 2, 3);
    Weapon CROSSBOW = new Weapon("Armageddon", "Wenn sie ausgerüstet ist, erhält der Spieler +4 Angriff", AssetIds.CROSSBOW, 3, 2);
    Weapon AXE = new Weapon("Axt", "Wenn sie ausgerüstet ist, erhält der Spieler +3 Angriff", AssetIds.AXE, 3, 1);
    Weapon WAND = new Weapon("Zauberstab", "Wenn sie ausgerüstet ist, erhält der Spieler +2 Angriff", AssetIds.WAND, 2, 2);
    Weapon CLAW = new Weapon("Klaue", "Wenn sie ausgerüstet ist, erhält der Spieler +1 Angriff", AssetIds.CLAW, 1, 1);
}
