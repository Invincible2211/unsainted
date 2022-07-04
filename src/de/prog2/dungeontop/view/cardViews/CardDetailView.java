package de.prog2.dungeontop.view.cardViews;

import de.prog2.dungeontop.resources.views.CardDetailConstants;
import javafx.scene.layout.AnchorPane;

public class CardDetailView extends CardView
{
    /**
     * Sets the scale of the card view.
     * @param scale The scale of the card view.
     */
    @Override
    public void setScale(double scale)
    {
        setWidth(CardDetailConstants.CARD_BASE_WIDTH * scale);
        setHeight(CardDetailConstants.CARD_BASE_HEIGHT * scale);
        setAnchorScale(scale);
    }
    /**
     * Sets the scale of the single elements on the card displaying the stats.
     * @param scale The scale of the single elements on the card displaying the stats.
     */
    protected void setAnchorScale(double scale)
    {
        super.setAnchorScale(scale);

        AnchorPane.setTopAnchor(summonCostContainer, scale * CardDetailConstants.SUMMON_COST_TOP_ANCHOR - CardDetailConstants.ICON_OFFSET);
        AnchorPane.setLeftAnchor(summonCostContainer, scale * CardDetailConstants.SUMMON_COST_LEFT_ANCHOR - CardDetailConstants.ICON_OFFSET);

        AnchorPane.setTopAnchor(rankContainer, scale * CardDetailConstants.RANK_TOP_ANCHOR - CardDetailConstants.ICON_OFFSET);
        AnchorPane.setLeftAnchor(rankContainer, scale * CardDetailConstants.RANK_LEFT_ANCHOR - CardDetailConstants.ICON_OFFSET);
    }
}
