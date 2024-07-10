package com.game;

public class Regex {

	public static String coordDigit() {
		return "\\((\\d+),(\\d+)\\)\\s(\\d+)";
	}

	public static String coord() {
		return "\\((\\d+),(\\d+)\\)";
	}
}
