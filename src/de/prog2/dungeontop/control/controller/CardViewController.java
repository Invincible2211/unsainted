package de.prog2.dungeontop.control.controller;

import de.prog2.dungeontop.DungeonTop;
import de.prog2.dungeontop.control.manager.AssetsManager;
import de.prog2.dungeontop.model.entities.Entity;
import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.model.game.EntityCard;
import de.prog2.dungeontop.model.game.SpellCard;
import de.prog2.dungeontop.resources.*;
import de.prog2.dungeontop.utils.GlobalLogger;
import de.prog2.dungeontop.view.cardViews.CardView;
import de.prog2.dungeontop.view.cardViews.EntityCardView;
import de.prog2.dungeontop.view.cardViews.SpellCardView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import javax.swing.text.View;

public abstract class CardViewController
{
    /**
     * @param card  The card that is displayed by the card view.
     * @return The Node that is controlled by this controller.
     */
    public static Node getCardView(Card card)
    {
        return getCardView(card, 1);
    }

    /**
     * @param card  The card that is displayed by the card view.
     * @param scale The scale of the card view. 1 makes the cardView have an height of 866 px.
     * @return The Node that is controlled by this controller.
     */
    public static Node getCardView(Card card, double scale)
    {
        Node cardView = null;
        try
        {
            FXMLLoader loader = new FXMLLoader();

            if(card instanceof EntityCard)
            {
                cardView = loader.load(DungeonTop.class.getClassLoader().getResourceAsStream(ViewStrings.ENTITY_CARD_VIEW_FXML));
            }
            else if(card instanceof SpellCard)
            {
                cardView = loader.load(DungeonTop.class.getClassLoader().getResourceAsStream(ViewStrings.SPELL_CARD_VIEW_FXML));
            }
            CardView controller = loader.getController();

            double width = CardConstants.CARD_BASE_WIDTH * scale;
            double height = CardConstants.CARD_BASE_HEIGHT * scale;
            controller.setWidth(width);
            controller.setHeight(height);
            controller.setAnchorScale(scale);
            fillCardViewWithData(card, controller);

            GlobalLogger.log(String.format(LoggerStringValues.CARD_VIEW_CONTROLLER_CREATED_CARD, scale));
        }
        catch (Exception e)
        {
            GlobalLogger.warning(e.getMessage());
        }
        return cardView;
    }

    /**
     * Fills the card view with data from the card.
     * @param card The card that is displayed by the card view.
     * @param controller The controller of the card view.
     */
    private static void fillCardViewWithData(Card card, CardView controller)
    {
        controller.getBackgroundImageView().imageProperty().setValue(AssetsManager.getImageByAssetId(AssetIds.CARD_BACKGROUND_IMAGE_ID));
        controller.getRankLabel().setText(card.getRank() + "");
        controller.getRankImageView().imageProperty().setValue(AssetsManager.getImageByAssetId(AssetIds.getRankIcon(card.getRank())));

        controller.getSummonCostLabel().setText(card.getSummonCost() + "");
        controller.getSummonImageView().imageProperty().setValue(AssetsManager.getImageByAssetId(AssetIds.SUMMON_COST_ICON));

        if(card instanceof EntityCard)
        {
            fillEntityCardView((EntityCard)card, (EntityCardView)controller);
        }
        else if(card instanceof SpellCard)
        {
            fillSpellCardView((SpellCard)card, (SpellCardView)controller);
        }

        controller.setCard(card);
    }

    /**
     * Fills the card view with data from the entity card.
     * @param card The entity card that is displayed by the card view.
     * @param controller The controller of the card view.
     */
    private static void fillEntityCardView(EntityCard card, EntityCardView controller)
    {
        Entity entity = card.getEntity();
        // Name
        controller.getEntityNameLabel().setText(entity.getName());

        // HP
        controller.getHpLabel().setText(entity.getHp() + "");
        controller.getHpImageView().imageProperty().setValue(AssetsManager.getImageByAssetId(AssetIds.HP_ICON));
        // Movement
        controller.getMovementLabel().setText(entity.getMovement() + "");
        controller.getMovementImageView().imageProperty().setValue(AssetsManager.getImageByAssetId(AssetIds.MOVEMENT_ICON));
        // Attack
        controller.getAttackLabel().setText(entity.getAttackDamage() + "");
        controller.getAttackImageView().imageProperty().setValue(AssetsManager.getImageByAssetId(AssetIds.ATTACK_ICON));

        // Image
        controller.getEntityImageView().imageProperty().setValue(AssetsManager.getImageByAssetId(entity.getAssetId()));
    }

    /**
     * Fills the card view with data from the spell card.
     * @param card The spell card that is displayed by the card view.
     * @param controller The controller of the card view.
     */
    private static void fillSpellCardView(SpellCard card, SpellCardView controller)
    {
        // TODO Implement SpellCardView
    }

    public static void mouseEntered (Node cardView)
    {
        cardView.setScaleX(ViewStrings.ZOOMFACTO_ON_MOUSE_HOVER_CARD);
        cardView.setScaleY(ViewStrings.ZOOMFACTO_ON_MOUSE_HOVER_CARD);
    }

    public static void mouseExited (Node cardView)
    {
        cardView.setScaleX(1);
        cardView.setScaleY(1);
    }
}
