package com.game;

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
}
