package com.mygdx.game;

public class Score {
    public int totalScore = 0;

    public Score(GameLogic gameLogic) {
    }


    public void calculateScore(int value) {
        totalScore += value;
        System.out.println(totalScore);
    }
}