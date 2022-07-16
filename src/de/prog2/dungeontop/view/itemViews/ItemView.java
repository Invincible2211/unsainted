package de.prog2.dungeontop.view.itemViews;

import de.prog2.dungeontop.control.controller.ItemClickedViewController;
import de.prog2.dungeontop.model.items.Item;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ItemView
{
    @FXML
    private ImageView itemImage;
    @FXML
    private Label itemName;
    private Item item;
    private static final Stage stage = new Stage();

    /**
     * init different pop up windows for different types of items.
     */
    public void itemButtonClicked()
    {
        initItemClicked();
        if (stage.getStyle() != StageStyle.TRANSPARENT)
        {
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setAlwaysOnTop(true);
        }
        stage.show();
    }

    public static void hideStage()
    {
        stage.hide();
    }

    public void initItemClicked()
    {
        AnchorPane itemClickedView = ItemClickedViewController.getItemClickedView(item);
        stage.setScene(new Scene(itemClickedView));
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
