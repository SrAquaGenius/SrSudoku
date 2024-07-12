package com.game;

import com.game.Sudoku.Cell;
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
		System.out.println("[Error] Type a valid option!");
	}

	public static void invalidInteger() {
		System.out.println("[Error] Type a valid integer!");
	}

	public static void invalidPattern() {
		System.out.println("[Error] Type a valid pattern!");
	}

	public static void invalidCoord(Coord coord, int size) {
		System.out.println("[Error] Coordinate " + coord + //
						 " is invalid for size " + size);
	}

	public static void overwriteHintCell(Cell cell) {
		System.out.println("[Error] Couldn't re-write hint cell " + cell);
	}

	public static void deleteHintCell(Cell cell) {
		System.out.println("[Error] Couldn't delete hint cell " + cell);
	}
}
