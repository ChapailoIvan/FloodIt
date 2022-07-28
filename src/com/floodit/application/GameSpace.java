package com.floodit.application;

import com.floodit.game.Field;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Arrays;

public class GameSpace {
    static final Color[] colorsIDs =
            {Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW, Color.PINK, Color.ORANGE};

    private final int x;
    private final int y;

    private Rectangle[] gameSpace;
    private final Field field;

    public GameSpace(int x, int y, Field field) {
        this.x = x;
        this.y = y;
        this.field = field;

        gameSpace = new Rectangle[x * y];

        for (int i = 0; i < y; ++i) {
            for (int j = 0; j < x; ++j) {
                gameSpace[i * y + j] = new Rectangle(100 + j * 30, 70 + i * 30, 30, 30);
                gameSpace[i * y + j].setStrokeWidth(0.5);

                gameSpace[i * y + j].setFill(colorsIDs[field.getColor(i, j)]);
                gameSpace[i * y + j].setStroke(colorsIDs[field.getColor(i, j)]);
            }
        }
    }

    Field getField() {
        return field;
    }

    public Rectangle[] getRectangles() {
        return gameSpace;
    }

    public void updateGameSpace() {
        for (int i = 0; i < y; ++i) {
            for (int j = 0; j < x; ++j) {
                gameSpace[i * y + j].setFill(GameSpace.colorsIDs[field.getColor(i, j)]);
                gameSpace[i * y + j].setStroke(GameSpace.colorsIDs[field.getColor(i, j)]);
            }
        }
    }

    public void setAllColor(Color color) {
        Arrays.stream(gameSpace).forEach(p -> {p.setFill(color); p.setStroke(color);});
    }
}
