package com.mygdx.game;
import java.util.Random;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;

public class Game2048 extends ApplicationAdapter {
	private boolean startGame = false;
	private boolean starterTilesRendered = false;
	private static final int BOARD_SIZE = 4;
	private static final int TILE_SIZE = 90;
	private int[][] board;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private BitmapFont font;
	private Texture mainMenuBackgroundTexture;
	private Texture gameboardTexture;
	private final Random random = new Random();
	@Override
	public void create() {
		board = new int[BOARD_SIZE][BOARD_SIZE];
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch = new SpriteBatch();
		font = new BitmapFont();
		mainMenuBackgroundTexture = new Texture("mainMenuBackground.png");
		gameboardTexture = new Texture("gameTemplate.png");
	}

	@Override
	public void render() {
		handleInput();
		update();
		if (!startGame) {
			drawMainMenu(); // Wywołaj drawMainMenu(), jeśli jeszcze nie zainicjowano
		} else {
			drawGameBoard(); // Dodaj rysowanie planszy gry w miejsce drawMainMenu()
			if (!starterTilesRendered) {
				spawnRandomTile(); // Wylosuj kafelki tylko raz na początku gry
				spawnRandomTile();
				starterTilesRendered = true; // Ustaw flagę na true, aby wiedzieć, że kafelki zostały już wylosowane
			}
		}
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
		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		batch.draw(mainMenuBackgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		batch.end();
		handleMainMenu();
	}

	private void handleGame(){
		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		float tileSize = 98; // Rozmiar kafelka

		for (int y = 0; y < BOARD_SIZE; y++) {
			for (int x = 0; x < BOARD_SIZE; x++) {
				int tileValue = board[y][x];

				if (tileValue > 0) {
					// Wybierz odpowiednią teksturę na podstawie wartości w macierzy
					Texture tileTexture = getTileTexture(tileValue);

					// Oblicz koordynaty x i y na podstawie indeksu w macierzy
					float tileX = 47 + x * tileSize;
					float tileY = 340 - y * tileSize;

					// Narysuj kafelek na ekranie
					batch.draw(tileTexture, tileX, tileY, 92, 92);
				}
			}
		}

		batch.end();
	}
	private Texture getTileTexture(int tileValue) {
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
				return new Texture("Tile265.png");
			case 512:
				return new Texture("Tile512.png");
			case 1024:
				return new Texture("Tile1024.png");
			case 2048:
				return new Texture("Tile2048.png");
			// Dodaj inne przypadki dla kolejnych wartości kafelków
			// ...
			default:
				return null; // Domyślna tekstura, jeśli wartość nie pasuje do żadnego przypadku
		}
	}
	private void drawGameBoard(){
		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		batch.draw(gameboardTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		batch.end();
		handleGame();
	}
	private void spawnRandomTile(){
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
			printBoard();
		}

	}

