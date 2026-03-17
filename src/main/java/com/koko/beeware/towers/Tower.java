package com.koko.beeware.towers;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.koko.beeware.assets.Bilder;
import com.koko.beeware.entities.Enemy;
import com.koko.beeware.game.Game;
import com.koko.beeware.game.buttonHandler;
import com.koko.beeware.input.MouseMotionHandler;
import com.koko.beeware.world.Lvl;
import com.koko.beeware.world.TileType;

public abstract class Tower {

	private boolean explosive;
	private int explRange;
	private boolean puddleSpawner;
	private float slowPerc;
	private Lvl lvl;
	private Lvl nextLvl; 
	private Lvl baseNextLvl;
	private Lvl l;
	private boolean attacking;
	private int range;
	private int damage;
	private int tileX;
	private int tileY;
	private BufferedImage texture;
	private boolean	spawned;
	private int cooldown;
	private int c;
	private int price;
	private boolean chosen;
	private int AnimateDur;
	private int AnimatSt;
	private boolean animating;
	private Enemy target;
	
	public void spawn(int x, int y) {
		if(Game.player.getTowers() < Game.player.getMaxTowers()) {
			if(Game.player.getHoneycomb() >= getPrice()) {
				setTileX(x);
				setTileY(y);
				setSpawned(true);
				setC(getL().getCooldown());
				Game.player.setTowers(Game.player.getTowers() + 1);
				Game.grid.map[MouseMotionHandler.curXTile][MouseMotionHandler.curYTile].setType(TileType.Tower);
				Game.player.setHoneycomb(Game.player.getHoneycomb() - getPrice());
				setLvl(getL());
				nextLvl = getBaseNextLvl();
				setTarget(Game.wAnt[0]);
			}
		}
	}
	
	public void destroy() {
		Game.grid.map[getTileX()][getTileY()].setType(TileType.Grass);
		setSpawned(false);
		setTileX(-100);
		setTileY(-100);
		Game.player.setHoneycomb(Game.player.getHoneycomb() + getLvl().getPrice()/2);
		Game.player.setTowers(Game.player.getTowers() - 1);
		System.out.println("destroyed");
		closeMenu();
		Game.sell.setVisible(false);
		Game.upgrade.setVisible(false);
	}
	public static void handleMenu(Tower e[]) {
		if(Game.grid.map[MouseMotionHandler.curXTile][MouseMotionHandler.curYTile].getType() == TileType.Tower) {				
			for(int i = 0; i < 100; i++) {
				if(e[i].getTileX() == MouseMotionHandler.curXTile && e[i].getTileY() == MouseMotionHandler.curYTile) {
					if(e[i].isChosen()) {
						e[i].closeMenu();
						Game.sell.setVisible(false);
						Game.upgrade.setVisible(false);
					}
					else if(e[i].isChosen() == false) {
						e[i].openMenu();
						Game.sell.setVisible(true);
						Game.sell.setBounds(e[i].getTileX() * 60 + 70, e[i].getTileY() * 60 + 70, 80, 20);
						Game.upgrade.setVisible(true);
						Game.upgrade.setBounds(e[i].getTileX() * 60 + 70, e[i].getTileY() * 60 + 40, 80, 20);
					}
				}
				else {
					e[i].closeMenu();
				}
			}	
		}
	}
	
	public void openMenu() {
		setChosen(true);
	}
	
	public void closeMenu() {
		setChosen(false);
	}
	
	public void draw(Graphics2D g) {
		if(isSpawned()) {
			g.setColor(Color.blue);
			g.drawImage(Bilder.towerBase, getTileX()*60 + 10, getTileY()*60 + 10, 40, 40, null);
			Bilder.rotate(getTexture(), calcRot(), g, getTileX()*60, getTileY()*60);
			g.setColor(Color.black);
			g.drawString(String.valueOf(getLvl()), getTileX()*60, getTileY()*60);
		}
	}
	public void drawMenu(Graphics2D g) {
		g.setColor(Color.blue);
		g.drawRect((getTileX()*60) - getRange(), (getTileY()*60) - getRange(), getRange()*2 + 60, getRange()*2 + 60);
		g.setColor(Color.black);
		g.drawImage(Bilder.menuSmall, getTileX()*60 + 60, getTileY()*60, 100, 100, null);
		g.setFont(new Font("Noteworthy",Font.BOLD, 10));
		g.drawString("Lvl: " + String.valueOf(getLvl()), getTileX()*60 + 70, getTileY()* 60 + 13);
		g.drawString("Damage: " + String.valueOf(getLvl().getDam()), getTileX()*60 + 70, getTileY()* 60 + 23);
		g.drawString("Cooldown: " + String.valueOf(getLvl().getCooldown() / 100 + "s"), getTileX()*60 + 70, getTileY()* 60 + 33);
		g.setFont(new Font("Noteworthy",Font.BOLD, 9));
		//Generelles
		buttonHandler.drawButtons(g);
		if(nextLvl != null) {
			g.drawString(String.valueOf(nextLvl.price), Game.upgrade.getX() + 60, Game.upgrade.getY() + 14);
		} 
		else {
			g.drawString("max.", Game.upgrade.getX() + 60, Game.upgrade.getY() + 14);
		}
		g.drawString(String.valueOf(lvl.price/2), Game.sell.getX() + 60, Game.sell.getY() + 14);
	}
	
