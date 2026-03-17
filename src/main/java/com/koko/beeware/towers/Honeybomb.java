package com.koko.beeware.towers;

import com.koko.beeware.assets.Bilder;
import com.koko.beeware.game.Game;
import com.koko.beeware.world.Lvl;

public class Honeybomb extends Tower{

	public Honeybomb() {
		setL(Lvl.Honeypop);
		setLvl(getL());
		setBaseNextLvl(Lvl.Honeyburst);
		setRange(50);
		setPrice(getLvl().getPrice());
		setDamage(getLvl().getDam());
		setCooldown(getLvl().getCooldown());
		setTexture(Bilder.honeyBomb[0]);
		setExplosive(true);
		setExplRange(60);
		setPuddleSpawner(false);
		setTarget(Game.wAnt[0]);
	}
	
}
