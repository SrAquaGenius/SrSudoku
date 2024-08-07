package com.game.Sudoku;

import java.io.Serializable;

import com.game.Utils.Debug;

public class SudokuState implements Serializable {

	private static final long serialVersionUID = 1L;

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

	/* --------------------------------------------------------------------- */
	public void populateBoard(int dim, String[] grid) {
		Debug.todoMsg("Bring method from board to here???");
		getBoard().create(dim, grid);
	}

	public void doIteration() {
		Debug.todoMsg("Bring method from board to here???");
		getBoard().doIteration();
	}

	/* ---------------------------- Operations ----------------------------- */
	public boolean addPenDigit(int value, int x, int y) {
		if (getBoard().addPenDigit(value, x, y)) {
			_id += 1;
			return true;
		}

		return false;
	}

	public boolean delPenDigit(int x, int y) {
		if (getBoard().delPenDigit(x, y)) {
			_id += 1;
			return true;
		}

		return false;
	}

	/* ------------------------ To String function ------------------------- */
	@Override
	public String toString() {
		return "[Sudoku State] id: " + getId() + "\n" + getBoard() + "\n";
	}
}
