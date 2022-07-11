package de.prog2.dungeontop.control.controller;

import de.prog2.dungeontop.DungeonTop;
import de.prog2.dungeontop.control.manager.AssetsManager;
import de.prog2.dungeontop.model.items.Item;
import de.prog2.dungeontop.resources.ViewStrings;
import de.prog2.dungeontop.view.itemViews.ArtifactClicked;
import de.prog2.dungeontop.view.itemViews.ItemClicked;
import de.prog2.dungeontop.view.itemViews.WeaponClicked;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class ItemClickedController
{
    public static AnchorPane getItemClicked(Item item)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            AnchorPane itemClicked = loader.load(DungeonTop.class.getClassLoader().getResourceAsStream(ViewStrings.ITEM_CLICKED_FXML));
            ItemClicked controller = loader.getController();
            fillItem(item, controller);
            return itemClicked;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static AnchorPane getWeaponClicked(Item item)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            AnchorPane itemClicked = loader.load(DungeonTop.class.getClassLoader().getResourceAsStream(ViewStrings.WEAPON_CLICKED_FXML));
            WeaponClicked controller = loader.getController();
            fillWeapon(item, controller);
            return itemClicked;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static AnchorPane getArtifactClicked(Item item)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            AnchorPane itemClicked = loader.load(DungeonTop.class.getClassLoader().getResourceAsStream(ViewStrings.ARTIFACT_CLICKED_FXML));
            ArtifactClicked controller = loader.getController();
            fillArtifact(item, controller);
            return itemClicked;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Add item to the pop-up view.
     *
     */
    private static void fillItem(Item item, ItemClicked controller)
    {
        controller.getItemImage().imageProperty().setValue(AssetsManager.getImageByAssetId(item.getAssetID()));
        controller.getItemDescription().setText(item.getDescription());
        controller.getPrice().setText(String.valueOf(item.getPrice()));
        controller.setItem(item);
    }

    /**
     * Add weapon to the pop-up view.
     *
     */
    private static void fillWeapon(Item item, WeaponClicked controller)
    {
        controller.getItemImage().imageProperty().setValue(AssetsManager.getImageByAssetId(item.getAssetID()));
        controller.getItemDescription().setText(item.getDescription());
        controller.getPrice().setText(String.valueOf(item.getPrice()));
        controller.setItem(item);
    }

    /**
     * Add artifact to the pop-up view.
     *
     */
    private static void fillArtifact(Item item, ArtifactClicked controller)
    {
        controller.getItemImage().imageProperty().setValue(AssetsManager.getImageByAssetId(item.getAssetID()));
        controller.getItemDescription().setText(item.getDescription());
        controller.getPrice().setText(String.valueOf(item.getPrice()));
        controller.setItem(item);
    }
}
