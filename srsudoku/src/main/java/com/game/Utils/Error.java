package com.game.Utils;

import com.game.Sudoku.Cell;
import com.game.Sudoku.Coord;

public class Error {

	public static String error() { return "[Error] "; }

	/* --------------------------------------------------------------------- */
	public static void invalidOption() {
		System.out.println(error() + "Type a valid option!");
	}

	public static void invalidInteger() {
		System.out.println(error() + "Type a valid integer!");
	}

	public static void invalidPattern() {
		System.out.println(error() + "Type a valid pattern!");
	}

	public static void invalidCoord(Coord coord, int size) {
		System.out.println(error() + "Coordinate " + coord + //
						 " is invalid for size " + size);
	}

	public static void overwriteHintCell(Cell cell) {
		System.out.println(error() + "Couldn't re-write hint cell " + cell);
	}

	public static void deleteHintCell(Cell cell) {
		System.out.println(error() + "Couldn't delete hint cell " + cell);
	}

	/* ------------------------ File related errors ------------------------ */
	public static void fileInteraction(String path) {
		System.out.println(error() + "IO Exception on file with path: " + path);
	}

	public static void emptyFile(String path) {
		System.out.println(error() + "Empty file with path: " + path);
	}

	public static void invalidFirstLine() {
		System.out.println(error() + "First line must be an integer " +
							"representing the number of rows in the sudoku.");
	}

	public static void notEnoughLines() {
		System.out.println(error() + "File contains fewer lines than expected");
	}

	public static void tooManyLines() {
		System.out.println(error() + "File contains more lines than expected");
	}

	public static void fileNotFound(String fileName) {
		System.out.println("File not found: " + fileName);
	}

	public static String onSaveObject() {
		String error = error() + "Serialized object was not properly saved";
		System.out.println(error);
		return error;
	}
}
