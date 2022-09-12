package sprites;

import java.util.ArrayList;

public class Collectible extends Sprite{

	private boolean isCollected;
	private String name, description;
	private ArrayList<String> consoleText;
	private Location inventoryLocation;
	private boolean isClicked;
	
	public Collectible(float x, float y, double width, double height, String img, String name) {
		super(x, y, width, height, img);
		isCollected = false;
		isClicked = false;
		this.name = name;
		this.description ="";
		this.consoleText = new ArrayList<String>();
		this.inventoryLocation = new Location(0,0);
		// TODO Auto-generated constructor stub
	}
	
	//SET METHODS
	public void click() {
		isClicked = true;
	}
	public void unclick() {
		isClicked = false;
	}
	public void setInventoryLocation(int row, int col) {
		inventoryLocation = new Location(row, col);
	}
	public void collect() {
		isCollected = true;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDescription(String des) {
		this.description = des;
	}
	public void addConsoleText(String s) {
		consoleText.add(s);
	}
	public void addConsoleTextToIndex(String s, int index) {
		consoleText.add(index, s);
	}
	public void removeConsoleText(String s) {
		consoleText.remove(s);
	}
	public void removeConsoleText(int index) {
		consoleText.remove(index);
	}
	
	
	//GET METHODS
	public boolean isClicked() {
		return isClicked;
	}
	public String getName() {
		return name;
	}
	public boolean getCollectState() {
		return isCollected;
	}
	public String getDescription() {
		return description;
	}
	public ArrayList<String> getConsoleText(){
		return consoleText;
	}
	public void printConsoleText() {
		for (String s: consoleText) {
			System.out.println(s);
		}
		System.out.println();
	}

	
	//LOCATION
	public void placeInLocation() {
		this.setwidth(50);
		this.setheight(50);
		if (inventoryLocation.getRow()==0) {
			this.sety(80);
			if (inventoryLocation.getCol()==0) {
				this.setx(70);
			}
			else if (inventoryLocation.getCol()==1) {
				this.setx(190);
			}
		}
		else if (inventoryLocation.getRow()==1) {
			this.sety(190);
			if (inventoryLocation.getCol()==0) {
				this.setx(70);
			}
			else if (inventoryLocation.getCol()==1) {
				this.setx(190);
			}
		}
		else if (inventoryLocation.getRow()==2) {
			this.sety(300);
			if (inventoryLocation.getCol()==0) {
				this.setx(70);
			}
			else if (inventoryLocation.getCol()==1) {
				this.setx(190);
			}
		}
	}
	public int getInventoryRow() {
		return inventoryLocation.getRow();
	}
	public int getInventoryCol() {
		return inventoryLocation.getCol();
	}

	private class Location{
		private int col, row;
		protected Location(int row, int col) {
			this.col= col;
			this.row= row;
		}
		protected int getRow() {
			return row;
		}
		protected int getCol() {
			return col;
		}
	}
	
}
