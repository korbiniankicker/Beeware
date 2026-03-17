package com.koko.beeware.game;

import java.util.Random;

import javax.swing.JButton;

import com.koko.beeware.entities.Bee;
import com.koko.beeware.entities.Drone;
import com.koko.beeware.entities.MajorAnt;
import com.koko.beeware.entities.Player;
import com.koko.beeware.entities.Projectile;
import com.koko.beeware.entities.SoliderAnt;
import com.koko.beeware.entities.StickTank;
import com.koko.beeware.entities.WorkerAnt;
import com.koko.beeware.towers.Catapult;
import com.koko.beeware.towers.Combspike;
import com.koko.beeware.towers.HoneyExplosion;
import com.koko.beeware.towers.HoneyPuddle;
import com.koko.beeware.towers.HoneyPuddleThrower;
import com.koko.beeware.towers.Honeybomb;
import com.koko.beeware.ui.MainMenu;
import com.koko.beeware.world.Obsticle;
import com.koko.beeware.world.TileGrid;

public class Game {
	
	//in game buttons
		public static JButton startWave = new JButton();
		public static JButton sell = new JButton();
		public static JButton upgrade = new JButton();
		public static JButton menu = new JButton();
		public static JButton selectCat = new JButton();
		public static JButton selectBom = new JButton();
		public static JButton selectSpi = new JButton();
		public static JButton selectThr = new JButton();
		public static JButton backToMainMenu = new JButton();
	
	//Objekte
	public static Obsticle obsticles [] = new Obsticle[100];
	public static TileGrid grid = new TileGrid(TileGrid.mapInt);
	public static WorkerAnt wAnt[] = new WorkerAnt[100];
	public static SoliderAnt sAnt[] = new SoliderAnt[100];
	public static MajorAnt mAnt[] = new MajorAnt[100];
	public static Drone drone[] = new Drone[100];
	public static Player player = new Player();
	public static WaveHandler wHandler = new WaveHandler();
	public static Catapult catapult[] = new Catapult[100];
	public static Honeybomb bomb[] = new Honeybomb[100];
	public static HoneyPuddleThrower thrower[] = new HoneyPuddleThrower[100];
	public static Combspike spike[] = new Combspike[100];
	public static StickTank tank[] = new StickTank[100];
	public static HoneyExplosion expl[] = new HoneyExplosion[100];
	public static Bee bee[] = new Bee[11];
	public static Projectile[] projectiles = new Projectile[100];
	public static HoneyPuddle puddle[] = new HoneyPuddle[100];
	public static MainMenu mainMenu = new MainMenu();
	public static buttonHandler buttonHandler = new buttonHandler();
	
	//Zufallsgenerator
	static Random random = new Random();
	public static int r(int rand) {
		int random1= random.nextInt(rand);
		return random1;
	}
	public Game() {		
		for(int i = 0; i < 100; i++) {
			spike[i] = new Combspike();
			bomb[i] = new Honeybomb();
			catapult[i] = new Catapult();
			drone[i] = new Drone();
			sAnt[i] = new SoliderAnt();
			mAnt[i] = new MajorAnt();
			wAnt[i] = new WorkerAnt();
			tank[i] = new StickTank();
			expl[i] = new HoneyExplosion();
			projectiles[i] = new Projectile();
			puddle[i] = new HoneyPuddle();
			thrower[i] = new HoneyPuddleThrower();
			obsticles[i] = new Obsticle();
		}
		for(int i = 0; i < 10; i++) {
			bee[i] = new Bee(3*60, 12*60, 15*60, 12*60);
		}
		//Buttons
		Game.menu.setBounds(830, 20, 60, 40);
		Game.startWave.setBounds(760, 20, 60, 40);
		Game.selectCat.setBounds(790, 70, 90, 40);
		Game.selectBom.setBounds(790, 120, 90, 40);
		Game.selectSpi.setBounds(790, 170, 90, 40);
		Game.selectThr.setBounds(790, 220, 90, 40);
		backToMainMenu.setBounds(690, 20, 60, 40);
	}
}
