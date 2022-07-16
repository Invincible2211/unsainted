package de.prog2.dungeontop.view;

import de.prog2.dungeontop.DungeonTop;
import de.prog2.dungeontop.control.file.GameSaveFileWriter;
import de.prog2.dungeontop.control.manager.AssetsManager;
import de.prog2.dungeontop.control.manager.AudioManager;
import de.prog2.dungeontop.control.manager.GameManager;
import de.prog2.dungeontop.resources.AssetIds;
import de.prog2.dungeontop.resources.GameEndDialogueConstants;
import de.prog2.dungeontop.resources.views.ViewStrings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class GameEndViewController
{
    @FXML
    public Button startNewgameButton;

    @FXML
    public Button backToMenuButton;

    @FXML
    private ImageView gameResultImage;

    @FXML
    private TextArea resultMessageText;

    private static final Stage gameEndStage = new Stage();
    private static GameEndViewController instance;

    public GameEndViewController ()
    {
        instance = this;
    }

    public static  GameEndViewController getInstance()
    {
        return instance;
    }

    /**
     * Initializes the GameEndDialogue Stage
     */
    public static void initStage()
    {
        final FXMLLoader fxmlLoader = new FXMLLoader();

        gameEndStage.initModality(Modality.APPLICATION_MODAL);
        gameEndStage.initOwner(DungeonTop.getStage());
        gameEndStage.initStyle(StageStyle.TRANSPARENT);
        Pane rootPane = new Pane();
        try {
            rootPane = fxmlLoader.load(DungeonTop.class.getClassLoader().getResourceAsStream(ViewStrings.GAME_END_DIALOGUE_VIEW_FXML));
        } catch (IOException e) {
            e.printStackTrace();
        }
        final Scene settingsScene = new Scene(rootPane, Color.TRANSPARENT);
        gameEndStage.setScene(settingsScene);

        instance.startNewgameButton.setOnMouseEntered(event -> AudioManager.getInstance().playSound(AssetIds.MOUSE_HOVER_SOUND, false));
        instance.startNewgameButton.setOnMousePressed(event -> AudioManager.getInstance().playSound(AssetIds.BUTTON_CLICK_SOUND, false));
        instance.backToMenuButton.setOnMouseEntered(event -> AudioManager.getInstance().playSound(AssetIds.MOUSE_HOVER_SOUND, false));
        instance.backToMenuButton.setOnMousePressed(event -> AudioManager.getInstance().playSound(AssetIds.BUTTON_CLICK_SOUND, false));
    }

    /**
     * Sets the image and text to show in the dialogue and show the dialogue after that.
     *
     * @param playerHasWon true if the player has won the game
     *                     false if the player lost the game
     */
    public void showGameEndDialogue (boolean playerHasWon)
    {
        if (playerHasWon)
        {
            gameResultImage.setImage(AssetsManager.getImageByAssetId(AssetIds.PLAYER_WINS));
            resultMessageText.setText(GameEndDialogueConstants.PLAYER_WINS_TEXT);
        }
        else
        {
            gameResultImage.setImage(AssetsManager.getImageByAssetId(AssetIds.PLAYER_LOSES));
            resultMessageText.setText(GameEndDialogueConstants.PLAYER_LOSES_TEXT);
        }
        gameEndStage.show();
    }

    /**
     * Hides the dialogue and starts a new game via the GameManager.
     */
    @FXML
    public void onStartNewGameButton()
    {
        GameManager.getInstance().startGame();
        gameEndStage.hide();
    }

    /**
     * Hides the dialogue and returns to the MainMenu Scene.
     */
    @FXML
    public void onBackToMenu()
    {
        Scene scene = new Scene(new AnchorPane());
        try {
            scene = new Scene(new FXMLLoader().load(DungeonTop.class.getClassLoader().getResourceAsStream(ViewStrings.MAIN_MENUE_FXML)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        GameSaveFileWriter.getInstance().saveGame(GameManager.getInstance().getSaveGame());
        DungeonTop.getStage().setScene(scene);
        gameEndStage.hide();
    }
}
