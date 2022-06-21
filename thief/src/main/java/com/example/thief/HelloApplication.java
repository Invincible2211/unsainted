package com.example.thief;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HelloApplication extends Application
{
    private GameMenu gameMenu;
    @Override
    public void start(Stage stage) throws IOException
    {
        Pane root = new Pane();
        root.setPrefSize(800,600);
        InputStream inputStream = Files.newInputStream(Paths.get("src/main/thumb-1920-407472.jpg"));
        Image image = new Image(inputStream);
        inputStream.close();
        InputStream inputStream1 = Files.newInputStream(Paths.get("src/main/607186.jpg"));
        Image icon = new Image(inputStream1);
        inputStream1.close();
        stage.getIcons().add(icon);

        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(600);
        imageView.setFitWidth(800);

        gameMenu = new GameMenu();
        gameMenu.setVisible(false);
        root.getChildren().addAll(imageView, gameMenu);
        Scene scene = new Scene(root);
        scene.setOnKeyPressed(event ->
        {
            if (event.getCode() == KeyCode.ESCAPE)
            {
                if (!gameMenu.isVisible())
                {
                    FadeTransition ft = new FadeTransition(Duration.seconds(0.5), gameMenu);
                    ft.setFromValue(0);
                    ft.setToValue(1);

                    gameMenu.setVisible(true);
                    ft.play();
                }
                else
                {
                    FadeTransition ft = new FadeTransition(Duration.seconds(0.5), gameMenu);
                    ft.setFromValue(1);
                    ft.setToValue(0);
                    ft.setOnFinished(evt -> gameMenu.setVisible(false));
                    ft.play();
                }
            }

        });

        stage.setScene(scene);
        stage.show();

    }

    private class GameMenu extends Parent
    {
        public GameMenu()
        {
            VBox menu0 = new VBox(10);
            VBox menu1 = new VBox(10);

            menu0.setTranslateX(100);
            menu0.setTranslateY(200);
            menu1.setTranslateX(100);
            menu1.setTranslateY(200);

            final int offset = 400;

            menu1.setTranslateX(offset);

            MenuButton resume = new MenuButton("Resume");
            resume.setOnMouseClicked(event ->
            {
                FadeTransition fade = new FadeTransition(Duration.seconds(0.5), this);
                fade.setFromValue(1);
                fade.setToValue(0);
                fade.setOnFinished(event1 ->
                {
                    setVisible(false);
                });
                fade.play();
            });

            MenuButton options = new MenuButton("Options");
            options.setOnMouseClicked(event ->
            {
                getChildren().add(menu1);

                TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.25), menu0);
                translateTransition.setToX(menu0.getTranslateX()-offset);
                TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), menu1);
                translateTransition1.setToX(menu0.getTranslateX());

                translateTransition.play();
                translateTransition1.play();
                translateTransition.setOnFinished(event1 ->
                {
                    getChildren().remove(menu0);
                });
            });

            MenuButton exit = new MenuButton("Exit");
            exit.setOnMouseClicked(event ->
            {
                System.exit(0);
            });

            MenuButton back = new MenuButton("Back");
            back.setOnMouseClicked( event ->
            {
                getChildren().add(menu0);

                TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.25), menu1);
                translateTransition.setToX(menu1.getTranslateX()+offset);
                TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), menu0);
                translateTransition1.setToX(menu1.getTranslateX());

                translateTransition.play();
                translateTransition1.play();
                translateTransition.setOnFinished(event1 ->
                {
                    getChildren().remove(menu1);
                });

            });

            MenuButton sound = new MenuButton("Sound");
            MenuButton video = new MenuButton("Video");

            menu0.getChildren().addAll(resume,options,exit);
            menu1.getChildren().addAll(back,sound,video);
            Rectangle rectangle = new Rectangle(800,600);
            rectangle.setFill(Color.GRAY);
            rectangle.setOpacity(0.4);
            getChildren().addAll(rectangle, menu0);
        }
    }

    private static class MenuButton extends StackPane
    {
        private Text text;

        public MenuButton(String name)
        {
            text = new Text(name);
            text.setFont(text.getFont().font(20));
            text.setFill(Color.WHITE);
            Rectangle bg = new Rectangle(250, 30);
            bg.setOpacity(0.6);
            bg.setFill(Color.BLACK);
            bg.setEffect(new GaussianBlur(3.5));
            setAlignment(Pos.CENTER_LEFT);
            setRotate(0.5);
            getChildren().addAll(bg, text);

            setOnMouseEntered(event ->
            {
                bg.setTranslateX(10);
                text.setTranslateX(10);
                bg.setFill(Color.WHITE);
                text.setFill(Color.BLACK);
            });

            setOnMouseExited(event ->
            {
                bg.setTranslateX(0);
                text.setTranslateX(0);
                bg.setFill(Color.BLACK);
                text.setFill(Color.WHITE);
            });

            DropShadow shadow = new DropShadow(50, Color.WHITE);
            shadow.setInput(new Glow());

            setOnMousePressed(event -> setEffect(shadow));
            setOnMouseReleased(event -> setEffect(null));
        }
    }

    public static void main(String[] args) {
        launch();
    }
}