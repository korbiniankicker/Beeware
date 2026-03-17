package com.koko.beeware.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import com.koko.beeware.Constants;
import com.koko.beeware.assets.Bilder;
import com.koko.beeware.game.Game;
import com.koko.beeware.towers.HoneyExplosion;
import com.koko.beeware.towers.HoneyPuddle;
import com.koko.beeware.towers.Tower;


public class Projectile {

	private int x, width;
	private int y, height;
	private int damage;
	private boolean spawned;
	private float speed;
	private Enemy target;
	private int targetX;
	private int targetY;
	private Tower source;
	private float dirX;
	private float dirY;
	private boolean explosive;
	BufferedImage texture;
	int explosionRange;
	boolean exploding;
	boolean spawnsPuddle;
	float slowPerc;
	
	public void spawn(Tower t,float speed,int damage, int width, int height, BufferedImage texture, boolean explosive, int explosionRange, boolean spawnsPuddle, float slowPerc) {
		setSpawned(true);
		setExplosive(explosive);
		setExploding(false);
		setExplosionRange(explosionRange);
		setDamage(damage);
		setSpeed(speed);
		setSource(t);
		setX(t.getTileX()*Constants.TILE_SIZE + Constants.TILE_SIZE / 2);
		setY(t.getTileY()*Constants.TILE_SIZE + Constants.TILE_SIZE / 2);	
		setWidth(width);
		setHeight(height);
		setTexture(texture);
		setTarget(findTarget());
		setTargetX(target.getX() + target.getWidth()/2);
		setTargetY(target.getY() + target.getHeight()/2);
		setSpawnsPuddle(spawnsPuddle);
		setSlowPerc(slowPerc);
		calcDir();
		move();
	}
	
	Enemy findTarget() {
		return source.getTarget();
	}
	void move() {
		final Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				
				if(isSpawned()) {
					checkIfOnScreen();
					for(int i = 0; i < Game.wAnt.size(); i++) {
						checkKollisions(Game.wAnt.get(i));
						checkKollisions(Game.mAnt.get(i));
						checkKollisions(Game.sAnt.get(i));
						checkKollisions(Game.drone.get(i));
						checkKollisions(Game.tank.get(i));
					}
					x += dirX * 10 * speed;
					y += dirY * 10 * speed;
				}
				else {
					timer.cancel();
					setSpawned(false);
					
				}
				
			}
			
		}, 0, 20);
	}
	
	void despawn() {
		setSpawned(false);
	}
	
	void checkIfOnScreen() {
		if(getX()  > 0 && getX() < 950 && getY() > 0 && getY() < 950) {			
		}
		else {
			despawn();
		}
	}
	
	void calcDir() {
		float totalMov = 1.0f;
		float xDist = Math.abs(getTarget().getX() - getX());
		float yDist = Math.abs(getTarget().getY() - getY());
		float totalDist = xDist + yDist;
		float xPercMov = xDist / totalDist;
		dirX = xPercMov;
		dirY = totalMov - xPercMov;
		if(getTargetX() < getX()) {
			dirX *= -1;
		}
		if(getTargetY() < getY()) {
			dirY *= -1;
		}
	}
	
	void checkKollisions(Enemy e) {
		if(e.getX() >  getX() - e.getWidth() && e.getX() < getX() + getWidth()) {
			if(e.getY() > getY() - e.getHeight() && e.getY() < getY() + getHeight()) {
				despawn();
				if(isExplosive()) {
					detonate();
				}
				else {
					if(spawnsPuddle) {
						for(int i = 0; i < Game.puddle.size(); i++) {
							if(Game.puddle.get(i).isSpawned() == false) {
								Game.puddle.get(i).spawn(getX() - 50/2, getY() - 50/2, 50, 50, Bilder.honeyPuddle, 1500, getSlowPerc());
								i = Game.puddle.size();
							}
						}
					}
					else {
						
						e.setHp(e.getHp() - getDamage());
						if(e.getHp() <= 0) {
							e.despawn();
						}	
					}		
				}
			}
		}
	}
	
	void checkExplosionKollisions(Enemy e) {
		if(e.getX() >  getX() - getExplosionRange() - e.getWidth() && e.getX() < getX() + getWidth() + getExplosionRange()*2) {
			if(e.getY() > getY() - getExplosionRange() - e.getHeight() && e.getY() < getY() + getHeight() + getExplosionRange()*2) {
				
				e.setHp(e.getHp() - getDamage());
				if(e.getHp() <= 0) {
					e.despawn();
				}
			}
		}
	}
	
	void detonate() {
		for(int i = 0; i < Game.wAnt.size(); i++) {
			checkExplosionKollisions(Game.wAnt.get(i));
			checkExplosionKollisions(Game.mAnt.get(i));
			checkExplosionKollisions(Game.sAnt.get(i));
			checkExplosionKollisions(Game.tank.get(i));
		}
		for(int i = 0; i < Game.expl.size(); i++) {
			if(Game.expl.get(i).isSpawned() == false) {
				Game.expl.get(i).spawn(getX() - getExplosionRange()/2 - getWidth()/2, getY() - getExplosionRange()/2 - getHeight()/2, this);
				Game.expl.get(i).start();
				i = Game.expl.size();
			}
		}
		despawn();
	}
	
	
	public void draw(Graphics g) {
		if(isSpawned()) {
			g.drawImage(getTexture(), getX(), getY(), getWidth(), getHeight(), null);
		}
	}
	
	//Getter und Setter
	public float getSpeed() {
		return speed;
	}
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	public Tower getSource() {
		return source;
	}
	public void setSource(Tower source) {
		this.source = source;
	}
	public void setDirY(int dirY) {
		this.dirY = dirY;
	}

	public boolean isSpawned() {
		return spawned;
	}

	public void setSpawned(boolean spawned) {
		this.spawned = spawned;
	}

	public Enemy getTarget() {
		return target;
	}

	public void setTarget(Enemy target) {
		this.target = target;
	}


	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}
	public BufferedImage getTexture() {
		return texture;
	}
	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}
	public void setDirX(float dirX) {
		this.dirX = dirX;
	}
	public void setDirY(float dirY) {
		this.dirY = dirY;
	}
	public int getTargetX() {
		return targetX;
	}
	public void setTargetX(int targetX) {
		this.targetX = targetX;
	}
	public int getTargetY() {
		return targetY;
	}
	public void setTargetY(int targetY) {
		this.targetY = targetY;
	}
	public float getDirX() {
		return dirX;
	}
	public float getDirY() {
		return dirY;
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
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	public boolean isExplosive() {
		return explosive;
	}
	public void setExplosive(boolean explosive) {
		this.explosive = explosive;
	}
	public int getExplosionRange() {
		return explosionRange;
	}
	public void setExplosionRange(int explosionRange) {
		this.explosionRange = explosionRange;
	}
	public boolean isExploding() {
		return exploding;
	}
	public void setExploding(boolean exploding) {
		this.exploding = exploding;
	}

	public boolean isSpawnsPuddle() {
		return spawnsPuddle;
	}

	public void setSpawnsPuddle(boolean spawnsPuddle) {
		this.spawnsPuddle = spawnsPuddle;
	}

	public float getSlowPerc() {
		return slowPerc;
	}

	public void setSlowPerc(float slowPerc) {
		this.slowPerc = slowPerc;
	}
	
	
}
