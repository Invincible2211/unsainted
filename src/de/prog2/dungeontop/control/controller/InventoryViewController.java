package de.prog2.dungeontop.control.controller;

import de.prog2.dungeontop.model.items.Artifact;
import de.prog2.dungeontop.model.items.Inventory;
import de.prog2.dungeontop.model.items.Item;
import de.prog2.dungeontop.model.items.Weapon;
import de.prog2.dungeontop.view.itemViews.InventoryView;
import javafx.scene.Node;

import java.util.List;

public class InventoryViewController
{
    public static void initInventory(InventoryView inventoryView, Inventory inventory)
    {
        inventoryView.setHPText();
        int columns = 0, rows = 0;
        for (Item item : inventory.getInventory())
        {
            Node itemView = ItemViewController.getItemView(item);
            inventoryView.getGridPane().add(itemView, columns, rows);
            columns++;
            if (columns == 7)
            {
                columns = 0;
                rows++;
            }
        }
    }


    /**
     * Equip weapon.
     * @param inventoryView The inventory view.
     */
    public static void equipWeapon(InventoryView inventoryView, Weapon weapon)
    {
        Node weaponView = ItemViewController.getEquippedItemView(weapon);
        inventoryView.getWeaponSlot().add(weaponView, 0, 0);
    }

    /**
     * Equip artifact to the artifact slots.
     * @param inventoryView The inventory view.
     */
    public static void equipArtifact(InventoryView inventoryView, List<Artifact> artifacts)
    {
        int columns = 0, rows = 0;
        for (Artifact artifact : artifacts)
        {
            Node artifactView = ItemViewController.getEquippedItemView(artifact);
            inventoryView.getArtifactSlots().add(artifactView, columns, rows);
            ++rows;
        }
    }
}
