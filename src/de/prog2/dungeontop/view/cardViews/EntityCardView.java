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


    public Label getEntityNameLabel()
    {
        return entityNameLabel;
    }

    public ImageView getEntityImageView()
    {
        return entityImageView;
    }
}
