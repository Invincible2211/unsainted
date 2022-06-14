package de.prog2.dungeontop.view;

import de.prog2.dungeontop.model.items.Item;
import javafx.scene.Node;

import java.util.List;

public class InventoryController
{
    public static void addItems(InventoryView inventoryView, List<Item> items)
    {
        int columns = 0, row = 0;
        for (Item item : items)
        {
            Node itemView = ItemViewController.getItemView(item);
            inventoryView.getGridPane().add(itemView, columns, row);
            columns++;
            if (columns == 5)
            {
                columns = 0;
                row++;
            }
        }
    }
}
