package com.floodit.game;

public class Field {
    private final int[][] field;

    // Defines linear sizes of field
    int x;
    int y;

    public Field(int x, int y) {
        this.x = x;
        this.y = y;

        field = new int[x][y];
    }

    public int getColor(int x, int y) {
        return field[x][y];
    }

    public boolean setColor(int x, int y, int colorID) {
        if (colorID >= 0 && colorID <= 5) {
            field[x][y] = colorID;
            return true;
        } else
            return false;
    }

    // Returns linear x-length
    public int getX() {
        return x;
    }

    // Returns linear y-length
    public int getY() {
        return y;
    }

    // Returns number of squares on game field
    public int getTerritory() {
        return x * y;
    }
}
