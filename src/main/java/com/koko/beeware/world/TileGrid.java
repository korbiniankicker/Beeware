package com.koko.beeware.world;

import java.awt.Graphics2D;


public class TileGrid {

	public Tile map[][];
	
		public static int[][] mapInt = {
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 2},
		{2, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 2},
		{1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 2},
		{2, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0},
		{0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0},
		{0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0},
		{0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0},
		{0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0},
		{0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
		{0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		};

		
	public TileGrid(int[][] newMap) {
		map = new Tile[15][15];
		for(int i = 0; i < map.length; i++) {
			for(int m = 0; m < map[i].length; m++) {
				if(newMap[i][m] == 0) {
					map[m][i] = new Tile(m*60, i*60, TileType.Grass);
				}
				else if (newMap[i][m] == 1){
					map[m][i] = new Tile(m*60, i*60, TileType.Dirt);
				}
				else if (newMap[i][m] == 2){
					map[m][i] = new Tile(m*60, i*60, TileType.Tower);
				}
			}
		}
	}
	public void draw(Graphics2D g) {
		for(int i = 0; i < 15; i++) {
			for(int m = 0; m < 15; m++) {
					g.drawImage( map[m][i].getType().textureName, map[m][i].getX(), map[m][i].getY(), 60, 60, null);
			}
		}
	}
}
