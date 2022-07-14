package de.prog2.dungeontop.control.controller;

import de.prog2.dungeontop.model.items.Item;
import de.prog2.dungeontop.view.itemViews.InventoryViewController;
import javafx.scene.Node;

import java.util.List;

public class InventoryController
{
    /**
     * Add items to the inventory.
     * @param inventoryView The inventory view.
     * @param items A List of items to add.
     */
    public static void addItems(InventoryViewController inventoryView, List<Item> items)
    {
        int columns = 0, rows = 0;
        for (Item item : items)
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
    public static boolean addItem(Item item)
    {
        // TODO: Implement
        return true;
    }


    // TODO Move to InvetntoryViewController
    /**
     * Equip weapon.
     * @param inventoryView The inventory view.
     * @param items A List of one item (the weapon).
     */
    public static void equipWeapon(InventoryViewController inventoryView, List<Item> items)
    {
        int columns = 0, rows = 0;
        for (Item item : items)
        {
            Node weaponView = ItemViewController.getEquippedItemView(item);
            inventoryView.getWeaponSlot().add(weaponView, columns, rows);
        }
    }

    /**
     * Equip artifact to the artifact slots.
     * @param inventoryView The inventory view.
     * @param items A List of two items (the artifacts).
     */
    public static void equipArtifact(InventoryViewController inventoryView, List<Item> items)
    {
        int columns = 0, rows = 0;
        for (Item item : items)
        {
            Node artifactView = ItemViewController.getEquippedItemView(item);
            inventoryView.getArtifactSlots().add(artifactView, columns, rows);
            ++rows;
        }
    }
}
