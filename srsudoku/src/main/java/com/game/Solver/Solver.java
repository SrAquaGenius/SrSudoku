package com.game.Solver;

import com.game.Sudoku.Sudoku;

public abstract class Solver {

	private Sudoku _game;

	public Solver(Sudoku game) {
		this._game = game;
	}

	public Sudoku getGame() { return _game; }
	public void setGame(Sudoku game) { this._game = game;}

	public abstract void solve();
}
