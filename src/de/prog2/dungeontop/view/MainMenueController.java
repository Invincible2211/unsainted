package de.prog2.dungeontop.view;

import de.prog2.dungeontop.DungeonTop;
import de.prog2.dungeontop.control.file.GameSaveFileWriter;
import de.prog2.dungeontop.control.manager.AudioManager;
import de.prog2.dungeontop.control.manager.GameManager;
import de.prog2.dungeontop.control.network.NetController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.UUID;

public class MainMenueController {

    private static UUID mainMenueSoundUUID;

    @FXML
    private AnchorPane root;

    /*----------------------------------------------METHODEN----------------------------------------------------------*/

    /**
     * Dies ist die Eventmethode, welche ausgefuehrt wird, wenn auf den Start-Button gedrueckt wird.
     * Das Spiel wird gestartet.
     */
    @FXML
    private void onGameStartButtonPressed() throws IOException
    {
        NetController.enable(null);
        GameManager.getInstance().startGame();
        AudioManager.getInstance().playSound(996,false);
    }

    /**
     * Dies ist die Eventmethode, welche ausgefuehrt wird, wenn auf den Shop-Button gedrueckt wird.
     * Der Shop wird geoeffnet.
     */
    @FXML
    private void onOpenShopButtonPressed ()
    {
        NpcRoomView view = new NpcRoomView(null);
        DungeonTop.getStage().setScene(view.getNpcRoomView());
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
        GameSaveFileWriter.getInstance().saveGame(GameManager.getInstance().getSaveGame());
        System.exit(0);
    }

    @FXML
    private void onPlayAsDungeonMasterButtonPressed(){
        GameManager.getInstance().setDM();
        NetworkController.showNetworkGUI();
        AudioManager.getInstance().changeClipVolumeWhilePlayingSound(995,mainMenueSoundUUID,10);
    }

    @FXML
    public void initialize(){
        Platform.runLater(() ->
        mainMenueSoundUUID = AudioManager.getInstance().playSoundOnScene(990,root.getScene(),true));
        for (Node n:
             root.getChildren()) {
            if (n instanceof Button){
                n.setOnMouseEntered(event -> AudioManager.getInstance().playSound(999, false));
                n.setOnMousePressed(event -> AudioManager.getInstance().playSound(998, false));
            }
        }
    }

    public static void addMenuebar(){
        Scene scene = DungeonTop.getStage().getScene();
        Parent root = scene.getRoot();
        MenuBar menuBar = new MenuBar();
        menuBar.setLayoutX(0);
        menuBar.setLayoutY(0);
        menuBar.setPrefHeight(10);
        menuBar.setPrefWidth(1920);
        Menu game = new Menu("Unsainted");
        Menu settings = new Menu("Einstellungen");
        Menu exit = new Menu("Spiel verlassen");
        MenuItem hero = new MenuItem("Als Held Spielen");
        MenuItem dm = new MenuItem("Als Dungeonmaster spielen");
        MenuItem settingsItem = new MenuItem("Einstllungen öffnen");
        MenuItem backToWindows = new MenuItem("Zurück zu Windows");
        game.getItems().add(hero);
        game.getItems().add(dm);
        settings.getItems().add(settingsItem);
        exit.getItems().add(backToWindows);
        menuBar.getMenus().add(game);
        menuBar.getMenus().add(settings);
        menuBar.getMenus().add(exit);
        ((AnchorPane) root).getChildren().add(menuBar);
    }

    public static UUID getMainMenueSoundUUID() {
        return mainMenueSoundUUID;
    }

}
