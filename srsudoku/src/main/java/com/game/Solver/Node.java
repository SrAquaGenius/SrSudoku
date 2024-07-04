package com.game.Solver;

import java.util.ArrayList;
import java.util.List;

import com.game.Sudoku.SudokuState;

public class Node {

	private SudokuState _state;
	private List<Node> _children;

	public Node(SudokuState state) {
		this._state = state;
		this._children = new ArrayList<Node>();
	}

	/* ------------------------ Getters and setters ------------------------ */
	public SudokuState getState() { return _state; }
	public void setState(SudokuState _state) { this._state = _state; }

	public Node getChild(int index) { return _children.get(index); }
	public void addChild(Node child) { _children.add(child); }

	/* ------------------------ To String function ------------------------- */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
