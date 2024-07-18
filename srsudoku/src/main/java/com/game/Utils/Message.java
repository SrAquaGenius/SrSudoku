package com.game.Utils;

import com.game.Sudoku.SudokuState;

public class Message {

	public static void initialBoard(SudokuState ss) {
		System.out.print("[Initial board] ");
		System.out.print(ss);
	}

	public static void board(SudokuState ss) {
		System.out.print("[Board] ");
		System.out.print(ss);
	}

	public static void nextAction() {
		System.out.print("[Next action]\n  0 - exit\n" + //
						 "  1 - manual move\n  2 - auto move\n: ");
	}

	public static void manualAction() {
		System.out.print("[Manual action]\n  0 - back\n" + //
						 "  1 - add pen digit\n  2 - delete pen digit\n" + //
						 "  3 - print board\n: ");
	}

	public static void autoAction() {
		System.out.print("[Auto action]\n  0 - back\n" + //
						 "  1 - do next iteration\n" + //
						 "  2 - do all possible generation\n: ");
	}

	public static void addPenDigitGuide() {
		System.out.print("[Add pen digit]\n  type <coord> <digit>," + //
						 " eg.: (1,3) 4\n  0 - cancel\n: ");
	}

	public static void delPenDigitGuide() {
		System.out.print("[Delete pen digit]\n  type <coord>," + //
						 " eg.: (1,3)\n  0 - cancel\n: ");
	}
}
