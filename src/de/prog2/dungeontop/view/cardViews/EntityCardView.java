package de.prog2.dungeontop.view.cardViews;

import de.prog2.dungeontop.resources.views.CardConstants;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class EntityCardView extends CardView
{
    @FXML
    private Label entityNameLabel;
    @FXML
    private ImageView entityImageView;
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
    @FXML
    private BorderPane nameContainer;
    @FXML
    private BorderPane entityImageContainer;
    @FXML
    private StackPane hpContainer;
    @FXML
    private StackPane attackContainer;
    @FXML
    private StackPane movementContainer;

    @Override
    protected void setAnchorScale(double scale)
    {
        super.setAnchorScale(scale);
        entityNameLabel.setStyle(String.format(CardConstants.NAME_FONT_SIZE_STYLE, (int)(scale * (double) CardConstants.ENTITY_NAME_FONT_SIZE)));
        AnchorPane.setTopAnchor(nameContainer, scale * (CardConstants.NAME_TOP_ANCHOR - CardConstants.NAME_OFFSET_Y));

        entityImageView.setFitWidth(scale * CardConstants.ENTITY_IMAGE_WIDTH);
        entityImageView.setFitHeight(scale * CardConstants.ENTITY_IMAGE_HEIGHT);
        AnchorPane.setTopAnchor(entityImageContainer, scale * (CardConstants.ENTITY_IMAGE_TOP_ANCHOR - CardConstants.ENTITY_IMAGE_OFFSET_Y));

        hpContainer.setScaleX(scale);
        hpContainer.setScaleY(scale);
        AnchorPane.setTopAnchor(hpContainer, scale * CardConstants.HP_TOP_ANCHOR - CardConstants.ICON_OFFSET);
        AnchorPane.setLeftAnchor(hpContainer, scale * CardConstants.HP_LEFT_ANCHOR - CardConstants.ICON_OFFSET);

        attackContainer.setScaleX(scale);
        attackContainer.setScaleY(scale);
        AnchorPane.setTopAnchor(attackContainer, scale * CardConstants.ATTACK_TOP_ANCHOR - CardConstants.ICON_OFFSET);
        AnchorPane.setLeftAnchor(attackContainer, scale * CardConstants.ATTACK_LEFT_ANCHOR - CardConstants.ICON_OFFSET);

        movementContainer.setScaleX(scale);
        movementContainer.setScaleY(scale);
        AnchorPane.setTopAnchor(movementContainer, scale * CardConstants.MOVEMENT_TOP_ANCHOR - CardConstants.ICON_OFFSET);
        AnchorPane.setLeftAnchor(movementContainer, scale * CardConstants.MOVEMENT_LEFT_ANCHOR - CardConstants.ICON_OFFSET);
    }


    public Label getEntityNameLabel()
    {
        return entityNameLabel;
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
}
