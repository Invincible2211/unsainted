package de.prog2.dungeontop.view;

import de.prog2.dungeontop.control.controller.CardViewController;
import de.prog2.dungeontop.control.controller.EntityViewController;
import de.prog2.dungeontop.control.manager.BattleManager2;
import de.prog2.dungeontop.control.manager.GameManager;
import de.prog2.dungeontop.control.manager.PlayerManager;
import de.prog2.dungeontop.control.network.NetManager;
import de.prog2.dungeontop.model.entities.Entity;
import de.prog2.dungeontop.model.entities.Talent;
import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.model.game.EntityCard;
import de.prog2.dungeontop.model.game.SpellCard;
import de.prog2.dungeontop.model.world.Coordinate;
import de.prog2.dungeontop.model.world.arena.Arena;
import de.prog2.dungeontop.resources.views.ArenaViewConstants;
import de.prog2.dungeontop.utils.ArenaUtils;
import de.prog2.dungeontop.view.handViews.EnemyHandView;
import de.prog2.dungeontop.view.handViews.PlayerHandView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import org.apache.commons.lang3.SerializationUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ArenaController
{
    private double tilepercentScale;

    private AnchorPane selected = null;

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
    private Button nextPhaseButton;

    @FXML
    private void onNextRound(){
        BattleManager2.getInstance().nextPhase();
        NetManager.getInstance().getNetworkAPI().sendNextRoundPackage();
        removeHighlight();
    }

    @FXML
    AnchorPane root;

    private Arena currentArena;

    @FXML
    private void onSurrender(){
        BattleManager2.getInstance().endBattle(GameManager.getInstance().isDM(),true);
    }

    public void initBattle(Arena arena){
        clearHands();
        currentArena = arena;
        setupBattlefield(arena);
        clearField();
    }

    private void setupBattlefield (Arena arena)
    {
        arenaGridPane = new GridPane();
        for (int i = 0; i < arena.getWidth(); i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / arena.getWidth());
            arenaGridPane.getColumnConstraints().add(colConst);
        }
        for (int i = 0; i < arena.getWidth(); i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / arena.getWidth());
            arenaGridPane.getRowConstraints().add(rowConst);
        }

        arenaGridPane.setPrefSize(ArenaViewConstants.BATTLEFIELD_SIZE_X,ArenaViewConstants.BATTLEFIELD_SIZE_Y);
        arenaGridPane.setMaxSize(ArenaViewConstants.BATTLEFIELD_SIZE_X,ArenaViewConstants.BATTLEFIELD_SIZE_Y);
        arenaGridPane.setLayoutX(ArenaViewConstants.BATTLEFIELD_X_SIDE_OFFSET);
        arenaGridPane.setGridLinesVisible(true);

        tilepercentScale = ArenaViewConstants.BATTLEFIELD_SIZE_X/(double) arena.getHeight()/ArenaViewConstants.BATTLEFIELD_TILE_TARGET_SIZE;
        double prefwith = ArenaViewConstants.BATTLEFIELD_TILE_TARGET_SIZE* tilepercentScale;
        double prefheight = ArenaViewConstants.BATTLEFIELD_TILE_TARGET_SIZE* tilepercentScale;

        for (int i = 0; i < arena.getWidth(); i++) {
            for (int j = 0; j < arena.getHeight(); j++) {
                AnchorPane anchorPane = new AnchorPane();
                anchorPane.setPrefSize(prefwith,prefheight);
                addEventHandler(anchorPane);
                arenaGridPane.add(anchorPane,i,j);
            }
        }
        arenaGrid.getChildren().add(arenaGridPane);
    }

    public void setPhaseLabel(String text){
        Platform.runLater(() -> currentPhase.setText(text));
    }

    private void clearHands (){
        playerCardView.getChildren().clear();
        enemyCardView.getChildren().clear();
    }

    public void placeEntityFriendly (Entity entity, Coordinate coordinate){
        AnchorPane card = placeEntity(entity);
        AnchorPane test = getNodeFromGridPane(coordinate.getX(), coordinate.getY());
        arenaGridPane.getChildren().remove(test);
        arenaGridPane.add(card, coordinate.getX(), coordinate.getY());
        currentArena.getFriendly().put(coordinate, entity);
        NetManager.getInstance().getNetworkAPI().sendSpawnEntity(entity, invertCoordinate(coordinate));
    }

    public void placeEntityOpponent (Entity entity, Coordinate coordinate){
        AnchorPane card = placeEntity(entity);
        AnchorPane test = getNodeFromGridPane(coordinate.getX(), coordinate.getY());
        arenaGridPane.getChildren().remove(test);
        arenaGridPane.add(card, coordinate.getX(), coordinate.getY());
        currentArena.getOpponent().put(coordinate, entity);
    }

    private AnchorPane placeEntity (Entity entity)
    {
        AnchorPane anchorPane = EntityViewController.getEntityView(entity, tilepercentScale);
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
            handleEvent((AnchorPane) event.getSource());
        });
        /*
        pane.setOnMousePressed(event-> {

            handleEvent((AnchorPane) event.getSource());
        });
        pane.setOnMouseReleased(event-> {
            if (isArenaAnchorPane(event.getPickResult().getIntersectedNode())) handleEvent((AnchorPane) event.getPickResult().getIntersectedNode());
        });

         */
    }

    private boolean isArenaAnchorPane(Node node){
        return node instanceof AnchorPane && node != cardView && node != root && node != arenaGrid;
    }

    private void handleEvent(AnchorPane source)
    {
        if (!BattleManager2.getInstance().isTurn()){
            return;
        }
        AnchorPane selected = getSelected();
        if (source == null)
        {
            return;
        }
        if (source.getChildren().isEmpty()) // if the source is an empty field
        {
            if (BattleManager2.getInstance().getBattlePhase() == BattleManager2.BattlePhase.FIRST_DUELLIST_PLACE_CARDS
                    || BattleManager2.getInstance().getBattlePhase() == BattleManager2.BattlePhase.SECOND_DUELLIST_PLACE_CARDS
                    || BattleManager2.getInstance().getBattlePhase() == BattleManager2.BattlePhase.FIRST_DUELLIST_SECOND_PLACE_CARDS
                    || BattleManager2.getInstance().getBattlePhase() == BattleManager2.BattlePhase.SECOND_DUELLIST_SECOND_PLACE_CARDS)
            {
                if (BattleManager2.getInstance().getSelectedHandCard() != null)
                {
                    Card card = BattleManager2.getInstance().getSelectedHandCard();
                    if (PlayerManager.getInstance().getPlayerEgoPoints() >= card.getSummonCost())
                    {
                        removeHighlight();
                        PlayerManager.getInstance().removeEgoPoints(card.getSummonCost());
                        Coordinate sourcePos = new Coordinate(GridPane.getColumnIndex(source), GridPane.getRowIndex(source));
                        if (card instanceof EntityCard)
                        {
                            EntityCard entityCard = (EntityCard) card;
                            entityCard.getEntity().setMovement(entityCard.getEntity().getTalent() == Talent.SPEEDKNOT ? entityCard.getEntity().getMaxMovement() : 0);
                            placeEntityFriendly(SerializationUtils.clone(entityCard.getEntity()), sourcePos);
                            BattleManager2.getInstance().removeCardFromHand(card);
                        } else
                        {
                            SpellCard spellCard = (SpellCard) card;
                            spellCard.getSpell().cast(currentArena, sourcePos);
                            BattleManager2.getInstance().removeCardFromHand(card);
                        }
                    }
                    return;
                }
            }
            if (selected != null){
                handleMoveSelected(source, selected);
                return;
            }
        }
        if (BattleManager2.getInstance().getBattlePhase() == BattleManager2.BattlePhase.FIRST_DUELLIST_PLACE_CARDS
                || BattleManager2.getInstance().getBattlePhase() == BattleManager2.BattlePhase.SECOND_DUELLIST_PLACE_CARDS
                || BattleManager2.getInstance().getBattlePhase() == BattleManager2.BattlePhase.FIRST_DUELLIST_SECOND_PLACE_CARDS
                || BattleManager2.getInstance().getBattlePhase() == BattleManager2.BattlePhase.SECOND_DUELLIST_SECOND_PLACE_CARDS)
        {

            Card card = BattleManager2.getInstance().getSelectedHandCard();
            if (card != null){
                if (PlayerManager.getInstance().getPlayerEgoPoints() >= card.getSummonCost())
                {
                    removeHighlight();
                    Coordinate sourcePos = new Coordinate(GridPane.getColumnIndex(source), GridPane.getRowIndex(source));
                    if (card instanceof SpellCard)
                    {
                        SpellCard spellCard = (SpellCard) card;
                        spellCard.getSpell().cast(currentArena, sourcePos);
                        BattleManager2.getInstance().removeCardFromHand(card);
                    }
                }
                return;
            }
        }
        if (selected == null)
        {
            handleSelect(source);
            return;
        }

        Coordinate sourcePos = new Coordinate(GridPane.getColumnIndex(source), GridPane.getRowIndex(source));
        if (currentArena.getFriendly().containsKey(sourcePos))
        {
            removeHighlight();
            setSelected(null);
        }
        else
        {
            Coordinate selectedPos = new Coordinate(GridPane.getColumnIndex(selected), GridPane.getRowIndex(selected));
            battle(selectedPos, sourcePos);
        }
    }

    private void battle(Coordinate selectedPos, Coordinate sourcePos) {
        if (!isOutOfRange(selectedPos, sourcePos, getFriendly().get(selectedPos).getAttackRange()) && getFriendly().get(selectedPos).getMovement() > 0) {
            System.out.println("Hallo");
            List<Coordinate> targets = collectTargets(selectedPos, sourcePos);
            List<Entity> targetedEntities = new ArrayList<>();
            HashMap<Entity, Coordinate> oldCoordinates = new HashMap<>();
            for (Coordinate coordinate :
                    targets) {
                Entity e = getOpponent().remove(coordinate);
                if (e != null) {
                    targetedEntities.add(e);
                    oldCoordinates.put(e, coordinate);
                    if (e != null) {
                        targetedEntities.add(e);
                        oldCoordinates.put(e, coordinate);
                    }
                }
                List<Entity> cards = BattleManager2.getInstance().battle(getFriendly().get(selectedPos), targetedEntities);
                if (!cards.isEmpty()) {
                    for (Entity ent :
                            cards) {
                        getOpponent().put(oldCoordinates.get(ent), ent);
                        NetManager.getInstance().getNetworkAPI().sendAttackPackage(getFriendly().get(selectedPos), invertCoordinate(oldCoordinates.get(ent)));
                    }
                } else {
                    for (Coordinate c :
                            targets) {
                        removeEntity(c);
                    }
                }
                removeHighlight();
                setSelected(null);
            }
        }
    }

    private void removeEntity(Coordinate coordinate) {
        arenaGridPane.getChildren().remove(getNodeFromGridPane(coordinate.getX(),coordinate.getY()));
        NetManager.getInstance().getNetworkAPI().sendRemoveEntity(invertCoordinate(coordinate));
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(170* tilepercentScale, 170* tilepercentScale);
        addEventHandler(anchorPane);
        arenaGridPane.add(anchorPane, coordinate.getX(), coordinate.getY());
    }

    public void removeEntityInCoordinate(Coordinate c)
    {
        currentArena.getOpponent().remove(c);
        arenaGridPane.getChildren().remove(getNodeFromGridPane(c.getX(), c.getY()));
        NetManager.getInstance().getNetworkAPI().sendRemoveEntity(ArenaUtils.invertCoordinate(currentArena, c));
    }

    private List<Coordinate> collectTargets(Coordinate sourcePos, Coordinate selectedPos) {
        System.out.println(sourcePos +" " +selectedPos);
        List<Coordinate> targets = new ArrayList<>();
        if (isX(sourcePos, selectedPos)){
            System.out.println(getDifferenz(sourcePos.getX(),selectedPos.getX()));
            for (int i = 0; i < getDifferenz(sourcePos.getX(),selectedPos.getX()); i++) {
                Coordinate coordinate = new Coordinate(sourcePos.getX()>selectedPos.getX() ? selectedPos.getX() + i : sourcePos.getX() + i+1,selectedPos.getY());
                System.out.println(coordinate);
                if (getOpponent().get(coordinate) != null){
                    targets.add(coordinate);
                }
            }
        } else {
            for (int i = 0; i < getDifferenz(sourcePos.getY(),selectedPos.getY()); i++) {
                System.out.println(getDifferenz(sourcePos.getY(),selectedPos.getY()));
                Coordinate coordinate = new Coordinate(selectedPos.getX(),sourcePos.getY()>selectedPos.getY() ? selectedPos.getY() + i : sourcePos.getY() + i+1);
                System.out.println(coordinate);
                if (getOpponent().get(coordinate) != null){
                    targets.add(coordinate);
                }
            }
        }
        return targets;
    }

    private boolean isX(Coordinate sourcePos, Coordinate selectedPos) {
        return sourcePos.getX() != selectedPos.getX();
    }

    private int getDifferenz(int a, int b){
        return a > b ? a-b :b-a;
    }

    /**
     * Handles the selection of a field.
     * @param source the field that was clicked
     */
    private void handleSelect(AnchorPane source)
    {
        Coordinate sourcePos = new Coordinate(GridPane.getColumnIndex(source), GridPane.getRowIndex(source));
        if (currentArena.getOpponent().containsKey(sourcePos)){
            return;
        }
        removeHighlight();
        if (getFriendly().containsKey(sourcePos)){
            source.setStyle("-fx-background-color: black;");
            //markAvailabelFields(sourcePos);
            setSelected(source);
        }
    }

    /**
     * Handles the move of a selected entity to a new field.
     * @param source The current field.
     * @param selected The selected field.
     */
    private void handleMoveSelected(AnchorPane source, AnchorPane selected)
    {
        if (BattleManager2.getInstance().getBattlePhase() == BattleManager2.BattlePhase.FIRST_DUELLIST_MINION_ACT || BattleManager2.getInstance().getBattlePhase() == BattleManager2.BattlePhase.SECOND_DUELLIST_MINION_ACT)
        {
            Coordinate currentPos = new Coordinate(GridPane.getColumnIndex(source), GridPane.getRowIndex(source));
            Coordinate selectedPos = new Coordinate(GridPane.getColumnIndex(selected), GridPane.getRowIndex(selected));

            if (selected == null || isOutOfRange(currentPos, selectedPos, 1)) return;
            if (getFriendly().get(selectedPos).getMovement() > 0){
                getFriendly().get(selectedPos).setMovement(getFriendly().get(selectedPos).getMovement()-1);
                moveSourceToSelectedField(true, currentPos, selectedPos);
                setSelected(null);
                removeHighlight();
                NetManager.getInstance().getNetworkAPI().sendMoveEntity(invertCoordinate(selectedPos), invertCoordinate(currentPos));
            }
        }
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
            currentArena.getFriendly().put(currentPos, currentArena.getFriendly().remove(targetPos));
        }
        else
        {
            currentPane.setStyle("-fx-background-color: black");
            currentArena.getOpponent().put(targetPos, currentArena.getOpponent().remove(currentPos));
        }
    }

    // TODO: Methodennamen passend umbenennen
    //TODO: auf range pruefen anstatt magic number 1
    /**
     * Checks if the selected field is in range of the source field
     */
    private boolean isOutOfRange(Coordinate currentPos, Coordinate targetPos, int range)
    {
        if (isX(currentPos,targetPos)&& currentPos.getY() != targetPos.getY()){
            return true;
        }
        if (isX(currentPos,targetPos)){
            return !(getDifferenz(targetPos.getX(), currentPos.getX()) <= range);
        } else {
            return !(getDifferenz(targetPos.getY(), currentPos.getY()) <= range);
        }
    }

    private Coordinate invertCoordinate(Coordinate coordinate){
        return new Coordinate(currentArena.getWidth() -1 - coordinate.getX(), currentArena.getHeight() -1 - coordinate.getY());
    }

    public HashMap<Coordinate, Entity> getFriendly() {
        return currentArena.getFriendly();
    }

    public HashMap<Coordinate, Entity> getOpponent() {
        return currentArena.getOpponent();
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
        labelEgoPointOpponent.textProperty().bind(GameManager.getInstance().getOpponentPlayer().currentEgoPointsProperty().asString());
        labelEgoPointsPlayer.textProperty().bind(PlayerManager.getInstance().getPlayer().currentEgoPointsProperty().asString());
    }

    public void move(Coordinate pos, Coordinate target)
    {
        moveSourceToSelectedField(false, pos, target);
    }

    public void remove(Coordinate pos) {
        currentArena.getFriendly().remove(pos);
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(170* tilepercentScale, 170* tilepercentScale);
        addEventHandler(anchorPane);
        AnchorPane target = getNodeFromGridPane(pos.getX(), pos.getY());
        arenaGridPane.getChildren().remove(target);
        arenaGridPane.add(anchorPane,pos.getX(),pos.getY());
    }

    public PlayerHandView getPlayerCardView() {
        return playerCardView;
    }

    public EnemyHandView getEnemyCardView() {
        return enemyCardView;
    }

    public Label getLabelEgoPointsPlayer() {
        return labelEgoPointsPlayer;
    }

    public Label getLabelEgoPointOpponent() {
        return labelEgoPointOpponent;
    }

    public AnchorPane getCardDetailViewContainer() {
        return cardView;
    }

    public Button getNextPhaseButton() {
        return nextPhaseButton;
    }

    public void clearField ()
    {
        for (Node node : arenaGridPane.getChildren()) {
            if (node instanceof AnchorPane pane){
                pane.setStyle("-fx-background-color: none;");
            }
        }
        currentArena.getArenaHashmap().clear();
        currentArena.getOpponent().clear();
        currentArena.getFriendly().clear();
        if (!GameManager.getInstance().isDM()){
            GameManager.getInstance().getOpponentPlayer().getHero().setHp((int) (10+(Math.random() * 100)*30));
        }
    }
}
