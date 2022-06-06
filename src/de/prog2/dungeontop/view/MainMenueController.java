package de.prog2.dungeontop.view;

import de.prog2.dungeontop.DungeonTop;
import de.prog2.dungeontop.resources.ViewStrings;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MainMenueController {

    private final FXMLLoader fxmlLoader = new FXMLLoader();
    private Stage settingsStage = new Stage();

    public MainMenueController(){
        settingsStage.initModality(Modality.APPLICATION_MODAL);
        settingsStage.initOwner(DungeonTop.getStage());
        settingsStage.initStyle(StageStyle.UNDECORATED);
        AnchorPane rootPane = new AnchorPane();
        try {
            rootPane = fxmlLoader.load(DungeonTop.class.getClassLoader().getResourceAsStream(ViewStrings.SETTINGS_FXML));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene settingsScene = new Scene(rootPane);
        settingsStage.setScene(settingsScene);
    }

    @FXML
    private void onGameStartButtonPressed(){

    }

    @FXML
    private void onSettingsButtonPressed(){
        settingsStage.show();
    }

    @FXML
    private void onBackToWindowsButtonPressed(){
        Platform.exit();
    }

}
