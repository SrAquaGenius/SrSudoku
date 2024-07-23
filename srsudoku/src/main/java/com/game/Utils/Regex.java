package com.game.Utils;

public class Regex {
	
	private static final String DEFAULT_TXT_PATH = "src/main/resources/txtData/";
	private static final String DEFAULT_OBJ_PATH = "src/main/resources/objData/";
	
	private static final String TXT_EXTENTION = ".txt";
	private static final String OBJ_EXTENTION = ".sst";

	public static String coordDigit() {
		return "\\((\\d+),(\\d+)\\)\\s(\\d+)";
	}

	public static String coord() {
		return "\\((\\d+),(\\d+)\\)";
	}

	/* -------------------- Construct Txt Path function -------------------- */
	public static String constructTxtPath(String input) {
		if (!input.contains("/"))
			return DEFAULT_TXT_PATH + input + TXT_EXTENTION;

		return input;
	}

	/* -------------------- Construct Obj Path function -------------------- */
	public String constructObjPath(String input) {
		if (!input.contains("/")) {
			return DEFAULT_OBJ_PATH + input + OBJ_EXTENTION;
		}
		return input;
	}
}
