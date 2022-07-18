package de.prog2.dungeontop.view;

import de.prog2.dungeontop.DungeonTop;
import de.prog2.dungeontop.control.controller.InventoryController;
import de.prog2.dungeontop.control.controller.ItemViewController;
import de.prog2.dungeontop.control.manager.PlayerManager;
import de.prog2.dungeontop.model.items.Item;
import de.prog2.dungeontop.resources.items.ItemConstants;
import de.prog2.dungeontop.resources.views.ViewStrings;
import de.prog2.dungeontop.utils.GlobalLogger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.IOException;

public class RewardView {

    private final static RewardView instance = new RewardView();

    private RewardView(){
        initStage();
    }

    private final Stage rewardsStage = new Stage();

    @FXML
    private Label soulsLabel;

    @FXML
    private AnchorPane root;


    @FXML
    private void onOkButtonPressed()
    {
        hideStage();
    }

    public void showStage()
    {
        int soules = (int) (10 + Math.random() * 50) + PlayerManager.getInstance().getPlayer().getSoulArtBonus();
        PlayerManager.getInstance().addSouls(soules);
        Item item = ItemConstants.getRandomItem();
        InventoryController.addItem(item);
        Node itemView = ItemViewController.getItemView(item);
        root.getChildren().clear();
        root.getChildren().add(itemView);
        soulsLabel.setText(soules+"");
        rewardsStage.show();
    }

    public void hideStage()
    {
        rewardsStage.hide();
    }

    private void initStage()
    {
        final FXMLLoader fxmlLoader = new FXMLLoader();
        rewardsStage.initModality(Modality.APPLICATION_MODAL);
        rewardsStage.initOwner(DungeonTop.getStage());
        rewardsStage.initStyle(StageStyle.TRANSPARENT);
        AnchorPane rootPane = new AnchorPane();
        try
        {
            rootPane = fxmlLoader.load(DungeonTop.class.getClassLoader().getResourceAsStream(ViewStrings.REWARDS_VIEW));
        } catch (IOException e)
        {
            GlobalLogger.warning(e.getMessage());
        }
        final Scene rewardsScene = new Scene(rootPane, Color.TRANSPARENT);
        rewardsStage.setScene(rewardsScene);
    }

    public static RewardView getInstance() {
        return instance;
    }

}
