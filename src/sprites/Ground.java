package sprites;

import displays.Display;
import processing.core.PApplet;

public class Ground extends Sprite{

	public Ground(float x, float y, double width, double height, String img) {
		super(x, y, width, height, img);
		// TODO Auto-generated constructor stub
	}
	
	public void draw(PApplet papp, Display d) {
		papp.push();
		papp.fill(162, 191, 147);
		papp.stroke(162, 191, 147);
		papp.rect(getx(), gety(), getWindowWidth(), getWindowWidth());
		papp.pop();
	}

}
