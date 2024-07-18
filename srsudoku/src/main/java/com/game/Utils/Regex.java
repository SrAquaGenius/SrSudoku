package com.game.Utils;

public class Regex {

	public static String coordDigit() {
		return "\\((\\d+),(\\d+)\\)\\s(\\d+)";
	}

	public static String coord() {
		return "\\((\\d+),(\\d+)\\)";
	}
}
