package de.prog2.dungeontop.view;

import de.prog2.dungeontop.DungeonTop;
import de.prog2.dungeontop.control.manager.GameManager;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;


public class InventoryView
{
    @FXML
    GridPane gridPane;
    @FXML
    private Button returnButton;

    public GridPane getGridPane()
    {
        return gridPane;
    }

    @FXML
    private void onReturnButtonClicked()
    {
        HellView view = new HellView();
        Scene scene = view.initHellView(GameManager.getInstance().getGameWorld().getCurrentHell());
        DungeonTop.getStage().setScene(scene);
    }
}
