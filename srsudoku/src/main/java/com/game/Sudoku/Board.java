package com.game.Sudoku;

import java.util.ArrayList;

public class Board {

	private int _size;
	private Coord _maxCoord;
	private ArrayList<Cell> _matrix;

	public Board() {}

	public void create(int size, String[] inputGrid) {

		this._size = size;
		_maxCoord = new Coord(this._size-1, this._size-1);
		_matrix = new ArrayList<Cell>(_size*_size);
	
		for (int i = 0; i < _size; i++) {
			String[] input = inputGrid[i].split("");

			for (int j = 0; j < _size; j++) {
				int index = i*_size + j;
				Coord coord = indexToNewCoord(index, _size);
				_matrix.add(index, new Cell(coord, input[j]));
			}
		}
	}

	/* ------------------------ Getters and setters ------------------------ */
	public Coord getMaxCoord() { return _maxCoord; }
	public void setMaxCoord(Coord maxCoord) { this._maxCoord = maxCoord; }

	public int getSize() { return _size; }
	public void setSize(int size) { this._size = size; }

	public ArrayList<Cell> getMatrix() { return _matrix; }
	public void setMatrix(ArrayList<Cell> matrix) { this._matrix = matrix; }

	public Coord indexToNewCoord(int index, int size) {

		int row = index / size;
		int col = index % size;

		return new Coord(row, col);
	}

	public int coordToIndex(Coord coord) {
		return coord.getRow() * getSize() + coord.getCol();
	}

	public int getBoxNumber(Coord coord) {

		int size = getSize();
		int row = coord.getRow();
		int col = coord.getCol();

		if (row < size/3 && col < size/3) return 1;
		if (row < size/3 && col < size/3*2) return 2;
		if (row < size/3 && col < size) return 3;
		if (row < size/3*2 && col < size/3) return 4;
		if (row < size/3*2 && col < size/3*2) return 5;
		if (row < size/3*2 && col < size) return 6;
		if (row < size && col < size/3) return 7;
		if (row < size && col < size/3*2) return 8;
		if (row < size && col < size) return 9;

		return 0;
	}

	/* --------------------------- Boolean tests --------------------------- */

	public boolean isCellEmpty(int index) {
		return getMatrix().get(index).getContent() == ".";
	}


	public boolean isRowFull(int rowNumber) {

		int row = rowNumber * getSize();
		for (int i = 0; i < getSize(); i++) {

			String content = getMatrix().get(row + i).getContent();
			if (content == ".") return false;
		}

		return true;
	}

	public boolean isColFull(int colNumber) {

		int col = colNumber * getSize();
		for (int j = 0; j < getSize(); j++) {

			String content = getMatrix().get(col + j*getSize()).getContent();
			if (content == ".") return false;
		}

		return true;
	}

	public boolean canAddInRow(int row, int digit) {

		for (int i = 0; i < getSize(); i++) {
			if (getMatrix().get(row + i).getContent() == "" + digit)
				return false;
		}

		return true;
	}

	public boolean canAddInCol(int col, int digit) {

		for (int j = 0; j < getSize(); j++) {
			if (getMatrix().get(col + j*getSize()).getContent() == "" + digit)
				return false;
		}

		return true;
	}

	public boolean canAddInBox(Coord coord, int digit) {

		int box = getBoxNumber(coord);

		if (box == 0) return false;

		// TODO


		return true;
	}

	public boolean canAddDigitIn(Coord coord, int digit) {

		if (!coord.isValid(_size)) return false;
		if (!isCellEmpty(coordToIndex(coord))) return false;
		if (!canAddInRow(coord.getRow(), digit)) return false;
		if (!canAddInCol(coord.getCol(), digit)) return false;
		if (!canAddInBox(coord, digit)) return false;

		return true;
	}

	/* ------------------------ To String function ------------------------- */
	@Override
	public String toString() {
		String text = "";

		for (int i = 0; i< _size*_size; i++) {
			if (i != 0 && i % _size == 0)
				text += '\n';

			if (i != 0 && i % _size != 0 && i % 3 == 0)
				text += '|';

			if (i != 0 && i % (3*_size) == 0)
				text += "---+---+---\n";

			text += _matrix.get(i);
		}

		return text;
	}
}
