
/* 
 * File Name: Breakout.java
 * Author: Scott Reese
 * Section Leader: Anand Shankar
 * Date:7/23/17
 * Description:
 * This program outputs the traditional game called "Breakout where 
 * graphics rectangles known as bricks, a ball and paddle are used 
 * to "break", or remove, all available rectangles within 3 turns. 
 * A turn is lost when a ball hits the bottom of the canvas.
 */

import acm.graphics.*; // GOval, GRect, etc.
import acm.program.*; // GraphicsProgram
import acm.util.*; // RandomGenerator
import java.awt.*; // Color
import java.awt.event.*; // MouseEvent

public class Breakout extends BreakoutProgram {

	// PADDLE is used for creating the movable paddle.
	// Justification: used with the event handler mouseMoved
	private GRect PADDLE;
	// VELOCITY_X & PVELOCITY_Y are the velocities in the X & Y directions.
	// Justification: these are regularly changed throughout the program.
	private double VELOCITY_X;
	private double PVELOCITY_Y;

	public void run() {
		int score = 0;
		int turns = NTURNS;
		setTitle("CS 106A Breakout");
		setCanvasSize(CANVAS_WIDTH, CANVAS_HEIGHT);
		GLabel scoreLabel = addLabelInfo(score, turns);
		int brickCount = placeBricks();
		placePaddle();
		GOval ball = placeBall();
		turns = moveBall(brickCount, score, turns, scoreLabel, ball);
		winOrLose(turns, ball);
	}

	// addLabelInfo() adds the game information by displaying score total and
	// number of turns left.

	private GLabel addLabelInfo(int score, int turns) {
		GLabel scoreLabel = new GLabel("Score: " + score + ", Turns: " + turns);
		scoreLabel.setFont(SCREEN_FONT);
		add(scoreLabel, 0, scoreLabel.getHeight());
		return scoreLabel;
	}

	// mouseMoved tracks the movement of the mouse and moves the paddle
	// accordingly only in the x-axis. It also constrains the paddle from moving
	// off the canvas.

	public void mouseMoved(MouseEvent event) {
		double mouseX = event.getX();
		double canvasWidth = getWidth();
		double mouseXMin = PADDLE_WIDTH / 2;
		double mouseXMaxLoc = canvasWidth - PADDLE_WIDTH;
		double mouseXMaxMove = canvasWidth - PADDLE_WIDTH / 2;
		double canvasHeight = getHeight();
		double x = 0.0;
		if (mouseX >= mouseXMaxMove) {
			x = mouseXMaxLoc;
		} else if (mouseX <= mouseXMin) {
			x = 0.0;
		} else {
			x = mouseX - PADDLE_WIDTH / 2;
		}
		double y = canvasHeight - PADDLE_Y_OFFSET - PADDLE_HEIGHT;
		PADDLE.setLocation(x, y);
	}

	// placeBricks() places the rows & columns of the upper bricks.

	private int placeBricks() {
		int brickCount = 0;
		double canvasWidth = getWidth();
		double xOffset = (canvasWidth - (NBRICK_COLUMNS * BRICK_WIDTH + BRICK_SEP * (NBRICK_COLUMNS - 1))) / 2;
		for (int row = 0; row < NBRICK_ROWS; row++) {
			for (int col = 0; col < NBRICK_COLUMNS; col++) {
				double x = 0;
				if (col > 0) {
					x = col * (BRICK_WIDTH + BRICK_SEP) + xOffset;
				} else {
					x = xOffset;
				}
				double y = row * (BRICK_HEIGHT + BRICK_SEP) + BRICK_Y_OFFSET;
				GRect brick = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
				determineColor(row, brick);
				brick.setFilled(true);
				brick.setColor(Color.WHITE);
				add(brick);
				brickCount++;
			}
		}
		return brickCount;
	}

	// determineColor() colors the bricks to a set pattern.

