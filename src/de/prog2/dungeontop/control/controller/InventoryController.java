package de.prog2.dungeontop.control.controller;

import de.prog2.dungeontop.model.items.Item;
import de.prog2.dungeontop.view.itemViews.InventoryViewController;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

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
     * Equip artifact to slot 1.
     * @param inventoryView The inventory view.
     * @param items A List of one item (the artifact).
     */
    public static void equipArtifact1(InventoryViewController inventoryView, List<Item> items)
    {
        int columns = 0, rows = 0;
        for (Item item : items)
        {
            Node artifactView = ItemViewController.getEquippedItemView(item);
            inventoryView.getArtifactSlot1().add(artifactView, columns, rows);
        }
    }

    /**
     * Equip artifact to slot 2.
     * @param inventoryView The inventory view.
     * @param items A List of one item (the artifact).
     */
    public static void equipArtifact2(InventoryViewController inventoryView, List<Item> items)
    {
        int columns = 0, rows = 0;
        for (Item item : items)
        {
            Node artifactView = ItemViewController.getEquippedItemView(item);
            inventoryView.getArtifactSlot2().add(artifactView, columns, rows);
        }
    }


}
