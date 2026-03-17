package com.koko.beeware.entities;

import com.koko.beeware.assets.Bilder;

public class MajorAnt extends Enemy{

	public MajorAnt() {
		setL(EnemyLvl.mAnt1);
		setRarity(120);
		setSpeed(1);
		setValue(3);
		setHp(20);
		setHealth(20);
		setWidth(25);
		setHeight(25);
		setStartingPoint();
		setY(-100);
		setX(-100);
		setTexture(Bilder.majorAnt[0]);
		findPath();
	}
	
}
