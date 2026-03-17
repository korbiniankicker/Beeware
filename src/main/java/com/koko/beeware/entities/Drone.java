package com.koko.beeware.entities;

import java.awt.Color;
import java.awt.Graphics;

import com.koko.beeware.assets.Bilder;

public class Drone extends Enemy{

	public Drone() {
		setL(EnemyLvl.drone1);
		setRarity(120);
		setValue(5);
		setSpeed(1);
		setHp(15);
		setHealth(15);
		setWidth(35);
		setHeight(35);
		setStartingPoint();
		setY(-100);
		setX(-100);
		setTexture(Bilder.drone[0]);
		findPath();
	}
	public void draw(Graphics g) {
		g.drawImage(getTexture(), getX(), getY(), getWidth(), getHeight(), null);
		g.setColor(Color.red);
		g.drawString(String.valueOf(getHp()), getX(), getY());
	}
	
}
