package com.game;

public class Game {
	
	public int DEFAULT_BOARD_SIZE = 9;

	private GameWindow gameWindow;
	private GamePanel gamePanel;

	private SudokuState _ss;

	public Game() {
		gamePanel = new GamePanel();
		gameWindow = new GameWindow(gamePanel);

		_ss = new SudokuState(DEFAULT_BOARD_SIZE);
		_ss.parseInput();

		System.out.println(_ss);
	}
}
