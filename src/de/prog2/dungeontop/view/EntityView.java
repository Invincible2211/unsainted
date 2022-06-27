package de.prog2.dungeontop.view;

import de.prog2.dungeontop.control.controller.CardViewController;
import de.prog2.dungeontop.control.controller.EntityViewController;
import de.prog2.dungeontop.control.manager.AssetsManager;
import de.prog2.dungeontop.model.entities.Entity;
import de.prog2.dungeontop.resources.EntityConstants;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;

public class EntityView
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
    private StackPane hpContainer;
    @FXML
    private StackPane attackContainer;
    @FXML
    private StackPane movementContainer;
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

    /**
     * Sets the width of the entity.
     * @param width the width, in pixels
     */
    public void setWidth(double width)
    {
        backgroundImageView.setFitWidth(width);
        container.setPrefWidth(width);
        container.setMaxWidth(width);
    }

    /**
     * Sets the height of the entity.
     * @param height the height, in pixels
     */
    public void setHeight(double height)
    {
        backgroundImageView.setFitHeight(height);
        container.setPrefHeight(height);
        container.setMaxHeight(height);
    }
    public void setAnchorScale(double scale)
    {
        entityImageView.setFitWidth(scale * EntityConstants.ENTITY_IMAGE_WIDTH);
        entityImageView.setFitHeight(scale * EntityConstants.ENTITY_IMAGE_HEIGHT);

        hpContainer.setScaleX(scale);
        hpContainer.setScaleY(scale);
        AnchorPane.setTopAnchor(hpContainer, scale * EntityConstants.HP_TOP_ANCHOR - EntityConstants.ICON_OFFSET);
        AnchorPane.setLeftAnchor(hpContainer, scale * EntityConstants.HP_LEFT_ANCHOR - EntityConstants.ICON_OFFSET);

        attackContainer.setScaleX(scale);
        attackContainer.setScaleY(scale);
        AnchorPane.setTopAnchor(attackContainer, scale * EntityConstants.ATTACK_TOP_ANCHOR - EntityConstants.ICON_OFFSET);
        AnchorPane.setLeftAnchor(attackContainer, scale * EntityConstants.ATTACK_LEFT_ANCHOR - EntityConstants.ICON_OFFSET);

        movementContainer.setScaleX(scale);
        movementContainer.setScaleY(scale);
        AnchorPane.setTopAnchor(movementContainer, scale * EntityConstants.MOVEMENT_TOP_ANCHOR - EntityConstants.ICON_OFFSET);
        AnchorPane.setLeftAnchor(movementContainer, scale * EntityConstants.MOVEMENT_LEFT_ANCHOR - EntityConstants.ICON_OFFSET);
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
        backgroundImageView.imageProperty().setValue(AssetsManager.getImageByAssetId(40));
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
