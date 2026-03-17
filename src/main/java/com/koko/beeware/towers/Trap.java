package com.koko.beeware.towers;

import com.koko.beeware.entities.Enemy;
import com.koko.beeware.game.Game;
import com.koko.beeware.input.MouseMotionHandler;
import com.koko.beeware.world.TileType;

public class Trap extends Tower{

	public Trap() {}

	public void detonate(Enemy e[]) {
		if(isSpawned()) {
			for(int i = 0;i < e.length; i++) {
				if(e[i].getX() > (getTileX()*60) && e[i].getX() < (getTileX()*60) + 60) {
					if(e[i].getY() > (getTileY()*60) && e[i].getY() < (getTileY()*60) + 60) {
						e[i].setHp(e[i].getHp() - getLvl().getDam());
						if(e[i].getHp() <= 0) {
							e[i].despawn();
						}
						destroy();
						Game.player.setHoneycomb(Game.player.getHoneycomb() - getLvl().getPrice()/2);
					}
				}
			}
		}
	}
	
	public static void handleSpawns(Tower e[]) {
		for(int i = 0; i < e.length; i++) {
			if(e[i].isSpawned() == false) {
				if(Game.grid.map[MouseMotionHandler.curXTile][MouseMotionHandler.curYTile].getType() == TileType.Dirt) {
					e[i].spawn(MouseMotionHandler.curXTile, MouseMotionHandler.curYTile);
					i = e.length;
					System.out.println("spawned");
				}
			}
		}
	}
	@Override
	public void spawn(int x, int y) {
		if(Game.player.getTowers() < Game.player.getMaxTowers()) {
			if(Game.player.getHoneycomb() >= getPrice()) {
				setTileX(x);
				setTileY(y);
				setSpawned(true);
				setC(getL().getCooldown());
				Game.player.setHoneycomb(Game.player.getHoneycomb() - getPrice());
				Game.grid.map[getTileX()][getTileY()].setType(TileType.Trap);
				setLvl(getL());
			}
		}
	}
	@Override
	public void destroy() {
		Game.grid.map[getTileX()][getTileY()].setType(TileType.Dirt);
		setSpawned(false);
		Game.player.setHoneycomb(Game.player.getHoneycomb() + getLvl().getPrice()/2);
		Game.player.setTowers(Game.player.getTowers() - 1);
		System.out.println("destroyed");
		closeMenu();
		Game.sell.setVisible(false);
		Game.upgrade.setVisible(false);
	}
	public static void handleMenu(Tower e[]) {
		if(Game.grid.map[MouseMotionHandler.curXTile][MouseMotionHandler.curYTile].getType() == TileType.Trap) {				
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
	
}
