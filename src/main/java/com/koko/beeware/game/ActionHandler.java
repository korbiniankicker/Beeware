package com.koko.beeware.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.koko.beeware.Constants;
import com.koko.beeware.ui.Gui;

public class ActionHandler implements ActionListener{
	
	@Override
	public void actionPerformed(ActionEvent e ) {
		
		//Start Game button
		if(e.getSource() == Game.mainMenu.start) {
			Game.mainMenu.setGamemode(1);
		}
		
		//Sell button
		if(e.getSource() == Game.sell) {
			for(int i = 0; i < Game.bomb.size(); i++) {
				if(Game.bomb.get(i).isChosen()) {
					Game.bomb.get(i).destroy();
				}
				if(Game.catapult.get(i).isChosen()) {
					Game.catapult.get(i).destroy();
				}
				if(Game.spike.get(i).isChosen()) {
					Game.spike.get(i).destroy();
				}
				if(Game.thrower.get(i).isChosen()) {
					Game.thrower.get(i).destroy();
				}
			}	
			Game.sell.setVisible(false);
			Gui.f.requestFocus();
		}
		//Upgrade button
		if(e.getSource() == Game.upgrade) {
			for(int i = 0; i < Game.bomb.size(); i++) {
				if(Game.bomb.get(i).isChosen()) {
					Game.bomb.get(i).upgrade();
				}
				if(Game.catapult.get(i).isChosen()) {
					Game.catapult.get(i).upgrade();
				}
				if(Game.spike.get(i).isChosen()) {
					Game.spike.get(i).upgrade();
				}
				if(Game.thrower.get(i).isChosen()) {
					Game.thrower.get(i).upgrade();
				}
			}	
			Gui.f.requestFocus();
		}
		//Zurueck zum Hauptmenue button
		if(e.getSource() == Game.backToMainMenu) {
			if(Game.mainMenu.gamemode == 2) {
				Game.player.reset();
				Game.backToMainMenu.setBounds(Constants.WINDOW_WIDTH - 210, 20, 60, 40);
			}
			Game.mainMenu.setGamemode(0);
		}
		//Menu button
		if(e.getSource() == Game.menu) {
			if(Game.player.isMenu()) {
				Game.player.setSelected(0);
				Game.player.setMenu(false);
				Game.selectCat.setVisible(false);
				Game.selectBom.setVisible(false);
				Game.selectSpi.setVisible(false);
				Game.selectThr.setVisible(false);
			}
			else {
				Game.player.setMenu(true);
				Game.selectCat.setVisible(true);
				Game.selectBom.setVisible(true);
				Game.selectSpi.setVisible(true);
				Game.selectThr.setVisible(true);
			}
			Gui.f.requestFocus();
		}
		//Buttons im Menu
		if(e.getSource() == Game.selectBom) {
			Game.player.setSelected(2);
			Gui.f.requestFocus();
		}
		if(e.getSource() == Game.selectCat) {
			Game.player.setSelected(1);
			Gui.f.requestFocus();
		}
		if(e.getSource() == Game.selectSpi) {
			Game.player.setSelected(3);
			Gui.f.requestFocus();
		}
		if(e.getSource() == Game.selectThr) {
			Game.player.setSelected(4);
			Gui.f.requestFocus();
		}
		
		//Start wave
		if(e.getSource() == Game.startWave) {
			Game.wHandler.startWave();
		}
		Gui.f.requestFocus();
	}

}
