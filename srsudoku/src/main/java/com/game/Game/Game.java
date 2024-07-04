package com.game.Game;

import java.util.Scanner;

import com.game.Solver.*;
import com.game.Sudoku.Sudoku;
import com.game.Sudoku.SudokuState;

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
		System.out.println(_ss.getBoard().print());

		Scanner scanner = new Scanner(System.in);

		String userInput;

		while (true) {
			System.out.print("[Next action] 0 - exit, 1 - auto solve\nChoose one: ");
			userInput = scanner.nextLine();

			if (Integer.parseInt(userInput) == 0) {
				scanner.close();
				return;
			}
			else if (Integer.parseInt(userInput) == 1) {

				boolean eval = true;

				while (eval) {
					eval = _ss.nextAction();
					System.out.println(_ss);
				}
				
				scanner.close();
				return;
			}
			else {
				System.out.print("[Invalid] Type a valid option:\n0 - exit, 1 - auto solve\nChoose one: ");
			}
		}
	}
}
