package de.prog2.dungeontop.view;

import de.prog2.dungeontop.model.items.Item;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class ItemClicked
{
    @FXML
    private Button returnButton;
    @FXML
    private Button useItemButton;
    @FXML
    private Text itemDescription;
    @FXML
    private ImageView itemImage;

    private Item item;

    //Get- und Setters
    public Text getItemDescription()
    {
        return itemDescription;
    }

    public Item getItem()
    {
        return item;
    }

    public ImageView getItemImage()
    {
        return itemImage;
    }

    public void setItem(Item item)
    {
        this.item = item;
    }
}
