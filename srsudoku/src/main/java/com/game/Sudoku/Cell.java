package com.game.Sudoku;

import com.game.App;
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

	private boolean _beenVisited = false;

	/* -----------------------------Constructor----------------------------- */
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

	public boolean beenVisited() { return _beenVisited; }
	public void changeVisibility() { _beenVisited = !_beenVisited; }

	public int getDigit() { return _digit; }
	public void setDigit(int digit) { this._digit = digit; }

	public String getContent() { return (isFilled()) ? "" + getDigit() : "."; }

	public List<Integer> getValidDigits() { return _validDigits; }

	public CellOptions getOptions() { return _options; }
	public void setOptions(CellOptions options) { this._options = options; }

	/* ------------------------- In list function -------------------------- */
	public boolean isIn(List<Cell> list) {
	
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).isEqual(this))
				return true;
		}
	
		return false;
	}

	/* ------------------------- Is Equal function ------------------------- */
	public boolean isEqual(Cell otherCell) {
		return this.getCoord().isEqual(otherCell.getCoord());
	}

	/* -------------------- Remove valid digit function -------------------- */
	public void removeValidDigit(int digit) {

		// String debug = "Remove: " + this + " " + digit + " [";

		for (int i = 0; i < _validDigits.size(); i++) {
			if (_validDigits.get(i) == digit)
				_validDigits.remove(i);
		}

		// for (int i = 0; i < _validDigits.size(); i++) {
			
		// 	debug += _validDigits.get(i);
		// 	if (i < _validDigits.size() - 1)
		// 		debug += ", ";
		// }

		// App.debug(debug + "]");
	}

	/* ----------------------- Update Digit function ----------------------- */
	public void updateDigit() {
		int digit = _validDigits.get(0);
		this._digit = digit;
		this._isFilled = true;
	}

	/* -------------------------- Print function --------------------------- */
	public String print(boolean showCoord) {
		return (showCoord) ? getCoord() + ": " + getContent() : getContent();
	}

	/* ------------------------ To String function ------------------------- */
	@Override
	public String toString() {
		return getCoord() + ": " + getContent();
	}
}
