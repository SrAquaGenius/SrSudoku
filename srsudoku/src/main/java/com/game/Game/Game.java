package com.game.Game;

import java.util.Scanner;

import com.game.App;
import com.game.Message;
// import com.game.Solver.*;
// import com.game.Sudoku.Sudoku;
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

		Message.initialBoard(_ss);
		
		App.debug(_ss.getBoard().print());

		Scanner sc = new Scanner(System.in);

		String userInput;

		while (true) {
			Message.nextAction();
			userInput = sc.nextLine();

			try {
				if (Integer.parseInt(userInput) == 0)
					break;
				else if (Integer.parseInt(userInput) == 1)
					manualAction(sc);
				else if (Integer.parseInt(userInput) == 2)
					autoAction(sc);
				else if (Integer.parseInt(userInput) == 3)
					Message.board(_ss);
			
				else Message.invalidOption();
			}

			catch (NumberFormatException e) {
				Message.invalidInteger();
			}
		}

		sc.close();
	}

	public void manualAction(Scanner scanner) {
		
		String manualInput;
		
		while (true) {
			Message.manualAction();
			manualInput = scanner.nextLine();

			try {
				if (Integer.parseInt(manualInput) == 0)
					break;
				else if (Integer.parseInt(manualInput) == 1)
					_ss.addPenDigit(scanner);
				else if (Integer.parseInt(manualInput) == 2)
					_ss.delPenDigit(scanner);

				else Message.invalidOption();
			}

			catch (NumberFormatException e) {
				Message.invalidInteger();
			}
		}
	}

	public void autoAction(Scanner scanner) {
		String autoInput;
		
		while (true) {
			Message.autoAction();
			autoInput = scanner.nextLine();

			try {
				if (Integer.parseInt(autoInput) == 0)
					break;
				else if (Integer.parseInt(autoInput) == 1)
					_ss.nextIteration();
				else if (Integer.parseInt(autoInput) == 2)
					_ss.fullGeneration();

				else Message.invalidOption();
			}

			catch (NumberFormatException e) {
				Message.invalidInteger();
			}
		}
	}
}
