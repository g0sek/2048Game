package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;

public class InputHandler {
    private MoveTile moveTile;
    public GameLogic gameLogic;

    public InputHandler(GameLogic gameLogic) {
        this.moveTile = new MoveTile(gameLogic);
        this.gameLogic = gameLogic;
    }

    public void handleInput(GameLogic gameLogic) {
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