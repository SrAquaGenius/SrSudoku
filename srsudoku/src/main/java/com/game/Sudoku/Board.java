package com.game.Sudoku;

import com.game.App;
import com.game.Error;
import com.game.Warning;

import com.game.Options.BoardOptions;

import java.util.ArrayList;
import java.util.List;

public class Board {

	public static final int BOX_SIZE = 3;

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
				_matrix.add(index, new Cell(coord, input[j], _size, true));

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

		int TWO_BOX_SIZE = 2 * BOX_SIZE;

		if (boxNumber <= 0) { return -1; }

		if (boxNumber <= BOX_SIZE * 1) {
			return BOX_SIZE * (boxNumber - 1);
		}
		if (boxNumber <= BOX_SIZE * 2) {
			return BOX_SIZE * (boxNumber % BOX_SIZE - 1) + BOX_SIZE * getSize();
		}
		if (boxNumber <= BOX_SIZE * 3) {
			return BOX_SIZE * (boxNumber % TWO_BOX_SIZE - 1) + TWO_BOX_SIZE * getSize();
		}

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

	/* --------------- Interation and propagation functions ---------------- */
	public void doIteration() {
		for (int m = 0; m < getSize()*getSize() - 1; m++) {
			Cell cell = _matrix.get(m);

			// App.debug(cell.toString());

			if (!cell.isFilled()) { continue; }

			propagateDigit(cell);
		}
	}

	public void propagateDigit(Cell cell) {
		// App.debug("Propagate digit: " + cell);

		Coord coord = cell.getCoord();
		List<Cell> cellsVisited = new ArrayList<Cell>();

		propagateInBox(cellsVisited, getBoxNumber(coord), cell.getDigit());
		propagateInRow(cellsVisited, coord.getRow(), cell.getDigit());
		propagateInCol(cellsVisited, coord.getCol(), cell.getDigit());
	}

	public void propagateInBox(List<Cell> cellsVisited, int box, int digit) {
		// App.debug("Propagate in box (box: " + box + ", digit: " + digit + "):");

		if (box == 0) return;

		int base = getBoxIndex(box);
		if (base < 0) return;

		int index;

		for (int i = 0; i < BOX_SIZE; i ++) {
			for (int j = 0; j < BOX_SIZE; j++) {

				index = base + i + j * getSize();
				Cell testCell = getMatrix().get(index);

				if (testCell.isIn(cellsVisited)) continue;

				if (!testCell.isHint() && !testCell.isFilled()) {
					testCell.removeValidDigit(digit);
					cellsVisited.add(testCell);
				}
			}
		}
	}

	public void propagateInRow(List<Cell> cellsVisited, int row, int digit) {
		// App.debug("Propagate int row (row: " + row + ", digit: " + digit + "):");

		for (int i = 0; i < getSize(); i++) {

			Cell testCell = getMatrix().get(row * getSize() + i);
			if (testCell.isIn(cellsVisited)) continue;

			if (!testCell.isFilled()) {
				testCell.removeValidDigit(digit);
				cellsVisited.add(testCell);
			}
		}
	}

	public void propagateInCol(List<Cell> cellsVisited, int col, int digit) {
		// App.debug("Propagate in col (col: " + col + ", digit: " + digit + "):");

		for (int j = 0; j < getSize(); j++) {

			Cell testCell = getMatrix().get(col + j * getSize());
			if (testCell.isIn(cellsVisited)) continue;

			if (!testCell.isFilled()) {
				testCell.removeValidDigit(digit);
				cellsVisited.add(testCell);
			}
		}
	}

	/* --------------------- Next Iteraction function ---------------------- */
	public boolean nextIteration() {

		for (int m = 0; m < getSize()*getSize() - 1; m++) {
			Cell cell = _matrix.get(m);

			// App.debug(cell.toString());

			if (cell.isFilled()) { continue; }
			if (cell.getValidDigits() == null) { continue; }

			List<Integer> digits = cell.getValidDigits();

			if (digits.size() == 1) {

				App.debug(cell.toString() + " " + digits.get(0));

				cell.updateDigit();
				_matrix.set(m, cell);
				propagateDigit(cell);
				return true;
			}
		}

		return false;
	}

	/* ----------------------- Add Digit In function ----------------------- */
	public boolean addPenDigit(int digit, int row, int col) {
		App.debug("[Board, Add pen digit]");

		Coord coord = new Coord(row, col);
		if (!coord.isValid(getSize())) {
			Error.invalidCoord(coord, getSize());
			return false;
		}

		int index = coordToIndex(coord);
		Cell oldCell = _matrix.get(index);

		if (oldCell.isHint()) {
			Error.overwriteHintCell(oldCell);
			return false;
		}

		if (oldCell.isFilled()) {
			Warning.overwriteFilledCell(oldCell);
		}
		
		Cell newCell = new Cell(coord, "" + digit, getSize(), false);
		_matrix.set(index, newCell);
		return true;
	}

	public boolean delPenDigit(int row, int col) {
		App.debug("[Board, Delete pen digit]");

		Coord coord = new Coord(row, col);
		if (!coord.isValid(getSize())) {
			Error.invalidCoord(coord, getSize());
			return false;
		}

		int index = coordToIndex(coord);
		Cell oldCell = _matrix.get(index);

		if (oldCell.isHint()) {
			Error.deleteHintCell(oldCell);
			return false;
		}

		if (!oldCell.isFilled()) {
			Warning.deleteEmptyCell(oldCell);
			return true;
		}

		Cell newCell = new Cell(coord, ".", getSize(), false);
		_matrix.set(index, newCell);
		return true;
	}

	/* ------------------------ To String function ------------------------- */
	@Override
	public String toString() {
		String text = "";

		for (int i = 0; i < _size*_size; i++) {
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

	public String print() {

		String text = "\nPrint board:\n";

		for (int i = 0; i < _size*_size; i++) {

			Cell cell = _matrix.get(i);
			text += cell.print(true) + " - " + cell.getValidDigits() + "\n";
		}

		return text;
	}
}
