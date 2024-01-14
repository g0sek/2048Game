package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DrawOnScreen {
    public final SpriteBatch batch;
    private final CameraHandler cameraHandler;
    private final FontHandler fontHandler;
    private final Score score;
    public MainMenu mainMenu;
    private final TextureManager textureManager;
    public GameLogic gameLogic;

    public DrawOnScreen(CameraHandler cameraHandler, FontHandler fontHandler,
                        Score score, MainMenu mainMenu, TextureManager textureManager, GameLogic gameLogic) {
        this.batch = new SpriteBatch();
        this.cameraHandler = cameraHandler;
        this.fontHandler = fontHandler;
        this.score = score;
        this.mainMenu = mainMenu;
        this.textureManager = textureManager;
        this.gameLogic = gameLogic;
        // Aktualizacja drawOnScreen w mainMenu
        if (mainMenu != null) {
            mainMenu.drawOnScreen = this;
        }
    }

    void drawScore() {
        batch.setProjectionMatrix(cameraHandler.camera.combined);
        batch.begin();

        float scoreX = 380;
        float scoreY = 525;
        // Rysuj wynik
        fontHandler.font.draw(batch, "" + score.totalScore, scoreX, scoreY);

        batch.end();
    }

    public void drawGameBoard(){
        batch.setProjectionMatrix(cameraHandler.camera.combined);
        batch.begin();

        batch.draw(textureManager.gameboardTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
        gameLogic.calculateTilePosition();
    }

    public void drawMainMenu() {
        batch.setProjectionMatrix(cameraHandler.camera.combined);
        batch.begin();

        batch.draw(textureManager.mainMenuBackgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        batch.end();
        mainMenu.handleMainMenu();
    }

}
