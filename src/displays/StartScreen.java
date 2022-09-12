package displays;

import java.awt.Color;

import processing.core.PApplet;

public class StartScreen extends GeneralDisplay{

	private String img;
	
	public StartScreen(double x, double y, double width, double height, Color fillColor, String img) {
		super(x, y, width, height, fillColor);
		this.img = img;
		// TODO Auto-generated constructor stub
	}
	
	public void draw(PApplet papp) {
		papp.image(papp.loadImage(img),  this.getx(), this.gety(), (float)this.getwidth(), (float)this.getheight());
	}


}
