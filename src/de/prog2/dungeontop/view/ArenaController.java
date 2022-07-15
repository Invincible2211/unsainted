package de.prog2.dungeontop.view;

import de.prog2.dungeontop.control.controller.CardViewController;
import de.prog2.dungeontop.control.controller.EntityViewController;
import de.prog2.dungeontop.control.manager.BattleManager2;
import de.prog2.dungeontop.control.manager.GameManager;
import de.prog2.dungeontop.control.manager.PlayerManager;
import de.prog2.dungeontop.control.network.NetManager;
import de.prog2.dungeontop.model.entities.Entity;
import de.prog2.dungeontop.model.game.EntityCard;
import de.prog2.dungeontop.model.world.Coordinate;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;

public class ArenaController
{
    private double scale;

    private AnchorPane selected = null;

    // TODO change EntityCard to Entity
    private HashMap<Coordinate, EntityCard> friendly = new HashMap<Coordinate, EntityCard>();
    private HashMap<Coordinate, EntityCard> opponent = new HashMap<Coordinate, EntityCard>();

    @FXML
    private Label currentPhase;

    @FXML
    Label labelEgoPointsPlayer;

    @FXML
    Label labelEgoPointOpponent;

    @FXML
    private PlayerHandView playerCardView;

    @FXML
    private EnemyHandView enemyCardView;

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
        BattleManager2.getInstance().endBattle(GameManager.getInstance().isDM());
    }

    public void initBattle(int width, int height){
        clear();
        setPlayerCardView(new PlayerHandView());
        setEnemyCardView(new EnemyHandView());
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

    public void placeCardFriendly(EntityCard entityCard, Coordinate coordinate){
        AnchorPane card = createCard(entityCard);
        AnchorPane test = getNodeFromGridPane(coordinate.getX(), coordinate.getY());
        arenaGridPane.getChildren().remove(test);
        arenaGridPane.add(card, coordinate.getX(), coordinate.getY());
        friendly.put(coordinate,entityCard);
        NetManager.getInstance().getNetworkAPI().sendSpawnEntity(entityCard, coordinate);
    }

    public void placeCardOpponent(EntityCard entityCard, Coordinate coordinate){
        AnchorPane card = createCard(entityCard);
        AnchorPane test = getNodeFromGridPane(coordinate.getX(), coordinate.getY());
        arenaGridPane.getChildren().remove(test);
        arenaGridPane.add(card, coordinate.getX(), coordinate.getY());
        opponent.put(coordinate,entityCard);
    }

    private AnchorPane createCard(EntityCard entityCard)
    {
        // this has to be done until the entityCard ist finally replaced with entity
        Entity entity = entityCard.getEntity();
        entity.setCard(entityCard);

        AnchorPane anchorPane = EntityViewController.getEntityView(entity, scale);

        anchorPane.setOnMouseEntered(event -> cardView.getChildren().add(CardViewController.getCardDetailView(entity.getCard(),1)));
        anchorPane.setOnMouseExited(event -> cardView.getChildren().clear());
        addEventHandler(anchorPane);
        return anchorPane;
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
        if (content!= null){
            content.setStyle("-fx-background-color: black");
        }
    }

    public AnchorPane getSelected() {
        return selected;
    }

    public void setSelected(AnchorPane selected) {
        this.selected = selected;
    }

    private void addEventHandler(AnchorPane pane)
    {
        pane.setOnMouseClicked(event -> {
            // TODO What is the difference between pane and source? (Not always the same??)
            AnchorPane source = (AnchorPane) event.getSource();
            AnchorPane selected = getSelected();
            if (source == null)
            {
                return;
            }
            if (source.getChildren().isEmpty()) // if the source is an empty field
            {
                handleMoveSelected(source, selected);
                return;
            }
            if (selected == null)
            {
                handleSelect(source);
                return;
            }

            Coordinate sourcePos = new Coordinate(GridPane.getColumnIndex(source), GridPane.getRowIndex(source));
            if (friendly.containsKey(sourcePos))
            {
                for (Node node : arenaGridPane.getChildren())
                {
                    if (node instanceof AnchorPane pane1)
                    {
                        pane1.setStyle("-fx-background-color: none;");
                    }
                }
                setSelected(null);
            }
            else
            {
                Coordinate selectedPos = new Coordinate(GridPane.getColumnIndex(selected), GridPane.getRowIndex(selected));
                for (Coordinate c : friendly.keySet())
                {
                    // TODO: Was soll dieses foreach machen?
                }
                List<EntityCard> cards = BattleManager2.getInstance().battle(getFriendly().get(selectedPos), getOpponent().remove(sourcePos));

                arenaGridPane.getChildren().remove(source);
                opponent.remove(sourcePos);
                if (!cards.isEmpty())
                {
                    getOpponent().put(sourcePos, cards.get(0));
                }
                else
                {
                    NetManager.getInstance().getNetworkAPI().sendRemoveEntity(sourcePos);
                    AnchorPane anchorPane = new AnchorPane();
                    anchorPane.setPrefSize(170*scale, 170*scale);
                    addEventHandler(anchorPane);
                    arenaGridPane.add(anchorPane, sourcePos.getX(), sourcePos.getY());
                }
                removeHighlight();
                setSelected(null);
            }
        });
        pane.setOnMousePressed(event->{
            Coordinate cord = new Coordinate(GridPane.getColumnIndex((Node) event.getSource()), GridPane.getRowIndex((Node) event.getSource()));

            System.out.println("mousepressed event in arenacontroller" + cord.getX() + " " + cord.getY() );
        });
        pane.setOnMouseReleased(event->{
            Coordinate cord = new Coordinate(GridPane.getColumnIndex(event.getPickResult().getIntersectedNode()), GridPane.getRowIndex(event.getPickResult().getIntersectedNode()));

            System.out.println("mousereleased event in arenacontroller " + cord.toString());
        });
    }

    /**
     * Handles the selection of a field.
     * @param source the field that was clicked
     */
    private void handleSelect(AnchorPane source)
    {
        Coordinate sourcePos = new Coordinate(GridPane.getColumnIndex(source), GridPane.getRowIndex(source));
        if (opponent.containsKey(sourcePos)){
            return;
        }
        removeHighlight();
        source.setStyle("-fx-background-color: black;");
        markAvailabelFields(sourcePos);
        setSelected(source);
    }

    /**
     * Handles the move of a selected entity to a new field.
     * @param source The current field.
     * @param selected The selected field.
     */
    private void handleMoveSelected(AnchorPane source, AnchorPane selected)
    {
        Coordinate currentPos = new Coordinate(GridPane.getColumnIndex(source), GridPane.getRowIndex(source));
        Coordinate selectedPos = new Coordinate(GridPane.getColumnIndex(selected), GridPane.getRowIndex(selected));

        if (selected == null || isOutOfRange(currentPos, selectedPos)) return;

        moveSourceToSelectedField(true, currentPos, selectedPos);

        setSelected(null);
        removeHighlight();
        NetManager.getInstance().getNetworkAPI().sendMoveEntity(selectedPos, currentPos);
    }

    private void markAvailabelFields(Coordinate sourcePos){
        if (sourcePos.getX() !=0){
            markField(sourcePos.getX() -1, sourcePos.getY());
        }
        if (sourcePos.getX() < arenaGridPane.getColumnCount()){
            markField(sourcePos.getX() +1, sourcePos.getY());
        }
        if (sourcePos.getY() !=0){
            markField(sourcePos.getX(), sourcePos.getY() -1);
        }
        if (sourcePos.getY() < arenaGridPane.getRowCount()){
            markField(sourcePos.getX(), sourcePos.getY() +1);
        }
    }

    /**
     * @param friendlyMove True if the friendly entity is moving, false if the opponent is moving.
     * @param currentPos The current position of the entity.
     * @param targetPos The target position of the entity.
     */
    private void moveSourceToSelectedField(boolean friendlyMove, Coordinate currentPos, Coordinate targetPos)
    {
        AnchorPane currentPane = getNodeFromGridPane(currentPos.getX(), currentPos.getY());
        AnchorPane targetPane  = getNodeFromGridPane(targetPos.getX(), targetPos.getY());

        arenaGridPane.getChildren().remove(targetPane);
        arenaGridPane.getChildren().remove(currentPane);
        arenaGridPane.add(targetPane, currentPos.getX(), currentPos.getY());
        arenaGridPane.add(currentPane, targetPos.getX(), targetPos.getY());

        if (friendlyMove)
        {
            friendly.put(currentPos, friendly.remove(targetPos));
        }
        else
        {
            currentPane.setStyle("-fx-background-color: black");
            opponent.put(targetPos, opponent.remove(currentPos));
        }
    }

    // TODO: Methodennamen passend umbenennen
    //TODO: auf range pruefen anstatt magic number 1
    /**
     * Checks if the selected field is in range of the source field
     */
    private boolean isOutOfRange(Coordinate currentPos, Coordinate targetPos)
    {
        return (currentPos.getX() != targetPos.getX() + 1 || currentPos.getY() != targetPos.getY()) &&
                (currentPos.getX() != targetPos.getX() - 1 || currentPos.getY() != targetPos.getY()) &&
                (currentPos.getY() != targetPos.getY() - 1 || currentPos.getX() != targetPos.getX()) &&
                (currentPos.getY() != targetPos.getY() + 1 || currentPos.getX() != targetPos.getX());
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
        System.out.println(labelEgoPointsPlayer == null);
        labelEgoPointsPlayer.textProperty().bind(PlayerManager.getInstance().getPlayer().currentEgoPointsProperty().asString());
    }

    public void move(Coordinate pos, Coordinate target)
    {
        moveSourceToSelectedField(false, pos, target);
    }

    public void remove(Coordinate pos) {
        friendly.remove(pos);
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(170*scale, 170*scale);
        addEventHandler(anchorPane);
        AnchorPane target = getNodeFromGridPane(pos.getX(), pos.getY());
        arenaGridPane.getChildren().remove(target);
        arenaGridPane.add(anchorPane,pos.getX(),pos.getY());
    }

    public PlayerHandView getPlayerCardView() {
        return playerCardView;
    }

    public void setPlayerCardView(PlayerHandView playerCardView) {
        this.playerCardView = playerCardView;
    }

    public EnemyHandView getEnemyCardView() {
        return enemyCardView;
    }

    public void setEnemyCardView(EnemyHandView enemyCardView) {
        this.enemyCardView = enemyCardView;
    }

    public Label getLabelEgoPointsPlayer() {
        return labelEgoPointsPlayer;
    }

    public Label getLabelEgoPointOpponent() {
        return labelEgoPointOpponent;
    }
}
