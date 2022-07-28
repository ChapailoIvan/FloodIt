package com.floodit.game;

import java.util.Random;

public class FieldColorizer {
    public static void colorizeField(Field field) {
        Random random = new Random();

        for (int i = 0; i < field.getX(); ++i)
            for (int j = 0; j < field.getY(); ++j)
                field.setColor(i, j, random.nextInt(6));
    }
}
