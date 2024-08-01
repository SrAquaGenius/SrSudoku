package com.game;

import java.util.Scanner;

import com.game.Utils.Debug;

public class App {
	public static void main(String[] args) {
		Debug.place();
		
		try {
			Game game = new Game();
			game.gameLoop(new Scanner(System.in));
		} catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
		}
	}
}
