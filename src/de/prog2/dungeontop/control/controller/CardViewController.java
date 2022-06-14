package de.prog2.dungeontop.control.controller;

import de.prog2.dungeontop.DungeonTop;
import de.prog2.dungeontop.control.manager.AssetsManager;
import de.prog2.dungeontop.model.entities.Entity;
import de.prog2.dungeontop.model.entities.Minion;
import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.model.game.EntityCard;
import de.prog2.dungeontop.model.game.SpellCard;
import de.prog2.dungeontop.resources.AssetIds;
import de.prog2.dungeontop.resources.StringValues;
import de.prog2.dungeontop.view.cardViews.CardView;
import de.prog2.dungeontop.view.cardViews.EntityCardView;
import de.prog2.dungeontop.view.cardViews.SpellCardView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import jdk.jshell.spi.ExecutionControl;

public abstract class CardViewController
{
    public static Node getCardView(Card card)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            Node cardView = null;
            if(card instanceof EntityCard)
            {
                cardView = loader.load(DungeonTop.class.getClassLoader().getResourceAsStream("view/cardViews/entityCardView.fxml"));
            }
            else if(card instanceof SpellCard)
            {
                cardView = loader.load(DungeonTop.class.getClassLoader().getResourceAsStream("view/cardViews/spellCardView.fxml"));
            }
            CardView controller = loader.getController();
            fillCardViewWithData(card, controller);
            return cardView;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    private static void fillCardViewWithData(Card card, CardView controller)
    {
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

    private static void fillEntityCardView(EntityCard card, EntityCardView controller)
    {
        Entity entity = card.getEntity();
        // Name
        controller.getEntityNameLabel().setText(entity.getName());

        // HP
        controller.getHpLabel().setText(entity.getHp() + "");
        controller.getHpImageView().imageProperty().setValue(AssetsManager.getImageByAssetId(AssetIds.HP_ICON));
        // Attack
        controller.getAttackLabel().setText(entity.getAttackDamage() + "");
        controller.getAttackImageView().imageProperty().setValue(AssetsManager.getImageByAssetId(AssetIds.ATTACK_ICON));
        // Movement
        controller.getMovementLabel().setText(entity.getMovement() + "");
        controller.getMovementImageView().imageProperty().setValue(AssetsManager.getImageByAssetId(AssetIds.MOVEMENT_ICON));

        // Image
        controller.getEntityImageView().imageProperty().setValue(AssetsManager.getImageByAssetId(entity.getAssetId()));
    }
    private static void fillSpellCardView(SpellCard card, SpellCardView controller)
    {
        // TODO Implement SpellCardView
    }
}
