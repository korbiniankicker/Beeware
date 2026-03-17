package com.koko.beeware.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;

import com.koko.beeware.Constants;
import com.koko.beeware.assets.Bilder;
import com.koko.beeware.game.Game;
import com.koko.beeware.game.ButtonHandler;

@SuppressWarnings("serial")
public class Label extends JLabel {
	
	protected void paintComponent(Graphics g1) {	
		Graphics2D g = (Graphics2D) g1;
		super.paintComponent(g);

		
	switch(Game.mainMenu.gamemode) {
	
//In-Game
	case 1: 
		//Generelles
				ButtonHandler.drawButtons(g);
		
		//Map
		Game.grid.draw(g);
		for(int i = 0; i < Game.obsticles.size(); i++) {
			if(Game.obsticles.get(i).isSpawned())
				Game.obsticles.get(i).draw(g);
			}
		
		//Honigpfuetze
		for(int i = 0; i < Game.thrower.size(); i++) {
			Game.puddle.get(i).draw(g);
		}
		//gegner
				for(int i = 0; i < Game.tank.size(); i++) {
					Game.tank.get(i).draw(g);	
				}	
				for(int i = 0; i < Game.wAnt.size(); i++) {
					Game.wAnt.get(i).draw(g);
				}
				for(int i = 0; i < Game.mAnt.size(); i++) {
					Game.mAnt.get(i).draw(g);
				}
				for(int i = 0; i < Game.sAnt.size(); i++) {
					Game.sAnt.get(i).draw(g);
				}
				for(int i = 0; i < Game.drone.size(); i++) {
					Game.drone.get(i).draw(g);
				}
					

		//Anderes	
		Game.player.draw(g);
		
		//Tuerme
		for(int i = 0; i < Game.thrower.size(); i++) {
			Game.thrower.get(i).draw(g);
		}
		for(int i = 0; i < Game.catapult.size(); i++) {
			Game.catapult.get(i).draw(g);
		}
		for(int i = 0; i < Game.expl.size(); i++) {
			Game.expl.get(i).draw(g);
		}
		for(int i = 0; i < Game.bomb.size(); i++) {
			Game.bomb.get(i).draw(g);
		}
		for(int i = 0; i < Game.bomb.size(); i++) {
			Game.spike.get(i).draw(g);
		}
		for(int i = 0; i < Game.projectiles.size(); i++) {
			Game.projectiles.get(i).draw(g);
		}
		
		
		//Bees
		for(int i = 0; i < Game.player.getHP(); i++) {
			Game.bee.get(i).draw(g);
		}
		
		//stats
		Game.player.drawStats(g);
		
		//Buttons
		if(Game.player.isMenu()) {
			g.drawImage(Bilder.menuBig, 780, 20, 110, 500, null);
		}
		
		//Menu der Tuerme
		//Generelles
		ButtonHandler.drawButtons(g);
				for(int i = 0; i < Game.thrower.size(); i++) {
					if(Game.thrower.get(i).isChosen())
					Game.thrower.get(i).drawMenu(g);
				}
				for(int i = 0; i < Game.catapult.size(); i++) {
					if(Game.catapult.get(i).isChosen())
						Game.catapult.get(i).drawMenu(g);
				}
				for(int i = 0; i < Game.bomb.size(); i++) {
					if(Game.bomb.get(i).isChosen())
						Game.bomb.get(i).drawMenu(g);
				}
				for(int i = 0; i < Game.bomb.size(); i++) {
					if(Game.spike.get(i).isChosen())
						Game.spike.get(i).drawMenu(g);;
				}
		
		break;
//Hauptmenue
	case 0:
			Game.mainMenu.draw(g);
			//Generelles
			ButtonHandler.drawButtons(g);
		break;
	
//Game Over
	case 2:
			g.setColor(new Color(30, 30, 30, 255));
			g.fillRect(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
			g.drawImage(Bilder.gameOver, 0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT, null);
			g.setColor(Color.black);
			g.setFont(new Font("Noteworthy",Font.BOLD, 100));
			g.drawString("Game Over", 210, 390);
			//Generelles
			ButtonHandler.drawButtons(g);
		break;
	}

	}
}
