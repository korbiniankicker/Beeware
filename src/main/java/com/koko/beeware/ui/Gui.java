package com.koko.beeware.ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.koko.beeware.game.ActionHandler;
import com.koko.beeware.game.Game;
import com.koko.beeware.game.buttonHandler;
import com.koko.beeware.input.KeyHandler;
import com.koko.beeware.input.MouseHandler;
import com.koko.beeware.input.MouseMotionHandler;

public class Gui {
	
	public static JFrame f = new JFrame();
	public static Label label = new Label();
	
	public Gui(int sizeX, int sizeY) {
		//Erstellt gui
		label.setBounds(0, 0, sizeX, sizeY);
		f.setSize(sizeX, sizeY);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("Tower Defense");
		f.setLayout(null);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.addKeyListener(new KeyHandler());
		f.addMouseMotionListener(new MouseMotionHandler());
		f.addMouseListener(new MouseHandler());
		f.add(label); 
		buttons();
		f.requestFocus();
		f.setVisible(true);
		
	}
	
	private void buttons() {
		setupButton(Game.menu, f, false, 0);
		setupButton(Game.sell, f, false, 1);
		setupButton(Game.upgrade, f, false, 2);
		setupButton(Game.selectCat, f, false, 3);
		setupButton(Game.selectBom, f, false, 4);
		setupButton(Game.selectSpi, f, false, 5);
		setupButton(Game.selectThr, f, false, 6);
		setupButton(Game.startWave, f, false, 7);
		setupButton(Game.mainMenu.start, f, false, 8);
		setupButton(Game.backToMainMenu, f, false, 9);
	}
	
	private void setupButton(JButton b, JFrame frame, boolean t, int number) {
		frame.add(b);
		b.addActionListener(new ActionHandler());
		b.setVisible(t);
		addListener(b, number);
	}
	private void addListener(JButton b, final int number) {
		b.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e){
				buttonHandler.pressed[number] = true;
			}
			public void mouseExited(MouseEvent e){
				buttonHandler.pressed[number] = false;
			}
		});
	}
	
}
