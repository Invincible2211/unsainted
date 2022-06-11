package de.prog2.dungeontop.view;

import de.prog2.dungeontop.control.manager.BattleManager;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

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

    //BIND THESE TWO WAY
    @FXML
    private Label egopointsPlayerTwo;

    //hier laesst sich drueber streiten ob nicht eigentlich erst ueber den Controller der aufruf am Battlemanager stattfinden sollte.
    @FXML
    private void endTurn(){BattleManager.getInstance().endAPhase();}

    //----------------------- Getter ------------------//

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
}
