package de.prog2.dungeontop.view;

import de.prog2.dungeontop.control.manager.AssetsManager;
import de.prog2.dungeontop.model.entities.Entity;
import de.prog2.dungeontop.resources.CardConstants;
import de.prog2.dungeontop.resources.EntityConstants;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class EntityView
{
    @FXML
    private ImageView entityImageView;
    @FXML
    private ImageView statusBarImageView;
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

    @FXML
    private AnchorPane container;
    @FXML
    private StackPane hpContainer;
    @FXML
    private StackPane attackContainer;
    @FXML
    private StackPane movementContainer;

    /**
     * Sets the width of the entity.
     * @param width the width, in pixels
     */
    public void setWidth(double width)
    {
        container.setPrefWidth(width);
        statusBarImageView.setFitWidth(width);
    }

    /**
     * Sets the height of the entity.
     * @param height the height, in pixels
     */
    public void setHeight(double height)
    {
        container.setPrefHeight(height);
        statusBarImageView.setFitHeight(height);
    }
    public void setAnchorScale(double scale)
    {
        entityImageView.setFitWidth(scale * EntityConstants.ENTITY_IMAGE_WIDTH);
        entityImageView.setFitHeight(scale * EntityConstants.ENTITY_IMAGE_HEIGHT);

        hpContainer.setScaleX(scale);
        hpContainer.setScaleY(scale);
        AnchorPane.setTopAnchor(hpContainer, scale * EntityConstants.HP_TOP_ANCHOR - CardConstants.ICON_OFFSET);
        AnchorPane.setLeftAnchor(hpContainer, scale * EntityConstants.HP_LEFT_ANCHOR - CardConstants.ICON_OFFSET);

        attackContainer.setScaleX(scale);
        attackContainer.setScaleY(scale);
        AnchorPane.setTopAnchor(attackContainer, scale * EntityConstants.ATTACK_TOP_ANCHOR - CardConstants.ICON_OFFSET);
        AnchorPane.setLeftAnchor(attackContainer, scale * EntityConstants.ATTACK_LEFT_ANCHOR - CardConstants.ICON_OFFSET);

        movementContainer.setScaleX(scale);
        movementContainer.setScaleY(scale);
        AnchorPane.setTopAnchor(movementContainer, scale * EntityConstants.MOVEMENT_TOP_ANCHOR - CardConstants.ICON_OFFSET);
        AnchorPane.setLeftAnchor(movementContainer, scale * EntityConstants.MOVEMENT_LEFT_ANCHOR - CardConstants.ICON_OFFSET);
    }

    @FXML
    private void initialize()
    {
        container.setBackground(Background.EMPTY);
        statusBarImageView.imageProperty().setValue(AssetsManager.getImageByAssetId(40));
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
