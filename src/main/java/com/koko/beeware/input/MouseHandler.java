package com.koko.beeware.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.koko.beeware.game.Game;
import com.koko.beeware.towers.Tower;
import com.koko.beeware.towers.Trap;

public class MouseHandler implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		//falls Rechtsclick
		if(e.getButton() == 3) {
			Tower.handleMenu(Game.catapult);
			Tower.handleMenu(Game.bomb);
			Tower.handleMenu(Game.thrower);
			Trap.handleMenu(Game.spike);
		}
		//Falls Linksclick
		if(e.getButton() == 1) {
			switch(Game.player.getSelected()) {
			case 1:Tower.handleSpawns(Game.catapult);break;
			case 2:Tower.handleSpawns(Game.bomb);break;
			case 3:Trap.handleSpawns(Game.spike);break;
			case 4:Tower.handleSpawns(Game.thrower);break;
			default:System.out.println("Kein Turm ausgewaehlt");break;
			}
		}

		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
