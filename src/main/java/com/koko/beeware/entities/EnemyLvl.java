
package com.koko.beeware.entities;

public enum EnemyLvl {

	wAnt1(1, 30, 2, 1), wAnt2(2, 25, 3, 1), wAnt3(3, 20, 5, 1),
	mAnt1(1, 20, 5, 1), mAnt2(2, 20, 7, 1), mAnt3(3, 15, 10, 1),
	sAnt1(1, 3, 5, 2), sAnt2(2, 2, 7, 2), sAnt3(3, 1, 10, 2),
	drone1(1, 10, 7, 3), drone2(2, 8, 10, 3), drone3(3, 6, 12, 3),
	tank1(1, 50, 30, 5), tank2(2, 40, 40, 5),tank3(3,30, 50, 5);
	
	public final int speed;
	public final int textureName;
	public final int HP;
	public final int value;
	
	
	EnemyLvl(int textureName, int speed, int HP, int value) {
		this.textureName = textureName;
		this.speed = speed;
		this.HP = HP;
		this.value = value;
	}
	
}
