package com.game;

import com.game.Sudoku.SudokuState;

public class Message {

	public static void initialBoard(SudokuState ss) {
		System.out.print("[Initial board] ");
		System.out.println(ss);
	}

	public static void board(SudokuState ss) {
		System.out.print("[Board] ");
		System.out.println(ss);
	}

	public static void nextAction() {
		System.out.print("[Next action]\n  0 - exit\n  1 - manual move\n  2 - auto move\n  3 - print actual board\nChoose one: ");
	}

	public static void invalidOption() {
		System.out.print("[Invalid] Type a valid option!\n");
	}

	public static void invalidInteger() {
		System.out.print("[Invalid] Type a valid integer!\n");
	}

	public static void manualAction() {
		System.out.print("[Manual action]\n  0 - back\n  1 - Add pen digit\n  2 - remove pen digit\nChoose one: ");
	}

	public static void autoAction() {
		System.out.print("[Auto action]\n  0 - back\n  1 - do next iteration\n  2 - do all possible generation\nChoose one: ");
	}
}
