package com.koko.beeware.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.koko.beeware.Constants;
import com.koko.beeware.assets.Bilder;
import com.koko.beeware.game.Game;
import com.koko.beeware.towers.HoneyPuddle;
import com.koko.beeware.world.TileType;

public abstract class Enemy {
	
	private int value;
	private int y;
	private int rarity;
	private int x;
	private int hp;
	private int Health;
	private float speed;
	private int dirX;
	private int dirY;
	private int startingPointY;
	private int startingPointX;
	private int curXTile;
	private int curYTile;
	private int width;
	private int height;
	private int damage;
	private int rot;
	private boolean spawned;
	private BufferedImage texture;
	private int animatSt;
	private int animateDur;
	private int movDur;
	private int movTime;
	private HoneyPuddle slowedDown;
	private EnemyLvl l;
	
	//Bewegt sich zur naechsten Platte
	public void moveToNextTile() {
		if(getMovTime() <= 0) {
			if(dirX > 0) {
				if(getX() < (getCurXTile() + 1)*Constants.TILE_SIZE + (Constants.TILE_SIZE-getWidth())/2) {
					x += 1 * getSpeed() + 1;
				}
				else {
					setCurXTile(getCurXTile() + 1);
					findPath();
				}
			}
			
			else if(dirX < 0) {
				if(getX() > (getCurXTile() - 1)*Constants.TILE_SIZE + (Constants.TILE_SIZE-getWidth())/2) {
					x -= (1 * getSpeed() + 1);
				}
				else {
					setCurXTile(getCurXTile() - 1);
					findPath();
				}
			}
			
			else if(dirY > 0) {
				if(getY() < (getCurYTile() + 1)*Constants.TILE_SIZE + (Constants.TILE_SIZE-getHeight())/2) {
					y += (1 * getSpeed() + 1);				
				}
				else {
					setCurYTile(getCurYTile() + 1);
					findPath();
				}
			}
			
			else if(dirY < 0) {
				if(getY() > (getCurYTile() - 1)*Constants.TILE_SIZE + (Constants.TILE_SIZE-getHeight())/2) {
					y -= (1 * getSpeed() + 1);
				}
				else {
					setCurYTile(getCurYTile() - 1);
					findPath();
				}
			}
			setMovTime(getMovDur());
		}
		else {
			setMovTime(getMovTime() - Constants.TICK_MS);
		}
		
	}
	
