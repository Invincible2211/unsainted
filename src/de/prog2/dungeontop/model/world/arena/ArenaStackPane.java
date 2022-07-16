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

@Deprecated
public class ArenaStackPane extends StackPane
{
    Coordinate coordinate;
    private boolean isSelected = false;

    public ArenaStackPane (Coordinate coordinate)
    {
        this.coordinate = coordinate;
        addEventHandler(MouseEvent.MOUSE_PRESSED, new MyEventHandler());
      //  addEventHandler(MouseEvent.MOUSE_RELEASED, new MyEventHandler());
    }

    private class MyEventHandler implements EventHandler<Event>
    {
        @Override
        public void handle(Event evt) {
            if (evt.getEventType().equals(MouseEvent.MOUSE_PRESSED)){
                GlobalLogger.log(LoggerStringValues.ARENA_TILE_PRESSED_MESSAGE + coordinate.toString());

                    if (BattleManager.getInstance().getArena().getEntity(coordinate.getX(), coordinate.getY()) != null)
                    {
                        GlobalLogger.log(BattleManager.getInstance().getArena().getEntity(coordinate.getX(), coordinate.getY()).getName() + LoggerStringValues.WAS_SELECTED);
                    }
                BattleManager.getInstance().arenaTilePressed(coordinate);
                    evt.consume();
                return;
            }
            if (evt.getEventType().equals(MouseEvent.MOUSE_RELEASED)){
                GlobalLogger.log(LoggerStringValues.ARENA_TILE_RELEASED_MESSAGE + coordinate.toString());
                BattleManager.getInstance().arenaTileReleased(coordinate);
                evt.consume();
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

    public boolean isSelected ()
    {
        return isSelected;
    }

    public void setSelected (boolean selected)
    {
        isSelected = selected;
    }
}
