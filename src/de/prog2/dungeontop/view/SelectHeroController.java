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
    private void onHero1ButtonEntered()
    {
        heroText1.setVisible(true);
    }
    @FXML
    private void onHero2ButtonEntered()
    {
        heroText2.setVisible(true);
    }
    @FXML
    private void onHero3ButtonEntered()
    {
        heroText3.setVisible(true);
    }

    @FXML
    private void onHero1ButtonExit()
    {
        heroText1.setVisible(false);
    }

    @FXML
    private void onHero2ButtonExit()
    {
        heroText2.setVisible(false);
    }
    @FXML
    private void onHero3ButtonExit()
    {
        heroText3.setVisible(false);
    }

    @FXML
    private void onHero1ButtonClicked()
    {

    }

    @FXML
    private void onHero2ButtonClicked()
    {


    }

    @FXML
    private void onHero3ButtonClicked()
    {


    }

    private void onConfirmButtonClicked()
    {

    }

    @FXML
    private void onReturnButtonClicked()
    {

    }

}
