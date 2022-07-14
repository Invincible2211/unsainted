package de.prog2.dungeontop.view.itemViews;

import de.prog2.dungeontop.DungeonTop;
import de.prog2.dungeontop.control.controller.InventoryController;
import de.prog2.dungeontop.control.controller.InventoryViewController;
import de.prog2.dungeontop.control.manager.PlayerManager;
import de.prog2.dungeontop.model.entities.Hero;
import de.prog2.dungeontop.model.items.*;
import de.prog2.dungeontop.resources.ViewStrings;
import de.prog2.dungeontop.utils.GlobalLogger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.awt.*;
import java.io.IOException;

import static de.prog2.dungeontop.DungeonTop.getStage;


public class ItemClicked
{
    @FXML
    private Text itemDescription;
    @FXML
    private ImageView itemImage;
    @FXML
    private Text price;
    @FXML
    private javafx.scene.control.Button useButton;
    private Item item;

    public void onReturnButtonClicked()
    {
        ItemView.hideStage();
    }

    /**
     * Method for when player uses Consumables or equip Weapon/Artifact
     */
    public void onUseItemButtonClicked() throws IOException
    {
        Equipable item = null;
        try
        {
            item = (Equipable) getItem();
        }
        catch (ClassCastException e)
        {
            GlobalLogger.warning(e.getMessage());
        }

        if(item == null)
        {
            getItem().equip();
            reloadInventory();
            return;
        }
        if(item.isEquipped())
        {
            item.unequip();
        }
        else
        {
            item.equip();
        }
        reloadInventory();
    }

    private void reloadInventory() throws IOException
    {
        Hero hero = PlayerManager.getInstance().getPlayerHero();

        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(DungeonTop.class.getClassLoader().getResourceAsStream(ViewStrings.INVENTORY_FXML));
        if(hero.getWeapon() != null)
        {
            InventoryViewController.equipWeapon(fxmlLoader.getController(), hero.getWeapon());
        }
        InventoryViewController.equipArtifact(fxmlLoader.getController(), hero.getArtifacts());
        InventoryViewController.initInventory(fxmlLoader.getController(), PlayerManager.getInstance().getPlayerInventory());
        Scene scene = new Scene(root);
        getStage().setScene(scene);
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

    public Button getUseButton()
    {
        return useButton;
    }
}
