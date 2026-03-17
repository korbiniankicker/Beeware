package com.koko.beeware.entities;

import com.koko.beeware.assets.Bilder;

public class SoliderAnt extends Enemy{

	public SoliderAnt() {
		setL(EnemyLvl.sAnt1);
		setRarity(170);
		setValue(5);
		setSpeed(1);
		setHp(30);
		setHealth(30);
		setWidth(30);
		setHeight(30);
		setStartingPoint();
		setY(-100);
		setX(-100);
		setTexture(Bilder.soliderAnt[0]);
		findPath();
	}
}
