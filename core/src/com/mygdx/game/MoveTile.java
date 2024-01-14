package com.mygdx.game;

public class MoveTile {
    private Score score;
    public MoveTile(GameLogic gameLogic) {
        this.score = new Score(gameLogic);
    }
    void moveRight(GameLogic gameLogic) {
        if (!canMoveRight(gameLogic)) {
            return;
        }

        for (int y = 0; y < gameLogic.BOARD_SIZE; y++) {
            for (int x = gameLogic.BOARD_SIZE - 2; x >= 0; x--) {
                if (gameLogic.board[y][x] > 0) {
                    int value = gameLogic.board[y][x];
                    int targetX = x;

                    while (targetX < gameLogic.BOARD_SIZE - 1 && gameLogic.board[y][targetX + 1] == 0) {
                        targetX++;
                    }

                    if (targetX < gameLogic.BOARD_SIZE - 1 && gameLogic.board[y][targetX + 1] == value) {
                        int newValue = gameLogic.board[y][targetX + 1] *= 2;
                        gameLogic.board[y][x] = 0;
                        score.calculateScore(newValue);
                    } else {
                        gameLogic.board[y][targetX] = value;
                        if (targetX != x) {
                            gameLogic.board[y][x] = 0;
                        }
                    }
                }
            }
        }
        gameLogic.spawnRandomTile();
    }

    void moveUp(GameLogic gameLogic) {
        if (!canMoveUp(gameLogic)) {
            return;
        }

        for (int x = 0; x < gameLogic.BOARD_SIZE; x++) {
            for (int y = 1; y < gameLogic.BOARD_SIZE; y++) {
                if (gameLogic.board[y][x] > 0) {
                    int value = gameLogic.board[y][x];
                    int targetY = y;

                    while (targetY > 0 && gameLogic.board[targetY - 1][x] == 0) {
                        targetY--;
                    }

                    if (targetY > 0 && gameLogic.board[targetY - 1][x] == value) {
                        int newValue = gameLogic.board[targetY - 1][x] *= 2;
                        gameLogic.board[y][x] = 0;
                        score.calculateScore(newValue);
                    } else {
                        gameLogic.board[targetY][x] = value;
                        if (targetY != y) {
                            gameLogic.board[y][x] = 0;
                        }
                    }
                }
            }
        }
        gameLogic.spawnRandomTile();
    }

    void moveDown(GameLogic gameLogic) {
        if (!canMoveDown(gameLogic)) {
            return;
        }

        for (int x = 0; x < gameLogic.BOARD_SIZE; x++) {
            for (int y = gameLogic.BOARD_SIZE - 2; y >= 0; y--) {
                if (gameLogic.board[y][x] > 0) {
                    int value = gameLogic.board[y][x];
                    int targetY = y;

                    while (targetY < gameLogic.BOARD_SIZE - 1 && gameLogic.board[targetY + 1][x] == 0) {
                        targetY++;
                    }

                    if (targetY < gameLogic.BOARD_SIZE - 1 && gameLogic.board[targetY + 1][x] == value) {
                        int newValue = gameLogic.board[targetY + 1][x] *= 2;
                        gameLogic.board[y][x] = 0;
                        score.calculateScore(newValue);
                    } else {
                        gameLogic.board[targetY][x] = value;
                        if (targetY != y) {
                            gameLogic.board[y][x] = 0;
                        }
                    }
                }
            }
        }
        gameLogic.spawnRandomTile();
    }

    void moveLeft(GameLogic gameLogic) {
        if (!canMoveLeft(gameLogic)) {
            return;
        }
        for (int y = 0; y < gameLogic.BOARD_SIZE; y++) {
            for (int x = 1; x < gameLogic.BOARD_SIZE; x++) {
                if (gameLogic.board[y][x] > 0) {
                    int value = gameLogic.board[y][x];
                    int targetX = x;

                    while (targetX > 0 && gameLogic.board[y][targetX - 1] == 0) {
                        targetX--;
                    }

                    if (targetX > 0 && gameLogic.board[y][targetX - 1] == value) {
                        int newValue = gameLogic.board[y][targetX - 1] *= 2;
                        gameLogic.board[y][x] = 0;
                        score.calculateScore(newValue);
                    } else {
                        gameLogic.board[y][targetX] = value;
                        if (targetX != x) {
                            gameLogic.board[y][x] = 0;
                        }
                    }
                }
            }
        }
        gameLogic.spawnRandomTile();
    }
    private boolean canMoveRight(GameLogic gameLogic) {
        for (int y = 0; y < gameLogic.BOARD_SIZE; y++) {
            for (int x = gameLogic.BOARD_SIZE - 2; x >= 0; x--) {
                if (gameLogic.board[y][x] > 0 && (gameLogic.board[y][x + 1] == 0 || gameLogic.board[y][x + 1] == gameLogic.board[y][x])) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean canMoveUp(GameLogic gameLogic) {
        for (int x = 0; x < gameLogic.BOARD_SIZE; x++) {
            for (int y = 1; y < gameLogic.BOARD_SIZE; y++) {
                if (gameLogic.board[y][x] > 0 && (gameLogic.board[y - 1][x] == 0 || gameLogic.board[y - 1][x] == gameLogic.board[y][x])) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean canMoveDown(GameLogic gameLogic) {
        for (int x = 0; x < gameLogic.BOARD_SIZE; x++) {
            for (int y = gameLogic.BOARD_SIZE - 2; y >= 0; y--) {
                if (gameLogic.board[y][x] > 0 && (gameLogic.board[y + 1][x] == 0 || gameLogic.board[y + 1][x] == gameLogic.board[y][x])) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean canMoveLeft(GameLogic gameLogic) {
        for (int y = 0; y < gameLogic.BOARD_SIZE; y++) {
            for (int x = 1; x < gameLogic.BOARD_SIZE; x++) {
                if (gameLogic.board[y][x] > 0 && (gameLogic.board[y][x - 1] == 0 || gameLogic.board[y][x - 1] == gameLogic.board[y][x])) {
                    return true;
                }
            }
        }
        return false;
    }
}
