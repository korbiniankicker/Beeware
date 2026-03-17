package com.koko.beeware.game;

import javax.swing.Timer;

import com.koko.beeware.Constants;
import com.koko.beeware.assets.Bilder;
import com.koko.beeware.entities.EnemyLvl;
import com.koko.beeware.ui.Gui;
import com.koko.beeware.world.Obsticle;

public class GameLoop {
	private final Timer timer;
	public GameLoop() {
		timer = new Timer(Constants.TICK_MS, e -> {
				Game.buttonHandler.handleButtons();
				switch(Game.mainMenu.getGamemode()) {
				case 1:
				//Game.wHandler.setWave(6);
				//hindernisse		
					if(!Game.obsticles.get(0).isSpawned()) 
						Game.obsticles.set(0, new Obsticle(14, 5, 1, 3, Bilder.beehive, -90, Constants.TILE_SIZE));
					if(!Game.obsticles.get(1).isSpawned())
						Game.obsticles.set(1, new Obsticle(0, 6, 1, 3, Bilder.anthill, 90, Constants.TILE_SIZE));
					if(!Game.obsticles.get(2).isSpawned())
						Game.obsticles.set(2, new Obsticle(10, 3, 2, 2, Bilder.tree, 0, 0));
					if(!Game.obsticles.get(3).isSpawned())
						Game.obsticles.set(3, new Obsticle(3, 9, 2, 2, Bilder.tree, 0, 0));
					
				//Regelt das Verhalten der Gegner
				for(int i = 0; i < Constants.MAX_ENTITIES; i++) {
					if(Game.wAnt.get(i).isSpawned()) {
						Game.wAnt.get(i).moveToNextTile();
						Game.wAnt.get(i).animate(Bilder.workerAnt, Bilder.workerAntR, Bilder.workerAntRR, 5);
					}
					if(Game.mAnt.get(i).isSpawned()) {
						Game.mAnt.get(i).moveToNextTile();
						Game.mAnt.get(i).animate(Bilder.majorAnt, Bilder.majorAntR, Bilder.majorAntRR, 5);
					}
					if(Game.sAnt.get(i).isSpawned()) {
						Game.sAnt.get(i).moveToNextTile();
						Game.sAnt.get(i).animate(Bilder.soliderAnt, Bilder.soliderAntR, Bilder.soliderAntRR, 3);
					}
					if(Game.tank.get(i).isSpawned()) {
						Game.tank.get(i).moveToNextTile();
						Game.tank.get(i).animate(Bilder.stickTank, Bilder.stickTankR, Bilder.stickTankRR, 1);
					}
					if(Game.drone.get(i).isSpawned()) {
						Game.drone.get(i).moveToNextTile();
						Game.drone.get(i).animate(Bilder.drone, Bilder.droneR, Bilder.droneRR, 3);
					}
					Game.wAnt.get(i).adaptLvl(EnemyLvl.wAnt1, EnemyLvl.wAnt2, EnemyLvl.wAnt3, 1, 3, 6);
					Game.drone.get(i).adaptLvl(EnemyLvl.drone1, EnemyLvl.drone2, EnemyLvl.drone3, 5, 8, 11);
					Game.tank.get(i).adaptLvl(EnemyLvl.tank1, EnemyLvl.tank2, EnemyLvl.tank3, 6, 9, 12);
					Game.sAnt.get(i).adaptLvl(EnemyLvl.sAnt1, EnemyLvl.sAnt2, EnemyLvl.sAnt3, 4, 7, 10);
					Game.mAnt.get(i).adaptLvl(EnemyLvl.mAnt1, EnemyLvl.mAnt2, EnemyLvl.mAnt3, 2, 5, 8);
				}
				
				
				//Regelt die Gegnerwellen
				if(Game.wHandler.isWaveOn() == true) {
					if(Game.wHandler.getWaveLength() > 0) {
						Game.wHandler.spawnWave(Game.wAnt);
						if(Game.wHandler.getWave() > 1) {
							Game.wHandler.spawnWave(Game.mAnt);
						}
						if(Game.wHandler.getWave() > 3) {
							Game.wHandler.spawnWave(Game.sAnt);
						}
						if(Game.wHandler.getWave() > 4) {
							Game.wHandler.spawnWave(Game.drone);
						}
						if(Game.wHandler.getWave() > 5) {
							Game.wHandler.spawnWave(Game.tank);
						}
					Game.wHandler.setWaveLength(Game.wHandler.getWaveLength() - 1);
					}
					else {
						Game.wHandler.setWaveOn(false);
						Game.wHandler.setWaveLength(Constants.WAVE_LENGTH_TICKS);
						
					}
				}
				else {
					Game.wHandler.setWaveOn(false);
				}
				
				//Regelt das Verhalten der Tuerme
				for(int i = 0; i < Constants.MAX_ENTITIES; i++) {
					Game.catapult.get(i).tryAquireTarget();
					Game.catapult.get(i).attack();
					Game.catapult.get(i).animate(Bilder.combsling, Bilder.combsling, Bilder.combsling, Bilder.combslingStill, 3);
					Game.bomb.get(i).tryAquireTarget();
					Game.bomb.get(i).attack();
					Game.thrower.get(i).tryAquireTarget();
					Game.thrower.get(i).attack();
					Game.thrower.get(i).animate(Bilder.honeyBomb, Bilder.honeyBomb, Bilder.honeyBomb, Bilder.honeyPuddleThrower, 4);
					Game.bomb.get(i).animate(Bilder.honeyBomb, Bilder.honeyBomb, Bilder.honeyBomb, Bilder.honeyBomb[0], 4);
					Game.spike.get(i).detonate(Game.wAnt);
					Game.spike.get(i).detonate(Game.mAnt);
					Game.spike.get(i).detonate(Game.sAnt);
					Game.expl.get(i).extend(0);
				}
				//Bienen
				for(int i = 0; i < Game.player.getHP(); i++) {
					Game.bee.get(i).animateBees(3);
					Game.bee.get(i).changeDir();
					Game.bee.get(i).move();
				}
				if(Game.player.getHP() <= 0) {
					Game.mainMenu.gamemode = 2;
				}
				break;
				
				case 2:
					
				
				}
				
				Gui.label.repaint();
			});
		timer.setRepeats(true);
		timer.start();
		
	}
}
