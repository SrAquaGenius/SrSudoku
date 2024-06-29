package com.game;

public class Coord {

	private int _row;
	private int _col;

	public Coord(int row, int col) {
		this._row = row;
		this._col = col;
	}

	public int getCol() { return _col; }
	public int getRow() { return _row; }

	public void setCol(int col) { this._col = col; }
	public void setRow(int row) { this._row = row; }

	public boolean isEqual(Coord other) {
		return this.getCol() == other.getCol() &&
			this.getRow() == other.getRow();
	}

	@Override
	public String toString() {
		return "(" + this.getRow() + "," + this.getCol() + ")";
	}
}
