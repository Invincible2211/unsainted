package de.prog2.dungeontop.view;

import de.prog2.dungeontop.DungeonTop;
import de.prog2.dungeontop.control.network.NetController;
import de.prog2.dungeontop.control.network.NetManager;
import de.prog2.dungeontop.resources.ViewStrings;
import de.prog2.dungeontop.utils.GlobalLogger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
        networkStage.show();
    }

    public static void hideNetworkGUI()
    {
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
        networkStage.setScene(settingsScene);
    }

}
