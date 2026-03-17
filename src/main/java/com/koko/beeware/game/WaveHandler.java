package com.koko.beeware.game;

import java.util.List;

import com.koko.beeware.Constants;
import com.koko.beeware.entities.Enemy;

public class WaveHandler {

	private int lvl;
	private int wave;
	private int difficulty;
	private int waveLength = Constants.WAVE_LENGTH_TICKS;
	private boolean waveOn;
	
	public void startWave() {
		setWave(getWave() + 1);
		setWaveLength(Constants.WAVE_LENGTH_TICKS);
		Game.player.setMaxTowers(Game.player.getMaxTowers() + 1);
		setWaveOn(true);
		
		for(int i = 0; i < Constants.MAX_ENTITIES; i++) {
			if(Game.wAnt.get(i).getRarity() > 5) { 
				Game.wAnt.get(i).setRarity(Game.wAnt.get(i).getRarity() - 5);
			}
			if(Game.mAnt.get(i).getRarity() > 30) {
				Game.mAnt.get(i).setRarity(Game.mAnt.get(i).getRarity() - 1);
			}
			if(Game.sAnt.get(i).getRarity() > 50) {
				Game.sAnt.get(i).setRarity(Game.sAnt.get(i).getRarity() - 1);
			}
		}
	}
	public void spawnWave(List<? extends Enemy> e) {
		if(isWaveOn()) {
			for(int i = 0; i < e.size(); i++) {
				if(Game.r(e.get(i).getRarity()*1000) == 1) {
					if(e.get(i).isSpawned() == false) {
						e.get(i).spawn();
						i = e.size();
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
