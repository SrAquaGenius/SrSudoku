package com.game.Sudoku;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.Serializable;

import com.game.Utils.Debug;
import com.game.Utils.Error;
import com.game.Utils.Message;
import com.game.Utils.Regex;
import com.game.Utils.Commands;

public class Sudoku implements Serializable {

	private static final long serialVersionUID = 1L;

	private String _name;
	private SudokuState _state;

	/* ---------------------------- Constructor ---------------------------- */
	public Sudoku(String name) {
		this._name = name;
	}

	/* ------------------- Getters and setters functions ------------------- */
	public SudokuState getState() { return _state; }
	public void setState(SudokuState state) { this._state = state; }

	public String getName() { return _name; }
	public void setName(String name) { this._name = name;}

	/* -------------------- Initialize Sudoku function --------------------- */
	public boolean initializeSudoku(String path) {
		try {
			SudokuState state = new SudokuState();

			if (!parseInput(path, state))
				return false;

			Debug.print(state.getBoard().print());
			setState(state);
			return true;
		}
		catch (IOException e) {
			Error.fileInteraction(path);
			return false;
		}
	}

	/* ----------------------- Parse Input function ------------------------ */
	public boolean parseInput(String path, SudokuState ss) throws IOException {

		BufferedReader reader = new BufferedReader(new FileReader(path));

		// Read the first line which contains the number of rows
		String firstLine = reader.readLine();
		if (firstLine == null) {
			Error.emptyFile(path);
			reader.close();
			return false;
		}

		int dim;
		try {
			dim = Integer.parseInt(firstLine.trim());
		} catch (NumberFormatException e) {
			Error.invalidFirstLine();
			reader.close();
			return false;
		}

		// Read the grid lines
		String[] grid = new String[dim];
		for (int i = 0; i < dim; i++) {
			String line = reader.readLine();
			if (line == null) {
				Error.notEnoughLines();
				reader.close();
				return false;
			}
			grid[i] = line;
		}

		// Check if there are extra lines beyond the expected number
		if (reader.readLine() != null) {
			Error.tooManyLines();
			reader.close();
			return false;
		}

		reader.close();

		ss.populateBoard(dim, grid);
		ss.doIteration();
		return true;
	}

	/* ---------------------- Manual Action functions ---------------------- */
	public void addPenDigit(Scanner scanner) {
		Debug.place();
		
		String addInput;

		int x, y, value;
		
		while (true) {
			Message.addPenDigitGuide();
			addInput = scanner.nextLine();

			Pattern pattern = Pattern.compile(Regex.coordDigit());
			Matcher matcher = pattern.matcher(addInput);

			try {
				if (matcher.matches()) {
					x = Integer.parseInt(matcher.group(1));
					y = Integer.parseInt(matcher.group(2));
					value = Integer.parseInt(matcher.group(3));
	
					Debug.print("Coordinates: (" + x + "," + y + ")");
					Debug.print("Value: " + value);

					if (getState().addPenDigit(value, x, y)) break;
				}
				
				else if (Integer.parseInt(addInput) == 0)
					return;
				else Error.invalidPattern();
			}

			catch (NumberFormatException e) {
				Error.invalidPattern();
			}
		}
	}

	public void delPenDigit(Scanner scanner) {
		Debug.place();
		
		while (true) {
			Message.delPenDigitGuide();
			String delInput = scanner.nextLine();

			if (Commands.isExitCommand(delInput)) return;

			Pattern pattern = Pattern.compile(Regex.coord());
			Matcher matcher = pattern.matcher(delInput);

			try {
				if (!matcher.matches()) {
					Error.invalidPattern();
					continue;
				}

				int x = Integer.parseInt(matcher.group(1));
				int y = Integer.parseInt(matcher.group(2));

				Debug.print("Coordinates: (" + x + "," + y + ")");

				if (getState().delPenDigit(x, y)) break;
			}
			catch (NumberFormatException e) {
				Error.invalidPattern();
			}
		}
	}

	/* ----------------------- Auto Action functions ----------------------- */
	public void nextIteration() {
		Debug.todo();
		// _id += 1;
		// getBoard().nextIteration();
	}

	public void fullGeneration() {
		Debug.todo();
		// boolean eval = true;

		// while (eval) {
		// 	_id += 1;
		// 	eval = getBoard().nextIteration();
		// }
	}

	/* -------------------------- Solve function --------------------------- */
	public void solve() {
		Debug.todo();
	}

	/* ------------------------ To String function ------------------------- */
	public String toString() {
		return "[Sudoku] name: " + getName() + ", id: " + getState().getId();
	}
}
