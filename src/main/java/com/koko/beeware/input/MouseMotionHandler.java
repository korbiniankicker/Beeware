package com.koko.beeware.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;


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
		y = e.getY() - 27;
		
		for(int i = 0; i < 15; i++) {
			for(int m = 0; m < 15; m++) {
				if(x > i*60 && x < i*60 + 60) {
					if(y > m*60 && y < m*60 + 60) {
						curXTile = i;
						curYTile = m;
					}
				}
			}
		}
		
	}

}
