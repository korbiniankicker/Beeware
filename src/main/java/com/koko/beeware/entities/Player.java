package com.koko.beeware.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.koko.beeware.assets.Bilder;
import com.koko.beeware.game.Game;
import com.koko.beeware.game.WaveHandler;
import com.koko.beeware.input.MouseMotionHandler;
import com.koko.beeware.towers.HoneyExplosion;
import com.koko.beeware.towers.HoneyPuddle;
import com.koko.beeware.towers.Catapult;
import com.koko.beeware.towers.Combspike;
import com.koko.beeware.towers.HoneyPuddleThrower;
import com.koko.beeware.towers.Honeybomb;
import com.koko.beeware.world.Obsticle;
import com.koko.beeware.world.TileGrid;
import com.koko.beeware.world.TileType;


public class Player {

	private int HP;
	private int honeycomb;
	private int selected;
	private int maxTowers;
	private int towers;
	private boolean menu;
	
	public Player() {
		setHP(10); 
		setHoneycomb(50);
		setSelected(0);
		setMaxTowers(10);
		setTowers(0);
		setMenu(false);
		
	}
	
	public void draw(Graphics2D g) {
		//Zeichnet vorschau vom turm
				g.setColor(new Color(100, 100, 100, 100));
				switch(Game.player.getSelected()) {
				case 1:	if(Game.grid.map[MouseMotionHandler.curXTile][MouseMotionHandler.curYTile].getType().buildable == false)
						g.setColor(new Color(230, 20, 0, 100));
						g.fillRect(MouseMotionHandler.curXTile*60, MouseMotionHandler.curYTile*60, 60, 60);
						g.fillRect(MouseMotionHandler.curXTile*60 - Game.catapult[0].getRange(), MouseMotionHandler.curYTile*60 - Game.catapult[0].getRange(), 2*Game.catapult[0].getRange() + 60, 2*Game.catapult[0].getRange() + 60);break;
				case 2: if(Game.grid.map[MouseMotionHandler.curXTile][MouseMotionHandler.curYTile].getType().buildable == false)
						g.setColor(new Color(230, 20, 0, 100));
						g.fillRect(MouseMotionHandler.curXTile*60, MouseMotionHandler.curYTile*60, 60, 60);
						g.fillRect(MouseMotionHandler.curXTile*60 - Game.bomb[0].getRange(), MouseMotionHandler.curYTile*60 - Game.bomb[0].getRange(), 2*Game.bomb[0].getRange() + 60, 2*Game.bomb[0].getRange() + 60);break;
				case 3: if(Game.grid.map[MouseMotionHandler.curXTile][MouseMotionHandler.curYTile].getType() != TileType.Dirt)
						g.setColor(new Color(230, 20, 0, 100));
						g.fillRect(MouseMotionHandler.curXTile*60, MouseMotionHandler.curYTile*60, 60, 60);break;
				case 4: if(Game.grid.map[MouseMotionHandler.curXTile][MouseMotionHandler.curYTile].getType().buildable == false)
						g.setColor(new Color(230, 20, 0, 100));
						g.fillRect(MouseMotionHandler.curXTile*60, MouseMotionHandler.curYTile*60, 60, 60);
						g.fillRect(MouseMotionHandler.curXTile*60 - Game.thrower[0].getRange(), MouseMotionHandler.curYTile*60 - Game.thrower[0].getRange(), 2*Game.thrower[0].getRange() + 60, 2*Game.thrower[0].getRange() + 60);break;		
				default:break;
				}
		
	}
	public void drawStats(Graphics2D g) {
		//stats
		g.setFont(new Font("Noteworthy",Font.BOLD,15));
		g.setColor(Color.black);
		int width = (int)((Game.wHandler.getWaveLength() / 5000.0) * 100.0);
		g.fillRect(10, 10, width, 20);
		g.setColor(Color.darkGray);
		g.drawRect(10, 10, 100, 20);
		g.setColor(Color.yellow);
		g.drawImage(Bilder.bee[0], 10, 80, 30, 30, null);
		g.drawString(String.valueOf(Game.player.getHP()), 33, 100);
		g.setColor(Color.orange);
		g.drawImage(Bilder.comb, 10, 135, 15, 15, null);
		g.drawString(String.valueOf(Game.player.getHoneycomb()), 30, 150);
		g.setColor(Color.red);
		g.setFont(new Font("Noteworthy",Font.BOLD,12));
		g.drawString("Wave: " + String.valueOf(Game.wHandler.getWave()), 35, 26);
		g.setFont(new Font("Noteworthy",Font.BOLD,15));
		g.setColor(new Color(127, 69, 0, 255));
		g.drawImage(Bilder.combslingStill, 10, 180, 30, 30, null);
		g.drawString(String.valueOf(Game.player.getTowers()) + " / " + String.valueOf(Game.player.getMaxTowers()), 43, 200);
			
	}
	public void reset() {
		for(int i = 0; i < 100; i++) {
			Game.spike[i] = new Combspike();
			Game.bomb[i] = new Honeybomb();
			Game.catapult[i] = new Catapult();
			Game.drone[i] = new Drone();
			Game.sAnt[i] = new SoliderAnt();
			Game.mAnt[i] = new MajorAnt();
			Game.wAnt[i] = new WorkerAnt();
			Game.tank[i] = new StickTank();
			Game.expl[i] = new HoneyExplosion();
			Game.projectiles[i] = new Projectile();
			Game.puddle[i] = new HoneyPuddle();
			Game.thrower[i] = new HoneyPuddleThrower();
			Game.obsticles[i] = new Obsticle();
			Game.grid = new TileGrid(TileGrid.mapInt);
			Game.expl[i] = new HoneyExplosion();
			Game.projectiles[i] = new Projectile();
			Game.puddle[i] = new HoneyPuddle();
		}
		for(int i = 0; i < 10; i++) {
			Game.bee[i] = new Bee(3*60, 12*60, 15*60, 12*60);
		}
		Game.wHandler = new WaveHandler();
		Game.player = new Player();
	}
	
	//Getter & Setter
	public int getHP() {
		return HP;
	}

	public void setHP(int hP) {
		HP = hP;
	}

	public int getHoneycomb() {
		return honeycomb;
	}

	public void setHoneycomb(int honeycomb) {
		this.honeycomb = honeycomb;
	}

	public int getSelected() {
		return selected;
	}

	public void setSelected(int selected) {
		this.selected = selected;
	}

	public int getMaxTowers() {
		return maxTowers;
	}

	public void setMaxTowers(int maxTowers) {
		this.maxTowers = maxTowers;
	}

	public int getTowers() {
		return towers;
	}

	public void setTowers(int towers) {
		this.towers = towers;
	}

	public boolean isMenu() {
		return menu;
	}

	public void setMenu(boolean menu) {
		this.menu = menu;
	}
	
	
}
