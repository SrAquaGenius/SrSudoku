package com.game;

import java.util.ArrayList;

public class Board {

	private int _size;
	private Coord _maxCoord;
	private ArrayList<Cell> _matrix;

	public Board(int size) {
		this._size = size;
		_maxCoord = new Coord(this._size-1, this._size-1);
		_matrix = new ArrayList<Cell>(_size*_size);
	}

	public void initCells() {

		String textInput = "92..45..." + "..8.7.249" + ".4....6.1"
					+ ".6951.3.2" + "281.3...5" + "7.......6"
					+ ".7..59..." + "69.3....." + "51376.8.4";

		String[] input = textInput.split("");
	
		for (int i = 0; i < _size*_size; i++) {
			_matrix.add(i, new Cell(indexToNewCoord(i, _size), input[i]));
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
