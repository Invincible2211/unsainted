package de.prog2.dungeontop.control.controller;

import de.prog2.dungeontop.DungeonTop;
import de.prog2.dungeontop.control.manager.AssetsManager;
import de.prog2.dungeontop.model.entities.Entity;
import de.prog2.dungeontop.resources.AssetIds;
import de.prog2.dungeontop.view.EntityView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

public abstract class EntityViewController
{
    /**
     * Returns a Node containing the EntityView for the given Entity.
     * @param entity The Entity to display.
     * @return A Node containing the EntityView for the given Entity.
     */
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

    /**
     * Fills the given EntityView with the data of the given Entity.
     * @param entity The Entity to display.
     * @param controller The EntityView to fill.
     */
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
