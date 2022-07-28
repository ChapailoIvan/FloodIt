package com.floodit.application;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.stream.Stream;

class ColorCodeInfo {
    static void createInfoRectangles(Group parent) {
        for (int i = 0; i < 6; ++i) {
            Rectangle rectangle =
                    new Rectangle(600, 160 + 45 * i, 20, 20);
            rectangle.setFill(GameSpace.colorsIDs[i]);

            rectangle.setStroke(Color.BLACK);
            rectangle.setStrokeWidth(0.5);

            parent.getChildren().add(rectangle);
        }
    }

    static void createInfoText(Group parent) {
        Text B = new Text(630, 175, "B");
        Text R = new Text(630, 220, "R");
        Text G = new Text(630, 265, "G");
        Text Y = new Text(630, 310, "Y");
        Text P = new Text(630, 355, "P");
        Text O = new Text(630, 400, "O");

        Stream.of(B, R, G, Y, P, O).
                forEach(p -> p.setFont(Font.loadFont("file:resources/font/main_font.ttf", 12)));

        parent.getChildren().addAll(B, R, G, Y, P, O);
    }
}