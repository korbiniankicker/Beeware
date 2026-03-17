package com.koko.beeware.towers;

import java.awt.Color;
import java.awt.Graphics2D;

import com.koko.beeware.Constants;
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
			g.drawImage(getTexture(), getTileX()*Constants.TILE_SIZE, getTileY()*Constants.TILE_SIZE, Constants.TILE_SIZE, Constants.TILE_SIZE, null);
			g.setColor(Color.black);
			g.drawString(String.valueOf(getLvl()), getTileX()*Constants.TILE_SIZE, getTileY()*Constants.TILE_SIZE);
			if(isChosen()) {
				g.setColor(Color.black);
				g.drawImage(Bilder.menuSmall, getTileX()*Constants.TILE_SIZE + Constants.TILE_SIZE, getTileY()*Constants.TILE_SIZE, 100, 100, null);
				g.setColor(Color.blue);
				g.drawRect((getTileX()*Constants.TILE_SIZE) - getRange(), (getTileY()*Constants.TILE_SIZE) - getRange(), getRange()*2 + Constants.TILE_SIZE, getRange()*2 + Constants.TILE_SIZE);
			}
		}
	}
	
}
