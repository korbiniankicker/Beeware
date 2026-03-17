package com.koko.beeware;

import com.koko.beeware.assets.Bilder;
import com.koko.beeware.game.Game;
import com.koko.beeware.game.GameLoop;
import com.koko.beeware.ui.Gui;

public class Main {

		public static void main(String[] args) {
		
			new Bilder();
			new Game();
			new GameLoop();
			new Gui(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
		}
	
}
