package de.prog2.dungeontop.view;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Window;

import java.util.ArrayList;
import java.util.List;

public class SceneScaler {

    private static double xScale = 0.5;
    private static double yScale = 0.5;

    public static void scaleScene(Scene scene){
        Window window = scene.getWindow();;
        window.setWidth(window.getWidth()*xScale);
        window.setHeight(window.getHeight()*yScale);
    }

    private static List<Node> getAllNodesInParent(Parent parent) {
        List<Node> components = new ArrayList<>();
        for (Node child : parent.getChildrenUnmodifiable()) {
            components.add(child);
            if (child instanceof Parent) {
                components.addAll(getAllNodesInParent((Parent) child));
            }
        }
        return components;
    }

    private static void resizeComponent(Node component){
        component.setLayoutX(component.getLayoutX() * xScale);
        component.setLayoutY(component.getLayoutY() * yScale);
        component.prefWidth(component.localToScene(component.getBoundsInParent()).getWidth() * xScale);
        component.prefHeight(component.localToScene(component.getBoundsInParent()).getHeight() * yScale);
        component.minWidth(component.localToScene(component.getBoundsInParent()).getWidth() * xScale);
        component.minHeight(component.localToScene(component.getBoundsInParent()).getHeight() * yScale);
        component.maxWidth(component.localToScene(component.getBoundsInParent()).getWidth() * xScale);
        component.maxHeight(component.localToScene(component.getBoundsInParent()).getHeight() * yScale);
    }

}
