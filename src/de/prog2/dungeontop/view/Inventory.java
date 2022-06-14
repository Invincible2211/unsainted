package de.prog2.dungeontop.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;


public class Inventory
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

    }
}
