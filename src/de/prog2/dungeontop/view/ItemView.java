package de.prog2.dungeontop.view;

import de.prog2.dungeontop.model.items.Item;
import javafx.fxml.FXML;

import javax.swing.text.html.ImageView;
import java.awt.*;

public class ItemView
{
    @FXML
    Label itemName;

    @FXML
    ImageView itemImage;

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
