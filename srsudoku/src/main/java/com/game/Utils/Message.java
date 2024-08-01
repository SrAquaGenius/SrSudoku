package com.game.Utils;

public class Message {

	public static String nextAction(boolean hasSudoku) {
		if (hasSudoku)
			return Message.nextDefaultAction();
		
		return Message.nextInitAction();
	}

	public static String nextDefaultAction() {
		String output = "[Next action]\n" +
						"  0 - exit\n" +
						"  1 - new sudoku\n" +
						"  2 - load sudoku\n" +
						"  3 - save sudoku\n" +
						"  4 - manual move\n" +
						"  5 - auto move\n" +
						": ";
		System.out.print(output);
		return output;
	}

	public static String nextInitAction() {
		String output = "[Next action]\n" +
						"  0 - exit\n" +
						"  1 - new sudoku\n" +
						"  2 - load sudoku\n" +
						": ";
		System.out.print(output);
		return output;
	}
	
	public static String manualAction() {
		String output = "[Manual action]\n" +
						"  0 - back\n" +
						"  1 - add pen digit\n" +
						"  2 - delete pen digit\n" +
						"  3 - print board\n" +
						": ";
		System.out.print(output);
		return output;
	}
	
	public static String autoAction() {
		String output = "[Auto action]\n" +
						"  0 - back\n" +
						"  1 - do next iteration\n" +
						"  2 - do all possible generation\n" +
						": ";
		System.out.print(output);
		return output;
	}
	
	public static String addPenDigitGuide() {
		String output = "[Add pen digit]\n" +
						"  type <coord> <digit>, eg.: (1,3) 4\n" +
						"  0 - cancel\n" +
						": ";
		System.out.print(output);
		return output;
	}
	
	public static String delPenDigitGuide() {
		String output = "[Delete pen digit]\n" +
						"  type <coord>, eg.: (1,3)\n" +
						"  0 - cancel\n" +
						": ";
		System.out.print(output);
		return output;
	}
	
	public static String newSudoku() {
		String output = "[New Sudoku]\n" +
						"  type filename or full path from root\n" +
						"  0 - cancel\n" +
						": ";
		System.out.print(output);
		return output;
	}

	public static String saveSudoku(String name) {
		String output = "[Save Sudoku]\n" +
						"  type filename or full path from root\n" +
						"  0 - save with name '" + name + "' at default root\n" +
						": ";
		System.out.print(output);
		return output;
	}

	public static String savedObject() {
		String output = "[Saved object] Serialized object saved";
		System.out.println(output);
		return output;
	}
}
