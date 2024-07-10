package com.game;

import com.game.Sudoku.Coord;

public class Error {

	public static String invalidFirstLine() {
		return "First line must be an integer representing the number of rows.";
	}

	public static String notEnoughLines() {
		return "File contains fewer lines than expected.";
	}

	public static String tooManyLines() {
		return "File contains more lines than expected.";
	}

	public static String fileNotFound(String fileName) {
		return "File not found: " + fileName;
	}

	/* --------------------------------------------------------------------- */
	public static void invalidOption() {
		System.out.print("[Error] Type a valid option!\n");
	}

	public static void invalidInteger() {
		System.out.print("[Error] Type a valid integer!\n");
	}

	public static void invalidPattern() {
		System.out.print("[Error] Type a valid pattern!\n");
	}

	public static void invalidCoord(Coord coord, int size) {
		System.out.print("[Error] Coordinate " + coord + //
						 " is invalid for size " + size);
	}
}
