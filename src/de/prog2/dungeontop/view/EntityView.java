package de.prog2.dungeontop.view;

import de.prog2.dungeontop.control.controller.EntityViewController;
import de.prog2.dungeontop.control.manager.AssetsManager;
import de.prog2.dungeontop.model.entities.Entity;
import de.prog2.dungeontop.resources.AssetIds;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;

public class EntityView extends Node
{
    @FXML
    private ImageView entityImageView;
    @FXML
    private ImageView backgroundImageView;
    @FXML
    private Label hpLabel;
    @FXML
    private ImageView hpImageView;
    @FXML
    private Label attackLabel;
    @FXML
    private ImageView attackImageView;
    @FXML
    private Label movementLabel;
    @FXML
    private ImageView movementImageView;
    private Entity entity;
    private double scale = 1;

    @FXML
    private AnchorPane container;
    @FXML
    private void mouseEntered()
    {
        EntityViewController.zoomEntityView(container, scale);
    }
    @FXML
    private void mouseExited()
    {
        EntityViewController.resetZoom(container, scale);
    }

    public void setScale(double scale)
    {
        container.setScaleX(scale);
        container.setScaleY(scale);
        this.scale = scale;
    }

    @FXML
    private void initialize()
    {
        container.setBackground(Background.EMPTY);
        backgroundImageView.imageProperty().setValue(AssetsManager.getImageByAssetId(AssetIds.ENTITY_VIEW_BACKGROUND));
    }


    public ImageView getEntityImageView()
    {
        return entityImageView;
    }

    public Label getHpLabel()
    {
        return hpLabel;
    }

    public ImageView getHpImageView()
    {
        return hpImageView;
    }

    public Label getAttackLabel()
    {
        return attackLabel;
    }

    public ImageView getAttackImageView()
    {
        return attackImageView;
    }

    public Label getMovementLabel()
    {
        return movementLabel;
    }

    public ImageView getMovementImageView()
    {
        return movementImageView;
    }

    public Entity getEntity()
    {
        return entity;
    }

    public void setEntity(Entity entity)
    {
        this.entity = entity;
    }
}
