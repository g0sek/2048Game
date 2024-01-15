package com.mygdx.game;
import java.io.*;
public class Score {
    public int totalScore = 0;
    public int highestScore = 0;
    public void calculateScore(int value) {
        totalScore += value;
    }
    private static final String HIGH_SCORE_FILE = "highscore.txt";

    public void loadHighScore() {

        try (BufferedReader reader = new BufferedReader(new FileReader(HIGH_SCORE_FILE))) {
            String line = reader.readLine();
            if (line != null && !line.isEmpty()) {
                highestScore = Integer.parseInt(line);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void saveHighScore() {
        if(totalScore > highestScore) {
            try (FileWriter writer = new FileWriter(HIGH_SCORE_FILE)) {
                writer.write(Integer.toString(totalScore));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
