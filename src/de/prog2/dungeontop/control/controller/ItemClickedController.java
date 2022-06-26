package de.prog2.dungeontop.control.controller;

import de.prog2.dungeontop.DungeonTop;
import de.prog2.dungeontop.control.manager.AssetsManager;
import de.prog2.dungeontop.model.items.Item;
import de.prog2.dungeontop.view.ItemClicked;
import de.prog2.dungeontop.view.ItemView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

public class ItemClickedController
{
    public static Node getItemClicked(Item item)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            Node itemClicked = null;

            itemClicked = loader.load(DungeonTop.class.getClassLoader().getResourceAsStream("view/itemView.fxml"));

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

    /**
     * Add item to the pop-up view.
     * @param controller an instance of ItemView, the controller of itemView.fxml
     *
     */
    private static void fillItem(Item item, ItemClicked controller)
    {
        controller.getItemImage().imageProperty().setValue(AssetsManager.getImageByAssetId(item.getAssetID()));
        controller.getItemDescription().setText(item.getDescription());
        controller.setItem(item);
    }
}
