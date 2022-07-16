package de.prog2.dungeontop.view;

import de.prog2.dungeontop.DungeonTop;
import de.prog2.dungeontop.control.file.GameSaveFileWriter;
import de.prog2.dungeontop.control.manager.AudioManager;
import de.prog2.dungeontop.control.manager.GameManager;
import de.prog2.dungeontop.control.network.IPChecker;
import de.prog2.dungeontop.resources.*;
import de.prog2.dungeontop.resources.NetworkingConstants;
import de.prog2.dungeontop.resources.views.ViewStrings;
import de.prog2.dungeontop.utils.GlobalLogger;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.Effect;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;

public class SettingsController {

    /*----------------------------------------------ATTRIBUTE---------------------------------------------------------*/

    @FXML
    private Slider volumeSlider;

    @FXML
    ChoiceBox<String> resolution;
    private static final Stage settingsStage = new Stage();

    @FXML
    private Label ipLabel;

    @FXML
    AnchorPane root;

    private double volume = AudioDefaultValues.DEFAULT_VOLUME;

    /*----------------------------------------------METHODEN----------------------------------------------------------*/

    /**
     * Dies ist die Eventmethode, welche ausgefuehrt wird, wenn auf den Save-Button gedrueckt wird.
     * Die Einstellungen werden ausgeblendet.
     */
    @FXML
    private void onSaveButtonPressed()
    {
        hideSettings();
    }

    @FXML
    private void onExitButtonPressed()
    {
        GameSaveFileWriter.getInstance().saveGame(GameManager.getInstance().getSaveGame());
        System.exit(0);
    }

    @FXML
    private void onMMButtonPressed()
    {
        hideSettings();
        Scene scene = new Scene(new AnchorPane());
        try {
            scene = new Scene(new FXMLLoader().load(DungeonTop.class.getClassLoader().getResourceAsStream(ViewStrings.MAIN_MENUE_FXML)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        HellView.stopHellViewBgMusic();
        GameSaveFileWriter.getInstance().saveGame(GameManager.getInstance().getSaveGame());

        DungeonTop.getStage().setScene(scene);
    }

    /**
     * Nach der Instanziierung der GUI-Komponenten wird der Volume-Slider an die Volume-Property des AudioManagers gebindet.
     */
    @FXML
    void initialize()
    {
        volumeSlider.valueProperty().bindBidirectional(AudioManager.getInstance().getVolume());
        ipLabel.setText(String.format(NetworkingConstants.SETTINGS_IP_LABEL, IPChecker.getLocalIPAdress()));
        for (Node n:
                root.getChildren()) {
            if (n instanceof Button || n instanceof Label){
                n.setOnMouseEntered(event -> AudioManager.getInstance().playSound(999, false));
                n.setOnMousePressed(event -> AudioManager.getInstance().playSound(998, false));
            }
        }
    }

    /**
     * Diese Methode blendet die Settings-Stage ein.
     */
    public static void showSettings()
    {
        DungeonTop.getStage().getScene().getRoot().setEffect(new GaussianBlur());
        settingsStage.show();
    }

    /**
     * Diese Methode blendet die Settings-Stage aus.
     */
    public static void hideSettings()
    {
        DungeonTop.getStage().getScene().getRoot().setEffect(null);
        settingsStage.hide();
    }

    /**
     * Diese Methode erzeugt die Stage aus der FXML-Datei der Settings
     */
    public static void initStage()
    {
        final FXMLLoader fxmlLoader = new FXMLLoader();
        settingsStage.initModality(Modality.APPLICATION_MODAL);
        settingsStage.initOwner(DungeonTop.getStage());
        settingsStage.initStyle(StageStyle.TRANSPARENT);
        AnchorPane rootPane = new AnchorPane();
        try
        {
            rootPane = fxmlLoader.load(DungeonTop.class.getClassLoader().getResourceAsStream(ViewStrings.SETTINGS_FXML));
        } catch (IOException e)
        {
            GlobalLogger.warning(e.getMessage());
        }
        final Scene settingsScene = new Scene(rootPane, Color.TRANSPARENT);
        settingsStage.setScene(settingsScene);
    }

    @FXML
    private void onIPLabelClicked(){
        String content = IPChecker.getLocalIPAdress();
        StringSelection selection = new StringSelection(content);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
    }

    @FXML
    private void mute(){
        if (volumeSlider.getValue()!=0){
            volume = volumeSlider.getValue();
            volumeSlider.setValue(0);
        } else {
            volumeSlider.setValue(volume);
        }

    }

}
