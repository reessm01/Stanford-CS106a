/* 
 * File Name: Hailstone.java
 * Author: Scott Reese
 * Section Leader: Anand Shankar
 * Date:7/5/17
 * Description:
 * This program prompts the user to input an integer for to run through a Hailstone
 * sequence and outputs how many steps were required to finish. The user is given the 
 * option to repeat.
*/


import acm.program.*;

public class Hailstone extends ConsoleProgram {
	public void run() {
		println("This program computes Hailstone sequences");
		println();
		hailStone();
		runAgain();
		}
	
	/*
	 * The hailStone() method prompts the user for a number and takes it through the
	 * Hailstone sequence and communicates every step to the user. At the end of the
	 * method the total number of steps is communicated.
	 */
	
	private void hailStone(){
		int nTemp = 0;
		int n = readInt("Enter a number: ");
		int steps = 0;
		while(n != 1){
			if(n==1){
				println("It took " + steps + " to reach 1.");
			} else if(n % 2 == 0){
				nTemp = n;
				n /= 2;
				steps++;
				println(nTemp + " is even, so I take half: " + n);
			} else{
				nTemp = n;
				n = 3 * n + 1;
				println(nTemp + " is odd, so I make 3n + 1: " + n);
				steps++;
			}
		}
		println("It took " + steps + " steps to reach 1.");
	}
	
	/*
	 * The runAgain() method prompts the user for input if they want to run again.
	 * If they do not want to the close line for the method is "returned", otherwise
	 * the Hailstone() method is repeated until a "no" is entered by the user.
	 */
	
	private void runAgain(){
		boolean repeatRun = true;
		while(repeatRun == true){
			repeatRun = readBoolean("Run again? ", "y", "n");
			if(repeatRun == false){
				println("Thanks for using Hailstone.");
			} else{
				println();
				hailStone();
			}
		}
	}
}
