package com.game;

public class Cell {

	public boolean showCoord = false;

	private Coord _coord;
	private String _content;
	private Object _options;

	public Cell(Coord coord, String content) {
		this._coord = coord;
		this._content = content;
	}

	public Coord getCoord() { return _coord; }
	public void setCoord(Coord coord) { this._coord = coord; }

	public String getContent() { return _content; }
	public void setContent(String content) { this._content = content; }

	public Object getOptions() { return _options; }
	public void setOptions(Object options) { this._options = options; }

	@Override
	public String toString() {
		if (showCoord)
			return getCoord() + ": " + getContent();

		return getContent();
	}
}
