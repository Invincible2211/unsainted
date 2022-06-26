package de.prog2.dungeontop.view;

import de.prog2.dungeontop.DungeonTop;
import de.prog2.dungeontop.resources.ViewStrings;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class MainMenueController {

    /*----------------------------------------------METHODEN----------------------------------------------------------*/

    /**
     * Dies ist die Eventmethode, welche ausgefuehrt wird, wenn auf den Start-Button gedrueckt wird.
     * Das Spiel wird gestartet.
     */
    @FXML
    private void onGameStartButtonPressed() throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(DungeonTop.class.getClassLoader().getResourceAsStream(ViewStrings.SELECT_HERO_FXML));
        Scene scene = new Scene(root);
        DungeonTop.getStage().setScene(scene);
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
