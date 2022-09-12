package displays;
import java.awt.Dimension;
import javax.swing.JFrame;
import processing.awt.PSurfaceAWT;
import processing.core.PApplet;

public class Main implements Runnable{
	/**
	 * Creates the window of the program and runs the program
	 * @param args default
	 */
	public static void main(String args[]) {

		DrawingSurface drawing = new DrawingSurface();
		PApplet.runSketch(new String[]{""}, drawing);
		PSurfaceAWT surf = (PSurfaceAWT) drawing.getSurface();

		PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
		JFrame window = (JFrame)canvas.getFrame();

		window.setSize(700, 500);
		window.setLocationRelativeTo(null);
		window.setMinimumSize(new Dimension(700, 500));
		window.setMaximumSize(new Dimension(700, 500));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);
		window.setTitle("An Ode to a Platypus");
		window.setVisible(true);
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}