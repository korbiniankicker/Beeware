package com.koko.beeware.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;

import com.koko.beeware.Constants;
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
	public static List<Obsticle> obsticles = new ArrayList<Obsticle>(Constants.MAX_ENTITIES);
	public static TileGrid grid = new TileGrid(TileGrid.mapInt);
	public static List<WorkerAnt> wAnt = new ArrayList<WorkerAnt>(Constants.MAX_ENTITIES);
	public static List<SoliderAnt> sAnt = new ArrayList<SoliderAnt>(Constants.MAX_ENTITIES);
	public static List<MajorAnt> mAnt = new ArrayList<MajorAnt>(Constants.MAX_ENTITIES);
	public static List<Drone> drone = new ArrayList<Drone>(Constants.MAX_ENTITIES);
	public static Player player = new Player();
	public static WaveHandler wHandler = new WaveHandler();
	public static List<Catapult> catapult = new ArrayList<Catapult>(Constants.MAX_ENTITIES);
	public static List<Honeybomb> bomb = new ArrayList<Honeybomb>(Constants.MAX_ENTITIES);
	public static List<HoneyPuddleThrower> thrower = new ArrayList<HoneyPuddleThrower>(Constants.MAX_ENTITIES);
	public static List<Combspike> spike = new ArrayList<Combspike>(Constants.MAX_ENTITIES);
	public static List<StickTank> tank = new ArrayList<StickTank>(Constants.MAX_ENTITIES);
	public static List<HoneyExplosion> expl = new ArrayList<HoneyExplosion>(Constants.MAX_ENTITIES);
	public static List<Bee> bee = new ArrayList<Bee>(11);
	public static List<Projectile> projectiles = new ArrayList<Projectile>(Constants.MAX_ENTITIES);
	public static List<HoneyPuddle> puddle = new ArrayList<HoneyPuddle>(Constants.MAX_ENTITIES);
	public static MainMenu mainMenu = new MainMenu();
	public static ButtonHandler buttonHandler = new ButtonHandler();
	
	//Zufallsgenerator
	static Random random = new Random();
	public static int r(int rand) {
		int random1= random.nextInt(rand);
		return random1;
	}
	public Game() {		
		for(int i = 0; i < Constants.MAX_ENTITIES; i++) {
			spike.add(new Combspike());
			bomb.add(new Honeybomb());
			catapult.add(new Catapult());
			drone.add(new Drone());
			sAnt.add(new SoliderAnt());
			mAnt.add(new MajorAnt());
			wAnt.add(new WorkerAnt());
			tank.add(new StickTank());
			expl.add(new HoneyExplosion());
			projectiles.add(new Projectile());
			puddle.add(new HoneyPuddle());
			thrower.add(new HoneyPuddleThrower());
			obsticles.add(new Obsticle());
		}
		for(int i = 0; i < 10; i++) {
			bee.add(new Bee(3 * Constants.TILE_SIZE, 12 * Constants.TILE_SIZE, 15 * Constants.TILE_SIZE, 12 * Constants.TILE_SIZE));
		}
		//Buttons
		Game.menu.setBounds(Constants.WINDOW_WIDTH - 70, 20, 60, 40);
		Game.startWave.setBounds(Constants.WINDOW_WIDTH - 140, 20, 60, 40);
		Game.selectCat.setBounds(Constants.WINDOW_WIDTH - 110, 70, 90, 40);
		Game.selectBom.setBounds(Constants.WINDOW_WIDTH - 110, 120, 90, 40);
		Game.selectSpi.setBounds(Constants.WINDOW_WIDTH - 110, 170, 90, 40);
		Game.selectThr.setBounds(Constants.WINDOW_WIDTH - 110, 220, 90, 40);
		backToMainMenu.setBounds(Constants.WINDOW_WIDTH - 210, 20, 60, 40);
	}
}
