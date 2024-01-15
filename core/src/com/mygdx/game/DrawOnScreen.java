package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DrawOnScreen {
    public final SpriteBatch batch;
    private final CameraHandler cameraHandler;
    private final FontHandler fontHandler;
    private final Score score;
    private final Animations animations;
    public MainMenu mainMenu;
    public EndGame endGame;
    public WinGame winGame;
    private final TextureManager textureManager;
    public GameLogic gameLogic;
    public boolean animationEndgameStart = false;
    public boolean animationWingameStart = false;
    public boolean animationDrawboard = false;
    public boolean winGameAchieved = false;


    public DrawOnScreen(CameraHandler cameraHandler, FontHandler fontHandler,
                        Score score, MainMenu mainMenu, TextureManager textureManager, GameLogic gameLogic, EndGame endGame, Animations animations, WinGame winGame) {
        this.batch = new SpriteBatch();
        this.cameraHandler = cameraHandler;
        this.fontHandler = fontHandler;
        this.score = score;
        this.mainMenu = mainMenu;
        this.textureManager = textureManager;
        this.gameLogic = gameLogic;
        this.endGame = endGame;
        this.animations = animations;
        this.winGame = winGame;

        // Aktualizacja drawOnScreen w mainMenu
        if (mainMenu != null) {
            mainMenu.drawOnScreen = this;
        }
    }

    public void drawScore() {

        batch.setProjectionMatrix(cameraHandler.camera.combined);
        batch.begin();

        String scoreText = "" + score.totalScore;
        GlyphLayout layout = new GlyphLayout(fontHandler.font, scoreText);
        float scoreX = 0;
        float scoreY = 0;
        if(endGame.endGame || winGame.winGame){
            scoreX = 290 - layout.width / 2;  // Wycentrowanie tekstu
            scoreY = 350;
            score.loadHighScore();
            score.saveHighScore();
            drawHighestScore();
        }else {
            scoreX = 380 - layout.width / 2;  // Wycentrowanie tekstu
            scoreY = 525;
        }
        // Rysuj wynik
        fontHandler.font.draw(batch, scoreText, scoreX, scoreY);

        batch.end();
    }

    public void drawHighestScore(){
        String highestScoreText = "" + score.highestScore;
        GlyphLayout layout = new GlyphLayout(fontHandler.font, highestScoreText);
        float highestScoreX = 0;
        float highestScoreY = 0;
        if(endGame.endGame || winGame.winGame){
            highestScoreX = 250 - layout.width / 2;  // Wycentrowanie tekstu
            highestScoreY = 430;
        }

        // Rysuj najwyższy wynik
        fontHandler.font.draw(batch, highestScoreText, highestScoreX, highestScoreY);

    }
    public void drawGameBoard(){
        batch.setProjectionMatrix(cameraHandler.camera.combined);
        batch.begin();

        if(!animationDrawboard) {
            animations.create(textureManager.gameboardTexture);
            animationDrawboard = true;
        }
        animations.render();
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
    public void drawEndGame(){
        animationDrawboard = false;
        batch.setProjectionMatrix(cameraHandler.camera.combined);
        batch.begin();

        if(!animationEndgameStart) {
            animations.create(textureManager.endGameboardTexture);
            animationEndgameStart = true;
        }
        animations.render();

        batch.end();
        if(!endGame.endGame) {
            endGame.endGame = true;
        }

        if(endGame.handleEndGame()){
            gameLogic.restartGame();
            animationEndgameStart = false;
        }
    }
    public void drawWinGame(){
        if(!winGameAchieved){
            animationDrawboard = false;
            batch.setProjectionMatrix(cameraHandler.camera.combined);
            batch.begin();

            if(!animationWingameStart) {
                animations.create(textureManager.winGameTexture);
                animationWingameStart = true;
            }
            animations.render();

            batch.end();
            if(!winGame.winGame) {
                winGame.winGame = true;
            }

            if(winGame.handleWinGame()) {
                winGameAchieved = true;
                animationWingameStart = false;
            }
        }
    }
}
