package com.floodit.game;

import javafx.util.Pair;

import java.util.ArrayDeque;

public class RecolorController {
    private final Field field;
    // 'queue' is used for BFS
    private final ArrayDeque<Pair<Integer, Integer>> queue;
    // Previous color ID
    private int pColorID;

    public RecolorController(Field field) {
        this.field = field;
        pColorID = field.getColor(0, 0);
        queue = new ArrayDeque<>();
    }

    public void updateStartingColor() {
        pColorID = field.getColor(0, 0);
    }

    // Uses BFS in order to find captured territory and recolor it
    // Returns number of captured rectangles
    public int recolor(int nColorID) {
        int capturedTerritory = 0;

        boolean[][] visited = new boolean[field.x][field.y];

        queue.add(new Pair<>(0, 0));
        visited[0][0] = true;

        int x, y;

        Pair<Integer, Integer> cPoint;

        while (!queue.isEmpty()) {
            cPoint = queue.pollFirst();

            x = cPoint.getKey();
            y = cPoint.getValue();

            if (x + 1 < field.x && !visited[x + 1][y]) {
                if (field.getColor(x + 1, y) == pColorID)
                    queue.add(new Pair<>(x + 1, y));
                visited[x + 1][y] = true;
            }

            if (x - 1 >= 0 && !visited[x - 1][y]) {
                if (field.getColor(x - 1, y) == pColorID)
                    queue.add(new Pair<>(x - 1, y));
                visited[x - 1][y] = true;
            }

            if (y + 1 < field.y && !visited[x][y + 1]) {
                if (field.getColor(x, y + 1) == pColorID)
                    queue.add(new Pair<>(x, y + 1));
                visited[x][y + 1] = true;
            }

            if (y - 1 >= 0 && !visited[x][y - 1]) {
                if (field.getColor(x, y - 1) == pColorID)
                    queue.add(new Pair<>(x, y - 1));
                visited[x][y - 1] = true;
            }

            // changes color on field
            field.setColor(x, y, nColorID);

            ++capturedTerritory;
        }

        if (nColorID >= 0) pColorID = nColorID; // Updates previous color
        return capturedTerritory;
    }
}
