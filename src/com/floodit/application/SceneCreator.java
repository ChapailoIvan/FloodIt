package com.floodit.application;

import com.floodit.game.Field;
import com.floodit.game.RecolorController;
import com.floodit.game.WinChecker;
import javafx.animation.FadeTransition;
import javafx.animation.StrokeTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Objects;

class SceneCreator {
    private static ImageView getGameLogo(int x, int y) {
        ImageView gameLogoView =
                new ImageView(
                        new Image(Objects.requireNonNull(SceneCreator.class.getResourceAsStream("/img/logo.png"))));

        gameLogoView.setPreserveRatio(true);

        gameLogoView.setFitWidth(400);
        gameLogoView.setFitHeight(400);

        gameLogoView.setX(x);
        gameLogoView.setY(y);

        return gameLogoView;
    }

    static Scene createWelcomeScene(SceneController sceneController) {
        // Adding image to the scene
        ImageView gameLogoView = getGameLogo(600, 300);

        // Adding text to the scene
        Text gameNameText = new Text(200, 180, "FloodIt");
        gameNameText.
                setFont(Font.loadFont(SceneCreator.class.getResourceAsStream("/font/main_font.ttf"), 50));

        Text continueText = new Text(220, 350, "press enter to continue");
        continueText.
                setFont(Font.loadFont(SceneCreator.class.getResourceAsStream("/font/main_font.ttf"), 12));

        // Adding 'blink' animation to continueText
        StrokeTransition blink = new StrokeTransition(Duration.millis(1200), continueText, Color.WHITE, Color.DIMGRAY);
        blink.setAutoReverse(true);
        blink.setCycleCount(Integer.MAX_VALUE);
        blink.play();

        // Adding 'fade' animation to FloodIt logo
        FadeTransition fade = new FadeTransition(Duration.millis(1000), gameLogoView);
        fade.setFromValue(0.3);
        fade.setToValue(1.0);
        fade.play();

        Scene welcomeScene = new Scene(new Group(gameLogoView, gameNameText, continueText));

        // Adding key listener, that provides a possibility to
        // change welcome scene to game scene
        welcomeScene.setOnKeyPressed(event -> { if (event.getCode() == KeyCode.ENTER) sceneController.next(); });

        return welcomeScene;
    }

    static Scene createGameScene(Field field, SceneController sceneController) {
        // Creating static game scene context
        Rectangle fieldFrame =
                new Rectangle(100, 70, 420, 420);

        fieldFrame.setStroke(Color.BLACK);
        fieldFrame.setStrokeWidth(3);

        // JavaFX parent, that contains nodes on game scene
        Group gameSceneElements = new Group(fieldFrame);

        // Continue creating static game context
        ColorCodeInfo.createInfoRectangles(gameSceneElements);
        ColorCodeInfo.createInfoText(gameSceneElements);

        GameSceneController gameSceneController = new GameSceneController(new GameSpace(14, 14, field),
                                                                 new ProgressIndicator(720, 300, gameSceneElements),
                                                                 new ProgressIndicator(720, 140, gameSceneElements));

        // Adding gameSpace to Group
        gameSceneElements.getChildren().addAll(gameSceneController.getGameSpace().getRectangles());

        Scene gameScene = new Scene(gameSceneElements);

        RecolorController recolorController = new RecolorController(field);

        WinChecker winChecker = new WinChecker(field.getTerritory());

        Text winningText = new Text(200, 265, "congratulations\n    you won!");

        gameScene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case B: {
                    winChecker.increaseCurrentNumOfSteps();
                    recolorController.recolor(0);
                    gameSceneController.updateGameScene(0, winChecker);
                    break;
                }
                case R: {
                    winChecker.increaseCurrentNumOfSteps();
                    recolorController.recolor(1);
                    gameSceneController.updateGameScene(1, winChecker);
                    break;
                }
                case G: {
                    winChecker.increaseCurrentNumOfSteps();
                    recolorController.recolor(2);
                    gameSceneController.updateGameScene(2, winChecker);
                    break;
                }
                case Y: {
                    winChecker.increaseCurrentNumOfSteps();
                    recolorController.recolor(3);
                    gameSceneController.updateGameScene(3, winChecker);
                    break;
                }
                case P: {
                    winChecker.increaseCurrentNumOfSteps();
                    recolorController.recolor(4);
                    gameSceneController.updateGameScene(4, winChecker);
                    break;
                }
                case O: {
                    winChecker.increaseCurrentNumOfSteps();
                    recolorController.recolor(5);
                    gameSceneController.updateGameScene(5, winChecker);
                    break;
                }
                case ENTER: {
                    if (winChecker.isWinningGame()) { gameSceneElements.getChildren().remove(winningText); }

                    gameSceneController.reset();
                    recolorController.updateStartingColor();
                    winChecker.reset();
                    break;
                }

                case ESCAPE: {
                    sceneController.prev();

                    gameSceneController.reset();
                    recolorController.updateStartingColor();
                    winChecker.reset();
                    break;
                }
            }

            winChecker.updateCapturedTerritory(recolorController.recolor(-1));

            if (winChecker.isFinished()) {
                if (winChecker.isWinningGame() &&
                        !gameSceneElements.getChildren().contains(winningText)) {
                    gameSceneController.clearGameSpace();

                    winningText.setFont(Font.loadFont(SceneCreator.class.getResourceAsStream("/font/main_font.ttf"), 20));
                    gameSceneElements.getChildren().add(winningText);
                } else {
                    gameSceneController.reset();
                    recolorController.updateStartingColor();
                    winChecker.reset();
                }
            }
        });

        return gameScene;
    }
}
