package de.prog2.dungeontop.view;

import de.prog2.dungeontop.DungeonTop;
import de.prog2.dungeontop.control.manager.AudioManager;
import de.prog2.dungeontop.control.network.NetController;
import de.prog2.dungeontop.model.world.actions.Action;
import de.prog2.dungeontop.resources.views.ViewStrings;
import de.prog2.dungeontop.utils.GlobalLogger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class NetworkController {

    private static final Stage networkStage = new Stage();

    @FXML
    private TextField partnerIP;

    @FXML
    private Label info;

    @FXML
    private void onConnectButtonPressed(){
        NetController.enable(partnerIP.getText());
        hideNetworkGUI();
    }

    public static void showNetworkGUI()
    {
        DungeonTop.getStage().getScene().getRoot().setEffect(new GaussianBlur());
        networkStage.show();
    }

    public static void hideNetworkGUI()
    {
        DungeonTop.getStage().getScene().getRoot().setEffect(null);
        networkStage.hide();
    }

    public static void initStage()
    {
        final FXMLLoader fxmlLoader = new FXMLLoader();
        networkStage.initModality(Modality.APPLICATION_MODAL);
        networkStage.initOwner(DungeonTop.getStage());
        networkStage.initStyle(StageStyle.TRANSPARENT);
        AnchorPane rootPane = new AnchorPane();
        try
        {
            rootPane = fxmlLoader.load(DungeonTop.class.getClassLoader().getResourceAsStream(ViewStrings.NETWORK_FXML));
        } catch (IOException e)
        {
            GlobalLogger.warning(e.getMessage());
        }
        final Scene settingsScene = new Scene(rootPane, Color.TRANSPARENT);
        for (Node n:
             rootPane.getChildren()) {
            if (n instanceof MenuBar){
                MenuBar menuBar = (MenuBar) n;
                Menu menu = menuBar.getMenus().get(1);
                menu.addEventHandler(ActionEvent.ANY, event -> AudioManager.getInstance().playSound(998, false));
                for (MenuItem item:
                        menu.getItems()) {
                    item.addEventHandler(ActionEvent.ANY, event -> AudioManager.getInstance().playSound(998, false));
                }

            }
        }
        networkStage.setScene(settingsScene);
    }

    @FXML
    private void openSettings(){
        SettingsController.showSettings();
    }

    @FXML
    private void onBackToMM(){
        hideNetworkGUI();
    }

}
