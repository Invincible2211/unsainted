package de.prog2.dungeontop.control.controller;

import de.prog2.dungeontop.control.manager.PlayerManager;
import de.prog2.dungeontop.model.entities.Hero;
import de.prog2.dungeontop.model.items.Artifact;
import de.prog2.dungeontop.model.items.Inventory;
import de.prog2.dungeontop.model.items.Item;
import de.prog2.dungeontop.model.items.Weapon;
import de.prog2.dungeontop.view.itemViews.InventoryView;
import javafx.scene.Node;

import java.util.List;

public class InventoryViewController
{
    /**
     * Creates an InventoryView for an inventory and fill it with the items.
     * @param inventoryView the fxmlController of the InventoryView.
     */
    public static void initInventory(InventoryView inventoryView)
    {
        Inventory inventory = PlayerManager.getInstance().getPlayerInventory();
        inventoryView.setHPText();
        // add items to the inventoryView
        int columns = 0, rows = 0;
        for (Item item : inventory.getItems())
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
        // add equipped items to the inventoryView
        Hero hero = PlayerManager.getInstance().getPlayerHero();
        if (hero.getWeapon() != null)
        {
            equipWeapon(inventoryView, hero.getWeapon());
        }
        equipArtifact(inventoryView, hero.getArtifacts());
    }

    /**
     * Equip weapon.
     * @param inventoryView the fxmlController of the InventoryView.
     */
    public static void equipWeapon(InventoryView inventoryView, Weapon weapon)
    {
        Node weaponView = ItemViewController.getEquippedItemView(weapon);
        inventoryView.getWeaponSlot().add(weaponView, 0, 0);
    }

    /**
     * Equip artifact to the artifact slots.
     * @param inventoryView the fxmlController of the InventoryView.
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
