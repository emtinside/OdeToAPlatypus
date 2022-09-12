package displays;

import java.awt.Color;

import processing.core.PApplet;

public class GeneralDisplay {
	private double x, y, width, height;
	private Color fillColor;
	private int r,g,b;
	
	public GeneralDisplay(double x, double y, double width, double height, Color fillColor) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.fillColor = fillColor;
		this.r = fillColor.getRed();
		this.g = fillColor.getGreen();
		this.b = fillColor.getBlue();
		
	}
	
	public void draw(PApplet papp) throws InterruptedException {
		papp.background(r,g,b);
	}
	
	public double getwidth() {
		return width;
	}
	public double getheight() {
		return height;
	}
	public float getx() {
		return (float)x;
	}
	public float gety() {
		return (float)y;
	}
	public int getRed() {
		return r;
	}
	public int getBlue() {
		return b;
	}
	public int getGreen() {
		return g;
	}

}
