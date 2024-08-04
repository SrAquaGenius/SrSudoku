package com.game;

import java.io.File;
import java.io.FileInputStream;
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

// import com.game.Solver.*;
// import com.game.Sudoku.Sudoku;

public class Game {

	// private GameWindow gameWindow;
	// private GamePanel gamePanel;

	private boolean _hasSudoku = false;
	private Sudoku _sudoku;

	/* ---------------------------- Constructor ---------------------------- */
	public Game() throws IllegalArgumentException {
		Debug.place();
	}

	/* ------------------------ Game Loop function ------------------------- */
	public void gameLoop(Scanner sc) {
		String userInput;

		while (true) {
			Message.nextAction(hasSudoku());
			userInput = sc.nextLine();

			try {
				int option = Integer.parseInt(userInput);

				if (option == 0) break;
				else if (option == 1) newSudoku(sc);
				else if (option == 2) loadSudoku(sc);
				else if (option == 3) saveSudoku(sc);                
				else if (option == 4) manualAction(sc);
				else if (option == 5) autoAction(sc);
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
	public void newSudoku(Scanner scanner) {
		Debug.place();

		while (true) {
			Message.newSudoku();
			String newInput = scanner.nextLine();

			if (isExitCommand(newInput)) break;

			Sudoku sudoku = new Sudoku(newInput);

			if (sudoku.initializeSudoku(Path.constructTxtPath(newInput))) {
				setHasSudoku(true);
				System.out.println(setSudoku(sudoku));
				break;
			}
		}
	}

	/* ----------------------- Load Sudoku function ------------------------ */
	public void loadSudoku(Scanner scanner) {
		Debug.place();

		while (true) {
			Message.loadSudoku();
			String loadInput = scanner.nextLine();

			if (isExitCommand(loadInput)) break;

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

	/* ----------------------- Save Sudoku function ------------------------ */
	public void saveSudoku(Scanner scanner) {
		Debug.place();

		while (true) {
			String sudokuName = getSudoku().getName();
			Message.saveSudoku(sudokuName);

			String saveInput = scanner.nextLine();
			
			if (isExitCommand(saveInput)) return;

			String path;

			if (isEnterCommand(saveInput)) {
				Debug.print("Enter command");
				path = Path.constructObjPath(sudokuName);
			}
			else {
				Debug.print("Not enter command");
				path = Path.constructObjExtention(saveInput);
			}

			if (saveSudokuAs(path)) return;
		}
	}

	/* ---------------------- Save Sudoku As function ---------------------- */
	public boolean saveSudokuAs(String objPath) {
		Debug.place();
		Debug.print(objPath);

		File file = new File(objPath);
		if (file.exists()) {
			Warning.onDuplicateFile(objPath);
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
		catch (IOException i) {
			Error.onSaveObject();
			return false;
		}
	}

	/* ---------------------- Manual Action function ----------------------- */
	public void manualAction(Scanner scanner) {
		
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
	public void autoAction(Scanner scanner) {
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

	private boolean isEnterCommand(String input) {
		return input.length() == 0;
	}
}
