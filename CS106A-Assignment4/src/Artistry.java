
/* 
 * File Name: Artistry.java
 * Author: Scott Reese
 * Section Leader: Anand Shankar
 * Date:7/28/17
 * Citation: 
 * 1. Photo used for reference: 
 * http://michellephan.com/content/uploads/2015/10/sacred-geometry-body-2.png
 * 2. Hexagons methods:
 * "The Art & Science of Java" by Eric Roberts, Chapter 9.3, page 323
 * Description:
 * This program outputs a graphical object that appears as 
 * "sacred geometry".
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class Artistry extends GraphicsProgram {

	private static final double SIDE = 100.0;
	private static final double HEX_FLATS = 173.2;
	private static final double HEX_CORNERS = 200;
	private static final double DIA = 50;
	private double CANVAS_WIDTH;
	private double CANVAS_HEIGHT;
	private GLabel LABEL;

	public void run() {
		setBackground(Color.blue);
		setCanvasSize(HEX_CORNERS * 1.5, HEX_FLATS * 1.5 + 50);
		CANVAS_WIDTH = getWidth();
		CANVAS_HEIGHT = getHeight();
		createHexagon();
		createHexagon2();
		createCirclePattern();
		createLinePattern1();
		createLinePattern2();
		createLinePattern3();
		createHexagon2();
		addName();
	}

	private void createHexagon() {
		double x = CANVAS_WIDTH / 2;
		double y = CANVAS_HEIGHT / 2;
		GPolygon hex = new GPolygon();

		hex.addVertex(-SIDE, 0);
		int angle = 60;
		for (int i = 0; i < 6; i++) {
			hex.addPolarEdge(SIDE, angle);
			angle -= 60;
		}
		hex.setFilled(true);
		hex.setFillColor(Color.RED);
		add(hex, x, y);
	}

	private void createHexagon2() {
		double x = CANVAS_WIDTH / 2;
		double y = CANVAS_HEIGHT / 2;
		double side = SIDE / 2;
		GPolygon hex2 = new GPolygon();

		hex2.addVertex(-side, 0);
		int angle = 60;
		for (int i = 0; i < 6; i++) {
			hex2.addPolarEdge(side, angle);
			angle -= 60;
		}
		add(hex2, x, y);
	}

	private void createCircle(double x, double y) {
		GOval circle = new GOval(x, y, DIA, DIA);
		circle.setFilled(true);
		circle.setFillColor(Color.YELLOW);
		add(circle);
	}

	private void createCirclePattern() {
		for (int i = 0; i < 5; i++) {
			double x = CANVAS_WIDTH / 2 - DIA / 2 - 100 + i * DIA;
			double y = CANVAS_HEIGHT / 2 - DIA / 2;
			createCircle(x, y);
		}
		for (int j = 0; j < 4; j++) {
			for (int i = 1; i < 3; i++) {
				int xInverse = 1;
				int yInverse = 1;
				if (j == 1) {
					xInverse = -1;
				} else if (j == 2) {
					xInverse = -1;
					yInverse = -1;
				} else if (j == 3) {
					yInverse = -1;
				}
				double radians = Math.toRadians(30);
				double xOffset = Math.sin(radians) * DIA * 2 / i;
				double x = CANVAS_WIDTH / 2 - DIA / 2 + xOffset * xInverse;
				double y = CANVAS_HEIGHT / 2 - DIA / 2 + (HEX_FLATS / 2 / i) * yInverse;
				createCircle(x, y);
			}
		}
	}

	private void createLine1(double x, double y) {
		double x1 = CANVAS_WIDTH / 2;
		double y1 = CANVAS_HEIGHT / 2;
		GLine line = new GLine(x1, y1, x, y);
		add(line);
	}

	private void createLinePattern1() {
		for (int i = 0; i < 6; i++) {
			double x = CANVAS_WIDTH / 2;
			double y = CANVAS_HEIGHT / 2;
			if ((i == 0) || (i == 3)) {
				if (i == 0) {
					x += HEX_CORNERS / 2;
				} else {
					x -= HEX_CORNERS / 2;
				}
			} else {
				int xFactoral = 1;
				int yFactoral = 1;
				if (i == 2) {
					xFactoral = -1;
				} else if (i == 4) {
					xFactoral = -1;
					yFactoral = -1;
				} else if (i == 5) {
					yFactoral = -1;
				}
				x += HEX_CORNERS / 4 * xFactoral;
				y += HEX_FLATS / 2 * yFactoral;
			}
			createLine1(x, y);
		}
	}

	private void createLine2(double x1, double y1, double x2, double y2) {
		GLine line = new GLine(x1, y1, x2, y2);
		add(line);
	}

	private void createLinePattern2() {
		double x = CANVAS_WIDTH / 2;
		double y = CANVAS_HEIGHT / 2;
		for (int i = 0; i < 2; i++) {
			double x1 = x - HEX_CORNERS / 2;
			double startx2 = x + HEX_CORNERS / 2;
			double y1 = y;
			double x2 = 0;
			double y2 = 0;
			if (i == 1) {
				x1 = startx2;
			}
			for (int j = 0; j < 3; j++) {
				int xFactoral = 1;
				int yFactoral = 1;
				if (i == 0) {
					if (j == 0) {
						yFactoral = -1;
					}
				} else if (i == 1) {
					if (j == 0) {
						xFactoral = -1;
					} else if (j == 1) {
						xFactoral = -1;
						yFactoral = -1;
					}
				}
				if (i == 0 && j == 2) {
					x1 = x + HEX_CORNERS / 4;
					y1 = y + HEX_FLATS / 2;
					x2 = x1;
					y2 = y - HEX_FLATS / 2;
				} else if (i == 1 && j == 2) {
					x1 = x - HEX_CORNERS / 4;
					y1 = y + HEX_FLATS / 2;
					x2 = x1;
					y2 = y - HEX_FLATS / 2;
				} else {
					x2 = x + (HEX_CORNERS / 4) * xFactoral;
					y2 = y + (HEX_FLATS / 2) * yFactoral;
				}
				createLine2(x1, y1, x2, y2);
			}
		}
	}

	private void createLinePattern3() {
		double x = CANVAS_WIDTH / 2;
		double y = CANVAS_HEIGHT / 2;
		for (int i = 0; i < 2; i++) {
			double x1 = x - HEX_CORNERS / 2 / 2;
			double startx2 = x + HEX_CORNERS / 2 / 2;
			double y1 = y;
			double x2 = 0;
			double y2 = 0;
			if (i == 1) {
				x1 = startx2;
			}
			for (int j = 0; j < 3; j++) {
				int xFactoral = 1;
				int yFactoral = 1;
				if (i == 0) {
					if (j == 0) {
						yFactoral = -1;
					}
				} else if (i == 1) {
					if (j == 0) {
						xFactoral = -1;
					} else if (j == 1) {
						xFactoral = -1;
						yFactoral = -1;
					}
				}
				if (i == 0 && j == 2) {
					x1 = x + HEX_CORNERS / 4 / 2;
					y1 = y + HEX_FLATS / 2 / 2;
					x2 = x1;
					y2 = y - HEX_FLATS / 2 / 2;
				} else if (i == 1 && j == 2) {
					x1 = x - HEX_CORNERS / 4 / 2;
					y1 = y + HEX_FLATS / 2 / 2;
					x2 = x1;
					y2 = y - HEX_FLATS / 2 / 2;
				} else {
					x2 = x + (HEX_CORNERS / 4) / 2 * xFactoral;
					y2 = y + (HEX_FLATS / 2) / 2 * yFactoral;
				}
				createLine2(x1, y1, x2, y2);
			}
		}
	}

	private void addName() {
		LABEL = new GLabel("Artistry by Scott Reese");
		add(LABEL, CANVAS_WIDTH - LABEL.getWidth(), CANVAS_HEIGHT - LABEL.getDescent());
	}

}
