package displays;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import processing.core.PApplet;
import sprites.Collectible;

public class InventoryGUI extends GeneralDisplay implements Runnable{

	private ArrayList<Collectible> memories, allMemories;
	private String img;
	
	public InventoryGUI(double x, double y, double width, double height, Color fillColor, String img, ArrayList<Collectible> allMemories) {
		super(x, y, width, height, fillColor);
		this.memories = new ArrayList<Collectible>(); 
		this.allMemories = allMemories;
		this.img = img;
		
		// TODO Auto-generated constructor stub
	}
	public void displayCollectibleClicked(ArrayList<Collectible> memories) {
		for (Collectible c: memories) {
			if(c.isClicked()) {
				
			}
		}
	}
	public void placeCollectibles() {
		for (Collectible c: memories) {
			c.placeInLocation();
		}
	}
	public void setBoardCollectibles(ArrayList<Collectible> collect) {
		allMemories = collect;
	}
	public void setCollectibles(ArrayList<Collectible> collectibles) {
		memories = collectibles;
	}
	public void addCollectibles(Collectible c) {
		memories.add(c);
	}
	public void removeCollectibles(Collectible c) {
		memories.remove(c);
	}

	public void draw(PApplet papp) {
		papp.image(papp.loadImage(img),  this.getx(), this.gety(), (float)this.getwidth(), (float)this.getheight());
		for(Collectible c: memories) {
			if(c.getCollectState()) {
				c.placeInLocation();
				drawCollectible(papp, c);
			}
		}
	}
	
	private void drawCollectible(PApplet papp, Collectible c) {
		papp.image(papp.loadImage(c.getImage()),  c.getx(), c.gety(), (float)c.getwidth(), (float)c.getheight());
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}

