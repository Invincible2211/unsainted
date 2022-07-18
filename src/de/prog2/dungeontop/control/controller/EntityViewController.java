package de.prog2.dungeontop.control.controller;

import de.prog2.dungeontop.model.entities.Entity;
import de.prog2.dungeontop.resources.AssetIds;
import de.prog2.dungeontop.resources.views.EntityConstants;
import de.prog2.dungeontop.utils.ImageAssetUtils;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

import javax.naming.Binding;

public abstract class EntityViewController
{
    /**
     * Creates a view for the given entity.
     * @param scale The scale of the view.
     */
    public static AnchorPane getEntityView(Entity entity, double scale)
    {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(EntityConstants.ENTITY_BASE_SIZE * scale, EntityConstants.ENTITY_BASE_SIZE * scale);
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

        // bind stats from entity to it's view
        hpL.textProperty().bind(Bindings.createStringBinding(
                () -> "" + entity.getHp(),
                entity.getHpProperty()
        ));

        moveL.textProperty().bind(Bindings.createStringBinding(
                () -> "" + entity.getMovement(),
                entity.getMovementProperty()
        ));

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
}
