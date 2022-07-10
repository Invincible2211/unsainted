package de.prog2.dungeontop.view;

import de.prog2.dungeontop.DungeonTop;
import de.prog2.dungeontop.control.controller.InventoryController;
import de.prog2.dungeontop.control.manager.PlayerManager;
import de.prog2.dungeontop.model.items.Item;
import de.prog2.dungeontop.model.items.ItemType;
import de.prog2.dungeontop.resources.ViewStrings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;

import static de.prog2.dungeontop.DungeonTop.getStage;


public class ItemClicked
{
    @FXML
    private Button button;
    @FXML
    private Text itemDescription;
    @FXML
    private ImageView itemImage;
    private Item item;

    public void onReturnButtonClicked()
    {
        ItemView.hideStage();
    }

    public void onUseItemButtonClicked() throws IOException {
        PlayerManager.getInstance().addHp(getItem().getValue());
        PlayerManager.getInstance().getPlayerInventory().removeItem(getItem());
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(DungeonTop.class.getClassLoader().getResourceAsStream(ViewStrings.INVENTORY_FXML));
        InventoryController.addItems(fxmlLoader.getController(), PlayerManager.getInstance().getPlayerInventory().getInventory());
        Scene scene = new Scene(root);
        getStage().setScene(scene);
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

    public Button getButton()
    {
        return button;
    }

    public void setItem(Item item)
    {
        this.item = item;
    }
}
