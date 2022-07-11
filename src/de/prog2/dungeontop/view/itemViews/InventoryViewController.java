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
    private Button returnButton;
    @FXML
    GridPane weaponSlot;
    @FXML
    GridPane artifactSlot1;
    @FXML
    GridPane artifactSlot2;


    public GridPane getGridPane()
    {
        return gridPane;
    }

    public GridPane getWeaponSlot()
    {
        return weaponSlot;
    }

    public GridPane getArtifactSlot1()
    {
        return artifactSlot1;
    }

    public GridPane getArtifactSlot2()
    {
        return artifactSlot2;
    }

    @FXML
    private void onReturnButtonClicked()
    {
        DungeonTop.getStage().setScene(HellView.getCurrHellView());
    }
}
