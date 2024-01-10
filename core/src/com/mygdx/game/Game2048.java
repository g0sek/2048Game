package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;

public class Game2048 extends ApplicationAdapter {

	private static final int BOARD_SIZE = 4;
	private static final int TILE_SIZE = 90;
	private int[][] board;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private BitmapFont font;
	private Texture mainMenuBackgroundTexture;
	@Override
	public void create() {
		board = new int[BOARD_SIZE][BOARD_SIZE];
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch = new SpriteBatch();
		font = new BitmapFont();
		mainMenuBackgroundTexture = new Texture("mainMenuBackground.png");
	}

	@Override
	public void render() {
		handleInput();
		update();
		drawMainMenu();
	}
	private void handleInput() {
		if (Gdx.input.isKeyJustPressed(Keys.LEFT)) {
			moveLeft();
		}
		if (Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
			moveRight();
		}
		if (Gdx.input.isKeyJustPressed(Keys.UP)) {
			moveUp();
		}
		if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
			moveDown();
		}
	}

	private void update() {
		// Add any additional logic for updating the game state here
	}

	private void drawMainMenu() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		batch.draw(mainMenuBackgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		handleMainMenu();

		batch.end();
	}

	private void handleMainMenu() {
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
				System.out.println("Start Game");
			} else if (mouseX >= quitButtonX && mouseX <= quitButtonX + clickAreaWidth &&
					mouseY >= quitButtonY && mouseY <= quitButtonY + clickAreaHeight){
				// Kliknięcie miało miejsce w wyznaczonym obszarze
				System.out.println("Quit Game");
			}
		}
	}

	private void spawnTile() {
		// Add logic to spawn a new tile on the board
	}

	private void moveLeft() {
		// Add logic to move tiles left
	}

	private void moveRight() {
		// Add logic to move tiles right
	}

	private void moveUp() {
		// Add logic to move tiles up
	}

	private void moveDown() {
		// Add logic to move tiles down
	}

	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();
		mainMenuBackgroundTexture.dispose();
	}
}
