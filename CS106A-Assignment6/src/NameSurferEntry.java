
/* 
 * File Name: NameSurferEntry.java
 * Author: Scott Reese
 * Section Leader: Anand Shankar
 * Date:8/14/17
 * 
 * Description: NameSurferEntry is a class that takes an name entry from 
 * the user and processes it to be passed into the NameSurferGraph.
 */

import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

	// Justification: dataline is the main private instance variable that is
	// used throughout the class.
	private String dataLine;

	// NameSurferEntry() is the constructor for this class.

	public NameSurferEntry(String dataLine) {
		this.dataLine = dataLine;
	}

	// getName() returns the name from a single data set.

	public String getName() {
		Scanner tokens = new Scanner(dataLine);
		String name = tokens.next();
		tokens.close();

		return name;
	}

	// getRank() returns the rank of a given decade. Decades are represented in
	// successive order from 1900's. Example: 1st decade, or 1910, equals "1" as
	// represented by the variable 'decadesSince1900'.

	public int getRank(int decadesSince1900) {

		Scanner tokens = new Scanner(dataLine);
		tokens.next();
		int i = 0;

		while (tokens.hasNext()) {
			int rank = Integer.valueOf(tokens.next());
			if (i == decadesSince1900) {
				tokens.close();
				return rank;
			}
			i++;
		}
		tokens.close();
		return -1;
	}

	// toString() returns the data from the database into "readable" form by
	// augmenting it and inserting brackets and commas in a new represented
	// String of data.

	public String toString() {
		int i = 0;
		String toString = "";
		Scanner tokens = new Scanner(dataLine);
		while (tokens.hasNext()) {
			String portion = tokens.next();
			if (i == 0) {
				toString = toString + portion + " [";
				i++;
			} else if (i == 11) {
				toString = toString + portion + "]";
			} else {
				toString = toString + portion + ", ";
				i++;
			}
		}
		tokens.close();
		return toString;
	}
}
