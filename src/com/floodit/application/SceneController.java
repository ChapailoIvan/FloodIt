package com.floodit.application;

import com.floodit.game.Field;
import javafx.stage.Stage;

/* SceneController provides an opportunity to control active scenes, change scenes on stage.
   Each scene has its own and unique ID (basically ID = position in the Scene array), so scenes
   can be easily changed using these IDs.

    There are ... scenes in FloodIt application:
    1. WelcomeScene, ID = 0
    2. GameScene, ID = 1

    */

public class SceneController {
    private final Stage primaryStage;
    private final Field field;
    private int iterator;

    public SceneController(Stage primaryStage, Field field) {
        this.primaryStage = primaryStage;
        this.iterator = -1;
        this.field = field;
    }

    public void next() {
        switch (++iterator) {
            case 0: {
                primaryStage.setScene(SceneCreator.createWelcomeScene(this));
                break;
            }
            case 1: {
                primaryStage.setScene(SceneCreator.createGameScene(field, this));
                break;
            }
        }
    }

    public void prev() {
        if (iterator > 0) {
            switch (--iterator) {
                case 0: {
                    primaryStage.setScene(SceneCreator.createWelcomeScene(this));
                    break;
                }
                case 1: {
                    primaryStage.setScene(SceneCreator.createGameScene(field, this));
                    break;
                }
            }
        }
    }
}
