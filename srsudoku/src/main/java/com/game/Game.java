package com.game;

import java.util.Scanner;

public class Game {

	// private GameWindow gameWindow;
	// private GamePanel gamePanel;

	private SudokuState _ss;

	public Game(String filePath) throws IllegalArgumentException {
		// gamePanel = new GamePanel();
		// gameWindow = new GameWindow(gamePanel);

		_ss = new SudokuState();
		_ss.parseInput(filePath);

		System.out.println(_ss);

		Scanner scanner = new Scanner(System.in);

		System.out.print("[Next action] 0 - exit, 1 - auto solve\nChoose one: ");
		String userInput;

		while (true) {
			userInput = scanner.nextLine();

			if (Integer.parseInt(userInput) == 0) {
				scanner.close();
				return;
			}
			else if (Integer.parseInt(userInput) == 1) {
				_ss.solve();
				System.out.println(_ss);
				scanner.close();
				return;
			}
			else {
				System.out.print("[Invalid] Type a valid option:\n0 - exit, 1 - auto solve\nChoose one: ");
			}
		}
		

	}
}
