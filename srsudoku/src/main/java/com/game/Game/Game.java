package com.game.Game;

import java.io.IOException;
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

	private boolean _hasSudoku = false;
	private SudokuState _ss;

	private static final String DEFAULT_TXT_PATH = "src/main/resources/txtData/";
	private static final String DEFAULT_OBJ_PATH = "src/main/resources/objData/";
	
	private static final String TXT_EXTENTION = ".txt";
	private static final String OBJ_EXTENTION = ".sst";

	public Game() throws IllegalArgumentException {
		Debug.place();

		// gamePanel = new GamePanel();
		// gameWindow = new GameWindow(gamePanel);

		Scanner sc = new Scanner(System.in);
		String userInput;

		while (true) {

			if (getHasSudoku()) {
				Message.nextAction();
			}
			else Message.nextInitAction();

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
	private void setHasSudoku(boolean state) {
		_hasSudoku = state;
	}

	private boolean getHasSudoku() {
		return _hasSudoku;
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

	/* ------------------------ New Sudoku function ------------------------ */
	private void newSudoku(Scanner scanner) {
		Debug.place();

		while (true) {
			Message.typeFilePath();
			String newInput = scanner.nextLine();

			if (isExitCommand(newInput)) break;

			String path = constructTxtPath(newInput);

			if (!initializeSudokuState(path)) {
				Error.invalidPath(path);
				continue;
			}

			Message.initialBoard(_ss);
			Debug.print(_ss.getBoard().print());
			setHasSudoku(true);
			break;
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

	/* ------------------- Is Exit Command test function ------------------- */
	private boolean isExitCommand(String input) {
		try {
			return Integer.parseInt(input) == 0;
		}
		catch (NumberFormatException e) {
			return false; // Not an integer, thus not an exit command
		}
	}

	/* -------------------- Construct Txt Path function -------------------- */
	private String constructTxtPath(String input) {
		if (!input.contains("/"))
			return DEFAULT_TXT_PATH + input + TXT_EXTENTION;

		return input;
	}

	/* -------------------- Construct Obj Path function -------------------- */
	// private String constructObjPath(String input) {
	// 	if (!input.contains("/")) {
	// 		return DEFAULT_OBJ_PATH + input + OBJ_EXTENTION;
	// 	}
	// 	return input;
	// }

	/* ----------------- Initialize Sudoku State function ------------------ */
	private boolean initializeSudokuState(String path) {
		try {
			_ss = new SudokuState();
			_ss.parseInput(path);
			return true;
		}
		catch (IOException e) {
			return false;
		}
	}
}
