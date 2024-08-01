package com.game.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Path {

	private static final String DEFAULT_TXT_PATH = "src/main/resources/txtData/";
	private static final String DEFAULT_OBJ_PATH = "src/main/resources/objData/";
	
	private static final String TXT_EXTENTION = ".txt";
	private static final String OBJ_EXTENTION = ".sst";

	/* -------------------- Construct Txt Path function -------------------- */
	public static String constructTxtPath(String input) {
		if (!input.contains("/"))
			return DEFAULT_TXT_PATH + input + TXT_EXTENTION;

		return input;
	}

	/* -------------------- Construct Obj Path function -------------------- */
	public static String constructObjPath(String input) {
		if (!input.contains("/"))
			return DEFAULT_OBJ_PATH + input + OBJ_EXTENTION;

		return input;
	}

	/* ----------------- Construct Obj Extention function ------------------ */
	public static String constructObjExtention(String input) {

		Pattern pattern = Pattern.compile(Regex.objExtention());
		Matcher matcher = pattern.matcher(input);

		if (matcher.matches())
			return input;

		return input + OBJ_EXTENTION;
	}
}
