/**
 *	SimpleGraphics.java
 *	Paints a window with a target and a set of bricks
 *	To compile Linux:	javac -cp .:acm.jar SimpleGraphics.java
 *	To execute Linux:	java -cp .:acm.jar SimpleGraphics
 *	To compile MS Powershell:	javac -cp ".;acm.jar" SimpleGraphics.java
 *	To execute MS Powershell:	java -cp ".;acm.jar" SimpleGraphics
 *
 *	@author Michael Yeung
 *	@since	September 19, 2023
 */
 
/*	All package classes should be imported before the class definition.
 *	"java.awt.Color" means package java.awt contains class Color. */
import java.awt.Color;

/*	The following libraries are in the acm.jar file. */
import acm.program.GraphicsProgram;
import acm.graphics.GLabel;
import acm.graphics.GOval;
import acm.graphics.GPoint;
import acm.graphics.GPolygon;
import acm.graphics.GRect;
import acm.graphics.GRectangle;

public class SimpleGraphics extends GraphicsProgram {
	
	/*	All fields and constants should be declared here.
	 *	Only constants (final) are initialized here. */
	 
	private double startRadius; //radius of first circle, decreases
	private double startX; //x coordinate of first circle, moves right
	private double startY; //y coordinate of first circle, moves down
	private final double RADIUS_CHANGE=60; //radius difference between target circles
	private final double BRICK_WIDTH = 50; //width of each brick
	private final double CIRCLE_SPACING = 30; //how much to shift the x and y of circle
	private final double BRICK_HEIGHT = 20; //height of each brick
	private final double LEFTBOUND=340; //the most left bound brick
	private final int NUM_ROWS=10; //number of rows of bricks
	private double brickX; //x coordinate of first brick, moves right
	private double brickY; //y coordinate of first brick, moves down
	
	private GOval[] target; //oval array that make up target
	private GRect[] bricks; //rectangle array that makes up brick wall
	
	/**	The init() method is executed before the run() method.
	 *	All initialization steps should be performed here.
	 */
	public void init() {
		target = new GOval[5];
		bricks = new GRect[55];
		startRadius = 400;
		startX = 170;
		startY = 280;
		brickX = 420;
		brickY = 10*BRICK_HEIGHT;
	}
	
	/**	The run() method is executed after init(), uses library to draw the shapes */
	public void run() {
		//prints out the target
		for (int i = 0; i < target.length; i++) {
			target[i]=new GOval(startX, startY, startRadius, startRadius);
			target[i].setFilled(true);
			if (i % 2 == 0) target[i].setFillColor(Color.RED);
			else target[i].setFillColor(Color.WHITE);
			add(target[i]);
			startRadius-=RADIUS_CHANGE;
			startX+=CIRCLE_SPACING;
			startY+=CIRCLE_SPACING;
		}
		
		//prints out the bricks
		for (int i = 0; i <= NUM_ROWS; i++) {
			for (int j = 0; j < i; j++) {
				bricks[i+j] = new GRect(brickX, brickY, BRICK_WIDTH, BRICK_HEIGHT);
				brickX+=BRICK_WIDTH;
				add(bricks[i+j]);
			}
			brickX=(LEFTBOUND-i*(int)(BRICK_WIDTH/2));
			brickY-=BRICK_HEIGHT;
			
		}
	}
}
