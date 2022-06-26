package de.prog2.dungeontop.control.controller;

import de.prog2.dungeontop.control.controller.ItemViewController;
import de.prog2.dungeontop.model.items.Item;
import de.prog2.dungeontop.view.InventoryView;
import javafx.scene.Node;

import java.util.List;

public class InventoryController
{
    /**
     * Add items to the inventory.
     * @param inventoryView The inventory view.
     * @param items A List of items to add.
     */
    public static void addItems(InventoryView inventoryView, List<Item> items)
    {
        int columns = 0, row = 0;
        for (Item item : items)
        {
            Node itemView = ItemViewController.getItemView(item);
            inventoryView.getGridPane().add(itemView, columns, row);
            columns++;
            if (columns == 7)
            {
                columns = 0;
                row++;
            }
        }
    }
}
