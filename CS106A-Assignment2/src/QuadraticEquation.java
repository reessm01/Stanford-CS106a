/* 
 * File Name: QuadraticEquation.java
 * Author: Scott Reese
 * Section Leader: Anand Shankar
 * Date:7/5/17
 * Description:
 * This program prompts the user to input a, b & c, inputs them into the quadratic 
 * equation, and outputs their respective roots.
*/

import acm.program.*;

public class QuadraticEquation extends ConsoleProgram {
	public void run() {
		println("CS 106A Quadratic Solver!");
		int a = readInt("Enter a: ");
		int b = readInt("Enter b: ");
		int c = readInt("Enter c: ");
		int discrim = b * b - 4 * a * c;
		double sqrtDiscrim = Math.sqrt(discrim);
		
		if(discrim < 0){
			println("No real roots");
		} else if(discrim == 0){
				double singleResult = -b / 2*a;
				println("One root: " + singleResult);
			} else{
				double firstResult = (-b + sqrtDiscrim)/2*a;
				double secondResult = (-b - sqrtDiscrim)/2*a;
				println("Two roots: " + firstResult + " and " + secondResult);
			}
	}
}
