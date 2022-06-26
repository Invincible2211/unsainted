package de.prog2.dungeontop.view;

import de.prog2.dungeontop.control.network.IPChecker;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LobbyController {

    @FXML
    private Label labelIP;

    @FXML
    private TextField partnerIP;

    @FXML
    private Label labelIPLocal;

    @FXML
    private void onOnlinebuttonAction(){
        de.prog2.dungeontop.control.network.LobbyController controller = new de.prog2.dungeontop.control.network.LobbyController(partnerIP.getText());
        controller.run();
    }

    @FXML
    void initialize(){
        labelIP.setText(IPChecker.getPublicIPAdress());
        labelIPLocal.setText(IPChecker.getLocalIPAdress());
    }

}
