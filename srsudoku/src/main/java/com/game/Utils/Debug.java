package com.game.Utils;

public class Debug {

	public static void place() {

		if (!Boolean.parseBoolean(System.getProperty("debug")))
			return;

		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		
		// The first element is getStackTrace, second is place, third is the caller
		StackTraceElement caller = stackTrace[2];
		
		System.out.println("[" + caller.getClassName() +
							":" + caller.getMethodName() +
							":" + caller.getLineNumber() + "]");
	}

	public static void print(String msg) {
		if (!Boolean.parseBoolean(System.getProperty("debug")))
			return;

		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StackTraceElement caller = stackTrace[2];
			
		System.out.println("[" + caller.getClassName() +
							":" + caller.getMethodName() +
							":" + caller.getLineNumber() + "] " + msg);
	}

	public static void show(Object obj) {
		if (!Boolean.parseBoolean(System.getProperty("debug")))
			return;

		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StackTraceElement caller = stackTrace[2];
		
		System.out.println("[" + caller.getClassName() +
							":" + caller.getMethodName() +
							":" + caller.getLineNumber() + "]" + obj);
	}

	public static void todo() {
		if (!Boolean.parseBoolean(System.getProperty("debug")))
			return;

		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StackTraceElement caller = stackTrace[2];
		
		System.out.println("[" + caller.getClassName() +
							":" + caller.getMethodName() +
							":" + caller.getLineNumber() + "]\n  TODO");
	}

	public static void todoMsg(String msg) {
		if (!Boolean.parseBoolean(System.getProperty("debug")))
			return;

		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StackTraceElement caller = stackTrace[2];
		
		System.out.println("[" + caller.getClassName() +
							":" + caller.getMethodName() +
							":" + caller.getLineNumber() + "]\n  TODO: " + msg);
	}
}
