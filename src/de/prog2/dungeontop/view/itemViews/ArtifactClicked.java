package de.prog2.dungeontop.view.itemViews;

import de.prog2.dungeontop.DungeonTop;
import de.prog2.dungeontop.control.controller.InventoryController;
import de.prog2.dungeontop.control.manager.PlayerManager;
import de.prog2.dungeontop.model.game.Player;
import de.prog2.dungeontop.model.items.Item;
import de.prog2.dungeontop.resources.ViewStrings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;

import static de.prog2.dungeontop.DungeonTop.getStage;


public class ArtifactClicked
{
    @FXML
    private Text itemDescription;
    @FXML
    private ImageView itemImage;
    @FXML
    private Text price;
    private Item item;

    public void onReturnButtonClicked()
    {
        ItemView.hideStage();
    }

    /**
     * Method to equip Artifact to slot 1
     */
    public void onEquipArtifactClicked() throws IOException
    {
        if (!PlayerManager.getInstance().getPlayer().getArtifactSlot1().isEmpty())
        {
            PlayerManager.getInstance().getPlayer().setHp(PlayerManager.getInstance().
                    getPlayerHp() - PlayerManager.getInstance().getPlayer().getArtifactSlot1().get(0).getValue());

            PlayerManager.getInstance().getPlayerInventory().addItem
                    (PlayerManager.getInstance().getPlayer().getArtifactSlot1().get(0));
            PlayerManager.getInstance().getPlayer().getArtifactSlot1().clear();
        }
        PlayerManager.getInstance().getPlayer().getArtifactSlot1().add(getItem());
        PlayerManager.getInstance().getPlayerInventory().removeItem(getItem());

        PlayerManager.getInstance().getPlayer().setHp(PlayerManager.getInstance().
                getPlayerHp() + PlayerManager.getInstance().getPlayer().getArtifactSlot1().get(0).getValue());

        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(DungeonTop.class.getClassLoader().getResourceAsStream(ViewStrings.INVENTORY_FXML));
        InventoryController.equipWeapon(fxmlLoader.getController(), PlayerManager.getInstance().getPlayer().getWeaponSlot());
        InventoryController.equipArtifact1(fxmlLoader.getController(), PlayerManager.getInstance().getPlayer().getArtifactSlot1());
        InventoryController.equipArtifact2(fxmlLoader.getController(), PlayerManager.getInstance().getPlayer().getArtifactSlot2());
        InventoryController.addItems(fxmlLoader.getController(), PlayerManager.getInstance().getPlayerInventory().getInventory());
        Scene scene = new Scene(root);
        getStage().setScene(scene);
        onReturnButtonClicked();
    }

    /**
     * Method to equip Artifact to slot 2
     */
    public void onEquipArtifactClicked2() throws IOException
    {
        if (!PlayerManager.getInstance().getPlayer().getArtifactSlot2().isEmpty())
        {
            PlayerManager.getInstance().getPlayer().setHp(PlayerManager.getInstance().
                    getPlayerHp() - PlayerManager.getInstance().getPlayer().getArtifactSlot2().get(0).getValue());

            PlayerManager.getInstance().getPlayerInventory().addItem
                    (PlayerManager.getInstance().getPlayer().getArtifactSlot2().get(0));
            PlayerManager.getInstance().getPlayer().getArtifactSlot2().clear();
        }
        PlayerManager.getInstance().getPlayer().getArtifactSlot2().add(getItem());
        PlayerManager.getInstance().getPlayerInventory().removeItem(getItem());


        PlayerManager.getInstance().getPlayer().setHp(PlayerManager.getInstance().
                getPlayerHp() + PlayerManager.getInstance().getPlayer().getArtifactSlot2().get(0).getValue());


        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(DungeonTop.class.getClassLoader().getResourceAsStream(ViewStrings.INVENTORY_FXML));
        InventoryController.equipWeapon(fxmlLoader.getController(), PlayerManager.getInstance().getPlayer().getWeaponSlot());
        InventoryController.equipArtifact1(fxmlLoader.getController(), PlayerManager.getInstance().getPlayer().getArtifactSlot1());
        InventoryController.equipArtifact2(fxmlLoader.getController(), PlayerManager.getInstance().getPlayer().getArtifactSlot2());
        InventoryController.addItems(fxmlLoader.getController(), PlayerManager.getInstance().getPlayerInventory().getInventory());
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
}
