package com.game.Utils;

import com.game.Sudoku.Cell;

public class Warning {

	public static String warning() { return "[Warning] "; }

	public static String duplicateFile(String file) {
		String warn = warning() + "File '" + file + "' already exists";
		System.out.println(warn);
		return warn;
	}

	public static String invalidYNOption() {
		String warn = warning() + "Invalid y/n option. Type one of them";
		System.out.println(warn);
		return warn;
	}

	public static String overwriteHintCell(Cell cell) {
		String warn = warning() + "Couldn't re-write hint cell " + cell;
		System.out.println(warn);
		return warn;
	}

	public static String deleteHintCell(Cell cell) {
		String warn = warning() + "Couldn't delete hint cell " + cell;
		System.out.println(warn);
		return warn;
	}

	public static String overwriteFilledCell(Cell cell) {
		String warn = warning() + "Re-write the filled cell " + cell;
		System.out.println(warn);
		return warn;
	}

	public static String deleteEmptyCell(Cell cell) {
		String warn = warning() + "Deleted an empty cell " + cell;
		System.out.println(warn);
		return warn;
	}
}
