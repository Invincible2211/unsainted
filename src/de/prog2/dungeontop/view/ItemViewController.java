package de.prog2.dungeontop.view;

import de.prog2.dungeontop.DungeonTop;
import de.prog2.dungeontop.control.manager.AssetsManager;
import de.prog2.dungeontop.model.items.Item;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

public class ItemViewController
{
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

    private static void fillItemViewWithData(Item item, ItemView controller)
    {
        controller.getItemImage().imageProperty().setValue(AssetsManager.getImageByAssetId(66));
        controller.getItemName().setText(item.getName());
        controller.setItem(item);
    }
}
