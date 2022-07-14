package de.prog2.dungeontop.control.controller;

import de.prog2.dungeontop.DungeonTop;
import de.prog2.dungeontop.control.manager.AssetsManager;
import de.prog2.dungeontop.control.manager.BattleManager2;
import de.prog2.dungeontop.model.entities.Entity;
import de.prog2.dungeontop.resources.*;
import de.prog2.dungeontop.resources.views.ArenaViewConstants;
import de.prog2.dungeontop.resources.views.EntityConstants;
import de.prog2.dungeontop.utils.GlobalLogger;
import de.prog2.dungeontop.utils.ImageAssetUtils;
import de.prog2.dungeontop.view.ArenaController;
import de.prog2.dungeontop.view.EntityView;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

import java.net.MalformedURLException;

public abstract class EntityViewController
{
    public static AnchorPane tempGetEntityView(Entity entity, double scale)
    {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(EntityConstants.ENTITY_BASE_SIZE * scale, EntityConstants.ENTITY_BASE_SIZE * scale);
        //TODO: Bilder für die Entities laden
        //anchorPane.setStyle("-fx-background-image: url(assets/490_CardBackground.png);");
        //anchorPane.setStyle("-fx-background-size: cover;");
        anchorPane.getStylesheets().add(EntityConstants.ENTITY_STYLE);

        ImageView icon = ImageAssetUtils.upsertImageFromAssets(entity.getAssetId(), EntityConstants.ENTITY_IMAGE_SIZE * scale, EntityConstants.ENTITY_IMAGE_SIZE * scale);

        icon.setLayoutX(EntityConstants.ENTITY_IMAGE_X * scale);
        icon.setLayoutY(EntityConstants.ENTITY_IMAGE_Y * scale);
        ImageView attack = ImageAssetUtils.upsertImageFromAssets(AssetIds.ATTACK_ICON, EntityConstants.ICON_SIZE * scale, EntityConstants.ICON_SIZE * scale);
        attack.setLayoutX(EntityConstants.ATTACK_X * scale);
        attack.setLayoutY(EntityConstants.ATTACK_Y * scale);
        attack.setOpacity(EntityConstants.ICON_OPACITY);
        ImageView hp = ImageAssetUtils.upsertImageFromAssets(AssetIds.HEART_ICON, EntityConstants.ICON_SIZE * scale, EntityConstants.ICON_SIZE * scale);
        hp.setLayoutX(EntityConstants.HP_X * scale);
        hp.setLayoutY(EntityConstants.HP_Y * scale);
        hp.setOpacity(EntityConstants.ICON_OPACITY);
        ImageView move = ImageAssetUtils.upsertImageFromAssets(AssetIds.MOVEMENT_ICON, EntityConstants.ICON_SIZE * scale, EntityConstants.ICON_SIZE * scale);
        move.setLayoutX(EntityConstants.MOVE_X * scale);
        move.setLayoutY(EntityConstants.MOVE_Y * scale);
        move.setOpacity(EntityConstants.ICON_OPACITY);
        anchorPane.getChildren().add(icon);
        anchorPane.getChildren().add(attack);
        anchorPane.getChildren().add(hp);
        anchorPane.getChildren().add(move);

        Label attackL = modifyLabel(new Label("" + entity.getAttackDamage()), EntityConstants.ATTACK_X, EntityConstants.ATTACK_Y, scale);
        Label hpL = modifyLabel(new Label("" + entity.getHp()),EntityConstants.HP_X,EntityConstants.HP_Y, scale);
        Label moveL = modifyLabel(new Label("" + entity.getMovement()), EntityConstants.MOVE_X,EntityConstants.MOVE_Y, scale);

        anchorPane.getChildren().add(attackL);
        anchorPane.getChildren().add(hpL);
        anchorPane.getChildren().add(moveL);

        return anchorPane;
    }

    /**
     * Modifies the label to the given position and size.
     * @param label The label to modify.
     * @param x The x layout-position.
     * @param y The y layout-position.
     */
    private static Label modifyLabel(Label label, double x, double y, double scale)
    {
        label.setLayoutX(x * scale);
        label.setLayoutY(y * scale);
        label.setPrefSize(EntityConstants.ICON_SIZE * scale,EntityConstants.ICON_SIZE * scale);
        label.setFont(new Font(EntityConstants.LABEL_FONT_SIZE * scale));
        label.getStyleClass().add(EntityConstants.LABEL_STYLE);
        return label;
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
        entityView.setScaleX(ArenaViewConstants.ZOOMFACTOR_ON_MOUSE_HOVER_CARD * scale);
        entityView.setScaleY(ArenaViewConstants.ZOOMFACTOR_ON_MOUSE_HOVER_CARD * scale);
    }

    public static void resetZoom(Node entityView, double scale)
    {
        entityView.setScaleX(scale);
        entityView.setScaleY(scale);
    }
}
