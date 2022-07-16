package de.prog2.dungeontop.view;

import de.prog2.dungeontop.control.manager.BattleManager;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

@Deprecated
public class ArenaBaseView
{

    @FXML
    private GridPane battlefieldGridPane;

    @FXML
    private HBox enemyHandHBox;

    @FXML
    private HBox playerHandHBox;

    @FXML
    private Accordion lastMovesAccordion;

    @FXML
    private Pane deckdisplay;

    @FXML
    private Button endPhaseButton;

    @FXML
    private Label egopointsPlayerOne;

    @FXML
    private Label egopointsPlayerTwo;

    @FXML
    private ImageView egopointsPlayerOneImageView;

    @FXML
    private ImageView egopointsPlayerTwoImageView;

    @FXML
    private AnchorPane backGroundAnchorPane;

    @FXML
    private StackPane backGroundStackPane;

    @FXML BorderPane borderPaneID;

    @FXML
    private VBox vBoxEnemyAreaContainer;

    @FXML
    private Label PhaseDisplayLabelID;

    //hier laesst sich drueber streiten ob nicht eigentlich erst ueber den Controller der aufruf am Battlemanager stattfinden sollte.
    @FXML
    private void endTurn(){BattleManager.getInstance().endAPhase();}

    //----------------------- Getter ------------------//


    public Label getPhaseDisplayLabelID ()
    {
        return PhaseDisplayLabelID;
    }

    public VBox getvBoxEnemyAreaContainer ()
    {
        return vBoxEnemyAreaContainer;
    }

    public BorderPane getBorderPaneID ()
    {
        return borderPaneID;
    }

    public StackPane getBackGroundStackPane ()
    {
        return backGroundStackPane;
    }

    public AnchorPane getBackGroundAnchorPane ()
    {
        return backGroundAnchorPane;
    }

    public GridPane getBattlefieldGridPane ()
    {
        return battlefieldGridPane;
    }

    public HBox getEnemyHandHBox ()
    {
        return enemyHandHBox;
    }

    public HBox getPlayerHandHBox ()
    {
        return playerHandHBox;
    }

    public Accordion getLastMovesAccordion ()
    {
        return lastMovesAccordion;
    }

    public Pane getDeckdisplay ()
    {
        return deckdisplay;
    }

    public Button getendPhaseButton ()
    {
        return endPhaseButton;
    }

    public Label getEgopointsPlayerOne ()
    {
        return egopointsPlayerOne;
    }

    public Label getEgopointsPlayerTwo ()
    {
        return egopointsPlayerTwo;
    }

    public ImageView getEgopointsPlayerOneImageView ()
    {
        return egopointsPlayerOneImageView;
    }

    public ImageView getEgopointsPlayerTwoImageView ()
    {
        return egopointsPlayerTwoImageView;
    }
}
