package com.game.Sudoku;

import com.game.Options.BoardOptions;

import java.util.ArrayList;
import java.util.List;

public class Board {

	public boolean showCoord = false;

	private int _size;
	private Coord _maxCoord;

	private List<Cell> _matrix;
	private List<Integer> _remainDigits;

	private BoardOptions _options;

	public Board() {}

	public void create(int size, String[] inputGrid) {

		this._size = size;
		_maxCoord = new Coord(this._size-1, this._size-1);
		_matrix = new ArrayList<Cell>(_size*_size);
		initializeDigits();
	
		for (int i = 0; i < _size; i++) {
			String[] input = inputGrid[i].split("");

			for (int j = 0; j < _size; j++) {
				int index = i*_size + j;
				Coord coord = indexToNewCoord(index, _size);
				_matrix.add(index, new Cell(coord, input[j], _size));

				try {
					int digitIndex = Integer.parseInt(input[j]) - 1;
					int newValue = _remainDigits.get(digitIndex) + 1;
					_remainDigits.set(digitIndex, newValue);
				}
				catch (NumberFormatException e) {
					// Do nothing, its an empty cell
				}
			}
		}
	}

	private void initializeDigits() {
		_remainDigits = new ArrayList<Integer>(_size);

		for (int d = 0; d < getSize(); d++) {
			_remainDigits.add(d, 0);
		}
	}

	/* ------------------------ Getters and setters ------------------------ */
	public Coord getMaxCoord() { return _maxCoord; }
	public void setMaxCoord(Coord maxCoord) { this._maxCoord = maxCoord; }

	public int getSize() { return _size; }
	public void setSize(int size) { this._size = size; }

	public List<Cell> getMatrix() { return _matrix; }
	public void setMatrix(ArrayList<Cell> matrix) { this._matrix = matrix; }

	public List<Integer> getDigits() { return _remainDigits; }
	public void setDigits(List<Integer> digits) { this._remainDigits = digits; }

	public BoardOptions getOptions() { return _options; }
	public void setOptions(BoardOptions options) { this._options = options; }

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

		if (row < 0 && col < 0) return 0;
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

	public int getBoxIndex(int boxNumber) {
		if (boxNumber < 0) { return -1; }
		if (boxNumber <= 3) { return boxNumber - 1; }
		if (boxNumber <= 6) { return boxNumber - 1 + 3 * getSize(); }
		if (boxNumber <= 9) { return boxNumber - 1 + 6 * getSize(); }
		else { return -1; }
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

	public boolean canAddDigit(int digit) {
		return getDigits().get(digit - 1) == getSize();
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

		int boxId = getBoxNumber(coord);
		if (boxId == 0) return false;

		int base = getBoxIndex(boxId);
		if (base < 0) return false;

		int index;

		for (int i = 0; i < 3; i ++) {
			for (int j = 0; j < 3; j++) {
				index = base + i + j * getSize();
				if (getMatrix().get(index).getContent() == "" + digit)
					return false;
			}
		}

		return true;
	}

	public boolean canAddDigitIn(Coord coord, int digit) {

		if (!coord.isValid(getSize())) return false;
		if (!canAddDigit(digit)) return false;
		if (!isCellEmpty(coordToIndex(coord))) return false;
		if (!canAddInRow(coord.getRow(), digit)) return false;
		if (!canAddInCol(coord.getCol(), digit)) return false;
		if (!canAddInBox(coord, digit)) return false;

		return true;
	}

	/* ------------------------- Actions function -------------------------- */
	// public void nextAction() {
	// 	for (int d = 0; d < getSize(); d++) {
	// 		if (addDigit(d + 1)) return;
	// 	}
	// }

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

			text += _matrix.get(i).print(showCoord);
		}

		return text;
	}
}
