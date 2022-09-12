package sprites;

import displays.Display;
import processing.core.PApplet;

public class Sprite {

		private float x, y;
		private double width, height;
		private String img;
		private float groundLevel;
		
		private static float windowWidth, windowHeight;

		public Sprite(float x, float y, double width, double height, String img){
			this.x = x;
			this.y = y;
			this.height = height;
			this.width = width;
			this.img = img;
			this.groundLevel = (float)(430);
//			System.out.println(img + " GroundLevel: " + groundLevel);
			
			windowWidth = 700;
			windowHeight = 480;
		}
	
		public void draw(PApplet papp, Display d) {
			papp.image(papp.loadImage(img),  this.x, this.y, (float)this.getwidth(), (float)this.getheight());
		}
	
		
		// GET METHODS
		public String getImage() {
			return img;
		}
		public String getType() {
			try {
				return img.substring(7, img.indexOf('.'));
			}
			catch(NullPointerException n) {
				System.out.println("type is null");
				return "no type";
			}
		}
		public double getwidth() {
			return width;
		}
		public double getheight() {
			return height;
		}
		public float getx() {
			return x;
		}
		public float gety() {
			return y;
		}
		public float getGround() {
			return groundLevel;
		}
		public float getWindowWidth() {
			return windowWidth;
		}
		public float getWindowHeight() {
			return windowHeight;
		}
		
		// SET METHODS
		public void setimg(String img) {
			this.img = img;
		}
		public void setx(float x) {
			this.x = x;
		}
		public void sety(float y) {
			this.y = y;
		}
		public void setwidth(double width) {
			this.width = width;
		}
		public void setheight(double height) {
			this.height = height;
		}
		public void setGround(float ground) {
			groundLevel = ground;
		}
		public void setWindowWidth(float width) {
			windowWidth = width;
		}
		public void setWindowHeight(float height) {
			windowHeight = height;
		}
}

