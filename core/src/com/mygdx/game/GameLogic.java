package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

public class GameLogic {
    public final int BOARD_SIZE = 4;
    public int[][] board;
    public boolean starterTilesRendered = false;
    private final Random random = new Random();
    private final DrawOnScreen drawOnScreen;
    private final TextureManager textureManager;
    private final CameraHandler cameraHandler;
    public final Score score;

    public GameLogic(DrawOnScreen drawOnScreen, TextureManager textureManager, CameraHandler cameraHandler, Score score) {
        this.drawOnScreen = drawOnScreen;
        this.textureManager = textureManager;
        this.cameraHandler = cameraHandler;
        this.score = score;
        board = new int[BOARD_SIZE][BOARD_SIZE];

    }
    public void printBoard() {
        int[][] board = getBoard(); // Uzyskaj dostęp do planszy z obiektu GameLogic
        for (int y = 0; y < BOARD_SIZE; y++) {
            for (int x = 0; x < BOARD_SIZE; x++) {
                System.out.print(board[y][x] + "\t");
            }
            System.out.println();
        }
    }
    public int[][] getBoard() {
        return board;
    }

    public int getScore(){
        return score.totalScore;
    }
    public void spawnRandomTile(){
        int[] emptyPositionsX = new int[BOARD_SIZE * BOARD_SIZE];
        int[] emptyPositionsY = new int[BOARD_SIZE * BOARD_SIZE];
        int emptyCount = 0;

        for (int y = 0; y < BOARD_SIZE; y++) {
            for (int x = 0; x < BOARD_SIZE; x++) {
                if (board[y][x] == 0) {
                    emptyPositionsX[emptyCount] = x;
                    emptyPositionsY[emptyCount] = y;
                    emptyCount++;
                }
            }
        }
        if (emptyCount > 0) {
            int randomIndex = random.nextInt(emptyCount);
            int randomX = emptyPositionsX[randomIndex];
            int randomY = emptyPositionsY[randomIndex];

            // Losuj wartość zgodnie z prawdopodobieństwem
            double randomValue = random.nextDouble();
            int tileValue = (randomValue < 0.9) ? 2 : 4;

            // Wpisz wartość do macierzy
            board[randomY][randomX] = tileValue;

        }
    }
    public void calculateTilePosition(){
        drawOnScreen.batch.setProjectionMatrix(cameraHandler.camera.combined);
        drawOnScreen.batch.begin();

        float tileSize = 98; // Rozmiar kafelka

        for (int y = 0; y < BOARD_SIZE; y++) {
            for (int x = 0; x < BOARD_SIZE; x++) {
                int tileValue = board[y][x];

                if (tileValue > 0) {
                    // Wybierz odpowiednią teksturę na podstawie wartości w macierzy
                    Texture tileTexture = textureManager.getTileTexture(tileValue);

                    // Oblicz koordynaty x i y na podstawie indeksu w macierzy
                    float tileX = 47 + x * tileSize;
                    float tileY = 340 - y * tileSize;

                    // Narysuj kafelek na ekranie
                    drawOnScreen.batch.draw(tileTexture, tileX, tileY, 92, 92);
                }
            }
        }

        drawOnScreen.batch.end();
    }
    public void gameOver(){
        textureManager.reloadEndGameTexture();
        drawOnScreen.drawEndGame(textureManager);
    }

    public void restartGame(){
        for (int y = 0; y < BOARD_SIZE; y++) {
            for (int x = 0; x < BOARD_SIZE; x++) {
                board[y][x] = 0;
            }
        }
        spawnRandomTile();
        spawnRandomTile();
        score.totalScore = 0;
    }
}
