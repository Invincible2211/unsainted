package de.prog2.dungeontop.view;

import javafx.application.Platform;
import javafx.fxml.FXML;

public class MainMenueController {

    /*----------------------------------------------METHODEN----------------------------------------------------------*/

    /**
     * Dies ist die Eventmethode, welche ausgefuehrt wird, wenn auf den Start-Button gedrueckt wird.
     * Das Spiel wird gestartet.
     */
    @FXML
    private void onGameStartButtonPressed()
    {

    }

    /**
     * Dies ist die Eventmethode, welche ausgefuehrt wird, wenn auf den Settings-Button gedrueckt wird.
     * Die Einstellungen werden eingeblendet.
     */
    @FXML
    private void onSettingsButtonPressed()
    {
        SettingsController.showSettings();
    }

    /**
     * Dies ist die Eventmethode, welche ausgefuehrt wird, wenn auf den BackToWindows-Button gedrueckt wird.
     * Das Spiel wird geschlossen.
     */
    @FXML
    private void onBackToWindowsButtonPressed()
    {
        Platform.exit();
    }

}
