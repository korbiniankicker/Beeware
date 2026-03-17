package com.koko.beeware.entities;

import com.koko.beeware.assets.Bilder;

public class WorkerAnt extends Enemy{

	public WorkerAnt() {
		setL(EnemyLvl.wAnt1);
		setRarity(30);
		setValue(1);
		setMovDur(30);
		setSpeed(1);
		setHp(10);
		setHealth(10);
		setWidth(20);
		setHeight(20);
		setStartingPoint();
		setY(-100);
		setX(-100);
		setTexture(Bilder.workerAnt[0]);
		findPath();
	}
	
}
