package de.prog2.dungeontop.view.itemViews;

import de.prog2.dungeontop.DungeonTop;
import de.prog2.dungeontop.control.manager.AudioManager;
import de.prog2.dungeontop.control.manager.PlayerManager;
import de.prog2.dungeontop.view.HellView;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;


public class InventoryView
{
    @FXML
    GridPane gridPane;
    @FXML
    GridPane weaponSlot;
    @FXML
    GridPane artifactSlots;
    @FXML
    Text healthPoints;

    public GridPane getGridPane()
    {
        return gridPane;
    }

    public GridPane getWeaponSlot()
    {
        return weaponSlot;
    }

    public GridPane getArtifactSlots()
    {
        return artifactSlots;
    }

    public Text getHealthPoints()
    {
        return healthPoints;
    }
    public void setHPText()
    {
        getHealthPoints().setText(String.valueOf(PlayerManager.getInstance().getPlayerHp()));
    }
    @FXML
    private void onReturnButtonClicked()
    {
        HellView.resumeHellViewBgMusic();
        DungeonTop.getStage().setScene(HellView.getCurrHellView());
        AudioManager.getInstance().stopSound(HellView.getInventorySoundId());
    }
}
