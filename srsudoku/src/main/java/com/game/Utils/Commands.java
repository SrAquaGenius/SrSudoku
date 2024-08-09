package com.game.Utils;

public class Commands {

	/* ----------------------- Clear Screen function ----------------------- */
	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	/* ------------------- Is Exit Command test function ------------------- */
	public static boolean isExitCommand(String input) {
		try {
			return Integer.parseInt(input) == 0;
		}
		catch (NumberFormatException e) {
			return false; // Not an integer, thus not an exit command
		}
	}

	/* ------------------ Is Enter Command test function ------------------- */
	public static boolean isEnterCommand(String input) {
		return input.length() == 0;
	}
}