	private void printBoard() {
		for (int y = 0; y < BOARD_SIZE; y++) {
			for (int x = 0; x < BOARD_SIZE; x++) {
				System.out.print(board[y][x] + "\t");
			}
			System.out.println();
		}
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
				mainMenuBackgroundTexture.dispose();
				startGame = true;
				drawGameBoard();
			} else if (mouseX >= quitButtonX && mouseX <= quitButtonX + clickAreaWidth &&
					mouseY >= quitButtonY && mouseY <= quitButtonY + clickAreaHeight){
				// Kliknięcie miało miejsce w wyznaczonym obszarze
				Gdx.app.exit();
			}
		}
	}

	private void moveRight() {
		if (!canMoveRight()) {
			return;
		}

		for (int y = 0; y < BOARD_SIZE; y++) {
			for (int x = BOARD_SIZE - 2; x >= 0; x--) {
				if (board[y][x] > 0) {
					int value = board[y][x];
					int targetX = x;

					while (targetX < BOARD_SIZE - 1 && board[y][targetX + 1] == 0) {
						targetX++;
					}

					if (targetX < BOARD_SIZE - 1 && board[y][targetX + 1] == value) {
						board[y][targetX + 1] *= 2;
						board[y][x] = 0;
					} else {
						board[y][targetX] = value;
						if (targetX != x) {
							board[y][x] = 0;
						}
					}
				}
			}
		}
		spawnRandomTile();
	}

	private void moveUp() {
		if (!canMoveUp()) {
			return;
		}

		for (int x = 0; x < BOARD_SIZE; x++) {
			for (int y = 1; y < BOARD_SIZE; y++) {
				if (board[y][x] > 0) {
					int value = board[y][x];
					int targetY = y;

					while (targetY > 0 && board[targetY - 1][x] == 0) {
						targetY--;
					}

					if (targetY > 0 && board[targetY - 1][x] == value) {
						board[targetY - 1][x] *= 2;
						board[y][x] = 0;
					} else {
						board[targetY][x] = value;
						if (targetY != y) {
							board[y][x] = 0;
						}
					}
				}
			}
		}
		spawnRandomTile();
	}

	private void moveDown() {
		if (!canMoveDown()) {
			return;
		}

		for (int x = 0; x < BOARD_SIZE; x++) {
			for (int y = BOARD_SIZE - 2; y >= 0; y--) {
				if (board[y][x] > 0) {
					int value = board[y][x];
					int targetY = y;

					while (targetY < BOARD_SIZE - 1 && board[targetY + 1][x] == 0) {
						targetY++;
					}

					if (targetY < BOARD_SIZE - 1 && board[targetY + 1][x] == value) {
						board[targetY + 1][x] *= 2;
						board[y][x] = 0;
					} else {
						board[targetY][x] = value;
						if (targetY != y) {
							board[y][x] = 0;
						}
					}
				}
			}
		}
		spawnRandomTile();
	}

	private void moveLeft() {
		if (!canMoveLeft()) {
			return;
		}

		for (int y = 0; y < BOARD_SIZE; y++) {
			for (int x = 1; x < BOARD_SIZE; x++) {
				if (board[y][x] > 0) {
					int value = board[y][x];
					int targetX = x;

					while (targetX > 0 && board[y][targetX - 1] == 0) {
						targetX--;
					}

					if (targetX > 0 && board[y][targetX - 1] == value) {
						board[y][targetX - 1] *= 2;
						board[y][x] = 0;
					} else {
						board[y][targetX] = value;
						if (targetX != x) {
							board[y][x] = 0;
						}
					}
				}
			}
		}
		spawnRandomTile();
	}

	private boolean canMoveRight() {
		for (int y = 0; y < BOARD_SIZE; y++) {
			for (int x = BOARD_SIZE - 2; x >= 0; x--) {
				if (board[y][x] > 0 && (board[y][x + 1] == 0 || board[y][x + 1] == board[y][x])) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean canMoveUp() {
		for (int x = 0; x < BOARD_SIZE; x++) {
			for (int y = 1; y < BOARD_SIZE; y++) {
				if (board[y][x] > 0 && (board[y - 1][x] == 0 || board[y - 1][x] == board[y][x])) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean canMoveDown() {
		for (int x = 0; x < BOARD_SIZE; x++) {
			for (int y = BOARD_SIZE - 2; y >= 0; y--) {
				if (board[y][x] > 0 && (board[y + 1][x] == 0 || board[y + 1][x] == board[y][x])) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean canMoveLeft() {
		for (int y = 0; y < BOARD_SIZE; y++) {
			for (int x = 1; x < BOARD_SIZE; x++) {
				if (board[y][x] > 0 && (board[y][x - 1] == 0 || board[y][x - 1] == board[y][x])) {
					return true;
				}
			}
		}
		return false;
	}


	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();
		mainMenuBackgroundTexture.dispose();
	}
}
