package displays;
import java.awt.Color;
import java.awt.event.KeyEvent;
//import processing.sound.*;
import java.util.ArrayList;

import processing.core.PApplet;
import sprites.Collectible;

public class DrawingSurface extends PApplet{

	private EndScreen end;
	private RulesScreen rules;
	private Display game;
	private StartScreen start;
	private InventoryGUI inventory;
	private boolean right, left, inventoryOpen, gameOn, startScreen, rulesScreen, endScreen;
	private ArrayList<Collectible> memories, collectedMemories;
	private Collectible theClickedCollectible;
	
	private boolean drawCrownText, drawLetterToPlatyText, drawCDText, drawPhotoText;
//	private SoundFile menuScreenSound;
	
	/**
	 * Creates a DrawingSurface
	 */
	public DrawingSurface() {
		
		this.game = new Display(0,0,700,480, new Color(203, 227, 242));
		new Thread(game).start();
		
		this.end = new EndScreen(0,0,700,480,Color.WHITE, "images/endScreen.png");
		this.start = new StartScreen(0,0,700,480,Color.WHITE,"images/title-screen.png");
		this.rules = new RulesScreen(0,0,700,480,Color.WHITE,"images/rulesScreen.png");
		
		this.inventory = new InventoryGUI(25,40,650,400,Color.white,"images/inventorylarger.png", memories);
		new Thread(inventory).start();
		
		memories = game.getMemories();
		collectedMemories = game.getCollectedMemories();
		right = false;
		left = false;
		inventoryOpen = false;
		gameOn = false;
		startScreen = true;
		rulesScreen = false;
		endScreen = false;
		drawCrownText = false;
		drawLetterToPlatyText= false;
		drawCDText = false;
		drawPhotoText = false;
		theClickedCollectible = new Collectible(375, 100, 150, 150, "no image", "null");
	}
	public void setup() {
		frameRate(30);
		
	}
	/**
	 * Draws the components of the program
	 * @pre this method runs with the conditions previously set on the given PApplet
	 */
	public void draw() {
		if(startScreen) {
			start.draw(this);
		}
		if(rulesScreen) {
			rules.draw(this);
		}
		
		if(gameOn) {
			try {
				if(game.getDuck().getEndOfGameStatus()) {
					gameOn = false;
					endScreen = true;
				}
				else {
					game.draw(this);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(endScreen) {
			end.draw(this);
		}
		if(inventoryOpen) {
			this.push();
			this.color(255);
			this.textSize(18);
			collectedMemories = game.getCollectedMemories();
			inventory.setCollectibles(collectedMemories);
			inventory.setBoardCollectibles(memories);
			inventory.draw(this);
			if(drawCrownText) {
				theClickedCollectible.draw(this, game);
				this.text(game.getCrown().getDescription(), 350, 360);
			}
			else if(drawLetterToPlatyText) {
				theClickedCollectible.draw(this, game);
				this.text(game.getLetterToPlaty().getDescription(), 350, 360);
			}
			else if(drawCDText) {
				theClickedCollectible.draw(this, game);
				this.text(game.getCD().getDescription(), 350, 360);
			}
			else if(drawPhotoText) {
				theClickedCollectible.draw(this, game);
				this.text(game.getPhoto().getDescription(), 350, 360);
			}
			this.pop();
		}
		
		if (right == true)
			game.getPlayer().moveXDirection(10);
		if (left == true)
			game.getPlayer().moveXDirection(-10);
	}
	
	/**
	 * Updates the menu and map based on the user clicking on certain keys
	 */
	public void keyPressed() {
		if (gameOn) {
			if (keyCode == KeyEvent.VK_LEFT){
				game.getPlayer().setimg("images/platypusL.png");
				left = true;
			}
			if (keyCode == KeyEvent.VK_RIGHT){
				game.getPlayer().setimg("images/platypusR.png");
				right = true;
			}
			// jump
			if (keyCode == KeyEvent.VK_SPACE) {
			}
			// open inventory
			if (keyCode == KeyEvent.VK_I) {
				if (inventoryOpen)
					inventoryOpen = false;
				else
					inventoryOpen = true;			
			}
			if (keyCode == KeyEvent.VK_T) {
				try {
					game.intersectingDuck();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}
	public void keyReleased() {
		if (gameOn) {
			if (keyCode == KeyEvent.VK_LEFT){
				left = false;
			}
			if (keyCode == KeyEvent.VK_RIGHT){
				right = false;
			}
			if (keyCode == KeyEvent.VK_SPACE){
				game.getPlayer().jump(15);
	
			}
		}
	}
	
	public void mousePressed() {
//		System.out.println("MouseX: " + this.mouseX + " MouseY: " + this.mouseY);
		if(startScreen) {
			if(this.mouseX >= 270 && this.mouseX <= 410) {
				if(this.mouseY >= 310 && this.mouseY <= 380) {
					startScreen = false;
					gameOn = true;
				}
				else if(this.mouseY >= 380 && this.mouseY <= 450) {
					rulesScreen = true;
					startScreen = false;
				}
			}
		}
		else if (rulesScreen) {
			if (this.mouseX >= 20 && this.mouseX <= 55) {
				if(this.mouseY >=20 && this.mouseY<= 55) {
					rulesScreen = false;
					startScreen = true;
				}
			}
		}
		else if(inventoryOpen) {
			if(this.mouseX >= 625 && this.mouseX <= 650) {
				if(this.mouseY >= 60 && this.mouseY <= 380)
					inventoryOpen = false;
			}
		//IN THE FIRST ROW
			if(this.mouseY >= 65 && this.mouseY <= 145) {
				if(this.mouseX >= 45 && this.mouseX <= 145) {
					if(game.getCollectedMemories().contains(game.getCrown()) && !drawCrownText) {
//						System.out.println(game.getCrown().getDescription());
						drawCDText = false;
						drawLetterToPlatyText = false;
						drawCDText = false;
						drawCrownText = true;
						theClickedCollectible.setimg(game.getCrown().getImage());
						game.getCrown().printConsoleText();
					}
					else
						drawCrownText = false;
				}
				else if(this.mouseX >= 160 && this.mouseX <= 265) {
					if(game.getCollectedMemories().contains(game.getLetterToPlaty()) && !drawLetterToPlatyText) {
//						System.out.println(game.getLetterToPlaty().getDescription());
						drawCrownText = false;
						drawCDText = false;
						drawPhotoText = false;
						drawLetterToPlatyText = true;
						theClickedCollectible.setimg(game.getLetterToPlaty().getImage());
						game.getLetterToPlaty().printConsoleText();
					}
					else 
						drawLetterToPlatyText = false;
				}	
			}
	//IN THE SECOND ROW
		else if(this.mouseY>=160 && this.mouseY <= 260) {
			if(this.mouseX >= 45 && this.mouseX <= 145) {
				if(game.getCollectedMemories().contains(game.getCD()) && !drawCDText) {
//					System.out.println(game.getCrown().getDescription());
					drawCDText = true;
					drawLetterToPlatyText = false;
					drawCrownText = false;
					drawPhotoText = false;
					theClickedCollectible.setimg(game.getCD().getImage());
					game.getCD().printConsoleText();
				}
				else
					drawCDText = false;
			}
			else if(this.mouseX >= 160 && this.mouseX <= 265) {
				if(game.getCollectedMemories().contains(game.getPhoto()) && !drawPhotoText) {
//					System.out.println(game.getLetterToPlaty().getDescription());
					drawLetterToPlatyText = false;
					drawCrownText = false;
					drawCDText = false;
					drawPhotoText = true;
					theClickedCollectible.setimg(game.getPhoto().getImage());
					game.getPhoto().printConsoleText();
				}
				else 
					drawPhotoText = false;
			}
		}
	}
}
}
	
	/**MUSIC METHODS**/
