package de.prog2.dungeontop.control.controller;

import de.prog2.dungeontop.DungeonTop;
import de.prog2.dungeontop.control.manager.AssetsManager;
import de.prog2.dungeontop.model.items.Artifact;
import de.prog2.dungeontop.model.items.Equippable;
import de.prog2.dungeontop.model.items.Item;
import de.prog2.dungeontop.model.items.Weapon;
import de.prog2.dungeontop.resources.items.ItemConstants;
import de.prog2.dungeontop.resources.views.ViewStrings;
import de.prog2.dungeontop.utils.GlobalLogger;
import de.prog2.dungeontop.view.itemViews.ItemClickedView;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class ItemClickedViewController
{
    /**
     * Initialize the pop-up view for a consumable.
     * The overlay consists of buttons, images and texts
     * @param item clicked Item.
     */
    public static AnchorPane getItemClickedView(Item item)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            AnchorPane itemClicked = loader.load(DungeonTop.class.getClassLoader().getResourceAsStream(ViewStrings.ITEM_CLICKED_FXML));
            ItemClickedView controller = loader.getController();
            fillItem(item, controller);
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
    private static void fillItem(Item item, ItemClickedView controller)
    {
        controller.getItemImage().imageProperty().setValue(AssetsManager.getImageByAssetId(item.getAssetID()));
        controller.getItemDescription().setText(item.getDescription());
        controller.setItem(item);

        if (item instanceof Artifact || item instanceof Weapon)
        {
            if (item instanceof Artifact)
            {
                controller.getEquipButton().setText(ItemConstants.EQUIP_ARTIFACT);
            }
            else
            {
                controller.getEquipButton().setText(ItemConstants.EQUIP_WEAPON);
            }

            Equippable item1 = null;
            try
            {
                item1 = (Equippable) item;
            }
            catch (ClassCastException e)
            {
                GlobalLogger.warning(e.getMessage());
            }
            if(item1.isEquipped())
            {
                controller.getEquipButton().setText(ItemConstants.UNEQUIP_ITEM);
                controller.getDeleteButton().setVisible(false);
            }
        }
    }
}
