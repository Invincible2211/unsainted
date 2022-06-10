package de.prog2.dungeontop.view;

import javafx.application.Platform;
import javafx.fxml.FXML;

public class MainMenueController {

    @FXML
    private void onGameStartButtonPressed(){

    }

    @FXML
    private void onSettingsButtonPressed(){
        SettingsController.showSettings();
    }

    @FXML
    private void onBackToWindowsButtonPressed(){
        Platform.exit();
    }

}