	public void shoot(Tower t,float speed,int damage, int width, int height, BufferedImage texture, boolean explosive, int explosionRange, boolean puddle, float slowPerc) {
		for(int i = 0; i < Game.projectiles.length; i++) {
			if(Game.projectiles[i].isSpawned() == false) {
				Game.projectiles[i].spawn(t, speed, damage, width, height, texture, explosive, explosionRange, puddle, slowPerc);
				i = Game.projectiles.length;
			}
			else {
				i++;
			}
		}
	}
	
	public float calcRot() {
		double angleTemp = Math.atan2(getTarget().getY() - getTileY()*60, getTarget().getX() - getTileX()*60);
		return (float) Math.toDegrees(angleTemp) + 90;
	}
	
	public void tryAquireTarget() {
		if(isAttacking() == false) {
			for(int i = 0; i < 100; i++) {
				checkHitboxEnemy(Game.wAnt[i]);
				checkHitboxEnemy(Game.mAnt[i]);
				checkHitboxEnemy(Game.sAnt[i]);
				checkHitboxEnemy(Game.drone[i]);
				checkHitboxEnemy(Game.tank[i]);
			}
		}
		else {
			checkHitboxTarget();
		}
	}
	
	public void checkHitboxEnemy(Enemy e) {
		if(e.getX() > getTileX()*60 - getRange() && e.getX() + e.getWidth() < getTileX()*60 + 60 + getRange()) {
			if(e.getY() > getTileY()*60 - getRange() && e.getY() + e.getHeight() < getTileY()*60 + 60 + getRange()) {
				setTarget(e);
				setAttacking(true);
			}
		}
	}
	public void checkHitboxTarget() {
		if(getTarget().getX() > getTileX()*60 - getRange() && getTarget().getX() + getTarget().getWidth() < getTileX()*60 + 60 + getRange() && getTarget().getY() > getTileY()*60 - getRange() && getTarget().getY() + getTarget().getHeight() < getTileY()*60 + 60 + getRange()) {
				setAttacking(true);
		}
		else {
			setAttacking(false);
		}
	}
	
	public void animationHelp(BufferedImage[] image, BufferedImage[] imageR, BufferedImage[] imageRR) {
		switch(getL().textureName) {
			case 1:setTexture(image[getAnimatSt()]);break;
			//case 2:setTexture(imageR[getAnimatSt()]);break;
			//case 3:setTexture(imageRR[getAnimatSt()]);break;
			default:setTexture(image[getAnimatSt()]);break;
		}
	}
	
	public void startAnimation() {
		setAnimating(true);
	}
	
	public void animate(BufferedImage[] image, BufferedImage[] imageR, BufferedImage[] imageRR, BufferedImage iStill, int c) {
		if(isSpawned()) {
			if(isAnimating()) {
				if(getAnimateDur() == 0) {
					if(getAnimatSt() < image.length - 1) {
						 animationHelp(image, imageR, imageRR);
						setAnimatSt(getAnimatSt() + 1);
					} else {
						animationHelp(image, imageR, imageRR);
						setAnimatSt(0);
						setAnimating(false);
					}
					setAnimateDur(c);
				}
				else {
					setAnimateDur(getAnimateDur() - 1);
				}
			}
			else {
				setTexture(iStill);
			}
		}
	}
	
	public static void handleSpawns(Tower e[]) {
		for(int i = 0; i < e.length; i++) {
			if(e[i].isSpawned() == false) {
				if(Game.grid.map[MouseMotionHandler.curXTile][MouseMotionHandler.curYTile].getType().buildable == true) {
					e[i].spawn(MouseMotionHandler.curXTile, MouseMotionHandler.curYTile);
					i = e.length;
					System.out.println("spawned");
				}
			}
		}
	}
	
	//greift Gegner an
	public void attack() {
		if(isSpawned()) {
			if(getC() >= getLvl().getCooldown()) {
				if(isAttacking()) {
					setC(0);
					//getTarget().setHp(getTarget().getHp() - getLvl().getDam());
					setTarget(getTarget());
					startAnimation();
					shoot(this, 1f, getLvl().getDam(), 10, 10, Bilder.comb, isExplosive(), getExplRange(), isPuddleSpawner(), getLvl().slowDownRate);
					if(getTarget().getHp() <= 0) {
						//getTarget().despawn();
						setAttacking(false);
					}
					System.out.println("attacked");
				}
			}
			else {
				if(isAttacking() == false) {
					if(getC() == getLvl().getCooldown() - 1) {
						setC(getC() + 1);
					}
				}
				else {
					setC(getC() + 1);
				}
			}
			
		}
	
}
	
