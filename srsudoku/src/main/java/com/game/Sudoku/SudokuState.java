package com.game.Sudoku;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import com.game.App;
import com.game.Error;

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
	public void parseInput(String filePath) {

		try {

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
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* ---------------------- Manual Action functions ---------------------- */
	public void addPenDigit(Scanner scanner) {
		_id += 1;
	}

	public void delPenDigit(Scanner scanner) {
		_id += 1;
	}

	/* ----------------------- Auto Action functions ----------------------- */
	public void nextIteration() {
		_id += 1;
		getBoard().nextIteration();
	}

	public void fullGeneration() {
		boolean eval = true;

		while (eval) {
			_id += 1;
			eval = getBoard().nextIteration();
		}
	}

	/* -------------------------- Solve function --------------------------- */
	public void solve() {
		App.debug("«Solve function»");
	}

	/* ------------------------ To String function ------------------------- */
	@Override
	public String toString() {
		return "id: " + getId() + "\n" + getBoard() + "\n";
	}
}
