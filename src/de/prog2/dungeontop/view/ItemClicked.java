package de.prog2.dungeontop.view;

import de.prog2.dungeontop.model.items.Item;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class ItemClicked
{
    @FXML
    private Text itemDescription;
    @FXML
    private ImageView itemImage;
    private Item item;

    public void onReturnButtonClicked()
    {
        ItemView.hideStage();
    }

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
