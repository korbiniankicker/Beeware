package com.koko.beeware.assets;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class Bilder {

	public static BufferedImage path, grass, menuSmall, menuBig, beehive, honeyExpl, combslingStill, honeyBombStill, comb, spike, towerBase, honeyPuddle, icon;
	public static BufferedImage tree, startSelected, start, backToMainMenu, backToMainMenuSelected, Towers, TowersSelected, startWave, startWaveSelected, honeyPuddleThrower;
	public static BufferedImage spikesButton, spikesButtonSelected, combslingButton, combslingButtonSelected, honeybombButton, honeybombButtonSelected, honeyThrowerButton, honeyThrowerButtonSelected;
	public static BufferedImage anthill, gameOver, gameOver1, upgradeButton, upgradeButtonSelected, sellButton, sellButtonSelected;
	public static BufferedImage[] workerAnt = new BufferedImage[3];
	public static BufferedImage[] workerAntR = new BufferedImage[3];
	public static BufferedImage[] workerAntRR = new BufferedImage[3];
	public static BufferedImage[] majorAnt = new BufferedImage[3];
	public static BufferedImage[] majorAntR = new BufferedImage[3];
	public static BufferedImage[] majorAntRR = new BufferedImage[3];
	public static BufferedImage[] soliderAnt = new BufferedImage[3];
	public static BufferedImage[] soliderAntR = new BufferedImage[3];
	public static BufferedImage[] soliderAntRR = new BufferedImage[3];
	public static BufferedImage[] drone = new BufferedImage[3];
	public static BufferedImage[] droneR = new BufferedImage[3];
	public static BufferedImage[] droneRR = new BufferedImage[3];
	public static BufferedImage[] stickTank = new BufferedImage[1];
	public static BufferedImage[] stickTankR = new BufferedImage[1];
	public static BufferedImage[] stickTankRR = new BufferedImage[1];
	public static BufferedImage[] honeyBomb = new BufferedImage[7];
	public static BufferedImage[] bee = new BufferedImage[3];
	public static BufferedImage[] combsling = new BufferedImage[8];
	
	private static Graphics2D g2d;
	
	public Bilder() {
		try {
			for(int i = 0; i <= 2; i++) {
				workerAnt[i] = loadImage("/rsc/WorkerAnt " + i + ".png");
				workerAntR[i] = loadImage("/rsc/WorkerAntR " + i + ".png");
				workerAntRR[i] = loadImage("/rsc/WorkerAntRR " + i + ".png");
			}
			for(int i = 0; i <= 2; i++) {
				majorAnt[i] = loadImage("/rsc/MajorAnt " + i + ".png");
				majorAntR[i] = loadImage("/rsc/MajorAntR " + i + ".png");
				majorAntRR[i] = loadImage("/rsc/MajorAntRR " + i + ".png");
			}
			for(int i = 0; i <= 2; i++) {
				soliderAnt[i] = loadImage("/rsc/SoliderAnt " + i + ".png");
				soliderAntR[i] = loadImage("/rsc/SoliderAntR " + i + ".png");
				soliderAntRR[i] = loadImage("/rsc/SoliderAntRR " + i + ".png");
			}
			for(int i = 0; i <= 2; i++) {
				drone[i] = loadImage("/rsc/Drone " + i + ".png");
				droneR[i] = loadImage("/rsc/DroneR " + i + ".png");
				droneRR[i] = loadImage("/rsc/DroneRR " + i + ".png");
			}
			for(int i = 0; i <= 0; i++) {
				stickTank[i] = loadImage("/rsc/StickTank " + i + ".png");
				stickTankR[i] = loadImage("/rsc/StickTankR " + i + ".png");
				stickTankRR[i] = loadImage("/rsc/StickTankRR " + i + ".png");
			}
			for(int i = 0; i <= 2; i++) {
				bee[i] = loadImage("/rsc/Bee " + i + ".png");
			}
			for(int i = 0; i <= 3; i++) {
				combsling[i] = loadImage("/rsc/Combsling " + i + ".png");
			}
			for(int i = 4; i <= 7; i++) {
				combsling[i] = loadImage("/rsc/Combsling " + (7 - i) + ".png");
			}
			for(int i = 0; i <= 3; i++) {
				honeyBomb[i] = loadImage("/rsc/HoneyBombThrower " + i + ".png");
			}
			for(int i = 3; i <= 6; i++) {
				honeyBomb[i] = loadImage("/rsc/HoneyBombThrower " + (6 - i) + ".png");
			}
			grass = loadImage("/rsc/Grass.png");
			path = loadImage("/rsc/Path.png");
			menuSmall = loadImage("/rsc/MenuSmall.png");
			beehive = loadImage("/rsc/Beehive.png");
			menuBig = loadImage("/rsc/MenuBig.png");
			honeyExpl = loadImage("/rsc/HoneyExpl.png");
			combslingStill = loadImage("/rsc/Combsling 14.png");
			comb = loadImage("/rsc/comb.png");
			spike = loadImage("/rsc/Spikes.png");
			honeyBombStill = loadImage("/rsc/Honeybomb 0 : 10.png");
			towerBase = loadImage("/rsc/TowerBase.png");
			honeyPuddle = loadImage("/rsc/HoneyPuddle.png");
			icon = loadImage("/rsc/Beeware Icon.png");
			tree = loadImage("/rsc/Tree.png");
			start = loadImage("/rsc/Start.png");
			startSelected = loadImage("/rsc/StartSelected.png");
			TowersSelected = loadImage("/rsc/TowersSelected.png");
			Towers = loadImage("/rsc/Towers.png");
			backToMainMenuSelected = loadImage("/rsc/BackToMainMenuSelected.png");
			backToMainMenu = loadImage("/rsc/BackToMainMenu.png");
			startWaveSelected = loadImage("/rsc/startWaveSelected.png");
			startWave = loadImage("/rsc/startWave.png");
			honeyPuddleThrower = loadImage("/rsc/HoneyPuddleThrower.png");
			spikesButton = loadImage("/rsc/SpikesButton.png");
			spikesButtonSelected = loadImage("/rsc/SpikesButtonSelected.png");
			combslingButtonSelected = loadImage("/rsc/CombslingButtonSelected.png");
			combslingButton = loadImage("/rsc/CombslingButton.png");
			honeybombButton = loadImage("/rsc/HoneybombButton.png");
			honeybombButtonSelected = loadImage("/rsc/HoneybombButtonSelected.png");
			honeyThrowerButton = loadImage("/rsc/HoneyThrowerButton.png");
			honeyThrowerButtonSelected = loadImage("/rsc/HoneyThrowerButtonSelected.png");
			anthill = loadImage("/rsc/Anthill.png");
			gameOver = loadImage("/rsc/GameOver.png");
			gameOver1 = loadImage("/rsc/GameOver1.png");
			upgradeButton = loadImage("/rsc/upgradeButton.png");
			upgradeButtonSelected = loadImage("/rsc/upgradeButtonSelected.png");
			sellButtonSelected = loadImage("/rsc/sellButtonSelected.png");
			sellButton = loadImage("/rsc/sellButton.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}

	private static BufferedImage loadImage(String resourcePath) throws IOException {
		try (InputStream is = Bilder.class.getResourceAsStream(resourcePath)) {
			if (is == null) {
				throw new IOException("Missing resource: " + resourcePath);
			}
			return ImageIO.read(is);
		}
	}
	
	public static void rotate(BufferedImage i, double rot, Graphics2D g, int x, int y) {
		ImageIcon icon = new ImageIcon(i);
		BufferedImage blankCanvas = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
		g2d = g;
		g2d.rotate(Math.toRadians(rot), x + (icon.getIconWidth()/ 2), y + (icon.getIconHeight()/ 2));
		g2d.drawImage(i, x, y, icon.getIconWidth(), icon.getIconHeight(), null);
		g2d.rotate(Math.toRadians(-rot), x + (icon.getIconWidth()/ 2), y + (icon.getIconHeight()/ 2));
		i = blankCanvas;
	}
	
}
