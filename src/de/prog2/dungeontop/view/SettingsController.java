package de.prog2.dungeontop.view;

import de.prog2.dungeontop.DungeonTop;
import de.prog2.dungeontop.control.manager.AudioManager;
import de.prog2.dungeontop.resources.FilePaths;
import de.prog2.dungeontop.resources.ViewStrings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class SettingsController {

    @FXML
    private Slider volumeSlider;

    @FXML
    ChoiceBox<String> resolution;
    private static final Stage settingsStage = new Stage();

    public SettingsController(){

    }

    @FXML
    private void onSaveButtonPressed(){
        hideSettings();
    }

    @FXML
    void initialize(){
        volumeSlider.valueProperty().bindBidirectional(AudioManager.getInstance().getVolume());
    }

    public static void showSettings(){
        settingsStage.show();
    }

    public static void hideSettings(){
        settingsStage.hide();
    }

    public static void initStage(){
        final FXMLLoader fxmlLoader = new FXMLLoader();
        settingsStage.initModality(Modality.APPLICATION_MODAL);
        settingsStage.initOwner(DungeonTop.getStage());
        settingsStage.initStyle(StageStyle.UNDECORATED);
        AnchorPane rootPane = new AnchorPane();
        try {
            rootPane = fxmlLoader.load(DungeonTop.class.getClassLoader().getResourceAsStream(ViewStrings.SETTINGS_FXML));
        } catch (IOException e) {
            e.printStackTrace();
        }
        final Scene settingsScene = new Scene(rootPane);
        settingsStage.setScene(settingsScene);
    }

}
