package de.prog2.dungeontop.view.itemViews;

import de.prog2.dungeontop.DungeonTop;
import de.prog2.dungeontop.control.manager.GameManager;
import de.prog2.dungeontop.view.HellView;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;


public class InventoryViewController
{
    @FXML
    GridPane gridPane;
    @FXML
    GridPane weaponSlot;
    @FXML
    GridPane artifactSlots;

    public GridPane getGridPane()
    {
        return gridPane;
    }

    public GridPane getWeaponSlot()
    {
        return weaponSlot;
    }

    public GridPane getArtifactSlots()
    {
        return artifactSlots;
    }

    @FXML
    private void onReturnButtonClicked()
    {
        DungeonTop.getStage().setScene(HellView.getCurrHellView());
    }
}
