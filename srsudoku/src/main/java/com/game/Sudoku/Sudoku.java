package com.game.Sudoku;

public class Sudoku {

	private SudokuState _state;

	public Sudoku(SudokuState state) {
		this._state = state;
	}

	public SudokuState getState() { return _state; }
	public void setState(SudokuState state) { this._state = state; }

}
