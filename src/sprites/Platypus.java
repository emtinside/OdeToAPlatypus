package sprites;

import java.util.ArrayList;

public class Platypus extends Sprite {

	private double yVelocity, xVelocity, gravity;
	private boolean direction; //left = false right = true
	private float thisBotY; //Bottom of Platy
	
	public Platypus(float x, float y, double width, double height, String img) {
		super(x, y, width, height, img);
		// TODO Auto-generated constructor stub
		this.yVelocity = 0;
		this.xVelocity = 0;
		this.gravity = 2;
		thisBotY = (float)(this.gety()+this.getheight());
		direction = true;
	
	}
	
	/**BASIC MOVEMENT METHODS**/
	public void moveYDirection() {
		if(this.gety() >= 0) {
		this.sety((float)(this.gety()-yVelocity));
		this.thisBotY = (float)(this.gety() + this.getheight());
		gravity();
		}
		else {
			this.sety(0);
		}
	}
	public void jump(double jumpVal) {
		yVelocity += jumpVal;
	}
	public void moveXDirection(double xVelocity) {
		if(this.getx() >= 0 && (float)(this.getx()+this.getwidth()) <= this.getWindowWidth()) //Check for window range
			this.setx((float)(this.getx()+xVelocity));
		else if ((float)(this.getx()+this.getwidth()) > this.getWindowWidth()) { //extends past the farthest width boundary
			xVelocity = 0;
			this.setx((float)(this.getWindowWidth()-50));
		}
		else if (this.getx() < 0) { //extends past the lower width boundary
			xVelocity = 0;
			this.setx((float)(10));
		}
	}
	
	/**INTERSECTION METHODS**/
	public boolean isOnTop(Sprite s) {
		float spriteLeftX = s.getx();
		float spriteRightX = (float)(s.getx() + s.getwidth());
		float spriteTopY = s.gety();
		
		float bodyStartIfLeft = (float)(this.getx());
		float bodyEndIfLeft = (float)(this.getx()+ this.getwidth()*(2/5));
		float bodyStartIfRight = (float)(this.getx() + this.getwidth()); 
		float bodyEndIfRight = (float)(this.getx() +  this.getwidth()/4);
		
		//Interval is 10 b/c when it was 5 it didn't always work
		if (direction) { //facing towards the right
			if (bodyStartIfRight >= spriteLeftX && bodyEndIfRight <= spriteRightX) {
				if (thisBotY >= spriteTopY - 10 && thisBotY <= spriteTopY + 10) { //in between a small Y range above and below the top of the sprite
//					System.out.println("Within sprite Y range");
					this.setGround(spriteTopY);
					return true; //is onTop
				}
			}
		}
		else { //facing towards the left
			if (bodyStartIfLeft <= spriteRightX && bodyEndIfLeft >= spriteLeftX) {
				if (thisBotY >= spriteTopY - 10 && thisBotY <= spriteTopY + 10) { //in between a small Y range above and below the top of the sprite
//					System.out.println("Within sprite Y range");
					this.setGround(spriteTopY);
					return true; //is onTop
				}
			}
		}
		return false; //not onTop
	}
	public ArrayList<Sprite> onTopOf(ArrayList<Sprite> boardSprites){
		ArrayList<Sprite> onTopOf = new ArrayList<Sprite>();
		for(int i=0; i<boardSprites.size(); i++) {
			if(this.isOnTop(boardSprites.get(i))) {
				onTopOf.add(boardSprites.get(i));
			}
		}
		return onTopOf; //Can return a null array, must check before running methods on the array when used in other classes
	}
	public boolean onTopOfCheck(ArrayList<Sprite> boardSprites){
		for(int i=0; i<boardSprites.size(); i++) {
			if(this.isOnTop(boardSprites.get(i))) {
				return true;
			}
		}
		return false; 
	}
	public boolean intersects(Sprite s) {
		float spriteLeftX = s.getx();
		float spriteRightX = (float)(s.getx() + s.getwidth());
		float spriteTopY = s.gety();
		float spriteBotY = (float)(s.gety()+s.getheight());
		float thisMidY = (float)(this.gety()+this.getheight()/2);
		float thisBotY = (float)(this.gety()+this.getheight());
		float thisMidX = (float)(this.getx()+this.getwidth()/2);
		
		if((this.gety() >= spriteTopY&& this.gety() <= spriteBotY)||(thisMidY >= spriteTopY && thisMidY <= spriteBotY) || (thisBotY >= spriteTopY && thisBotY <= spriteBotY)){
			if(thisMidX >= spriteLeftX && thisMidX <= spriteRightX) 
				return true;
		}

		return false;
	}
	
	
	/**GRAVITY METHODS**/
	private void gravity() {
		if (this.thisBotY < this.getGround())
			yVelocity -= gravity;
		else if (this.thisBotY > this.getGround()) {
			yVelocity = 0;
			this.sety((float)(this.getGround()- this.getheight()));
		}
//		System.out.println("YVelocity: " + yVelocity);
	}
	
	/**GET AND SETS METHODS**/
	public boolean getDirection() {
		return direction;
	}
	public void setDirection(boolean dir) {
		this.direction = dir;
	}

}
