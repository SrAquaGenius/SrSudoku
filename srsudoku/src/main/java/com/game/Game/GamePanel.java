package com.game.Game;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

	public GamePanel() {}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		this.setBackground(Color.LIGHT_GRAY);
		g.drawRect(100, 100, 200, 200);
	}
}
