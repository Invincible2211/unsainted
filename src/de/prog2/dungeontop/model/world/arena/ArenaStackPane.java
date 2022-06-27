package de.prog2.dungeontop.model.world.arena;

import de.prog2.dungeontop.control.manager.BattleManager;
import de.prog2.dungeontop.model.world.Coordinate;
import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.utils.GlobalLogger;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import javax.naming.ldap.Control;

public class ArenaStackPane extends StackPane
{

    Coordinate coordinate = new Coordinate(0, 0);

    public ArenaStackPane (Coordinate coordinate)
    {
        this.coordinate = coordinate;
        addEventHandler(MouseEvent.MOUSE_PRESSED, new MyEventHandler());
        addEventHandler(MouseEvent.MOUSE_RELEASED, new MyEventHandler());
    }

    private class MyEventHandler implements EventHandler<Event>
    {
        @Override
        public void handle(Event evt) {
            if (evt.getEventType().equals(MouseEvent.MOUSE_PRESSED)){
                GlobalLogger.log(LoggerStringValues.ARENA_TILE_PRESSED_MESSAGE + coordinate.toString());
                BattleManager.getInstance().arenaTilePressed(coordinate);
                return;
            }
            if (evt.getEventType().equals(MouseEvent.MOUSE_RELEASED)){
                GlobalLogger.log(LoggerStringValues.ARENA_TILE_RELEASED_MESSAGE + coordinate.toString());
                BattleManager.getInstance().arenaTileReleased(coordinate);
                return;
            }
            GlobalLogger.warning(LoggerStringValues.ARENA_TILE_UNHANDLEDEVENT + coordinate.toString());
        }
    }

    public Coordinate getCoordinate ()
    {
        return coordinate;
    }

    public void setCoordinate (Coordinate coordinate)
    {
        this.coordinate = coordinate;
    }
}