	//findet naechste Platte
	public void findPath() {
			if(getDirX() == 1) {
				if(getCurYTile() > 0 && getCurXTile() < Constants.GRID_SIZE && getCurYTile() < Constants.GRID_SIZE) {
				if(Game.grid.map[getCurXTile() + 1][getCurYTile()].getType() == TileType.Dirt || Game.grid.map[getCurXTile() + 1][getCurYTile()].getType() == TileType.Trap) {
					setDirX(1);
					setRot(90);
					setDirY(0);
				}
				else if(Game.grid.map[getCurXTile()][getCurYTile() + 1].getType() == TileType.Dirt || Game.grid.map[getCurXTile()][getCurYTile() + 1].getType() == TileType.Trap) {
					setDirY(1);
					setRot(180);
					setDirX(0);
				}
				else if(Game.grid.map[getCurXTile()][getCurYTile() - 1].getType() == TileType.Dirt || Game.grid.map[getCurXTile()][getCurYTile() - 1].getType() == TileType.Trap) {
					setDirY(-1);
					setRot(0);
					setDirX(0);
				}
				else {
					despawn();
					Game.player.setHP(Game.player.getHP() - 1);
				}
				}
			}
			
			else if(getDirX() == -1) {
				if(getCurXTile() > 0 && getCurYTile() > 0 && getCurYTile() < Constants.GRID_SIZE) {
				if(Game.grid.map[getCurXTile() - 1][getCurYTile()].getType() == TileType.Dirt || Game.grid.map[getCurXTile() - 1][getCurYTile()].getType() == TileType.Trap) {
					setDirX(-1);
					setRot(-90);
					setDirY(0);
				}
				else if(Game.grid.map[getCurXTile()][getCurYTile() + 1].getType() == TileType.Dirt || Game.grid.map[getCurXTile()][getCurYTile() + 1].getType() == TileType.Trap) {
					setDirY(1);
					setRot(180);
					setDirX(0);
				}
				else if(Game.grid.map[getCurXTile()][getCurYTile() - 1].getType() == TileType.Dirt || Game.grid.map[getCurXTile()][getCurYTile() - 1].getType() == TileType.Trap) {
					setDirY(-1);
					setRot(0);
					setDirX(0);
				}
				else {
					despawn();
					Game.player.setHP(Game.player.getHP() - 1);
				}
				}
			}
			
			else if(getDirY() == 1) {
			if(getCurXTile() > 0 && getCurXTile() < Constants.GRID_SIZE && getCurYTile() < Constants.GRID_SIZE) {
				if(Game.grid.map[getCurXTile()][getCurYTile() + 1].getType() == TileType.Dirt || Game.grid.map[getCurXTile()][getCurYTile() + 1].getType() == TileType.Trap) {
					setDirY(1);
					setRot(180);
					setDirX(0);
				}
				else if(Game.grid.map[getCurXTile() + 1][getCurYTile()].getType() == TileType.Dirt || Game.grid.map[getCurXTile() + 1][getCurYTile()].getType() == TileType.Trap) {
					setDirX(1);
					setRot(90);
					setDirY(0);
				}
				else if(Game.grid.map[getCurXTile() - 1][getCurYTile()].getType() == TileType.Dirt || Game.grid.map[getCurXTile() - 1][getCurYTile()].getType() == TileType.Trap) {
					setDirX(-1);
					setRot(-90);
					setDirY(0);
				}
				else {
					despawn();
					Game.player.setHP(Game.player.getHP() - 1);
				}
			}
			}
			
			else if(getDirY() == -1) {
				if(getCurXTile() > 0 && getCurYTile() > 0 && getCurXTile() < Constants.GRID_SIZE) {
				if(Game.grid.map[getCurXTile()][getCurYTile() - 1].getType() == TileType.Dirt || Game.grid.map[getCurXTile()][getCurYTile() - 1].getType() == TileType.Trap) {
					setDirY(-1);
					setRot(0);
					setDirX(0);
				}
				else if(Game.grid.map[getCurXTile() + 1][getCurYTile()].getType() == TileType.Dirt || Game.grid.map[getCurXTile() + 1][getCurYTile()].getType() == TileType.Trap) {
					setDirX(1);
					setRot(90);
					setDirY(0);
				}
				else if(Game.grid.map[getCurXTile() - 1][getCurYTile()].getType() == TileType.Dirt || Game.grid.map[getCurXTile() - 1][getCurYTile()].getType() == TileType.Trap) {
					setDirX(-1);
					setRot(-90);
					setDirY(0);
				}
				else {
					despawn();
					Game.player.setHP(Game.player.getHP() - 1);
				}
				}
			}
	}

	//Setzt Startpunkt fest
	public void setStartingPoint() {
		for(int i = 0; i < 15; i++) {
				if(Game.grid.map[0][i].getType() == TileType.Dirt) {
					setStartingPointX(i * 60  + 30 - getWidth() / 2);
					setX(0*60);
					setY(i*60 + 30 - getHeight()/2);
					setCurXTile(getX()/60);
					setCurYTile(getY()/60);
					
					setDirY(0);
					setDirX(1);
					findPath();
				}
				else if(Game.grid.map[i][14].getType() == TileType.Dirt) {
					setStartingPointX(i * 60  + 30 - getWidth() / 2);
					setX(i*60 + 30 - getWidth()/2);
					setY(14*60);
					setCurXTile(getX()/60);
					setCurYTile(getY()/60);
					
					setDirY(-1);
					setDirX(0);
					findPath();
				}
				else if(Game.grid.map[i][0].getType() == TileType.Dirt) {
					setStartingPointY(i * 60 + 30 - getHeight() / 2);
					setX(i*60 + 30 - getWidth()/2);
					setY(0*60);
					setCurXTile(getX()/60);
					setCurYTile(getY()/60);
					
					setDirY(1);
					setDirX(0);
					findPath();
				}
				else if(Game.grid.map[14][i].getType() == TileType.Dirt) {
					setStartingPointY(i * 60  + 30 - getHeight() / 2);
					setX(14*60);
					setY(i*60 + 30 - getHeight()/2);
					setCurXTile(getX()/60);
					setCurYTile(getY()/60);
					
					setDirY(0);
					setDirX(-1);
					findPath();
				}
		}
	}
	//Animiert
	public void animate(BufferedImage[] image, BufferedImage[] imageR, BufferedImage[] imageRR, int c) {
		if(isSpawned()) {
			if(getAnimateDur() == 0) {
				switch(getAnimatSt()) {
				case 0: switch(getL().textureName) {
							case 1:setTexture(image[getAnimatSt()]);break;
							case 2:setTexture(imageR[getAnimatSt()]);break;
							case 3:setTexture(imageRR[getAnimatSt()]);break;
							default:setTexture(image[getAnimatSt()]);break;
						}
						setAnimatSt(getAnimatSt() + 1);break;
				case 1:	switch(getL().textureName) {
							case 1:setTexture(image[getAnimatSt()]);break;
							case 2:setTexture(imageR[getAnimatSt()]);break;
							case 3:setTexture(imageRR[getAnimatSt()]);break;
							default:setTexture(image[getAnimatSt()]);break;
						}
						setAnimatSt(getAnimatSt() + 1);break;
				case 2:	switch(getL().textureName) {
							case 1:setTexture(image[getAnimatSt()]);break;
							case 2:setTexture(imageR[getAnimatSt()]);break;
							case 3:setTexture(imageRR[getAnimatSt()]);break;
							default:setTexture(image[getAnimatSt()]);break;
						}
						setAnimatSt(0);break;
				default:break;
				}
				setAnimateDur(c);
			}
			else {
				setAnimateDur(getAnimateDur() - 1);
			}
		}
	}
	
