package de.prog2.dungeontop.control.controller;

import de.prog2.dungeontop.DungeonTop;
import de.prog2.dungeontop.control.manager.AssetsManager;
import de.prog2.dungeontop.model.entities.Entity;
import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.model.game.EntityCard;
import de.prog2.dungeontop.model.game.SpellCard;
import de.prog2.dungeontop.model.spells.Spell;
import de.prog2.dungeontop.resources.*;
import de.prog2.dungeontop.resources.views.CardConstants;
import de.prog2.dungeontop.resources.views.ViewStrings;
import de.prog2.dungeontop.utils.GlobalLogger;
import de.prog2.dungeontop.view.cardViews.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public abstract class CardViewController
{
    public static Node getCardView(Card card, double scale)
    {
        return getCardView(card, scale, null);
    }
    /**
     * @param card  The card that is displayed by the card view.
     * @param scale The scale of the card view. 1 makes the cardView have an height of 866 px.
     * @return The Node that is controlled by this controller.
     */
    public static Node getCardView(Card card, double scale, AnchorPane cardDetailViewContainer)
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

            controller.setScale(scale);
            if(cardDetailViewContainer != null)
            {
                controller.setDetailViewContainer(cardDetailViewContainer);
            }

            populateCardView(card, controller, false);

            GlobalLogger.log(String.format(LoggerStringValues.CARD_VIEW_CONTROLLER_CREATED_CARD, scale));
        }
        catch (Exception e)
        {
            GlobalLogger.warning(e.getMessage());
        }
        return cardView;
    }

    /**
     * @param scale The scale of the card view.
     */
    public static Node getEnemyCardView(double scale)
    {
        Node cardView = null;
        try
        {
            FXMLLoader loader = new FXMLLoader();

            cardView = loader.load(DungeonTop.class.getClassLoader().getResourceAsStream(ViewStrings.ENEMY_CARD_VIEW_FXML));
            EnemyCardView controller = loader.getController();

            controller.setScale(scale);
            controller.getBackgroundImageView().imageProperty().setValue(AssetsManager.getImageByAssetId(AssetIds.ENEMY_CARD_BACKGROUND));
            GlobalLogger.log(String.format(LoggerStringValues.CARD_VIEW_CONTROLLER_CREATED_CARD, scale));
        }
        catch (Exception e)
        {
            GlobalLogger.warning(e.getMessage());
        }
        return cardView;
    }

    /**
     * @param card The card that is displayed by the card view.
     * @param scale The scale of the card view. 1 makes the cardView have a height of 866 px.
     * @return The Node that is controlled by this controller.
     */
    public static Node getCardDetailView(Card card, double scale)
    {
        Node cardView = null;
        try
        {
            FXMLLoader loader = new FXMLLoader();

            if(card instanceof EntityCard)
            {
                cardView = loader.load(DungeonTop.class.getClassLoader().getResourceAsStream(ViewStrings.ENTITY_CARD_DETAIL_VIEW_FXML));
            }
            else if(card instanceof SpellCard)
            {
                cardView = loader.load(DungeonTop.class.getClassLoader().getResourceAsStream(ViewStrings.SPELL_CARD_DETAIL_VIEW_FXML));
            }
            CardView controller = loader.getController();

            controller.setScale(scale);

            populateCardView(card, controller, true);

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
    private static void populateCardView(Card card, CardView controller, boolean detail)
    {
        controller.getBackgroundImageView().imageProperty().setValue(AssetsManager.getImageByAssetId(
                detail ? AssetIds.CARD_DETAIL_BACKGROUND_IMAGE_ID : AssetIds.CARD_BACKGROUND_IMAGE_ID));
        controller.getRankLabel().setText(card.getRank() + "");
        controller.getRankImageView().imageProperty().setValue(AssetsManager.getImageByAssetId(AssetIds.getRankIcon(card.getRank())));

        controller.getSummonCostLabel().setText(card.getSummonCost() + "");
        controller.getSummonImageView().imageProperty().setValue(AssetsManager.getImageByAssetId(AssetIds.SUMMON_COST_ICON));

        if(card instanceof EntityCard)
        {
            if(detail)
                populateEntityCardDetailView((EntityCard)card, (EntityCardDetailView)controller);
            else
                populateEntityCardView((EntityCard)card, (EntityCardView)controller);
        }
        else if(card instanceof SpellCard)
        {
            if(detail)
                populateSpellCardDetailView((SpellCard) card, (SpellCardDetailView) controller);
            else
                populateSpellCardView((SpellCard) card, (SpellCardView) controller);
        }

        controller.setCard(card);
    }

    /**
     * Fills the card view with data from the entity card.
     * @param card The entity card that is displayed by the card view.
     * @param controller The controller of the card view.
     */
    private static void populateEntityCardView(EntityCard card, EntityCardView controller)
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
     * Fills the card view with data from the entity card.
     * @param card The entity card that is displayed by the card view.
     * @param controller The controller of the card view.
     */
    private static void populateEntityCardDetailView(EntityCard card, EntityCardDetailView controller)
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
    private static void populateSpellCardView(SpellCard card, SpellCardView controller)
    {
        Spell spell = card.getSpell();
        // Name
        controller.getNameLabel().setText(spell.getName());
        
        // Image
        controller.getSpellImageView().imageProperty().setValue(AssetsManager.getImageByAssetId(spell.getAssetId()));
    }
    /**
     * Fills the card view with data from the spell card.
     * @param card The spell card that is displayed by the card view.
     * @param controller The controller of the card view.
     */
    private static void populateSpellCardDetailView(SpellCard card, SpellCardDetailView controller)
    {
        Spell spell = card.getSpell();
        // Name
        controller.getNameLabel().setText(spell.getName());
        // Description
        controller.getDescriptionLabel().setText(spell.getDescription());

        // Image
        controller.getSpellImageView().imageProperty().setValue(AssetsManager.getImageByAssetId(spell.getAssetId()));
    }

    /**
     * Zooms the card view in and moves it a tiny bit up.
     */
    public static void zoomCardView(Node cardView)
    {
        cardView.setScaleX(CardConstants.ZOOM_FACTOR);
        cardView.setScaleY(CardConstants.ZOOM_FACTOR);

        cardView.translateYProperty().set(cardView.getTranslateY() - CardConstants.ZOOM_TRANSLATE_Y);
    }

    /**
     * Resets the applied zoom of the methed above.
     */
    public static void resetZoom(Node cardView)
    {
        cardView.setScaleX(CardConstants.NO_ZOOM_FACTOR);
        cardView.setScaleY(CardConstants.NO_ZOOM_FACTOR);

        cardView.translateYProperty().set(cardView.getTranslateY() + CardConstants.ZOOM_TRANSLATE_Y);
    }
}
