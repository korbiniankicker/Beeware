package com.koko.beeware.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import com.koko.beeware.Constants;

public class MouseMotionHandler implements MouseMotionListener{

	public static int x;
	public static int y;
	public static int curXTile;
	public static int curYTile;
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
		x = e.getX();
		y = e.getY() - Constants.MOUSE_Y_OFFSET;
		
		for(int i = 0; i < Constants.GRID_SIZE; i++) {
			for(int m = 0; m < Constants.GRID_SIZE; m++) {
				if(x > i*Constants.TILE_SIZE && x < i*Constants.TILE_SIZE + Constants.TILE_SIZE) {
					if(y > m*Constants.TILE_SIZE && y < m*Constants.TILE_SIZE + Constants.TILE_SIZE) {
						curXTile = i;
						curYTile = m;
					}
				}
			}
		}
		
	}

}
