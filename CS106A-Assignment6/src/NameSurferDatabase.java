
/* 
 * File Name: NameSurferDatabase.java
 * Author: Scott Reese
 * Section Leader: Anand Shankar
 * Date:8/14/17
 * 
 * Description: NameSurferData populates an ArrayList based on the name(s) 
 * entered in by the user. The names entered in by the user are not case 
 * dependent and will be processed to a desired state. 
 */

import java.io.*;
import java.util.*;

public class NameSurferDatabase implements NameSurferConstants {

	private ArrayList<String> dataBaseArray = new ArrayList<>();

	// NameSurferDatabase() takes a filename as an input and populates an
	// ArrayList with the data to be processed later.

	public NameSurferDatabase(String filename) {
		try {
			Scanner input = new Scanner(new File(NAMES_DATA_FILE));
			while (input.hasNextLine()) {
				String line = input.nextLine();
				dataBaseArray.add(line);
			}
			input.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Error reading the file " + ex);
		}
	}

	// findEntry() searches for a single line of data from a given name input in
	// from the user and returns the line of data.

	public NameSurferEntry findEntry(String name) {

		name = formatName(name);

		for (int i = 0; i < dataBaseArray.size(); i++) {
			String dataLine = dataBaseArray.get(i);
			if (dataLine.contains(name)) {
				NameSurferEntry dataEntry = new NameSurferEntry(dataLine);
				return dataEntry;
			}
		}
		return null;
	}

	// formatName() allows the user to input non-case dependent names. A name is
	// processed, so that it matches the formatting of names found in the file
	// 'names-data.txt'.

	private String formatName(String name) {
		String formattedName = "";
		name = name.toLowerCase();
		for (int i = 0; i < name.length(); i++) {
			char c = name.charAt(i);
			if (i == 0) {
				c = Character.toUpperCase(c);
				formattedName += c;
			} else {
				formattedName += c;
			}
		}

		return formattedName;
	}
}
