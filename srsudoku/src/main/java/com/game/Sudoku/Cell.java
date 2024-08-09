package com.game.Sudoku;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.game.Sudoku.Options.CellOptions;

import com.game.Utils.Debug;

public class Cell implements Serializable {

	private static final long serialVersionUID = 1L;

	private Coord _coord;
	private int _digit = 0;

	private boolean _isHint = false;
	private boolean _isFilled = false;
	private boolean _beenVisited = false;

	private List<Integer> _pencilDigits = null;

	private List<Integer> _validDigits = null;

	private CellOptions _options;

	/* -----------------------------Constructor----------------------------- */
	public Cell(Coord coord, String content, int size, boolean isHint) {
		this._coord = coord;

		int digit;
		try {
			digit = Integer.parseInt(content);
			_digit = digit;
			_isHint = isHint;
			_isFilled = true;
		}
		catch (NumberFormatException e) {
			_validDigits = new ArrayList<Integer>(size);

			for (int i = 1; i <= size; i++)
				_validDigits.add(i);
		}

		_pencilDigits = new ArrayList<Integer>(size);
	}

	/* ------------------- Getters and setters functions ------------------- */
	public Coord getCoord() { return _coord; }
	public void setCoord(Coord coord) { this._coord = coord; }

	public boolean isHint() { return _isHint; }
	public boolean isFilled() { return _isFilled; }

	public boolean beenVisited() { return _beenVisited; }
	public void toggleVisibility() { _beenVisited = !_beenVisited; }

	public int getDigit() { return _digit; }
	public void setDigit(int digit) { this._digit = digit; }

	public String getContent() { return (isFilled()) ? "" + getDigit() : "."; }

	public List<Integer> getValidDigits() { return _validDigits; }
	public List<Integer> getPencilDigits() { return _pencilDigits; }

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
		Debug.print("[Remove Valid Digit] " + digit + " from " + print(false));

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

	/* ------------------------ Print Base function ------------------------ */
	public String printBase() {
		return "[Cell] " + getCoord() + ": " + getContent();
	}

	/* -------------------------- Print function --------------------------- */
	public String print(boolean verbose) {
		return (verbose) ? this.toString() : this.printBase();
	}

	/* ------------------------ To String function ------------------------- */
	@Override
	public String toString() {
		String text = printBase();

		text += "\n  Is hint cell? " + isHint() +
				"\n  Is cell filled? " + isFilled();

		if (!isFilled()) {

			// text += "\n  Annotated digits: ";

			// if (getPencilDigits().size() == 0) {
			// 	text += "empty list";
			// }
			// else {
			// 	text += listToString(getPencilDigits());
			// }

			text += "\n  Possible digits: " + listToString(getValidDigits());
		}

		return text;
	}

	/* ---------------------- List To String function ---------------------- */
	private String listToString(List<Integer> list) {
		Debug.place();

		String str = "";

		for (int i = 0; i < list.size(); i++) {
			str += list.get(i);

			if (i < list.size() - 1)
				str += ", ";
		}

		return str;
	}
}
