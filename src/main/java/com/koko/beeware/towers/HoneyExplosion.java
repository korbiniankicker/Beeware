package com.koko.beeware.towers;

import java.awt.Graphics2D;

import com.koko.beeware.assets.Bilder;
import com.koko.beeware.entities.Projectile;


public class HoneyExplosion {

	public HoneyExplosion() {}

	int srX;
	int srY;
	int x;
	int y;
	int width;
	int height;
	boolean spawned;
	int cooldown;
	boolean ext;
	Projectile source;
	
	public void spawn(int x, int y, Projectile source) {
		if(isSpawned() == false) {
			setSource(source);
			setSrX(x);
			setSrY(y);
			setX(getSrX());
			setY(getSrY());
			setHeight(60);
			setWidth(60);
			setSpawned(true);
			setExt(false);
		}
	}
	
	public void despawn() {
		setSpawned(false);
	}
	
	public void extend(int c) {
		if(isExt()) {
		if(getCooldown() <= 0) {
			if(getWidth() < getSource().getExplosionRange() * 2 + getSource(). getWidth()) {
				setX(getX() - 1);
				setY(getY() - 1);
				setHeight(getHeight() + 2);
				setWidth(getWidth() + 2);
			}
			else {
				reset();
			}
		setCooldown(c);
		}
		else {
			setCooldown(getCooldown() - 1);
		}
		}
	}
	
	public void reset() {
		despawn();
		setX(getSrX());
		setY(getSrY());
		setHeight(60);
		setWidth(60);
		setExt(false);
	}
	
	public void start() {
		setCooldown(0);
		setExt(true);
	}
	
	public void draw(Graphics2D g) {
		if(isSpawned()) {
			if(isExt()) {
				g.drawImage(Bilder.honeyExpl, getX(), getY(), getWidth(), getHeight(), null);
			}
		}
	}
	//Getter und Setter

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

	public int getSrX() {
		return srX;
	}

	public void setSrX(int srX) {
		this.srX = srX;
	}

	public int getSrY() {
		return srY;
	}

	public void setSrY(int srY) {
		this.srY = srY;
	}

	public boolean isSpawned() {
		return spawned;
	}

	public void setSpawned(boolean spawned) {
		this.spawned = spawned;
	}

	public int getCooldown() {
		return cooldown;
	}

	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}

	public boolean isExt() {
		return ext;
	}

	public void setExt(boolean ext) {
		this.ext = ext;
	}

	public Projectile getSource() {
		return source;
	}

	public void setSource(Projectile source) {
		this.source = source;
	}

}