	private void determineColor(int row, GRect brick) {
		row = row % 10;
		if (row == 0 || row == 1) {
			brick.setFillColor(Color.RED);
		} else if (row == 2 || row == 3) {
			brick.setFillColor(Color.ORANGE);
		} else if (row == 4 || row == 5) {
			brick.setFillColor(Color.YELLOW);
		} else if (row == 6 || row == 7) {
			brick.setFillColor(Color.GREEN);
		} else if (row == 8 || row == 9) {
			brick.setFillColor(Color.CYAN);
		}

	}

	// placePaddle() adds a paddle towards the bottom middle of the screen.

	private void placePaddle() {
		double canvasWidth = getWidth();
		double canvasHeight = getHeight();
		double x = canvasWidth / 2 - PADDLE_WIDTH / 2;
		double y = canvasHeight - PADDLE_Y_OFFSET - PADDLE_HEIGHT;
		PADDLE = new GRect(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
		PADDLE.setFilled(true);
		PADDLE.setColor(Color.BLACK);
		add(PADDLE);
	}

	// placeBall() adds a ball in the middle of the screen.

	private GOval placeBall() {
		double x = getWidth() / 2 - BALL_RADIUS;
		double y = getHeight() / 2 - BALL_RADIUS;
		GOval ball = new GOval(x, y, BALL_RADIUS * 2, BALL_RADIUS * 2);
		ball.setFilled(true);
		ball.setColor(Color.BLACK);
		add(ball);
		return ball;
	}

	// moveBall() begins by moving the ball in a random X velocity with a Y
	// velocity component. It then evaluates if there are collisions and
	// respectively bounces or removes a brick.

	private int moveBall(int brickCount, int score, int turns, GLabel scoreLabel, GOval ball) {
		VELOCITY_X = randomXVelocity(VELOCITY_X_MIN, VELOCITY_X_MAX);
		PVELOCITY_Y = VELOCITY_Y;
		boolean errorProofTest = false;
		int counter = 0;
		while (turns != 0 && brickCount != 0) {
			wallBounce(ball);
			GObject collider = getCollidingObject(ball);
			brickCount = isBrickForBrickCounter(collider, brickCount, scoreLabel);
			score = isBrickForScore(collider, score, turns, scoreLabel);
			errorProofTest = isPaddle(collider, counter);
			counter = counter(errorProofTest, counter);
			ball.move(VELOCITY_X, PVELOCITY_Y);
			pause(DELAY);
			turns = checkIfBallHitBottom(score, turns, scoreLabel, ball);
		}
		return turns;
	}

	// checkIfBallHitBottom checks the balls position to the bottom of the
	// canvas, removes the ball if it is outside the boundary, tracks the
	// number of remaining turns and determines if it'll replace the ball.

	private int checkIfBallHitBottom(int score, int turns, GLabel scoreLabel, GOval ball) {
		double y = ball.getY();
		double cornerY = 2 * BALL_RADIUS + y;
		boolean removedBall = false;
		double canvasHeight = getHeight();
		if (cornerY > canvasHeight) {
			turns--;
			scoreLabel.setText("Score: " + score + ", Turns: " + turns);
			removedBall = true;
		}
		if ((turns != 0) && removedBall) {
			double moveX = getWidth() / 2 - BALL_RADIUS;
			double moveY = getHeight() / 2 - BALL_RADIUS;
			ball.setLocation(moveX, moveY);
			VELOCITY_X = randomXVelocity(VELOCITY_X_MIN, VELOCITY_X_MAX);
		} else if (turns == 0) {
			remove(ball);
		}
		return turns;
	}

	// randomXVelocity determines randomly a x velocity from a given range, as
	// well it randomly determines its direction.

	private double randomXVelocity(double xMin, double xMax) {
		RandomGenerator velocity = RandomGenerator.getInstance();
		RandomGenerator direction = RandomGenerator.getInstance();
		double xVelocity = velocity.nextDouble(xMin, xMax);
		if (direction.nextBoolean() == false) {
			xVelocity = -xVelocity;
		}
		return xVelocity;
	}

	// wallBounce() bounces the ball off the wall reverse its X or Y velocity
	// respectively. If the location in which this checks has no walls it does
	// nothing.

	private void wallBounce(GOval ball) {
		double canvasWidth = getWidth();
		double x = ball.getX();
		double y = ball.getY();
		double cornerX = 2 * BALL_RADIUS + x;

		if (y <= 0) {
			PVELOCITY_Y = -PVELOCITY_Y;
		}
		if (cornerX >= canvasWidth || x <= 0) {
			VELOCITY_X = -VELOCITY_X;
		}
	}

	// getCollidingObject evaluates if the ball is in contact with anything or
	// nothing.

	private GObject getCollidingObject(GOval ball) {
		double x = ball.getX();
		double y = ball.getY();
		double cornerX = 2 * BALL_RADIUS + x;
		double cornerY = 2 * BALL_RADIUS + y;

		if (getElementAt(x, y) != null) {
			return getElementAt(x, y);
		} else if (getElementAt(cornerX, y) != null) {
			return getElementAt(cornerX, y);
		} else if (getElementAt(x, cornerY) != null) {
			return getElementAt(x, cornerY);
		} else if (getElementAt(cornerX, cornerY) != null) {
			return getElementAt(cornerX, cornerY);
		} else {
			return null;
		}
	}

	// isPaddle determines if the ball collided in with a paddle and returns a
	// special boolean that assists with preventing the ball from getting
	// "stuck" on the paddle.

	private boolean isPaddle(GObject collider, int counter) {
		if ((collider == PADDLE) && (counter == 0)) {
			PVELOCITY_Y = -PVELOCITY_Y;
			return true;
		} else if ((collider == PADDLE) && counter != 0) {
			return true;
		}
		return false;
	}

	// isBrick() determines if the ball hit a brick and decrements the total
	// brick count by one.

	private int isBrickForBrickCounter(GObject collider, int brickCount, GLabel scoreLabel) {
		if (collider != null && collider != scoreLabel && collider != PADDLE) {
			remove(collider);
			brickCount--;
			PVELOCITY_Y = -PVELOCITY_Y;
		}
		return brickCount;
	}

	// isBrick2() determines if the ball hit a brick and increments the score up
	// one point.

	private int isBrickForScore(GObject collider, int score, int turns, GLabel scoreLabel) {
		if (collider != null && collider != scoreLabel && collider != PADDLE) {
			score++;
			scoreLabel.setText("Score: " + score + ", Turns: " + turns);
		}
		return score;
	}

	// counter() works to also prevent the ball from getting stuck on the paddle
	// by giving it 20 moves before being reset to zero after hitting the
	// paddle.

	private int counter(boolean errorProofTest, int counter) {
		if (errorProofTest && counter == 0) {
			counter = 20;
			return counter;
		} else if (counter < 21 && counter != 0) {
			counter--;
			return counter;
		} else {
			return counter = 0;
		}
	}

	// winOrLose() determines if the player wins or loses based upon their
	// brickCount remaining and prints a respective GLabel.

	private void winOrLose(int turns, GOval ball) {
		if (turns == 0) {
			GLabel lost = new GLabel("GAME OVER");
			lost.setFont(SCREEN_FONT);
			double x = getWidth() / 2 - lost.getWidth() / 2;
			double y = getHeight() / 2 - lost.getHeight() / 2;
			add(lost, x, y);
			remove(PADDLE);
		} else {
			GLabel win = new GLabel("YOU WIN!");
			win.setFont(SCREEN_FONT);
			double x = getWidth() / 2 - win.getWidth() / 2;
			double y = getHeight() / 2 - win.getHeight() / 2;
			add(win, x, y);
			remove(ball);
			remove(PADDLE);
		}
	}
}
