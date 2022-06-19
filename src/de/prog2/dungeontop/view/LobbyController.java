package de.prog2.dungeontop.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class LobbyController {

    @FXML
    private Label infoText;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private void onOnlinebuttonAction(){
        new de.prog2.dungeontop.control.network.LobbyController(progressBar, infoText);
    }

}
