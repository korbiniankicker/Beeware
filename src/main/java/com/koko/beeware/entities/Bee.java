package com.koko.beeware.entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.koko.beeware.assets.Bilder;
import com.koko.beeware.game.Game;

public class Bee {

	int x;
	int y;
	private BufferedImage Texture;
	private int AnimaStage;
	private int AnimateDur;
	private int dir;
	private int moving;
	private int maxUp;
	private int maxDown;
	private int maxLeft;
	private int maxRight;
	
	public Bee(int maxUp, int maxDown, int maxRight, int maxLeft) {
		setX(maxLeft + Game.r(maxRight - maxLeft));
		setY(maxUp + Game.r(maxDown - maxUp));
		this.maxDown = maxDown;
		this.maxLeft = maxLeft;
		this.maxRight = maxRight;
		this.maxUp = maxUp;
	}
	
	public void animateBees(int time) {
		if(getAnimateDur() == 0) {
			switch(getAnimaStage()) {
			case 0: setTexture(Bilder.bee[1]);
					setAnimaStage(getAnimaStage() + 1);break;
			case 1: setTexture(Bilder.bee[2]);
					setAnimaStage(getAnimaStage() + 1);break;
			case 2: setTexture(Bilder.bee[0]);
					setAnimaStage(0);break;
			default:break;
			}
			setAnimateDur(time);
		}
		else {
			setAnimateDur(getAnimateDur() - 1);
		}
	}
	
	void moveUp() {
		setY(getY() + 1);
	}
	void moveDown() {
		setY(getY() - 1);
	}
	void moveLeft() {
		setX(getX() - 1);
	}
	void moveRight() {
		setX(getX() + 1);
	}
	
	public void changeDir() {
		if(getMoving() <= 0) {
			switch(Game.r(4)) {
			case 0:setDir(1);break;
			case 1:setDir(2);break;
			case 2:setDir(3);break;
			case 3:setDir(4);break;
			default:setDir(1);break;
			}
			setMoving(30);
		}
		else {
			setMoving(getMoving() - 1);
		}
	}
	
	public void move() {
		if(getX() > maxLeft) {
			if(getX() + 40 < maxRight) {
				if(getY() > maxUp) {
					if(getY() + 40 < maxDown) {
						switch(getDir()) {
						case 1:moveUp();break;
						case 2:moveDown();break;
						case 3:moveLeft();break;
						case 4:moveRight();break;
						default:break;
						}
					}
					else {
						setDir(2);
						setMoving(30);
						moveDown();
					}
				}
				else {
					setDir(1);
					setMoving(30);
					moveUp();
				}
			}
			else {
				setDir(3);
				setMoving(30);
				moveLeft();
			}
		}
		else {
			setDir(4);
			setMoving(30);
			moveRight();
		}
	}
	
	public void draw(Graphics2D g) {
		g.drawImage(getTexture(), getX(), getY(), 40, 40, null);		
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
	public BufferedImage getTexture() {
		return Texture;
	}

	public void setTexture(BufferedImage texture) {
		Texture = texture;
	}

	public int getAnimaStage() {
		return AnimaStage;
	}

	public void setAnimaStage(int animaStage) {
		AnimaStage = animaStage;
	}

	public int getAnimateDur() {
		return AnimateDur;
	}

	public void setAnimateDur(int animateDur) {
		AnimateDur = animateDur;
	}

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}

	public int getMoving() {
		return moving;
	}

	public void setMoving(int moving) {
		this.moving = moving;
	}
	
}
