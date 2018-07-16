
/* 
 * File Name: NameSurfer.java
 * Author: Scott Reese
 * Section Leader: Anand Shankar
 * Date:8/14/17
 * 
 * Description: this program is the main algorithm that creates a graph with 
 * buttons and labels. It takes a name as input from the user and displays 
 * its ranking per decade up to year 2,000.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

	// Justification for JTextField as private instance variable:
	private JTextField nameEntryField;
	// Justification for NameSurferGraph: graph is used throughout the init()
	// and actionPerformed() without being passed as a variable between one
	// another.
	private NameSurferGraph graph;

	// init() initializes the form and graph area.

	public void init() {

		JLabel nameLabel = new JLabel("Name: ");
		add(nameLabel, NORTH);
		nameEntryField = new JTextField(TEXT_FIELD_WIDTH);
		nameEntryField.addActionListener(this);
		add(nameEntryField, NORTH);
		JButton graphButton = new JButton("Graph");
		add(graphButton, NORTH);
		nameEntryField.setActionCommand("Graph");
		JButton clearButton = new JButton("Clear");
		add(clearButton, NORTH);
		graph = new NameSurferGraph();
		add(graph);

		addActionListeners();

	}

	// actionPerformed() registers if the "Graph" or enter button is entered and
	// prints the graph of the names on screen with their lines and values. As
	// well, clears data when the user indicates to do so.

	public void actionPerformed(ActionEvent event) {

		NameSurferDatabase dataBase = new NameSurferDatabase(NAMES_DATA_FILE);

		if (event.getActionCommand().equals("Graph")) {
			String name = nameEntryField.getText();
			if (dataBase.findEntry(name) != null) {
				NameSurferEntry dataLine = dataBase.findEntry(name);
				graph.addEntry(dataLine);
				graph.update();
			}
		} else if (event.getActionCommand().equals("Clear")) {
			graph.clear();
			graph.update();
		}

	}

}
