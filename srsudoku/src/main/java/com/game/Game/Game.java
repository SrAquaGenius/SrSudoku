package com.game.Game;

import java.util.Scanner;

import com.game.Sudoku.SudokuState;

import com.game.Utils.Debug;
import com.game.Utils.Error;
import com.game.Utils.Message;

// import com.game.Solver.*;
// import com.game.Sudoku.Sudoku;

public class Game {

	// private GameWindow gameWindow;
	// private GamePanel gamePanel;

	private SudokuState _ss;

	public Game(String filePath) throws IllegalArgumentException {
		Debug.place();

		// gamePanel = new GamePanel();
		// gameWindow = new GameWindow(gamePanel);

		_ss = new SudokuState();
		_ss.parseInput(filePath);

		Message.initialBoard(_ss);
		Debug.print(_ss.getBoard().print());

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
			
				else Error.invalidOption();
			}

			catch (NumberFormatException e) {
				Error.invalidInteger();
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
				else if (Integer.parseInt(manualInput) == 3)
					Message.board(_ss);

				else Error.invalidOption();
			}

			catch (NumberFormatException e) {
				Error.invalidInteger();
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

				else Error.invalidOption();
			}

			catch (NumberFormatException e) {
				Error.invalidInteger();
			}
		}
	}
}
