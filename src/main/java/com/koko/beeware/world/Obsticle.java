package com.koko.beeware.world;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.koko.beeware.assets.Bilder;
import com.koko.beeware.game.Game;

public class Obsticle {

	boolean spawned = false;
	int tileX;
	int tileY;
	int width;
	int height;
	double rot;
	int v;
	BufferedImage texture;
	
	public Obsticle() {	
	}
	
	public Obsticle(int tileX, int tileY, int width, int height, BufferedImage texture, double rot, int v) {
		this.tileX = tileX;
		this.tileY = tileY;
		this.width = width;
		this.height = height;
		this.texture = texture;
		this.v = v;
		this.rot = rot;
		spawned = true;
		
		for(int i = tileX; i < width + tileX; i++) {
			for(int m = tileY; m < height + tileY; m++) {
				if(Game.grid.map[i][m].getType() == TileType.Grass) {
					Game.grid.map[i][m].setType(TileType.Tower);
				}
			}
		}
	}
	
	public void draw(Graphics2D g) {
		//g.drawImage(texture, tileX*60, tileY*60, null);
		Bilder.rotate(texture, rot, g, getTileX()*60 - v, getTileY()*60);
	}

	
	
	
	public boolean isSpawned() {
		return spawned;
	}

	public void setSpawned(boolean spawned) {
		this.spawned = spawned;
	}

	public int getTileX() {
		return tileX;
	}

	public void setTileX(int tileX) {
		this.tileX = tileX;
	}

	public int getTileY() {
		return tileY;
	}

	public void setTileY(int tileY) {
		this.tileY = tileY;
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

	public double getRot() {
		return rot;
	}

	public void setRot(double rot) {
		this.rot = rot;
	}

	public BufferedImage getTexture() {
		return texture;
	}

	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}
	
	
	
}
