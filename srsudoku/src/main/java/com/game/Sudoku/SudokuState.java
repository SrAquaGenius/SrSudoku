package com.game.Sudoku;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.game.App;
import com.game.Utils.Debug;
import com.game.Utils.Error;
import com.game.Utils.Message;
import com.game.Utils.Regex;

public class SudokuState {

	public int _STATE_ID = 0;

	private int _id;
	private Board _board;

	public SudokuState() {
		this._board = new Board();
		this._id = _STATE_ID;
		_STATE_ID += 1;
	}

	/* ------------------- Getters and setters functions ------------------- */
	public Board getBoard() { return _board; }
	public void setBoard(Board board) { this._board = board; }

	public int getId() { return _id; }
	public void setId(int id) { this._id = id; }

	/* ----------------------- Parse Input function ------------------------ */
	public void parseInput(String filePath) throws IOException {

		BufferedReader reader = new BufferedReader(new FileReader(filePath));

		// Read the first line which contains the number of rows
		String firstLine = reader.readLine();
		if (firstLine == null) {
			reader.close();
			throw new IllegalArgumentException("File is empty.");
		}

		int dim;
		try {
			dim = Integer.parseInt(firstLine.trim());
		} catch (NumberFormatException e) {
			reader.close();
			throw new IllegalArgumentException(Error.invalidFirstLine());
		}

		// Read the grid lines
		String[] grid = new String[dim];
		for (int i = 0; i < dim; i++) {
			String line = reader.readLine();
			if (line == null) {
				reader.close();
				throw new IllegalArgumentException(Error.notEnoughLines());
			}
			grid[i] = line;
		}

		// Check if there are extra lines beyond the expected number
		if (reader.readLine() != null) {
			reader.close();
			throw new IllegalArgumentException(Error.tooManyLines());
		}

		reader.close();
		_board.create(dim, grid);
		_board.doIteration();
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
					break;
				}
				
				else if (Integer.parseInt(addInput) == 0)
					return;
				else Error.invalidPattern();
			}

			catch (NumberFormatException e) {
				Error.invalidPattern();
			}
		}

		if (getBoard().addPenDigit(value, x, y)) {
			_id += 1;
		}
	}

	public void delPenDigit(Scanner scanner) {
		Debug.place();

		String delInput;
		int x, y;
		
		while (true) {
			Message.delPenDigitGuide();
			delInput = scanner.nextLine();

			Pattern pattern = Pattern.compile(Regex.coord());
			Matcher matcher = pattern.matcher(delInput);

			try {
				if (matcher.matches()) {
					x = Integer.parseInt(matcher.group(1));
					y = Integer.parseInt(matcher.group(2));
	
					Debug.print("Coordinates: (" + x + "," + y + ")");
					break;
				}
	
				else if (Integer.parseInt(delInput) == 0)
					return;
				else Error.invalidPattern();
			}

			catch (NumberFormatException e) {
				Error.invalidPattern();
			}			
		}

		if (getBoard().delPenDigit(x, y)) {
			_id += 1;
		}
	}

	/* ----------------------- Auto Action functions ----------------------- */
	public void nextIteration() {
		Debug.place();
		_id += 1;
		getBoard().nextIteration();
	}

	public void fullGeneration() {
		Debug.place();
		boolean eval = true;

		while (eval) {
			_id += 1;
			eval = getBoard().nextIteration();
		}
	}

	/* -------------------------- Solve function --------------------------- */
	public void solve() {
		Debug.place();
	}

	/* ------------------------ To String function ------------------------- */
	@Override
	public String toString() {
		return "id: " + getId() + "\n" + getBoard() + "\n";
	}
}
