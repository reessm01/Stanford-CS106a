
/* 
 * File Name: NameSurferGraph.java
 * Author: Scott Reese
 * Section Leader: Anand Shankar
 * Date:8/14/17
 * 
 * Description: NameSurferGraph() handles all the graphing components. It 
 * takes the data line of a name in as input and processes it into its 
 * appropriate spacing to make a line graph with labels.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas implements NameSurferConstants, ComponentListener {

	// Justification: dataEntries (ranks) & nameEntries (names only): for ease
	// of coding making these private instance variables was required to keep
	// the composition simple and easier to read for this class.
	private ArrayList<Integer> dataEntries = new ArrayList<>();
	private ArrayList<String> nameEntries = new ArrayList<>();

	// NameSurferGraph() is the constructor to initialize the graph to the main
	// program.
	public NameSurferGraph() {
		addComponentListener(this);
	}

	// clear() clears all data from the ArrayLists in this class.
	public void clear() {
		dataEntries.clear();
		nameEntries.clear();
	}

	// addEntry() takes the NameSurferEntry class and its entry and adds the
	// corresponding data to both ArrayLists in this class. At the end of the
	// entry cycle a '-1' is added to dataEntries and " " is added to the
	// nameEntries. This assists the drawDataEntries method.

	public void addEntry(NameSurferEntry entry) {
		for (int i = 0; i < NUM_DECADES; i++) {
			dataEntries.add(entry.getRank(i));
			nameEntries.add(entry.getName());
		}
		dataEntries.add(-1);
		nameEntries.add(" ");
	}

	// update() removes and draws all graphing lines. It essentially either
	// refreshes the screen with previous and new entries, or clears the screen.

	public void update() {
		removeAll();
		drawMargins();
		drawDateLines();
		drawDataEntries();
	}

	// drawMargins() is a method that draws only the horizontal margins for the
	// graph.

	private void drawMargins() {
		double width = getWidth();
		double height = getHeight();
		GLine upperMargin = new GLine(0.0, GRAPH_MARGIN_SIZE, width, GRAPH_MARGIN_SIZE);
		add(upperMargin);
		GLine lowerMargin = new GLine(0.0, height - GRAPH_MARGIN_SIZE, width, height - GRAPH_MARGIN_SIZE);
		add(lowerMargin);
	}

	// drawDateLines() processes the graphing area and draws the vertical lines
	// and decade labels.

	private void drawDateLines() {
		double width = getWidth();
		double height = getHeight();
		double xMod = width / NUM_DECADES;
		double x = xMod;
		double xDecade = 0.0;
		double y1 = 0;
		double y2 = height;
		int decade = START_DECADE;
		String decadeString = "" + decade;

		drawVerticalLine(0.0, y1, y2);

		for (int i = 0; i < NUM_DECADES; i++) {
			if (i != 0) {
				x += xMod;
				xDecade += xMod;
				decade += 10;
				decadeString = "" + decade;
			}
			drawVerticalLine(x, y1, y2);
			drawDecades(xDecade, height, decadeString);
		}
	}

	// drawVerticalLine() takes the associated x and y values and draws a
	// vertical line.

	private void drawVerticalLine(double x, double y1, double y2) {
		GLine verticalLine = new GLine(x, y1, x, y2);
		add(verticalLine);
	}

	// drawDecades() takes the associated x, y and String values and places a
	// decade label on the screen.

	private void drawDecades(double x, double y, String decadeString) {
		y -= DECADE_LABEL_MARGIN_SIZE;
		GLabel decadeLabel = new GLabel(decadeString, x, y);
		add(decadeLabel);
	}

	// drawDataEntries() draws a line graph by taking both of the classes
	// ArrayLists and draws each name with its own line and color.

	private void drawDataEntries() {
		int colorSwitcher = 0;
		int size = dataEntries.size();

		for (int i = 0; i < size; i++) {
			String name = "";
			// This if statement determines when a line of data for a name entry
			// has ended and adds the last label as needed. As well, it "skips"
			// this entry.
			if (dataEntries.get(i) == -1) {
				int start = dataEntries.get(i - 1);
				if (start != 0) {
					name = nameEntries.get(i - 1) + " " + start;
				} else {
					name = nameEntries.get(i - 1) + " *";
				}
				labelMaker(start, i - 1, colorSwitcher, name);
				i++;
				colorSwitcher++;
			}
			if (i + 1 <= size && dataEntries.get(i + 1) != -1) {
				int start = dataEntries.get(i);
				int end = dataEntries.get(i + 1);

				if (start != 0) {
					name = nameEntries.get(i) + " " + start;
				} else {
					name = nameEntries.get(i) + " *";
				}

				drawLines(start, end, i, colorSwitcher);
				labelMaker(start, i, colorSwitcher, name);
			}
		}
	}

	// drawLine() draws the graph lines. It takes the "start" and "end" values
	// as for the y coordinates, uses the variable 'i' as a multiplier for the
	// x-axis, and uses 'colorSwitcher' to determine what color to use.

	private void drawLines(int start, int end, int i, int colorSwitcher) {

		if (i > 11) {
			i %= 12;
		}

		double y1 = yInfo(start);
		double y2 = yInfo(end);
		double width = getWidth();
		double xMod = width / NUM_DECADES;
		double x1 = xInfo(i, xMod);
		double x2 = xInfo(i, xMod) + xMod;

		GLine entryLine = new GLine(x1, y1, x2, y2);
		determineColor(entryLine, colorSwitcher);
		add(entryLine);
	}

	// labelMaker() draws the data point lables. It takes the "start" and "end"
	// values
	// as for the y coordinates, uses the variable 'i' as a multiplier for the
	// x-axis, and uses 'colorSwitcher' to determine what color to use.

	private void labelMaker(int start, int i, int colorSwitcher, String name) {

		if (i > 11) {
			i %= 12;
		}

		double width = getWidth();
		double xMod = width / NUM_DECADES;
		double x1 = xInfo(i, xMod);
		double y1 = yInfo(start);

		GLabel names = new GLabel(name, x1, y1);
		determineColor(names, colorSwitcher);
		add(names);
	}

	// determineColor() sets the color for the GObject being passed through.

	private void determineColor(GObject object, int colorSwitcher) {
		colorSwitcher %= 4;
		if (colorSwitcher == 1) {
			object.setColor(Color.red);
		} else if (colorSwitcher == 2) {
			object.setColor(Color.blue);
		} else if (colorSwitcher == 3) {
			object.setColor(Color.magenta);
		}
	}

	// xInfo() determines where on the x-axis the lines & labels should be
	// placed. 

	private double xInfo(int i, double xMod) {
		double x = 0.0;
		for (int j = 0; j < i; j++) {
			x += xMod;
		}
		return x;
	}

	// yInfo() uses the rank's data point and determines where on the y-axis the
	// lines and labels should be placed.

	// Special note: I spoke with Brahm during SCPD hours about how my solution
	// differs from the demo's output. We could not come to a conclusion as to
	// why this is happening despite analyzing my approach. I was given the
	// instructions to make mention of this.

	private double yInfo(int rank) {
		double height = getHeight();
		double minHeight = GRAPH_MARGIN_SIZE;
		double maxHeight = height - GRAPH_MARGIN_SIZE;
		double pixelHeight = maxHeight - minHeight;
		double dRank = rank;

		if (rank == 0) {
			return maxHeight;
		} else {
			double yScale = (dRank - 1) / MAX_RANK;
			double result = pixelHeight * yScale + GRAPH_MARGIN_SIZE;
			return result;
		}
	}

	/*
	 * Implementation of the ComponentListener interface for updating when the
	 * window is resized
	 */
	public void componentHidden(ComponentEvent e) {
	}

	public void componentMoved(ComponentEvent e) {
	}

	public void componentResized(ComponentEvent e) {
		update();
	}

	public void componentShown(ComponentEvent e) {
	}
}
