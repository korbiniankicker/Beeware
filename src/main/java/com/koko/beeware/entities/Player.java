package com.koko.beeware.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.koko.beeware.Constants;
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
						g.fillRect(MouseMotionHandler.curXTile*Constants.TILE_SIZE, MouseMotionHandler.curYTile*Constants.TILE_SIZE, Constants.TILE_SIZE, Constants.TILE_SIZE);
						g.fillRect(MouseMotionHandler.curXTile*Constants.TILE_SIZE - Game.catapult.get(0).getRange(), MouseMotionHandler.curYTile*Constants.TILE_SIZE - Game.catapult.get(0).getRange(), 2*Game.catapult.get(0).getRange() + Constants.TILE_SIZE, 2*Game.catapult.get(0).getRange() + Constants.TILE_SIZE);break;
				case 2: if(Game.grid.map[MouseMotionHandler.curXTile][MouseMotionHandler.curYTile].getType().buildable == false)
						g.setColor(new Color(230, 20, 0, 100));
						g.fillRect(MouseMotionHandler.curXTile*Constants.TILE_SIZE, MouseMotionHandler.curYTile*Constants.TILE_SIZE, Constants.TILE_SIZE, Constants.TILE_SIZE);
						g.fillRect(MouseMotionHandler.curXTile*Constants.TILE_SIZE - Game.bomb.get(0).getRange(), MouseMotionHandler.curYTile*Constants.TILE_SIZE - Game.bomb.get(0).getRange(), 2*Game.bomb.get(0).getRange() + Constants.TILE_SIZE, 2*Game.bomb.get(0).getRange() + Constants.TILE_SIZE);break;
				case 3: if(Game.grid.map[MouseMotionHandler.curXTile][MouseMotionHandler.curYTile].getType() != TileType.Dirt)
						g.setColor(new Color(230, 20, 0, 100));
						g.fillRect(MouseMotionHandler.curXTile*Constants.TILE_SIZE, MouseMotionHandler.curYTile*Constants.TILE_SIZE, Constants.TILE_SIZE, Constants.TILE_SIZE);break;
				case 4: if(Game.grid.map[MouseMotionHandler.curXTile][MouseMotionHandler.curYTile].getType().buildable == false)
						g.setColor(new Color(230, 20, 0, 100));
						g.fillRect(MouseMotionHandler.curXTile*Constants.TILE_SIZE, MouseMotionHandler.curYTile*Constants.TILE_SIZE, Constants.TILE_SIZE, Constants.TILE_SIZE);
						g.fillRect(MouseMotionHandler.curXTile*Constants.TILE_SIZE - Game.thrower.get(0).getRange(), MouseMotionHandler.curYTile*Constants.TILE_SIZE - Game.thrower.get(0).getRange(), 2*Game.thrower.get(0).getRange() + Constants.TILE_SIZE, 2*Game.thrower.get(0).getRange() + Constants.TILE_SIZE);break;		
				default:break;
				}
		
	}
	public void drawStats(Graphics2D g) {
		//stats
		g.setFont(new Font("Noteworthy",Font.BOLD,15));
		g.setColor(Color.black);
		int width = (int)((Game.wHandler.getWaveLength() / (double) Constants.WAVE_LENGTH_TICKS) * Constants.HUD_WAVE_BAR_W);
		if (width < 0) width = 0;
		if (width > Constants.HUD_WAVE_BAR_W) width = Constants.HUD_WAVE_BAR_W;
		g.fillRect(Constants.HUD_WAVE_BAR_X, Constants.HUD_WAVE_BAR_Y, width, Constants.HUD_WAVE_BAR_H);
		g.setColor(Color.darkGray);
		g.drawRect(Constants.HUD_WAVE_BAR_X, Constants.HUD_WAVE_BAR_Y, Constants.HUD_WAVE_BAR_W, Constants.HUD_WAVE_BAR_H);
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
		Game.spike.clear();
		Game.bomb.clear();
		Game.catapult.clear();
		Game.drone.clear();
		Game.sAnt.clear();
		Game.mAnt.clear();
		Game.wAnt.clear();
		Game.tank.clear();
		Game.expl.clear();
		Game.projectiles.clear();
		Game.puddle.clear();
		Game.thrower.clear();
		Game.obsticles.clear();
		for(int i = 0; i < Constants.MAX_ENTITIES; i++) {
			Game.spike.add(new Combspike());
			Game.bomb.add(new Honeybomb());
			Game.catapult.add(new Catapult());
			Game.drone.add(new Drone());
			Game.sAnt.add(new SoliderAnt());
			Game.mAnt.add(new MajorAnt());
			Game.wAnt.add(new WorkerAnt());
			Game.tank.add(new StickTank());
			Game.expl.add(new HoneyExplosion());
			Game.projectiles.add(new Projectile());
			Game.puddle.add(new HoneyPuddle());
			Game.thrower.add(new HoneyPuddleThrower());
			Game.obsticles.add(new Obsticle());
		}
		Game.grid = new TileGrid(TileGrid.mapInt);
		Game.bee.clear();
		for(int i = 0; i < 10; i++) {
			Game.bee.add(new Bee(3*Constants.TILE_SIZE, 12*Constants.TILE_SIZE, 15*Constants.TILE_SIZE, 12*Constants.TILE_SIZE));
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
