package com.game;

import java.io.File;

import com.game.Game.Game;

public class App {
	public static void main(String[] args) {

		String path = System.getProperty("filePath");
        debug("File path: " + path);
		
		try {
			// Construct the absolute file path based on the project root
            File file = new File(path);
            if (!file.exists()) {
                throw new IllegalArgumentException(Error.fileNotFound(path));
            }

            new Game(file.getAbsolutePath());
		} catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
		}
	}

	public static void debug(String msg) {
		if (Boolean.parseBoolean(System.getProperty("debug"))) {
			System.out.println("[Debug] " + msg);
		}
	}
}
