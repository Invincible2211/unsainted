package de.prog2.dungeontop.view;

import de.prog2.dungeontop.model.items.Item;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;


public class ItemView
{

    @FXML
    private ImageView itemImage;
    @FXML
    private Label itemName;
    private Item item;

    public Label getItemName()
    {
        return itemName;
    }

    public ImageView getItemImage()
    {
        return itemImage;
    }

    public Item getItem()
    {
        return item;
    }

    public void setItem(Item item)
    {
        this.item = item;
    }
}
