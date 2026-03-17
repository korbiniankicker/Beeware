package com.koko.beeware.world;

public enum Lvl {

	Honeypop(1, 1, 25, 200, 0), Honeyburst(1, 2, 50, 650, 0), Honeyexplosion(1, 3, 75, 650, 0),
	Combsling(1, 1, 10, 200, 0), Crosscomb(1, 2, 20, 275, 0), Catapultcomb(1, 3, 30, 250, 0),
	Piece(1, 10, 5, 0, 0), Sharp(1, 20, 10, 0, 0), Spike(1, 30, 20, 0, 0),
	Thrower(1, 0, 15, 700, 1.5f), Launcher(1, 0, 35, 650, 2f), Shooter(1, 0, 35, 600, 3f);
	
	
	public int price;
	public int dam;
	public int cooldown;
	public int textureName;
	public float slowDownRate;
	
	Lvl(int texture,int dam, int price, int cooldown, float slowDownRate) {
		this.dam = dam;
		this.price = price;
		this.cooldown = cooldown;
		this.textureName = texture;
		this.slowDownRate = slowDownRate;
	}
	//Getter und Setter

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getDam() {
		return dam;
	}

	public void setDam(int dam) {
		this.dam = dam;
	}

	public int getCooldown() {
		return cooldown;
	}

	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}
	
}