	//Zeichnet Objekt
	public void draw(Graphics2D g) {
		if(isSpawned()) {
			Bilder.rotate(getTexture(), getRot(), g, getX(), getY());
			g.setColor(Color.red);
			g.fillRect(getX(), getY(), (getWidth()*getHp())/getHealth(), 3);
			g.setColor(Color.black);
			g.drawRect(getX(), getY(), getWidth(), 3);
		}
	}
	//spawned Objekt
	public void spawn() {
		setStartingPoint();
		setSpawned(true);
		setHp(l.HP);
		setMovDur(l.speed);
		setValue(l.value);
	}
	//despawned Objekt
	public void despawn() {
		setSpawned(false);
		setStartingPoint();
		setX(-100);
		setY(-100);
		setRot(180);
		setHp(getHealth());
		Game.player.setHoneycomb(Game.player.getHoneycomb() + getValue());
	}
	//upgraded
	void upgrade(EnemyLvl lvl) {
		setMovDur(lvl.speed);
		setValue(lvl.value);
		setHealth(lvl.HP);
		setL(lvl);
	}
	//Passt Lvl der Welle an
	public void adaptLvl(EnemyLvl a1, EnemyLvl a2, EnemyLvl a3, int u1, int u2, int u3) {
		if(isSpawned() == false) {
			if(Game.wHandler.getWave() >= u3) {
				 upgrade(a3);
			}			
			else if(Game.wHandler.getWave() >= u2) {
				upgrade(a2);
			}
			else if(Game.wHandler.getWave() >= 1) {
				upgrade(a1);
			}
		}
 	}
	
	//Getter & Setter 
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public float getSpeed() {
		return speed;
	}
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	public int getDirX() {
		return dirX;
	}
	public void setDirX(int dirX) {
		this.dirX = dirX;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getCurXTile() {
		return curXTile;
	}

	public void setCurXTile(int curXTile) {
		this.curXTile = curXTile;
	}

	public int getCurYTile() {
		return curYTile;
	}

	public void setCurYTile(int curYTile) {
		this.curYTile = curYTile;
	}

	public int getDirY() {
		return dirY;
	}

	public void setDirY(int dirY) {
		this.dirY = dirY;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isSpawned() {
		return spawned;
	}

	public void setSpawned(boolean spawned) {
		this.spawned = spawned;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getHealth() {
		return Health;
	}

	public void setHealth(int health) {
		Health = health;
	}

	public int getRarity() {
		return rarity;
	}

	public void setRarity(int rarity) {
		this.rarity = rarity;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}

	public BufferedImage getTexture() {
		return texture;
	}

	public int getRot() {
		return rot;
	}

	public void setRot(int rot) {
		this.rot = rot;
	}

	public int getAnimatSt() {
		return animatSt;
	}

	public void setAnimatSt(int animatSt) {
		this.animatSt = animatSt;
	}

	public int getAnimateDur() {
		return animateDur;
	}

	public void setAnimateDur(int animateDur) {
		this.animateDur = animateDur;
	}

	public int getMovDur() {
		return movDur;
	}

	public void setMovDur(int movDur) {
		this.movDur = movDur;
	}

	public int getMovTime() {
		return movTime;
	}

	public void setMovTime(int movTime) {
		this.movTime = movTime;
	}

	public EnemyLvl getL() {
		return l;
	}

	public void setL(EnemyLvl l) {
		this.l = l;
	}

	public HoneyPuddle getSlowedDown() {
		return slowedDown;
	}

	public void setSlowedDown(HoneyPuddle slowedDown) {
		this.slowedDown = slowedDown;
	}

	public int getStartingPointY() {
		return startingPointY;
	}

	public void setStartingPointY(int startingPointY) {
		this.startingPointY = startingPointY;
	}

	public int getStartingPointX() {
		return startingPointX;
	}

	public void setStartingPointX(int startingPointX) {
		this.startingPointX = startingPointX;
	}

	
}
