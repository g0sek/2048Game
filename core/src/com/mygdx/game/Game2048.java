package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;

public class Game2048 extends ApplicationAdapter {
	private InputHandler inputHandler;
	private MainMenu mainMenu;
	private DrawOnScreen drawOnScreen;
    private GameLogic gameLogic;
   public Score score = new Score();
    @Override
	public void create() {
		initializeDependencies();
	}

	private void initializeDependencies() {
        TextureManager textureManager = new TextureManager();
        CameraHandler cameraHandler = new CameraHandler();
		FontHandler fontHandler = new FontHandler();
		gameLogic = new GameLogic(drawOnScreen, textureManager, cameraHandler, score);
		drawOnScreen = new DrawOnScreen(cameraHandler, fontHandler, score, new MainMenu(drawOnScreen, textureManager), textureManager, gameLogic);
		mainMenu = new MainMenu(drawOnScreen, textureManager);

		inputHandler = new InputHandler();
		drawOnScreen = new DrawOnScreen(cameraHandler, new FontHandler(), score, new MainMenu(null, textureManager), textureManager, null);
		gameLogic = new GameLogic(drawOnScreen, textureManager, cameraHandler, score);
		mainMenu = new MainMenu(drawOnScreen, textureManager);

		// Aktualizacja drawOnScreen i gameLogic w MainMenu
		drawOnScreen.mainMenu = mainMenu;
		drawOnScreen.gameLogic = gameLogic;
		mainMenu.drawOnScreen = drawOnScreen;

	}



	@Override
	public void render() {
		inputHandler.handleInput(gameLogic);
		update();
		if (!mainMenu.startGame) {
			drawOnScreen.drawMainMenu(); // Wywołaj drawMainMenu(), jeśli jeszcze nie zainicjowano
		} else {
			drawOnScreen.drawGameBoard(); // Dodaj rysowanie planszy gry w miejsce drawMainMenu()
			drawOnScreen.drawScore();
			if (!gameLogic.starterTilesRendered) {
				gameLogic.spawnRandomTile(); // Wylosuj kafelki tylko raz na początku gry
				gameLogic.spawnRandomTile();
				gameLogic.starterTilesRendered = true; // Ustaw flagę na true, aby wiedzieć, że kafelki zostały już wylosowane
			}
		}
	}

	private void update() {
		// Add any additional logic for updating the game state here
	}


	@Override
	public void dispose() {
	}
}