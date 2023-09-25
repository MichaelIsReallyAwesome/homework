import java.awt.Color;
import acm.program.GraphicsProgram;
import acm.graphics.GLabel;
import acm.graphics.GOval;
import acm.graphics.GPoint;
import acm.graphics.GPolygon;
import acm.graphics.GRect;
import acm.graphics.GRectangle;
import java.awt.event.MouseEvent;

/**
 * MotionGraphics.java
 * Paints an animated window with an expanding square, 
 * moving circle and moving arrow
 * @author Michael Yeung
 * @since September 21, 2023
 */
 
public class MotionGraphics extends GraphicsProgram {
	private GRect rect;
	private GOval oval;
	private GPolygon arrow;
	private boolean movementPaused;
	private int rectX, rectY, rectWidth, rectHeight;
	
	/** Initializes the fields in the program*/
	public void init() {
		addMouseListeners();
		rect=new GRect(300, 300, 80, 80);
		add(rect);
		movementPaused=false;
	}
	
	/** Changes the size/location of shapes through timers */
	public void run() {
		while (movementPaused) {
			
		}
	}
	/** Pauses shapes when mouse is clicked 
	 * @param information on the mouse event that happened
	 */
	public void mouseClicked(MouseEvent e) {
		if (movementPaused) movementPaused=false;
		else movementPaused=true;
	}
}
