package de.prog2.dungeontop.control.controller;

import de.prog2.dungeontop.DungeonTop;
import de.prog2.dungeontop.control.manager.AssetsManager;
import de.prog2.dungeontop.model.entities.Entity;
import de.prog2.dungeontop.resources.*;
import de.prog2.dungeontop.resources.views.ArenaViewConstants;
import de.prog2.dungeontop.utils.GlobalLogger;
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
    public static AnchorPane tempGetEntityView(Entity entity, double scale, AnchorPane cardDetailViewContainer)
    {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(170*scale, 170 * scale);
        anchorPane.setStyle("-fx-background-image: url(assets/490_CardBackground.png);");
        anchorPane.setStyle("-fx-background-size: cover;");
        ImageView icon = null;
        try {
            icon = new ImageView(new Image(AssetsManager.getAssetById(entity.getAssetId()).toURI().toURL().toString() ,100*scale, 100 *scale,true,true));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        icon.setLayoutX(35*scale);
        icon.setLayoutY(0);
        ImageView attack = new ImageView(new Image("assets/520_Attack_Icon.png",50*scale, 50 *scale,true,true));
        attack.setLayoutX(0);
        attack.setLayoutY(70*scale);
        attack.setOpacity(0.7);
        ImageView live = new ImageView(new Image("assets/060_Heart.png",50*scale, 50 *scale,true,true));
        live.setLayoutX(120*scale);
        live.setLayoutY(70*scale);
        live.setOpacity(0.7);
        ImageView move = new ImageView(new Image("assets/510_Movement_Icon.png",50*scale, 50 *scale,true,true));
        move.setLayoutX(60*scale);
        move.setLayoutY(120*scale);
        move.setOpacity(0.7);
        anchorPane.getChildren().add(icon);
        anchorPane.getChildren().add(attack);
        anchorPane.getChildren().add(live);
        anchorPane.getChildren().add(move);
        Label attackL = modifyLabel(new Label("" + entity.getAttackDamage()),0,70, scale);
        Label liveL = modifyLabel(new Label("" + entity.getHp()),120,70, scale);
        Label moveL = modifyLabel(new Label("" + entity.getMovement()), 60,120, scale);
        anchorPane.getChildren().add(attackL);
        anchorPane.getChildren().add(liveL);
        anchorPane.getChildren().add(moveL);
        anchorPane.setOnMouseEntered(event -> cardDetailViewContainer.getChildren().add(CardViewController.getCardDetailView(entity.getCard(),1)));
        anchorPane.setOnMouseExited(event -> cardDetailViewContainer.getChildren().clear());
        //addEventHandler(anchorPane);
        return anchorPane;
    }
    private static Label modifyLabel(Label label,int x, int y, double scale)
    {
        label.setLayoutX(x*scale);
        label.setLayoutY(y*scale);
        label.setPrefSize(50*scale,50*scale);
        label.setFont(new Font(24*scale));
        label.setAlignment(Pos.CENTER);
        label.setStyle("-fx-font-size: "+24*scale+";");
        label.setStyle("-fx-font-weight: bold;");
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