	public void upgrade() {
		switch(getLvl()) {
		//Catapult lvls
			case Combsling: if(Game.player.getHoneycomb() >= Lvl.Crosscomb.getPrice()) {
								setLvl(Lvl.Crosscomb);
								nextLvl = Lvl.Catapultcomb;
								Game.player.setHoneycomb(Game.player.getHoneycomb() - Lvl.Crosscomb.getPrice());
							};break;
			case Crosscomb: if(Game.player.getHoneycomb() >= Lvl.Catapultcomb.getPrice()) {
								setLvl(Lvl.Catapultcomb);
								nextLvl = null;
								Game.player.setHoneycomb(Game.player.getHoneycomb() - Lvl.Catapultcomb.getPrice());
							};break;
			case Catapultcomb: System.out.println("Max Lvl");break;
		//Honeybomb lvls		
			case Honeypop: if(Game.player.getHoneycomb() >= Lvl.Honeyburst.getPrice()) {
								setLvl(Lvl.Honeyburst);
								nextLvl = Lvl.Honeyexplosion;
								Game.player.setHoneycomb(Game.player.getHoneycomb() - Lvl.Honeyburst.getPrice());
							};break;
			case Honeyburst: if(Game.player.getHoneycomb() >= Lvl.Honeyexplosion.getPrice()) {
								setLvl(Lvl.Honeyexplosion);
								nextLvl = null;
								Game.player.setHoneycomb(Game.player.getHoneycomb() - Lvl.Honeyexplosion.getPrice());
							 };break;
			case Honeyexplosion: System.out.println("Max Lvl");break;
		//Combspike lvls
			case Piece: if(Game.player.getHoneycomb() >= Lvl.Sharp.getPrice()) {
							setLvl(Lvl.Sharp);
							nextLvl = Lvl.Spike;
							Game.player.setHoneycomb(Game.player.getHoneycomb() - Lvl.Sharp.getPrice());
						};break;
			case Sharp: if(Game.player.getHoneycomb() >= Lvl.Spike.getPrice()) {
							setLvl(Lvl.Spike);
							nextLvl = null;
							Game.player.setHoneycomb(Game.player.getHoneycomb() - Lvl.Spike.getPrice());
						};break;
			case Spike: System.out.println("Max Lvl");break;
		//Thrower lvls
			case Thrower: if(Game.player.getHoneycomb() >= Lvl.Launcher.getPrice()) {
							setLvl(Lvl.Launcher);
							nextLvl = Lvl.Shooter;
							Game.player.setHoneycomb(Game.player.getHoneycomb() - Lvl.Launcher.getPrice());
						};break;
			case Launcher: if(Game.player.getHoneycomb() >= Lvl.Shooter.getPrice()) {
							setLvl(Lvl.Shooter);
							nextLvl = null;
							Game.player.setHoneycomb(Game.player.getHoneycomb() - Lvl.Shooter.getPrice());
						   };break;
			case Shooter: System.out.println("Max Lvl");break;
		default:break;
		}
	}
	
	//Getter und Setter
	public int getRange() {
		return range;
	}
	public void setRange(int range) {
		this.range = range;
	}
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
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
	public boolean isSpawned() {
		return spawned;
	}
	public void setSpawned(boolean spawned) {
		this.spawned = spawned;
	}
	public int getCooldown() {
		return cooldown;
	}
	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}

	public boolean isAttacking() {
		return attacking;
	}

	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}

	public boolean isChosen() {
		return chosen;
	}

	public void setChosen(boolean chosen) {
		this.chosen = chosen;
	}

	public Lvl getLvl() {
		return lvl;
	}

	public void setLvl(Lvl lvl) {
		this.lvl = lvl;
	}

	public Lvl getL() {
		return l;
	}

	public void setL(Lvl l) {
		this.l = l;
	}

	public BufferedImage getTexture() {
		return texture;
	}

	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}

	public int getAnimateDur() {
		return AnimateDur;
	}

	public void setAnimateDur(int animateDur) {
		AnimateDur = animateDur;
	}

	public int getAnimatSt() {
		return AnimatSt;
	}

	public void setAnimatSt(int animatSt) {
		AnimatSt = animatSt;
	}

	public boolean isAnimating() {
		return animating;
	}

	public void setAnimating(boolean animating) {
		this.animating = animating;
	}
	
	public Enemy getTarget() {
		return target;
	}

	public void setTarget(Enemy target) {
		this.target = target;
	}

	public boolean isExplosive() {
		return explosive;
	}

	public void setExplosive(boolean explosive) {
		this.explosive = explosive;
	}

	public int getExplRange() {
		return explRange;
	}

	public void setExplRange(int explRange) {
		this.explRange = explRange;
	}

	public boolean isPuddleSpawner() {
		return puddleSpawner;
	}

	public void setPuddleSpawner(boolean puddleSpawner) {
		this.puddleSpawner = puddleSpawner;
	}

	public float getSlowPerc() {
		return slowPerc;
	}

	public void setSlowPerc(float slowPerc) {
		this.slowPerc = slowPerc;
	}

	public Lvl getBaseNextLvl() {
		return baseNextLvl;
	}

	public void setBaseNextLvl(Lvl baseNextLvl) {
		this.baseNextLvl = baseNextLvl;
	}
	
	
}
