package com.game.Game;

import java.util.Scanner;

import com.game.Sudoku.Sudoku;

import com.game.Utils.Debug;
import com.game.Utils.Error;
import com.game.Utils.Message;
import com.game.Utils.Regex;

// import com.game.Solver.*;
// import com.game.Sudoku.Sudoku;

public class Game {

	// private GameWindow gameWindow;
	// private GamePanel gamePanel;

	private boolean _hasSudoku = false;
	private Sudoku _sudoku;

	public Game() throws IllegalArgumentException {
		Debug.place();

		// gamePanel = new GamePanel();
		// gameWindow = new GameWindow(gamePanel);

		Scanner sc = new Scanner(System.in);
		String userInput;

		while (true) {

			Message.nextAction(hasSudoku());
			userInput = sc.nextLine();

			try {
				if (Integer.parseInt(userInput) == 0)
					break;
				else if (Integer.parseInt(userInput) == 1)
					newSudoku(sc);
				else if (Integer.parseInt(userInput) == 2)
					loadSudoku();
				else if (Integer.parseInt(userInput) == 3)
					saveSudoku();				
				else if (Integer.parseInt(userInput) == 4)
					manualAction(sc);
				else if (Integer.parseInt(userInput) == 5)
					autoAction(sc);
			
				else Error.invalidOption();
			}

			catch (NumberFormatException e) {
				Error.invalidInteger();
			}
		}

		sc.close();
	}

	/* ------------------------ Getters and setters ------------------------ */
	public boolean hasSudoku() { return _hasSudoku; }
	public void setHasSudoku(boolean state) { _hasSudoku = state; }

	public Sudoku getSudoku() { return _sudoku; }
	public Sudoku setSudoku(Sudoku sudoku) {return this._sudoku = sudoku; } 

	/* ------------------------ New Sudoku function ------------------------ */
	private void newSudoku(Scanner scanner) {
		Debug.place();

		while (true) {
			Message.typeFilePath();
			String newInput = scanner.nextLine();

			if (isExitCommand(newInput)) break;

			String path = Regex.constructTxtPath(newInput);
			Sudoku sudoku = new Sudoku(path);

			if (sudoku.initializeSudokuState(path)) {
				setHasSudoku(true);
				System.out.println(setSudoku(sudoku));
				break;
			}
		}
	}

	/* ----------------------- Load Sudoku function ------------------------ */
	private void loadSudoku() {
		Debug.todo();
	}

	/* ----------------------- Save Sudoku function ------------------------ */
	private void saveSudoku() {
		Debug.todo();
	}

	/* ---------------------- Manual Action function ----------------------- */
	private void manualAction(Scanner scanner) {
		
		String manualInput;
		
		while (true) {
			Message.manualAction();
			manualInput = scanner.nextLine();

			try {
				if (Integer.parseInt(manualInput) == 0)
					break;
				else if (Integer.parseInt(manualInput) == 1)
					getSudoku().addPenDigit(scanner);
				else if (Integer.parseInt(manualInput) == 2)
					getSudoku().delPenDigit(scanner);
				else if (Integer.parseInt(manualInput) == 3)
					System.out.println(getSudoku().getState());

				else Error.invalidOption();
			}

			catch (NumberFormatException e) {
				Error.invalidInteger();
			}
		}
	}

	/* ----------------------- Auto Action function ------------------------ */
	private void autoAction(Scanner scanner) {
		String autoInput;
		
		while (true) {
			Message.autoAction();
			autoInput = scanner.nextLine();

			try {
				if (Integer.parseInt(autoInput) == 0)
					break;
				else if (Integer.parseInt(autoInput) == 1)
					getSudoku().nextIteration();
				else if (Integer.parseInt(autoInput) == 2)
					getSudoku().fullGeneration();

				else Error.invalidOption();
			}

			catch (NumberFormatException e) {
				Error.invalidInteger();
			}
		}
	}

	/* ------------------- Is Exit Command test function ------------------- */
	private boolean isExitCommand(String input) {
		try {
			return Integer.parseInt(input) == 0;
		}
		catch (NumberFormatException e) {
			return false; // Not an integer, thus not an exit command
		}
	}
}
