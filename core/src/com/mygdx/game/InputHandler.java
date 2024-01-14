package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;

public class InputHandler {
    public void handleInput(GameLogic gameLogic) {
        MoveTile moveTile = new MoveTile();
        if (Gdx.input.isKeyJustPressed(Keys.LEFT)) {
            moveTile.moveLeft(gameLogic);
        }
        if (Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
            moveTile.moveRight(gameLogic);
        }
        if (Gdx.input.isKeyJustPressed(Keys.UP)) {
            moveTile.moveUp(gameLogic);
        }
        if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
            moveTile.moveDown(gameLogic);
        }
    }
}