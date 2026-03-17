package com.koko.beeware.world;

import java.awt.Graphics2D;

import com.koko.beeware.Constants;

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
		map = new Tile[Constants.GRID_SIZE][Constants.GRID_SIZE];
		for(int i = 0; i < map.length; i++) {
			for(int m = 0; m < map[i].length; m++) {
				if(newMap[i][m] == 0) {
					map[m][i] = new Tile(m*Constants.TILE_SIZE, i*Constants.TILE_SIZE, TileType.Grass);
				}
				else if (newMap[i][m] == 1){
					map[m][i] = new Tile(m*Constants.TILE_SIZE, i*Constants.TILE_SIZE, TileType.Dirt);
				}
				else if (newMap[i][m] == 2){
					map[m][i] = new Tile(m*Constants.TILE_SIZE, i*Constants.TILE_SIZE, TileType.Tower);
				}
			}
		}
	}
	public void draw(Graphics2D g) {
		for(int i = 0; i < Constants.GRID_SIZE; i++) {
			for(int m = 0; m < Constants.GRID_SIZE; m++) {
					g.drawImage( map[m][i].getType().textureName, map[m][i].getX(), map[m][i].getY(), Constants.TILE_SIZE, Constants.TILE_SIZE, null);
			}
		}
	}
}
