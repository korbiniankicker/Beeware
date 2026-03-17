package com.koko.beeware.ui;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JButton;

import com.koko.beeware.Constants;
import com.koko.beeware.assets.Bilder;
import com.koko.beeware.game.ButtonHandler;

public class MainMenu {

	public JButton start = new JButton();
	public int gamemode;
	private BufferedImage backgroundTexture;
	
	public MainMenu() {
		backgroundTexture = Bilder.icon;
		gamemode = 0;
		start.setBounds(300, 400, 300, 50);
	}
	
	public void draw(Graphics2D g) {
		g.drawImage(backgroundTexture, 0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT, null);
		ButtonHandler.drawButtons(g);
	}

	//Getter und Setter
	public int getGamemode() {
		return gamemode;
	}

	public void setGamemode(int started) {
		this.gamemode = started;
	}

	public BufferedImage getBackgroundTexture() {
		return backgroundTexture;
	}

	public void setBackgroundTexture(BufferedImage backgroundTexture) {
		this.backgroundTexture = backgroundTexture;
	}
	
	
	
}
