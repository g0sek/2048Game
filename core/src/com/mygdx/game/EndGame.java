package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
public class EndGame {
    public boolean endGame = false;
    private final TextureManager textureManager;
    public DrawOnScreen drawOnScreen;

    public EndGame(DrawOnScreen drawOnScreen, TextureManager textureManager) {
        this.drawOnScreen = drawOnScreen;
        this.textureManager = textureManager;
    }
    public boolean handleEndGame() {
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            float mouseX = Gdx.input.getX();
            float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY(); // Odwróć y, ponieważ Gdx używa odwrotnego układu współrzędnych

            // Określ obszar, na którym bedzie reakcja na klikniecie
            float startButtonX = 105;
            float startButtonY = 163;
            float quitButtonX = 105;
            float quitButtonY = 42;
            float clickAreaWidth = 272;
            float clickAreaHeight = 92;

            // Sprawdź, czy kliknięcie nastąpiło w wyznaczonym obszarze
            if (mouseX >= startButtonX && mouseX <= startButtonX + clickAreaWidth &&
                    mouseY >= startButtonY && mouseY <= startButtonY + clickAreaHeight) {
                // Kliknięcie miało miejsce w wyznaczonym obszarze
                endGame = false;
                //textureManager.endGameboardTexture.dispose();
                return true;
            } else if (mouseX >= quitButtonX && mouseX <= quitButtonX + clickAreaWidth &&
                    mouseY >= quitButtonY && mouseY <= quitButtonY + clickAreaHeight){
                // Kliknięcie miało miejsce w wyznaczonym obszarze
                Gdx.app.exit();
            }
        }
        return false;
    }
}
