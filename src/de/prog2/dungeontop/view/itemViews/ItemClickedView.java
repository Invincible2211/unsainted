package de.prog2.dungeontop.view.itemViews;

import de.prog2.dungeontop.DungeonTop;
import de.prog2.dungeontop.control.controller.InventoryViewController;
import de.prog2.dungeontop.model.items.*;
import de.prog2.dungeontop.resources.views.ViewStrings;
import de.prog2.dungeontop.utils.GlobalLogger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;

import static de.prog2.dungeontop.DungeonTop.getStage;


public class ItemClickedView
{
    @FXML
    private Text itemDescription;
    @FXML
    private ImageView itemImage;
    @FXML
    private Text price;
    @FXML
    private javafx.scene.control.Button equipButton;
    private Item item;

    /**
     * Hides the item popup-view.
     */
    public void onReturnButtonClicked()
    {
        ItemView.hideStage();
    }

    /**
     * Handles the equip button click.
     */
    public void onEquipButtonClicked() throws IOException
    {
        Equippable item = null;
        try
        {
            item = (Equippable) getItem();
        }
        catch (ClassCastException e)
        {
            GlobalLogger.warning(e.getMessage());
        }

        if(item == null)
        {
            getItem().equip();
        }
        else if(item.isEquipped())
        {
            item.unequip();
        }
        else
        {
            item.equip();
        }
        reloadInventory();
    }

    /**
     * Reloads the inventory view.
     */
    private void reloadInventory() throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(DungeonTop.class.getClassLoader().getResourceAsStream(ViewStrings.INVENTORY_FXML));
        InventoryViewController.initInventory(fxmlLoader.getController());
        Scene scene = new Scene(root);
        getStage().setScene(scene);
        // Hide the popup stage.
        onReturnButtonClicked();
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

    public Text getPrice()
    {
        return price;
    }

    public void setItem(Item item)
    {
        this.item = item;
    }

    public Button getEquipButton()
    {
        return equipButton;
    }
}
