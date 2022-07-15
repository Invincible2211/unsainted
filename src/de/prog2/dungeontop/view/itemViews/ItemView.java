package de.prog2.dungeontop.view.itemViews;

import de.prog2.dungeontop.control.controller.ItemClickedController;
import de.prog2.dungeontop.model.items.Item;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class ItemView
{
    @FXML
    private ImageView itemImage;
    @FXML
    private Label itemName;
    private Item item;
    private static Stage stage = new Stage();

    /**
     * init different pop up windows for different types of items.
     *
     */
    public void itemButtonClicked()
    {
        AnchorPane rootPane;
        Item selectedItem = getItem();
        rootPane = ItemClickedController.getItemClicked(selectedItem);
        final Scene scene = new Scene(rootPane);
        stage.setScene(scene);
        stage.show();
    }

    public static void hideStage()
    {
        stage.hide();
    }

    //Get- und Setters
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
