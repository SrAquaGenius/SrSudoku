package com.game;

import java.util.ArrayList;

public class Board {

	private int _size;
	private Coord _maxCoord;
	private ArrayList<Cell> _matrix;

	public Board() {}

	public void create(int size, String[] inputGrid) {

		this._size = size;
		_maxCoord = new Coord(this._size-1, this._size-1);
		_matrix = new ArrayList<Cell>(_size*_size);
	
		for (int i = 0; i < _size; i++) {
			String[] input = inputGrid[i].split("");

			for (int j = 0; j < _size; j++) {
				int index = i*_size + j;
				Coord coord = indexToNewCoord(index, _size);
				_matrix.add(index, new Cell(coord, input[j]));
			}
		}
	}

	public Coord getMaxCoord() { return _maxCoord; }
	public void setMaxCoord(Coord maxCoord) { this._maxCoord = maxCoord; }

	public int getSize() { return _size; }
	public void setSize(int size) { this._size = size; }

	public ArrayList<Cell> getMatrix() { return _matrix; }
	public void setMatrix(ArrayList<Cell> matrix) { this._matrix = matrix; }

	public Coord indexToNewCoord(int index, int size) {

		int row = index / size;
		int col = index % size;

		return new Coord(row, col);
	}

	@Override
	public String toString() {
		String text = "";

		for (int i = 0; i< _size*_size; i++) {
			if (i != 0 && i % _size == 0)
				text += '\n';

			if (i != 0 && i % _size != 0 && i % 3 == 0)
				text += '|';

			if (i != 0 && i % (3*_size) == 0)
				text += "---+---+---\n";

			text += _matrix.get(i);
		}

		return text;
	}
}
