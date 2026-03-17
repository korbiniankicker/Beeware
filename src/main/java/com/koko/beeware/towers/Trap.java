package com.koko.beeware.towers;

import java.util.List;

import com.koko.beeware.Constants;
import com.koko.beeware.entities.Enemy;
import com.koko.beeware.game.Game;
import com.koko.beeware.input.MouseMotionHandler;
import com.koko.beeware.world.TileType;

public class Trap extends Tower{

	public Trap() {}

	public void detonate(List<? extends Enemy> e) {
		if(isSpawned()) {
			for(int i = 0; i < e.size(); i++) {
				if(e.get(i).getX() > (getTileX()*Constants.TILE_SIZE) && e.get(i).getX() < (getTileX()*Constants.TILE_SIZE) + Constants.TILE_SIZE) {
					if(e.get(i).getY() > (getTileY()*Constants.TILE_SIZE) && e.get(i).getY() < (getTileY()*Constants.TILE_SIZE) + Constants.TILE_SIZE) {
						e.get(i).setHp(e.get(i).getHp() - getLvl().getDam());
						if(e.get(i).getHp() <= 0) {
							e.get(i).despawn();
						}
						destroy();
						Game.player.setHoneycomb(Game.player.getHoneycomb() - getLvl().getPrice()/2);
					}
				}
			}
		}
	}
	
	public static void handleSpawns(List<? extends Tower> e) {
		for(int i = 0; i < e.size(); i++) {
			if(e.get(i).isSpawned() == false) {
				if(Game.grid.map[MouseMotionHandler.curXTile][MouseMotionHandler.curYTile].getType() == TileType.Dirt) {
					e.get(i).spawn(MouseMotionHandler.curXTile, MouseMotionHandler.curYTile);
					i = e.size();
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
	public static void handleMenu(List<? extends Tower> e) {
		if(Game.grid.map[MouseMotionHandler.curXTile][MouseMotionHandler.curYTile].getType() == TileType.Trap) {				
			for(int i = 0; i < e.size(); i++) {
				if(e.get(i).getTileX() == MouseMotionHandler.curXTile && e.get(i).getTileY() == MouseMotionHandler.curYTile) {
					if(e.get(i).isChosen()) {
						e.get(i).closeMenu();
						Game.sell.setVisible(false);
						Game.upgrade.setVisible(false);
					}
					else if(e.get(i).isChosen() == false) {
						e.get(i).openMenu();
						Game.sell.setVisible(true);
						Game.sell.setBounds(e.get(i).getTileX() * Constants.TILE_SIZE + 70, e.get(i).getTileY() * Constants.TILE_SIZE + 70, 80, 20);
						Game.upgrade.setVisible(true);
						Game.upgrade.setBounds(e.get(i).getTileX() * Constants.TILE_SIZE + 70, e.get(i).getTileY() * Constants.TILE_SIZE + 40, 80, 20);
					}
				}
				else {
					e.get(i).closeMenu();
				}
			}	
		}
	}
	
}
