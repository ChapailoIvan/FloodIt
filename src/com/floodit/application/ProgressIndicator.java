package com.floodit.application;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Arrays;

public class ProgressIndicator {
    private final Group parent;
    private final Rectangle[] progress = new Rectangle[25];

    public Rectangle[] getProgress() {
        return progress;
    }

    public ProgressIndicator(int x, int y, Group parent) {
        this.parent = parent;
        createProgressRect(x, y);
    }

     public void createProgressRect(int x, int y) {
        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 5; ++j) {
                progress[i * 5 + j] = new Rectangle(x + j * 25, y + i * 25, 25, 25);
                progress[i * 5 + j].setFill(Color.WHITE);
                progress[i * 5 + j].setStrokeWidth(1);
                progress[i * 5 + j].setStroke(Color.BLACK);
                parent.getChildren().add( progress[i * 5 + j]);
            }
        }
    }

    public void setColor(int x, int y, Color color) {
        progress[x + y * 5].setFill(color);
    }

    public void reset() {
        Arrays.stream(progress).forEach(p -> p.setFill(Color.WHITE));
    }
}
