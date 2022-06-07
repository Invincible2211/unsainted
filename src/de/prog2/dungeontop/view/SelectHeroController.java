package de.prog2.dungeontop.view;

import de.prog2.dungeontop.DungeonTop;
import de.prog2.dungeontop.resources.ViewStrings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class SelectHeroController
{
    private final FXMLLoader fxmlLoader = new FXMLLoader();
    private Stage heroScreenStage = new Stage();

    public SelectHeroController()
    {
        heroScreenStage.initModality(Modality.APPLICATION_MODAL);
        heroScreenStage.initOwner(DungeonTop.getStage());
        heroScreenStage.initStyle(StageStyle.UNDECORATED);
        AnchorPane root = new AnchorPane();
        try {
            root = fxmlLoader.load(DungeonTop.class.getClassLoader().getResourceAsStream(ViewStrings.HERO_SCREEN_FXML));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene heroScreenScene = new Scene(root);
        heroScreenStage.setScene(heroScreenScene);
    }

    @FXML
    private void onHero1ButtonPressed()
    {

        heroScreenStage.show();
    }

    @FXML
    private void onHero2ButtonPressed()
    {

        heroScreenStage.show();
    }

    @FXML
    private void onHero3ButtonPressed()
    {

        heroScreenStage.show();
    }

    @FXML
    private void onHero4ButtonPressed()
    {

        heroScreenStage.show();
    }

    @FXML
    private void onReturnButtonPressed()
    {

    }

}
