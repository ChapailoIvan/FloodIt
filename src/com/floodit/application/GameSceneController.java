package com.floodit.application;

import com.floodit.game.FieldColorizer;
import com.floodit.game.WinChecker;
import javafx.scene.paint.Color;

import static java.lang.Math.*;

public class GameSceneController {
    private final ProgressIndicator territoryIndicator;
    private final ProgressIndicator stepsIndicator;
    private final GameSpace gameSpace;
    private int terrProgress;

    public GameSceneController(GameSpace gameSpace, ProgressIndicator territoryIndicator, ProgressIndicator stepsIndicator) {
        this.stepsIndicator = stepsIndicator;
        this.territoryIndicator = territoryIndicator;
        this.gameSpace = gameSpace;
        terrProgress = 0;
    }

    public GameSpace getGameSpace() {
        return gameSpace;
    }

    public ProgressIndicator getStepsIndicator() {
        return stepsIndicator;
    }

    public ProgressIndicator getTerritoryIndicator() {
        return territoryIndicator;
    }

    public void reset() {
        terrProgress = 0;
        FieldColorizer.colorizeField(gameSpace.getField());
        gameSpace.updateGameSpace();
        territoryIndicator.reset();
        stepsIndicator.reset();
    }

    public void updateGameScene(int colorID, WinChecker winChecker) {
        gameSpace.updateGameSpace();

        int currSteps = winChecker.getCurrentNumOfSteps();
        stepsIndicator.setColor(floorMod(currSteps - 1, 5), floorDiv(currSteps - 1, 5),
                GameSpace.colorsIDs[colorID]);

        int currTerr = (int) Math.ceil(winChecker.getCapturedTerritoryPercentage() * 25);
        for (int i = terrProgress + 1; i <= currTerr; ++i) {
            territoryIndicator.setColor(floorMod(i - 1, 5), floorDiv(i - 1, 5),
                    GameSpace.colorsIDs[colorID]);
        }

        terrProgress = currTerr;
    }

    public void clearGameSpace() {
        gameSpace.setAllColor(Color.WHITE);
    }
}
