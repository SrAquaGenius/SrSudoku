package com.game.Solver;

import com.game.Sudoku.Sudoku;

public abstract class Solver {

	private Sudoku _sudoku;

	public Solver(Sudoku sudoku) {
		this._sudoku = sudoku;
	}

	public Sudoku getGame() { return _sudoku; }
	public void setGame(Sudoku sudoku) { this._sudoku = sudoku;}

	public abstract void solve();
}
