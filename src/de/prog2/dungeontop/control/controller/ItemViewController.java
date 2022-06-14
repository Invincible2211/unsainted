package de.prog2.dungeontop.control.controller;

import de.prog2.dungeontop.DungeonTop;
import de.prog2.dungeontop.control.manager.AssetsManager;
import de.prog2.dungeontop.model.items.Item;
import de.prog2.dungeontop.resources.AssetIds;
import de.prog2.dungeontop.view.ItemView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

public class ItemViewController
{
    /**
     * create an ItemView for an item.
     *
     */
    public static Node getItemView(Item item)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            Node itemView = null;

                itemView = loader.load(DungeonTop.class.getClassLoader().getResourceAsStream("view/itemView.fxml"));

            ItemView controller = loader.getController();
            fillItemViewWithData(item, controller);
            return itemView;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Add cards to the shop view.
     * @param controller an instance of ItemView, the controller of itemView.fxml
     *
     */
    private static void fillItemViewWithData(Item item, ItemView controller)
    {
        controller.getItemImage().imageProperty().setValue(AssetsManager.getImageByAssetId(item.getAssetID()));
        controller.getItemName().setText(item.getName());
        controller.setItem(item);
    }
}
