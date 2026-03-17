package com.koko.beeware.game;

import com.koko.beeware.entities.Enemy;

public class WaveHandler {

	private int lvl;
	private int wave;
	private int difficulty;
	private int waveLength = 5000;
	private boolean waveOn;
	
	public void startWave() {
		setWave(getWave() + 1);
		setWaveLength(5000);
		Game.player.setMaxTowers(Game.player.getMaxTowers() + 1);
		setWaveOn(true);
		System.out.println("STARTED");
		for(int i = 0; i < 100; i++) {
			if(Game.wAnt[i].getRarity() > 5) { 
				Game.wAnt[i].setRarity(Game.wAnt[i].getRarity() - 5);
			}
			if(Game.mAnt[i].getRarity() > 30) {
				Game.mAnt[i].setRarity(Game.mAnt[i].getRarity() - 1);
			}
			if(Game.sAnt[i].getRarity() > 50) {
				Game.sAnt[i].setRarity(Game.sAnt[i].getRarity() - 1);
			}
		}
	}
	public void spawnWave(Enemy e[]) {
		if(isWaveOn()) {
			for(int i = 0;i < e.length; i++) {
				if(Game.r(e[i].getRarity()*1000) == 1) {
					if(e[i].isSpawned() == false) {
						e[i].spawn();
						i = e.length;
					}
				}
			}
		}
	}
	
	//Getter & Setter
	
	public int getLvl() {
		return lvl;
	}


	public void setLvl(int lvl) {
		this.lvl = lvl;
	}


	public int getWave() {
		return wave;
	}


	public void setWave(int wave) {
		this.wave = wave;
	}


	public int getDifficulty() {
		return difficulty;
	}


	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public int getWaveLength() {
		return waveLength;
	}

	public void setWaveLength(int waveLength) {
		this.waveLength = waveLength;
	}
	public boolean isWaveOn() {
		return waveOn;
	}
	public void setWaveOn(boolean waveOn) {
		this.waveOn = waveOn;
	}
	
	
}
