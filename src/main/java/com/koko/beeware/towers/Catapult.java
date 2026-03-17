package com.koko.beeware.towers;

import com.koko.beeware.assets.Bilder;
import com.koko.beeware.game.Game;
import com.koko.beeware.world.Lvl;

public class Catapult extends Tower{

	public Catapult() {
		setL(Lvl.Combsling);
		setLvl(getL());
		setBaseNextLvl(Lvl.Crosscomb);
		setRange(70);
		setPrice(getLvl().getPrice());
		setDamage(getLvl().getDam());
		setCooldown(getLvl().getCooldown());
		setTexture(Bilder.combslingStill);
		setExplosive(false);
		setPuddleSpawner(false);
		setTarget(Game.wAnt[0]);
	}
	
	
}
