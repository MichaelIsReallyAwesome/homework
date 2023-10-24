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
	private GRect rect; //The size changing rectangle
	private GOval circle; //The bouncing ball
	private GPolygon arrow; //The bouncing arrow
	private final double ARROW_HEAD_WIDTH = 20; //The width of the arrow head
	private final double ARROW_HEAD_HEIGHT = 40; //The height of the arrow head
	private final double ARROW_BASE_WIDTH = 22; //The width of the arrow hilt
	private final double ARROW_BASE_HEIGHT = 14; //The height of the arrow hilt
	private final int ARROW_X = 150; //The initial x position of the arrow
	private final int ARROW_Y = 250; //The initial y position of the arrow
	private double arrowVelocityX; // The x velocity of the arrow
	private double arrowVelocityY; // The y velocity of the arrow

	//The coordinates of arrow shape
	private final GPoint[] ARROW_SHAPE = {
			new GPoint(0, (ARROW_HEAD_HEIGHT - ARROW_BASE_HEIGHT) / 2),
			new GPoint(ARROW_BASE_WIDTH, (ARROW_HEAD_HEIGHT - ARROW_BASE_HEIGHT) / 2),
			new GPoint(ARROW_BASE_WIDTH, 0),
			new GPoint(ARROW_BASE_WIDTH + ARROW_HEAD_WIDTH, ARROW_HEAD_HEIGHT / 2),
			new GPoint(ARROW_BASE_WIDTH, ARROW_HEAD_HEIGHT),
			new GPoint(ARROW_BASE_WIDTH, (ARROW_HEAD_HEIGHT + ARROW_BASE_HEIGHT) / 2),
			new GPoint(0, (ARROW_HEAD_HEIGHT + ARROW_BASE_HEIGHT) / 2),
			new GPoint(0, (ARROW_HEAD_HEIGHT - ARROW_BASE_HEIGHT) / 2)
	};

	private final int BALL_RADIUS = 40; //The radius of the ball
	private double ballVelocityX; // The x velocity of the ball
	private double ballVelocityY; // The y velocity of the ball
	private double rectExpand; // The rate the rectangle expands
	private boolean moving; // If the animation is playing
	private final double DELAY = 5; // The delay between frames
	private final double FRAME_WIDTH = getWidth();
	private final double FRAME_HEIGHT = getHeight();

	/** Initializes the fields and adds them to the frame in the program */
	public void init() {
		moving = false;
		arrowVelocityX = -3;
		arrowVelocityY = -3;
		ballVelocityX = 2;
		ballVelocityY = 2;
		rectExpand = 1;

		rect = new GRect(400, 200, 50, 50);
		rect.setFillColor(Color.GREEN);
		rect.setFilled(true);
		add(rect);

		circle = new GOval(40, 90, BALL_RADIUS, BALL_RADIUS);
		circle.setFillColor(Color.RED);
		circle.setFilled(true);
		add(circle);

		arrow = new GPolygon(ARROW_SHAPE);
		arrow.setFillColor(Color.BLUE);
		arrow.setFilled(true);
		arrow.setLocation(70, 60);
		add(arrow);
		addMouseListeners();
	}

	/**
	 * Runs the animation loop
	 */
	public void run() {
		System.out.println("running");
		while (true) {
			if (moving) {
				if (circle.getX() <= 0 || circle.getX()+BALL_RADIUS >= getWidth())
					ballVelocityX *= -1;
				if (circle.getY() <= 0 || circle.getY()+BALL_RADIUS >= getHeight())
					ballVelocityY *= -1;
				if (arrow.getX() <= 0 || arrow.getX()+BALL_RADIUS >= getWidth())
					arrowVelocityX *= -1;
				if (arrow.getY() <= 0 || arrow.getY()+BALL_RADIUS >= getHeight())
					arrowVelocityY *= -1;
				circle.move(ballVelocityX, ballVelocityY);
				arrow.move(arrowVelocityX, arrowVelocityY);
				rect.setSize(rect.getWidth() + rectExpand, rect.getHeight() + rectExpand);
				rect.move(-rectExpand / 2, -rectExpand / 2);
				if (rect.getWidth() > 90 || rect.getWidth() < 40)
					rectExpand = -rectExpand;

			}
			pause(DELAY);
		}
	}
	/**
	 * Pauses and starts animation based on mouse click
	 * @param MouseEvent, not used but contains information about the mouse
	 */
	public void mouseClicked(MouseEvent e) {
		moving = !moving;
	}
}
