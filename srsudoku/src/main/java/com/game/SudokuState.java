package com.game;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Set;

public class SudokuState {

	public int _STATE_ID = 0;

	private int _id;
	private Board _board;

	public SudokuState(int size) {
		_board = new Board(size);
		this._id = _STATE_ID;
		_STATE_ID += 1;
	}

	public Board getBoard() { return _board; }
	public void setBoard(Board board) { this._board = board; }

	public int getId() { return _id; }
	public void setId(int id) { this._id = id; }

	public void parseInput() {
		_board.initCells();
	}

	@Override
	public String toString() {
		return "id: " + getId() + "\n" + getBoard();
	}
}
