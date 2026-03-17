package com.koko.beeware.game;

import java.util.Timer;
import java.util.TimerTask;

import com.koko.beeware.assets.Bilder;
import com.koko.beeware.entities.EnemyLvl;
import com.koko.beeware.world.Obsticle;

public class GameLoop {
	Timer timer = new Timer();
	public GameLoop() {
		timer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				Game.buttonHandler.handleButtons();
				switch(Game.mainMenu.getGamemode()) {
				case 1:
				//Game.wHandler.setWave(6);
				//hindernisse		
					if(!Game.obsticles[0].isSpawned()) 
						Game.obsticles[0] = new Obsticle(14, 5, 1, 3, Bilder.beehive, -90, 60);
					if(!Game.obsticles[1].isSpawned())
						Game.obsticles[1] = new Obsticle(0, 6, 1, 3, Bilder.anthill, 90, 60);
					if(!Game.obsticles[2].isSpawned())
						Game.obsticles[2] = new Obsticle(10, 3, 2, 2, Bilder.tree, 0, 0);
					if(!Game.obsticles[3].isSpawned())
						Game.obsticles[3] = new Obsticle(3, 9, 2, 2, Bilder.tree, 0, 0);
					
				//Regelt das Verhalten der Gegner
				for(int i = 0; i < 100; i++) {
					if(Game.wAnt[i].isSpawned()) {
						Game.wAnt[i].moveToNextTile();
						Game.wAnt[i].animate(Bilder.workerAnt, Bilder.workerAntR, Bilder.workerAntRR, 5);
					}
					if(Game.mAnt[i].isSpawned()) {
						Game.mAnt[i].moveToNextTile();
						Game.mAnt[i].animate(Bilder.majorAnt, Bilder.majorAntR, Bilder.majorAntRR, 5);
					}
					if(Game.sAnt[i].isSpawned()) {
						Game.sAnt[i].moveToNextTile();
						Game.sAnt[i].animate(Bilder.soliderAnt, Bilder.soliderAntR, Bilder.soliderAntRR, 3);
					}
					if(Game.tank[i].isSpawned()) {
						Game.tank[i].moveToNextTile();
						Game.tank[i].animate(Bilder.stickTank, Bilder.stickTankR, Bilder.stickTankRR, 1);
					}
					if(Game.drone[i].isSpawned()) {
						Game.drone[i].moveToNextTile();
						Game.drone[i].animate(Bilder.drone, Bilder.droneR, Bilder.droneRR, 3);
					}
					Game.wAnt[i].adaptLvl(EnemyLvl.wAnt1, EnemyLvl.wAnt2, EnemyLvl.wAnt3, 1, 3, 6);
					Game.drone[i].adaptLvl(EnemyLvl.drone1, EnemyLvl.drone2, EnemyLvl.drone3, 5, 8, 11);
					Game.tank[i].adaptLvl(EnemyLvl.tank1, EnemyLvl.tank2, EnemyLvl.tank3, 6, 9, 12);
					Game.sAnt[i].adaptLvl(EnemyLvl.sAnt1, EnemyLvl.sAnt2, EnemyLvl.sAnt3, 4, 7, 10);
					Game.mAnt[i].adaptLvl(EnemyLvl.mAnt1, EnemyLvl.mAnt2, EnemyLvl.mAnt3, 2, 5, 8);
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
						Game.wHandler.setWaveLength(5000);
						System.out.println("STOPPED");
					}
				}
				else {
					Game.wHandler.setWaveOn(false);
				}
				
				//Regelt das Verhalten der Tuerme
				for(int i = 0; i < 100; i++) {
					Game.catapult[i].tryAquireTarget();
					Game.catapult[i].attack();
					Game.catapult[i].animate(Bilder.combsling, Bilder.combsling, Bilder.combsling, Bilder.combslingStill, 3);
					Game.bomb[i].tryAquireTarget();
					Game.bomb[i].attack();
					Game.thrower[i].tryAquireTarget();
					Game.thrower[i].attack();
					Game.thrower[i].animate(Bilder.honeyBomb, Bilder.honeyBomb, Bilder.honeyBomb, Bilder.honeyPuddleThrower, 4);
					Game.bomb[i].animate(Bilder.honeyBomb, Bilder.honeyBomb, Bilder.honeyBomb, Bilder.honeyBomb[0], 4);
					Game.spike[i].detonate(Game.wAnt);
					Game.spike[i].detonate(Game.mAnt);
					Game.spike[i].detonate(Game.sAnt);
					Game.expl[i].extend(0);
				}
				//Bienen
				for(int i = 0; i < Game.player.getHP(); i++) {
					Game.bee[i].animateBees(3);
					Game.bee[i].changeDir();
					Game.bee[i].move();
				}
				if(Game.player.getHP() <= 0) {
					Game.mainMenu.gamemode = 2;
				}
				break;
				
				case 2:
					
				
				}
				
			}
			
		}, 0, 10);
		
	}
}
