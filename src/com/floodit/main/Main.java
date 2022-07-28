package com.floodit.main;

import com.floodit.application.SceneController;
import com.floodit.game.Field;
import com.floodit.game.FieldColorizer;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private void initPrimaryStage(Stage primaryStage) {
        primaryStage.setTitle(" FloodIt");
        primaryStage.setWidth(1000);
        primaryStage.setHeight(600);
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/logo.png"))));
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
    }

    @Override
    public void start(Stage primaryStage) {
        Field field = new Field(14, 14);

        FieldColorizer.colorizeField(field);

        initPrimaryStage(primaryStage);

        SceneController sceneController = new SceneController(primaryStage, field);

        sceneController.next();

        primaryStage.show();
    }
}