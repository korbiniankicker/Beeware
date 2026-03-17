package com.koko.beeware.game;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JButton;

import com.koko.beeware.Constants;
import com.koko.beeware.assets.Bilder;


public class ButtonHandler {

	public static boolean pressed[] = new boolean[Constants.UI_BUTTON_SLOTS];
	
	//Handled das erscheinen der Buttons
	public void handleButtons() {
		switch(Game.mainMenu.getGamemode()) {
		case 0:
			setInGameButtons(false);
			setMainMenuButtons(true);
			break;
		
		case 1:
			setInGameButtons(true);
			setMainMenuButtons(false);
			if(Game.player.isMenu()) {
				setSelectButtons(true);
			}else {
				setSelectButtons(false);
			}
			break;
			
		case 2:
			Game.startWave.setVisible(false);
			Game.menu.setVisible(false);
			setMainMenuButtons(false);
			Game.backToMainMenu.setBounds(Constants.WINDOW_WIDTH / 2 - 50, 430, 100, 60);
			Game.backToMainMenu.setVisible(true);
			break;
		}
 	}
	
	private void setInGameButtons(boolean t) {
		Game.startWave.setVisible(t);
		Game.menu.setVisible(t);
		Game.backToMainMenu.setVisible(t);
	}
	private void setSelectButtons(boolean t) {
		Game.selectBom.setVisible(t);
		Game.selectCat.setVisible(t);
		Game.selectSpi.setVisible(t);
		Game.selectThr.setVisible(t);
	}
	@SuppressWarnings("unused")
	private void setTowerButtons(boolean t) {
		Game.upgrade.setVisible(t);
		Game.sell.setVisible(t);
	}
	private void setMainMenuButtons(boolean t) {
		Game.mainMenu.start.setVisible(t);
	}
	
	//Handled das Druecken der Buttons
	public static void drawButtons(Graphics2D g) {
		checkButton(Game.menu, Bilder.TowersSelected, Bilder.Towers, 0, g);
		checkButton(Game.sell, Bilder.sellButtonSelected, Bilder.sellButton, 1, g);
		checkButton(Game.upgrade, Bilder.upgradeButtonSelected, Bilder.upgradeButton, 2, g);
		checkButton(Game.selectCat, Bilder.combslingButtonSelected, Bilder.combslingButton, 3, g);
		checkButton(Game.selectBom , Bilder.honeybombButtonSelected, Bilder.honeybombButton, 4, g);
		checkButton(Game.selectSpi, Bilder.spikesButtonSelected, Bilder.spikesButton, 5, g);
		checkButton(Game.selectThr, Bilder.honeyThrowerButtonSelected, Bilder.honeyThrowerButton, 6, g);
		checkButton(Game.startWave, Bilder.startWaveSelected, Bilder.startWave, 7, g);
		checkButton(Game.mainMenu.start, Bilder.startSelected, Bilder.start, 8, g);
		checkButton(Game.backToMainMenu, Bilder.backToMainMenuSelected, Bilder.backToMainMenu, 9, g);
	}
	public static void checkButton(JButton b, BufferedImage i1, BufferedImage i2, int number, Graphics2D g) {
		if(b.isVisible() == true) {
			if(pressed[number] == true) {
				g.drawImage(i1, b.getX(), b.getY(), b.getWidth(), b.getHeight(), null);
			}
			else {
				g.drawImage(i2, b.getX(), b.getY(), b.getWidth(), b.getHeight(), null);
			}
		}
	}
}
