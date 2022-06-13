package de.prog2.dungeontop.view;

import de.prog2.dungeontop.DungeonTop;
import de.prog2.dungeontop.control.manager.AssetsManager;
import de.prog2.dungeontop.model.entities.Entity;
import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.model.game.EntityCard;
import de.prog2.dungeontop.model.game.SpellCard;
import de.prog2.dungeontop.model.items.Item;
import de.prog2.dungeontop.resources.AssetIds;
import de.prog2.dungeontop.view.cardViews.CardView;
import de.prog2.dungeontop.view.cardViews.EntityCardView;
import de.prog2.dungeontop.view.cardViews.SpellCardView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

public class ItemViewController
{
    public static Node getItemView(Item item)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            Node cardView = null;

                cardView = loader.load(DungeonTop.class.getClassLoader().getResourceAsStream("view/cardViews/entityCardView.fxml"));

            CardView controller = loader.getController();
            fillCardViewWithData(item, controller);
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

        fillEntityCardView((EntityCard)card, (EntityCardView)controller);

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

}
