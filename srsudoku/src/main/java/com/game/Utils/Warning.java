package com.game.Utils;

import com.game.Sudoku.Cell;

public class Warning {

	public static void overwriteFilledCell(Cell cell) {
		System.out.println("[Warning] Re-write the filled cell " + cell);
	}

	public static void deleteEmptyCell(Cell cell) {
		System.out.println("[Warning] Deleted an empty cell " + cell);
	}
}
