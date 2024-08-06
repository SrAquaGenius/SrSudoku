package com.game.Sudoku;

import java.io.Serializable;

public class Coord implements Serializable {

	private static final long serialVersionUID = 1L;

	private int _row;
	private int _col;

	/* ---------------------------- Constructor ---------------------------- */
	public Coord(int row, int col) {
		this._row = row;
		this._col = col;
	}

	/* ------------------- Getters and Setters functions ------------------- */
	public int getCol() { return _col; }
	public int getRow() { return _row; }

	public void setCol(int col) { this._col = col; }
	public void setRow(int row) { this._row = row; }

	/* ---------------------- Is Equal test function ----------------------- */
	public boolean isEqual(Coord other) {
		return this.getCol() == other.getCol() &&
			this.getRow() == other.getRow();
	}

	/* ---------------------- Is Valid test function ----------------------- */
	public boolean isValid(int size) {
		return this.getCol() >= 0 && this.getCol() < size
			&& this.getRow() >= 0 && this.getRow() < size;
	}

	/* ------------------------ To String function ------------------------- */
	@Override
	public String toString() {
		return "(" + this.getRow() + "," + this.getCol() + ")";
	}
}
