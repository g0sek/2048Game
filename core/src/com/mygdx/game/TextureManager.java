package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class TextureManager {
    public final Texture mainMenuBackgroundTexture;
    public final Texture gameboardTexture;
    public Texture endGameboardTexture;
    public TextureManager() {
        mainMenuBackgroundTexture = new Texture("mainMenuBackground.png");
        gameboardTexture = new Texture("gameTemplate.png");
        endGameboardTexture = new Texture("endGameTemplate.png");
    }
    public Texture getTileTexture(int tileValue) {
        // W zależności od wartości kafelka, wybierz odpowiednią teksturę
        switch (tileValue) {
            case 2:
                return new Texture("Tile2.png");
            case 4:
                return new Texture("Tile4.png");
            case 8:
                return new Texture("Tile8.png");
            case 16:
                return new Texture("Tile16.png");
            case 32:
                return new Texture("Tile32.png");
            case 64:
                return new Texture("Tile64.png");
            case 128:
                return new Texture("Tile128.png");
            case 256:
                return new Texture("Tile256.png");
            case 512:
                return new Texture("Tile512.png");
            case 1024:
                return new Texture("Tile1024.png");
            case 2048:
                return new Texture("Tile2048.png");
            default:
                return null; // Domyślna tekstura, jeśli wartość nie pasuje do żadnego przypadku
        }
    }
}
