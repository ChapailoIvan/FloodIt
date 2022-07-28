package com.floodit.game;

/*
   WinChecker controls all captured territory (stores number of captures squares), so
   in case number of captured squares coincide with number of squares on the field, game
   is considered to be ended.
*/

import javafx.util.Pair;

public class WinChecker {
    private final static int MAX_STEPS_NUMBER = 25;
    private final int TERRITORY;

    private int currentNumOfSteps;
    private int currentCapturedTerr;

    // Field TERRITORY is final
    public WinChecker(int TERRITORY) {
        this.TERRITORY = TERRITORY;
        currentNumOfSteps = 0;
        currentCapturedTerr = 0;
    }

    public int getCurrentNumOfSteps() {
        return currentNumOfSteps;
    }

    public int getCurrentCapturedTerr() {
        return currentCapturedTerr;
    }

    public void increaseCurrentNumOfSteps() {
        ++currentNumOfSteps;
    }

    public boolean isFinished() {
        return currentNumOfSteps == MAX_STEPS_NUMBER || currentCapturedTerr == TERRITORY;
    }

    public boolean isWinningGame() {
        return currentNumOfSteps <= MAX_STEPS_NUMBER && currentCapturedTerr == TERRITORY;
    }

    public void reset() {
        this.currentCapturedTerr = 0;
        this.currentNumOfSteps = 0;
    }

    public void updateCapturedTerritory(int currentCapturedTerr) {
        this.currentCapturedTerr = currentCapturedTerr;
    }

    public double getCapturedTerritoryPercentage() {
        return (double) currentCapturedTerr / TERRITORY;
    }
}
