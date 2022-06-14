package de.prog2.dungeontop.control.controller;

import de.prog2.dungeontop.DungeonTop;
import de.prog2.dungeontop.control.manager.AssetsManager;
import de.prog2.dungeontop.model.entities.Entity;
import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.model.game.EntityCard;
import de.prog2.dungeontop.model.game.SpellCard;
import de.prog2.dungeontop.resources.AssetIds;
import de.prog2.dungeontop.view.EntityView;
import de.prog2.dungeontop.view.cardViews.CardView;
import de.prog2.dungeontop.view.cardViews.EntityCardView;
import de.prog2.dungeontop.view.cardViews.SpellCardView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

public abstract class EntityViewController
{
    public static Node getEntityView(Entity entity)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            Node cardView = loader.load(DungeonTop.class.getClassLoader().getResourceAsStream("view/entityView.fxml"));
            EntityView controller = loader.getController();
            fillEntityViewWithData(entity, controller);
            return cardView;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    private static void fillEntityViewWithData(Entity entity, EntityView controller)
    {
        controller.setEntity(entity);
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
