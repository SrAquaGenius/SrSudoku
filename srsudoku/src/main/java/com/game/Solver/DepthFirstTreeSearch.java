package com.game.Solver;

import com.game.Sudoku.Sudoku;

import com.game.Utils.Debug;

public class DepthFirstTreeSearch extends Solver {

	public DepthFirstTreeSearch(Sudoku sudoku) {
		super(sudoku);
	}

	@Override
	public void solve() {
		Debug.place();

	}
}
