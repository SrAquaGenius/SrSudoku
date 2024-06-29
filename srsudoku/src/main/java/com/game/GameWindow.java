package com.game;

import javax.swing.JFrame;

public class GameWindow {

	private JFrame jframe;

	public GameWindow(GamePanel gamePanel) {
		jframe = new JFrame();

		jframe.setSize(1000, 1000);
		jframe.setTitle("SrSudoku");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.add(gamePanel);
		jframe.setVisible(true);
	}
}
