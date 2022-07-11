package de.prog2.dungeontop.view;

import de.prog2.dungeontop.control.controller.CardViewController;
import de.prog2.dungeontop.control.controller.EntityViewController;
import de.prog2.dungeontop.control.manager.AssetsManager;
import de.prog2.dungeontop.control.manager.BattleManager2;
import de.prog2.dungeontop.control.network.NetManager;
import de.prog2.dungeontop.model.entities.Entity;
import de.prog2.dungeontop.model.game.EntityCard;
import de.prog2.dungeontop.model.world.Coordinate;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;

public class ArenaController {

    private double scale;

    private AnchorPane selected = null;

    private HashMap<Coordinate, EntityCard> friendly = new HashMap<Coordinate, EntityCard>();
    private HashMap<Coordinate, EntityCard> opponent = new HashMap<Coordinate, EntityCard>();

    @FXML
    private Label currentPhase;

    @FXML
    Label labelEgoPointsPlayer;

    @FXML
    Label labelEgoPointOpponent;

    @FXML
    private HBox playerCardView;

    @FXML
    private HBox enemyCardView;

    @FXML
    private AnchorPane arenaGrid;

    @FXML
    private AnchorPane cardView;

    private GridPane arenaGridPane;

    @FXML
    private void onNextRound(){

    }

    @FXML
    AnchorPane root;

    @FXML
    private void onSurrender(){
        BattleManager2.getInstance().endBattle();
        NetManager.getInstance().getNetworkAPI().sendEndBattlePackage();
    }

