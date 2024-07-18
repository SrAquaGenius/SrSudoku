package com.game;

import java.io.File;

import com.game.Game.Game;
import com.game.Utils.Debug;
import com.game.Utils.Error;

public class App {
	public static void main(String[] args) {
		Debug.place();

		String path = System.getProperty("filePath");
        Debug.print("File path: " + path);
		
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
}
