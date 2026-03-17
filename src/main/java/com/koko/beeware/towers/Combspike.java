package com.koko.beeware.towers;

import java.awt.Color;
import java.awt.Graphics2D;

import com.koko.beeware.assets.Bilder;
import com.koko.beeware.world.Lvl;

public class Combspike extends Trap{

	public Combspike() {
		setL(Lvl.Piece);
		setLvl(getL());
		setBaseNextLvl(Lvl.Sharp);
		setRange(0);
		setPrice(getLvl().getPrice());
		setDamage(getLvl().getDam());
		setTexture(Bilder.spike);
	}
	
	@Override
	public void draw(Graphics2D g) {
		if(isSpawned()) {
			g.setColor(Color.blue);			
			g.drawImage(getTexture(), getTileX()*60, getTileY()*60, 60, 60, null);
			g.setColor(Color.black);
			g.drawString(String.valueOf(getLvl()), getTileX()*60, getTileY()*60);
			if(isChosen()) {
				g.setColor(Color.black);
				g.drawImage(Bilder.menuSmall, getTileX()*60 + 60, getTileY()*60, 100, 100, null);
				g.setColor(Color.blue);
				g.drawRect((getTileX()*60) - getRange(), (getTileY()*60) - getRange(), getRange()*2 + 60, getRange()*2 + 60);
			}
		}
	}
	
}
