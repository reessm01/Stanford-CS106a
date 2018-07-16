/* 
 * File Name: Weather.java
 * Author: Scott Reese
 * Section Leader: Anand Shankar
 * Date:7/6/17
 * Description:
 * This program prompts the user to input a single or series of temperatures one at
 * a time. The output after a sentinel number is entered is the highest temperature,
 * lowest temperature, average temperature and the count of cold days.
*/

import acm.program.*;

public class Weather extends ConsoleProgram {
	
	private static final int SENTINEL = -1;
	private static final int COLD_TEMP = 50;
	
	public void run() {
		println("CS 106A \"Weather Master\" 4000!");
		
		int temp = 0;
		double totalTemp = 0;
		int highestTemp = 0;
		int lowestTemp = 0;
		double count = 0;
		int coldCount = 0;
		
		while(temp != SENTINEL){
			temp = readInt("Next temperature (or " + SENTINEL + " to quit)? ");
			if(count == 0 && temp == SENTINEL){
				println("No temperatures were entered.");
			} else if(count==0){
				totalTemp += temp;
				count++;
				highestTemp = temp;
				lowestTemp = temp;
				if(temp <= COLD_TEMP){
					coldCount++;
				}
			} else if(count>=1 && temp!=SENTINEL){
				totalTemp += temp;
				count++;
				if(temp > highestTemp){
					highestTemp = temp;
				} 
				if(temp < lowestTemp){
					lowestTemp = temp;
				}
				if(temp <= COLD_TEMP){
					coldCount++;
				}			
			} else{
				double avgTemp = totalTemp / count;
				println("Highest temperature = " + highestTemp);
				println("Lowest temperature = " + lowestTemp);
				println("Average = " + avgTemp);
				println(coldCount + " cold day(s).");
			}	
		}
	}
}
	

