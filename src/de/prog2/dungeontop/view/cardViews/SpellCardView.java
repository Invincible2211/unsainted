package de.prog2.dungeontop.view.cardViews;

import de.prog2.dungeontop.resources.views.CardConstants;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class SpellCardView extends CardView
{
    @FXML
    private Label nameLabel;
    @FXML
    private ImageView spellImageView;
    @FXML
    private BorderPane nameContainer;
    @FXML
    private BorderPane spellImageContainer;

    @Override
    protected void setAnchorScale(double scale)
    {
        super.setAnchorScale(scale);
        nameLabel.setStyle(String.format(CardConstants.NAME_FONT_SIZE_STYLE, (int)(scale * (double) CardConstants.ENTITY_NAME_FONT_SIZE)));
        AnchorPane.setTopAnchor(nameContainer, scale * (CardConstants.NAME_TOP_ANCHOR - CardConstants.NAME_OFFSET_Y));

        spellImageView.setFitWidth(scale * CardConstants.ENTITY_IMAGE_WIDTH);
        spellImageView.setFitHeight(scale * CardConstants.ENTITY_IMAGE_HEIGHT);
        AnchorPane.setTopAnchor(spellImageContainer, scale * (CardConstants.ENTITY_IMAGE_TOP_ANCHOR - CardConstants.ENTITY_IMAGE_OFFSET_Y));
    }


    public ImageView getSpellImageView()
    {
        return spellImageView;
    }

    public Label getNameLabel()
    {
        return nameLabel;
    }
}
