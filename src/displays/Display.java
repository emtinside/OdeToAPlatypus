package displays;
import java.awt.Color;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.applet.Applet;
import java.applet.AudioClip;
import processing.core.PApplet;
import sprites.Sprite;
import sprites.Box;
import sprites.CD;
import sprites.Collectible;
import sprites.Crown;
import sprites.Duck;
import sprites.Ground;
import sprites.Letter;
import sprites.Photo;
import sprites.Platypus;

public class Display extends GeneralDisplay implements Runnable{
	
	private float groundLevel;
	
	private ArrayList<Sprite> sprites; 
	private Platypus platy;
	private Duck duck;
	private Ground ground;
	private Box box;
	private ArrayList<Collectible> memories, collectedMemories;
	private Letter letter;
	private Crown crown;
	private CD cd;
	private Photo photo;
	
	private int duckSpeakCount;
	
	@Override
	public void run() {

	}
	
	/**
	 * Creates a Display with specified colors
	 * @param x top left x
	 * @param y top left y
	 * @param width window width
	 * @param height window height 
	 * @param strokeWeight weight of the stroke to draw the displayed element
	 * @param strokeColor color of the stroke to draw the displayed element
	 * @param fillColor inner color of the displayed element
	 */
	public Display(double x, double y, double width, double height, Color fillColor) {
		super(x,y,width,height,fillColor);
		this.groundLevel = (float)(y + height - 50); 
		this.duckSpeakCount = 0;
		
		//All the interactable sprites (i.e boxes)
		this.sprites = new ArrayList<Sprite>();
		
		this.platy = new Platypus((float)(x+50),groundLevel-50,50,50,"images/platypusR.png"); //groundLevel - 50 because 50=height
		this.ground = new Ground(0, groundLevel, 700, 50, "images/ground.png");
		this.duck = new Duck(600, groundLevel-50, 50,50, "images/duck.png");
		sprites.add(duck);
		
		//Staircase
		this.box = new Box((float)(x+150), groundLevel-50, 50,50,"images/box.png");
		sprites.add(box);
		this.box = new Box((float)(x+200), groundLevel-100, 50,50,"images/box.png");	
		sprites.add(box);
		this.box = new Box((float)(x+300), groundLevel-150, 50,50,"images/box.png");	
		sprites.add(box);
		
		//Right platform
		this.box = new Box((float)(x+400), groundLevel-200, 50,50,"images/box.png");	
		sprites.add(box);
		
		this.box = new Box ((float)(x+550), groundLevel-200, 150,50, "images/3boxplatform150px.png");
		sprites.add(box);
		
		//Left platform
		this.box = new Box ((float)(x), groundLevel-300, 150,50, "images/3boxplatform150px.png");
		sprites.add(box);
		
		//Singular Box
		this.box = new Box((float)(x+325), groundLevel-375, 50,50,"images/box.png");	
		sprites.add(box);
		
		//Collectibles
		this.collectedMemories = new ArrayList<Collectible>();
		this.memories = new ArrayList<Collectible>();
		this.letter = new Letter((float)(x+335), groundLevel-400, 25,25,"images/letter.png", "A Letter"); //ON TOP OF SINGULAR BOX
		letter.setDescription("An unassuming letter\naddressed to Duck\n ???????");
		letter.addConsoleText("Who could that be?");
		letter.setInventoryLocation(0, 1);
		this.crown = new Crown((float)(x+480), groundLevel-250, 25,25,"images/crown.png","A Golden Crown"); //ON TOP OF THE RIGHT PLATFORM
		crown.setDescription("The date of coronation\n remains unknown...");
		crown.addConsoleText("Shiny!");
		crown.setInventoryLocation(0, 0);
		this.cd = new CD((float)(x+225), groundLevel-25, 25,25,"images/cd.png","A CD & Sleeve"); //LITTLE CORNER BETWEEN BOXES ON STAIRS
		cd.setDescription("1:43... \nAh. I misheard the lyric");
		cd.addConsoleText("What even is this... ");
		cd.setInventoryLocation(1, 0);
		this.photo = new Photo((float)(x+100), groundLevel-400, 25,25,"images/photo.png","A Lovely Photo"); //ON TOP OF LEFT PLATFORM
		photo.setDescription("A pretty afternoon view");
		photo.addConsoleText("I wonder where this was taken... ");
		photo.setInventoryLocation(1, 1);
			//LETTER
			memories.add(letter);
			//CROWN
			memories.add(crown);;
			//CD
			memories.add(cd);
			//PHOTO
			memories.add(photo);

	}
	
	public void draw(PApplet papp) throws InterruptedException {
		papp.background(this.getRed(),this.getGreen(),this.getBlue());
		ground.draw(papp, this);
		
		for(Sprite s: sprites) {
			s.draw(papp, this);
		}
		for(Collectible c: memories) {
			c.draw(papp, this);
		}
		
		continuousOnTopCheck();
		intersectingCollectibles();
		platy.moveYDirection();
		platy.draw(papp, this);
	}
	
	/**PLATYPUS METHODS**/
	public void continuousOnTopCheck() {
		if(platy.onTopOfCheck(sprites)) {
			try {
			Sprite onTopOf = platy.onTopOf(sprites).get(0);
			platy.setGround(onTopOf.gety());
			}
			catch (NullPointerException n) {
				System.out.println("Not on top of any sprites");
			}
		}
		else {
			platy.setGround(groundLevel);
		}
		
//		System.out.println("Ground check: " + platy.getGround());
	}
	public void intersectingDuck() throws InterruptedException {
		if(platy.intersects(this.duck)){
	
			if(duckSpeakCount != duck.START_TEXT) {
				if(memories.size() == 0 && duckSpeakCount != duck.ALMOST_THERE && duckSpeakCount != duck.FINAL_QUESTION) {
					duckSpeakCount = duck.ALL_EVIDENCE_FOUND_TEXT;
				}
				else if(memories.size() != 0){
					duckSpeakCount = duck.MISSING_EVIDENCE_TEXT;
				}
			}
			duck.talk(this);
			if(duckSpeakCount == duck.START_TEXT) {
				duckSpeakCount = duck.MISSING_EVIDENCE_TEXT;
			}
			
			
		}
	}
	public void intersectingCollectibles() {
		for (int i=0; i<memories.size(); i++) {
			if(platy.intersects(memories.get(i))) {
				memories.get(i).collect();
					System.out.println("You collected: "+memories.get(i).getName() +"\n");
				collectedMemories.add(memories.get(i));
				memories.remove(memories.get(i));
			}
		}

	}
	
	/**GET AND SET METHODS**/
	public Platypus getPlayer() {
		return platy;
	}
	public ArrayList<Sprite> getSprites(){
		return sprites;
	}
	public ArrayList<Collectible> getMemories() {
		return memories;
	}
	public Crown getCrown() {
		return crown;
	}
	public Letter getLetterToPlaty() {
		return letter;
	}
	public Photo getPhoto() {
		return photo;
	}
	public CD getCD() {
		return cd;
	}
	public ArrayList<Collectible> getCollectedMemories(){
		return collectedMemories;
	}
	public int getDuckSpeakCount() {
		return duckSpeakCount;
	}
	public void setDuckSpeakCount(int i) {
		duckSpeakCount =i;
	}
	public Duck getDuck() {
		return duck;
	}





}