    public void initBattle(int width, int height){
        clear();
        arenaGridPane = new GridPane();
        for (int i = 0; i < width; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / width);
            //colConst.setFillWidth(true);
            arenaGridPane.getColumnConstraints().add(colConst);
        }
        for (int i = 0; i < height; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / height);
            //rowConst.setFillHeight(true);
            arenaGridPane.getRowConstraints().add(rowConst);
        }
        arenaGridPane.setPrefSize(680, 680);
        arenaGridPane.setMaxSize(680, 680);
        arenaGridPane.setLayoutX(370);
        arenaGridPane.setGridLinesVisible(true);
        scale = (double)680/(double)height/(double)170;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                AnchorPane anchorPane = new AnchorPane();
                anchorPane.setPrefSize(170*scale,170*scale);
                addEventHandler(anchorPane);
                arenaGridPane.add(anchorPane,i,j);
            }
        }
        arenaGrid.getChildren().add(arenaGridPane);
    }

    private void clear(){
        playerCardView.getChildren().clear();
        enemyCardView.getChildren().clear();
    }

    public void setPlayerCardView(List<EntityCard> cardList){

    }

    public void setOpponentCardView(List<EntityCard> cardList){

    }

    public void placeCardFriendly(EntityCard entityCard, Coordinate coordinate){
        AnchorPane card = createCard(entityCard);
        arenaGridPane.add(card, coordinate.getX(), coordinate.getY());
        friendly.put(coordinate,entityCard);
    }

    public void placeCardOpponent(EntityCard entityCard, Coordinate coordinate){
        AnchorPane card = createCard(entityCard);
        arenaGridPane.add(card, coordinate.getX(), coordinate.getY());
        opponent.put(coordinate,entityCard);
    }

    private AnchorPane createCard(EntityCard entityCard){
        System.out.println(scale);
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(170*scale, 170 * scale);
        anchorPane.setStyle("-fx-background-image: url(assets/490_CardBackground.png);");
        anchorPane.setStyle("-fx-background-size: cover;");
        ImageView icon = null;
        try {
            icon = new ImageView(new Image(AssetsManager.getAssetById(entityCard.getEntity().getAssetId()).toURI().toURL().toString() ,100*scale, 100 *scale,true,true));
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
        Label attackL = modifyLabel(new Label(""+ entityCard.getEntity().getAttackDamage()),0,70);
        Label liveL = modifyLabel(new Label(""+entityCard.getEntity().getHp()),120,70);
        Label moveL = modifyLabel(new Label(""+entityCard.getEntity().getMovement()), 60,120);
        anchorPane.getChildren().add(attackL);
        anchorPane.getChildren().add(liveL);
        anchorPane.getChildren().add(moveL);
        anchorPane.setOnMouseEntered(event -> cardView.getChildren().add(CardViewController.getCardDetailView(entityCard,1)));
        anchorPane.setOnMouseExited(event -> cardView.getChildren().clear());
        addEventHandler(anchorPane);
        return anchorPane;
    }

    private Label modifyLabel(Label label,int x, int y){
        label.setLayoutX(x*scale);
        label.setLayoutY(y*scale);
        label.setPrefSize(50*scale,50*scale);
        label.setFont(new Font(24*scale));
        label.setAlignment(Pos.CENTER);
        label.setStyle("-fx-font-size: "+24*scale+";");
        label.setStyle("-fx-font-weight: bold;");
        return label;
    }

    private AnchorPane getNodeFromGridPane(int col, int row) {
        for (Node node : arenaGridPane.getChildren()) {
            if (node instanceof AnchorPane){
                AnchorPane pane = (AnchorPane) node;
                if (GridPane.getColumnIndex(pane) == col && GridPane.getRowIndex(pane) == row) {
                    return pane;
                }
            }
        }
        return null;
    }

    private void markField(int x, int y){
        AnchorPane content = getNodeFromGridPane(x,y);
        content.setStyle("-fx-background-color: black");
    }

    public AnchorPane getSelected() {
        return selected;
    }

    public void setSelected(AnchorPane selected) {
        this.selected = selected;
    }

    private void addEventHandler(AnchorPane pane){
        pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                AnchorPane source = (AnchorPane) event.getSource();
                AnchorPane selected = getSelected();
                if (source != null){
                    Integer colIndex = GridPane.getColumnIndex(source);
                    Integer rowIndex = GridPane.getRowIndex(source);
                    if (source.getChildren().isEmpty()){
                        if (selected != null){
                            AnchorPane anchorPane = getNodeFromGridPane(colIndex,rowIndex);
                            Integer colIndexSelected = GridPane.getColumnIndex(selected);
                            Integer rowIndexSelected = GridPane.getRowIndex(selected);
                            if (( (colIndex==colIndexSelected+1 && rowIndex==rowIndexSelected)|| (colIndex == colIndexSelected-1 && rowIndex==rowIndexSelected)) || ((rowIndex == rowIndexSelected-1 && colIndex == colIndexSelected)|| (rowIndex == rowIndexSelected+1 && colIndex == colIndexSelected))){
                                arenaGridPane.getChildren().remove(selected);
                                arenaGridPane.getChildren().remove(source);
                                arenaGridPane.add(selected,colIndex,rowIndex);
                                arenaGridPane.add(anchorPane,colIndexSelected,rowIndexSelected);
                                friendly.put(new Coordinate(colIndex,rowIndex),friendly.remove(new Coordinate(colIndexSelected,rowIndexSelected)));
                                setSelected(null);
                                for (Node node : arenaGridPane.getChildren()) {
                                    if (node instanceof AnchorPane){
                                        AnchorPane pane = (AnchorPane) node;
                                        pane.setStyle("-fx-background-color: none;");
                                    }
                                }
                            }
                        }
                    } else {
                        if (selected == null){
                            if (opponent.containsKey(new Coordinate(colIndex,rowIndex))){
                                return;
                            }
                            for (Node node : arenaGridPane.getChildren()) {
                                if (node instanceof AnchorPane){
                                    AnchorPane pane = (AnchorPane) node;
                                    pane.setStyle("-fx-background-color: none;");
                                }
                            }
                            source.setStyle("-fx-background-color: black");
                            if (colIndex!=0){
                                markField(colIndex-1,rowIndex);
                            }
                            if (colIndex < arenaGridPane.getColumnCount()){
                                markField(colIndex+1,rowIndex);
                            }
                            if (rowIndex!=0){
                                markField(colIndex,rowIndex-1);
                            }
                            if (rowIndex < arenaGridPane.getRowCount()){
                                markField(colIndex,rowIndex+1);
                            }
                            setSelected(source);
                        } else {
                            if (friendly.containsKey(new Coordinate(colIndex,rowIndex))){
                                for (Node node : arenaGridPane.getChildren()) {
                                    if (node instanceof AnchorPane){
                                        AnchorPane pane = (AnchorPane) node;
                                        pane.setStyle("-fx-background-color: none;");
                                    }
                                }
                                setSelected(null);
                            } else {
                                Integer colIndexSelected = GridPane.getColumnIndex(selected);
                                Integer rowIndexSelected = GridPane.getRowIndex(selected);
                                System.out.println(colIndexSelected +" "+rowIndexSelected+" "+colIndex+" "+rowIndex);
                                for (Coordinate c:
                                        friendly.keySet()) {
                                    System.out.println(c.getX() +" "+c.getY());
                                }
                                List<EntityCard> cards = BattleManager2.getInstance().battle(getFriendly().get(new Coordinate(colIndexSelected,rowIndexSelected)),getOpponent().get(new Coordinate(colIndexSelected,rowIndex)));
                                arenaGridPane.getChildren().remove(selected);
                                arenaGridPane.getChildren().remove(source);
                                if (cards.isEmpty()){
                                    return;
                                }else {
                                    for (EntityCard card:
                                         cards) {
                                        if (friendly.containsValue(card)){
                                            placeCardFriendly(card, new Coordinate(colIndexSelected,rowIndexSelected));
                                        } else {
                                            placeCardOpponent(card, new Coordinate(colIndexSelected,rowIndex));
                                        }
                                    }
                                }
                                removeHighlight();
                            }
                        }
                    }
                    }
            }
        });
    }

    public HashMap<Coordinate, EntityCard> getFriendly() {
        return friendly;
    }

    public HashMap<Coordinate, EntityCard> getOpponent() {
        return opponent;
    }

    private void removeHighlight(){
        for (Node node : arenaGridPane.getChildren()) {
            if (node instanceof AnchorPane){
                AnchorPane pane = (AnchorPane) node;
                pane.setStyle("-fx-background-color: none;");
            }
        }
    }

    @FXML
    public void initialize()
    {
        initBattle(8,8);
    }

}
