package com.game.Sudoku;

import com.game.Options.CellOptions;

import java.util.ArrayList;
import java.util.List;

public class Cell {

	private Coord _coord;

	private boolean _isHint = false;
	private boolean _isFilled = false;
	private int _digit = 0;

	private List<Integer> _validDigits = null;

	private CellOptions _options;

	public Cell(Coord coord, String content, int size) {
		this._coord = coord;

		int digit;
		try {
			digit = Integer.parseInt(content);
			_digit = digit;
			_isHint = true;
			_isFilled = true;
		}
		catch (NumberFormatException e) {
			_validDigits = new ArrayList<Integer>(size);

			for (int i = 1; i <= size; i++)
				_validDigits.add(i);
		}
	}

	/* ------------------- Getters and setters functions ------------------- */
	public Coord getCoord() { return _coord; }
	public void setCoord(Coord coord) { this._coord = coord; }

	public boolean isHint() { return _isHint; }
	public boolean isFilled() { return _isFilled; }

	public int getDigit() { return _digit; }
	public void setDigit(int digit) { this._digit = digit; }

	public CellOptions getOptions() { return _options; }
	public void setOptions(CellOptions options) { this._options = options; }

	public String getContent() {
		if (isFilled())
			return "" + getDigit();

		return ".";
	}

	/* -------------------------- Print function --------------------------- */
	public String print(boolean showCoord) {
		if (showCoord)
			return getCoord() + ": " + getContent();
	
		return getContent();
	}

	/* ------------------------ To String function ------------------------- */
	@Override
	public String toString() {
		return getCoord() + ": " + getContent();
	}
}
