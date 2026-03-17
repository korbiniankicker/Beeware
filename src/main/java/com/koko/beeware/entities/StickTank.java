package com.koko.beeware.entities;

import java.awt.image.BufferedImage;

import com.koko.beeware.assets.Bilder;

public class StickTank extends Enemy{
	
	public StickTank() {
		setL(EnemyLvl.tank1);
		setRarity(200);
		setValue(25);
		setSpeed(1);
		setHp(100);
		setHealth(100);
		setWidth(30);
		setHeight(30);
		setStartingPoint();
		setY(-100);
		setX(-100);
		setTexture(Bilder.stickTank[0]);
		findPath();
	}
	
	public void animate(BufferedImage[] image, BufferedImage[] imageR, BufferedImage[] imageRR, int c) {
		if(isSpawned()) {
			if(getAnimateDur() == 0) {
				 switch(getL().textureName) {
					case 1:setTexture(image[getAnimatSt()]);break;
					case 2:setTexture(imageR[getAnimatSt()]);break;
					case 3:setTexture(imageRR[getAnimatSt()]);break;
					default:setTexture(image[getAnimatSt()]);break;
				}
				setAnimateDur(c);
			}
			else {
				setAnimateDur(getAnimateDur() - 1);
			}
		}
	}
	
}
