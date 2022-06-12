package de.prog2.dungeontop.view.cardViews;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

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
