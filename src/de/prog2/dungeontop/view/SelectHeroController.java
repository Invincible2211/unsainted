package de.prog2.dungeontop.view;

import de.prog2.dungeontop.DungeonTop;
import de.prog2.dungeontop.resources.ViewStrings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class SelectHeroController
{

    @FXML
    private VBox heroText1;

    @FXML
    private VBox heroText2;

    @FXML
    private VBox heroText3;

    @FXML
    private void onHero1ButtonClicked()
    {
        heroText1.setVisible(true);
        heroText2.setVisible(false);
        heroText3.setVisible(false);
    }

    @FXML
    private void onHero2ButtonClicked()
    {
        heroText2.setVisible(true);
        heroText1.setVisible(false);
        heroText3.setVisible(false);
    }

    @FXML
    private void onHero3ButtonClicked()
    {
        heroText3.setVisible(true);
        heroText1.setVisible(false);
        heroText2.setVisible(false);
    }

    @FXML
    private void onConfirmButtonClicked()
    {

    }

    @FXML
    private void onReturnButtonClicked()
    {

    }

}
