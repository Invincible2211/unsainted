package de.prog2.dungeontop.control.controller;

import de.prog2.dungeontop.DungeonTop;
import de.prog2.dungeontop.control.manager.AssetsManager;
import de.prog2.dungeontop.model.entities.Entity;
import de.prog2.dungeontop.resources.*;
import de.prog2.dungeontop.utils.GlobalLogger;
import de.prog2.dungeontop.view.EntityView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

public abstract class EntityViewController
{
    /**
     * Returns a Node containing the EntityView for the given Entity with a scale of 1.
     * @param entity The entity that is displayed by the entity view.
     * @return The Node that is controlled by this controller.
     */
    public static Node getEntityView(Entity entity)
    {
        return getEntityView(entity, 1);
    }
    /**
     * Returns a Node containing the EntityView for the given Entity.
     * @param entity The Entity to display.
     * @param scale The scale of the entity view.
     * @return A Node containing the EntityView for the given Entity.
     */
    public static Node getEntityView(Entity entity, double scale)
    {
        Node entityView = null;
        try
        {
            FXMLLoader loader = new FXMLLoader();
            entityView = loader.load(DungeonTop.class.getClassLoader().getResourceAsStream(ViewStrings.ENTITY_VIEW_FXML));
            EntityView controller = loader.getController();
            fillEntityViewWithData(entity, controller);

            controller.setScale(scale);

            GlobalLogger.log(String.format(LoggerStringValues.ENTITY_VIEW_CONTROLLER_CREATED_ENTITY, scale));
        }
        catch (Exception e)
        {
            GlobalLogger.warning(e.getMessage());
        }
        return entityView;
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

    public static void zoomEntityView(Node entityView, double scale)
    {
        entityView.setScaleX(ViewStrings.ZOOMFACTO_ON_MOUSE_HOVER_CARD * scale);
        entityView.setScaleY(ViewStrings.ZOOMFACTO_ON_MOUSE_HOVER_CARD * scale);
    }

    public static void resetZoom(Node entityView, double scale)
    {
        entityView.setScaleX(scale);
        entityView.setScaleY(scale);
    }
}
