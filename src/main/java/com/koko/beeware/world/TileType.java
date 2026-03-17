package com.koko.beeware.world;

import java.awt.image.BufferedImage;

import com.koko.beeware.assets.Bilder;

public enum TileType {

	Grass(Bilder.grass, true), Dirt(Bilder.path, false), Tower(Bilder.grass, false), Trap(Bilder.path,false);
	
	public final BufferedImage textureName;
	public final boolean buildable;
	
	TileType(BufferedImage textureName, boolean buildable) {
		this.buildable = buildable;
		this.textureName = textureName;
	}
	
}
