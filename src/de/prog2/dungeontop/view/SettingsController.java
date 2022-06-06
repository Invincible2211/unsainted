package de.prog2.dungeontop.view;

import de.prog2.dungeontop.control.manager.AudioManager;
import javafx.beans.binding.DoubleBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

public class SettingsController {

    @FXML
    private Slider volumeSlider;

    @FXML
    private void onCancelButtonPressed(ActionEvent event){
        Node source = (Node) event.getSource();
        source.getScene().getWindow().hide();
    }

    @FXML
    private void onSaveButtonPressed(ActionEvent event){
        Node source = (Node) event.getSource();
        source.getScene().getWindow().hide();
    }

    @FXML
    void initialize(){
        volumeSlider.valueProperty().bindBidirectional(AudioManager.getInstance().getVolume());
    }

}
