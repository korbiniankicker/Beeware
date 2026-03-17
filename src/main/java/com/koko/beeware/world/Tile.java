package com.koko.beeware.world;

public class Tile {

	//Variablen & Konstruktor
	private int x;
	private int y;
	private TileType type;
	
	Tile(int x, int y, TileType type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}
	
	//Getter & Setter

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

	public TileType getType() {
		return type;
	}

	public void setType(TileType type) {
		this.type = type;
	}
	
}
