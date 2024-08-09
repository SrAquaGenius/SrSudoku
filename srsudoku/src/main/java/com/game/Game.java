package com.game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import com.game.Sudoku.Sudoku;

import com.game.Utils.Debug;
import com.game.Utils.Error;
import com.game.Utils.Message;
import com.game.Utils.Path;
import com.game.Utils.Warning;
import com.game.Utils.Commands;

// import com.game.Solver.*;
// import com.game.Sudoku.Sudoku;

public class Game {

	// private GameWindow gameWindow;
	// private GamePanel gamePanel;

	private Sudoku _sudoku;

	private boolean _isLoaded = false;
	private boolean _isSaved = false;

	/* ---------------------------- Constructor ---------------------------- */
	public Game() throws IllegalArgumentException {
		Debug.place();
	}

	/* ------------------------ Game Loop function ------------------------- */
	public void gameLoop(Scanner scanner) {
		Debug.place();
		Commands.clearScreen();

		while (true) {
			Message.nextAction(isSudokuLoaded());
			String userInput = scanner.nextLine();

			try {
				int option = Integer.parseInt(userInput);

				if (option == 0) {
					if (isSudokuLoaded() && !isSudokuSaved())
						saveBeforeExit(scanner);
					break;
				}
				else if (option == 1) newSudoku(scanner);
				else if (option == 2) loadSudoku(scanner);
				else if (option == 3) saveSudoku(scanner);                
				else if (option == 4) manualAction(scanner);
				else if (option == 5) autoAction(scanner);
				else Error.invalidOption();
			}
			catch (NumberFormatException e) {
				Error.invalidInteger();
			}
		}

		scanner.close();
	}

	/* ------------------------ Getters and setters ------------------------ */
	public Sudoku getSudoku() { return _sudoku; }
	public Sudoku setSudoku(Sudoku sudoku) { return this._sudoku = sudoku; } 

	public boolean isSudokuLoaded() { return _isLoaded; }
	public void setSudokuLoadedStatus(boolean state) { _isLoaded = state; }

	public boolean isSudokuSaved() { return _isSaved; }
	public void setSudokuSavedStatus(boolean state) { _isSaved = state; }

	/* ------------------------ New Sudoku function ------------------------ */
	public void newSudoku(Scanner scanner) {
		Debug.place();

		while (true) {

			if (isSudokuLoaded() && !isSudokuSaved()) saveBeforeExit(scanner);

			Message.newSudoku();
			String newInput = scanner.nextLine();

			if (Commands.isExitCommand(newInput)) break;

			Sudoku sudoku = new Sudoku(newInput);

			if (sudoku.initializeSudoku(Path.constructTxtPath(newInput))) {
				setSudokuLoadedStatus(true);
				setSudokuSavedStatus(false);
				System.out.println(setSudoku(sudoku));
				break;
			}
		}
	}

	/* ----------------------- Load Sudoku function ------------------------ */
	public void loadSudoku(Scanner scanner) {
		Debug.place();

		while (true) {

			if (isSudokuLoaded() && !isSudokuSaved()) saveBeforeExit(scanner);

			Message.loadSudoku();
			String loadInput = scanner.nextLine();

			if (Commands.isExitCommand(loadInput)) break;

			String path = Path.constructObjPath(loadInput);
			if (loadSudokuAs(path)) return;
		}
	}

	/* ---------------------- Load Sudoku As function ---------------------- */
	public boolean loadSudokuAs(String objPath) {
		Debug.place();

		try (
			FileInputStream fileIn = new FileInputStream(objPath);
			ObjectInputStream in = new ObjectInputStream(fileIn)
		) {
			Sudoku sudoku = (Sudoku) in.readObject();
			Message.loadedObject();
			setSudoku(sudoku);
			setSudokuLoadedStatus(true);
			return true;
		} 
		catch (IOException i) {
			Error.onLoadObject();
			i.printStackTrace();
		} 
		catch (ClassNotFoundException c) {
			Error.onClassNotFound();
		}

		return false;
	}

	/* --------------------- Save Before Exit function --------------------- */
	public void saveBeforeExit(Scanner scanner) {
		Debug.place();

		while (true) {
			Message.saveBeforeExit();

			String exitInput = scanner.nextLine();

			if (exitInput.startsWith("y") || exitInput.startsWith("Y")) {
				saveSudoku(scanner);
				return;
			}
			else if (exitInput.startsWith("n") || exitInput.startsWith("N")) {
				return;
			}
			else {
				Warning.invalidYNOption();
			}
		}
	}

	/* ----------------------- Save Sudoku function ------------------------ */
	public void saveSudoku(Scanner scanner) {
		Debug.place();

		while (true) {
			String sudokuName = getSudoku().getName();
			Message.saveSudoku(sudokuName);

			String saveInput = scanner.nextLine();
			
			if (Commands.isExitCommand(saveInput)) return;

			String path;

			if (Commands.isEnterCommand(saveInput)) {
				Debug.print("Enter command");
				path = Path.constructObjPath(sudokuName);
			}
			else {
				Debug.print("Not enter command");
				path = Path.constructObjPath(saveInput);
			}

			if (saveSudokuAs(path)) {
				setSudokuSavedStatus(true);
				return;
			}
		}
	}

	/* ---------------------- Save Sudoku As function ---------------------- */
	public boolean saveSudokuAs(String objPath) {
		Debug.place();
		Debug.print(objPath);

		File file = new File(objPath);
		if (file.exists()) {
			Warning.duplicateFile(objPath);
			return false;
		}

		try (
			FileOutputStream fileOut = new FileOutputStream(objPath);
			ObjectOutputStream out = new ObjectOutputStream(fileOut)
		) {
			out.writeObject(getSudoku());
			Message.savedObject();
			return true;
		}
		catch (FileNotFoundException i) {
			Error.fileNotFound(objPath);
		}
		catch (IOException i) {
			Error.onSaveObject();
		}

		return false;
	}

	/* ---------------------- Manual Action function ----------------------- */
	public void manualAction(Scanner scanner) {
		Debug.place();
		
		while (true) {
			// Commands.clearScreen();
			System.out.println(getSudoku().getState());
			Message.manualAction();
			String manualInput = scanner.nextLine();

			try {
				if (Integer.parseInt(manualInput) == 0)
					break;
				else if (Integer.parseInt(manualInput) == 1)
					getSudoku().addPenDigit(scanner);
				else if (Integer.parseInt(manualInput) == 2)
					getSudoku().delPenDigit(scanner);
				else if (Integer.parseInt(manualInput) == 3)
					getSudoku().showCell(scanner);

				else Error.invalidOption();
			}

			catch (NumberFormatException e) {
				Error.invalidInteger();
			}
		}
	}

	/* ----------------------- Auto Action function ------------------------ */
	public void autoAction(Scanner scanner) {
		Debug.place();
		
		while (true) {
			// Commands.clearScreen();
			System.out.println(getSudoku().getState());
			Message.autoAction();
			String autoInput = scanner.nextLine();

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
}
