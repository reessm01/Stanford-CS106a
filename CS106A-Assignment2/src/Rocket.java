/* 
 * File Name: Rocket.java
 * Author: Scott Reese
 * Section Leader: Anand Shankar
 * Date:7/9/17
 * Description:
 * This console program generates a rocket ship that can be scaled 
 * by size. There is no prompt to change the scale, however it may
 * be changed by the static variable labeled "SIZE".
*/

import java.awt.Color;
import acm.program.*;

public class Rocket extends ConsoleProgram {
	
	private static final int SIZE = 12;
	
	/* The main run() method outputs labels and indicates chosen SIZE, or scale,
	 * of the rocket. The rocket is systematically built top down. The
	 * middle section of the rocket is decomposed into two methods 
	 * quadrantsIIAndI() and quadrantIIIAndIV(). 
	 */
	
	public void run() {
		println("CS 106A Rocket");
		println("(SIZE " + SIZE + ")");
		buildTriangle();
		buildCollar();
		quadrantsIIAndI();
		quadrantsIIIAndIV();
		buildCollar();
		buildTriangle();
	}
	
	/*
	 * The buildTriangle() method builds large section of forward and backward slashes
	 * that makeup the top and bottom sections of the rocket.
	 */
	
	private void buildTriangle(){
		for(int i = 0; i < SIZE; i++){
			for(int m = 0; m < ((SIZE + 1) - (i+1)) ; m++){
				print(" ");
			}
			for(int j = 0; j < (i+1); j++){
				print("/", Color.BLUE);
		}
			for(int n = 0; n < (i+1); n ++){
				print("\\", Color.BLUE);
			}
			println();
		}
	}
	
	 // The buildCollar() method builds the single lines "+=...=+" sections of the rocket.
	
	private void buildCollar(){
		print("+", Color.RED);
		for(int i = 0; i < SIZE * 2; i++){
			print("=", Color.RED);
		}
		print("+", Color.RED);
		println();
	}
	
	/*
	 * The quandrantsIIAndI() method builds the pattern of dots and slashes that make up the
	 * first and second quadrants of the middle section of the rocket. At a high level the 
	 * method uses the SCALE of the rocket and decomposes it into two quadrants and integrates 
	 * the desired pattern row by row.
	 */
	
	private void quadrantsIIAndI(){
		for(int i = 0; i < SIZE; i++){
			int dots = SIZE - i - 1;
			int slashes = (SIZE - dots)*2;
			print("|", Color.RED);
			for(int m = 0; m < dots; m++){
				print(".", Color.BLUE);
				}
			for(int n = 0; n < slashes; n++){
				print("/", Color.RED);
				n++;
				if(n < slashes){
					print("\\", Color.RED);
				}
			}
			for(int t = 0; t < dots; t++){
				print(".", Color.BLUE);
			}
			print("|", Color.RED);
			println();
		}
	}
	
	/*
	 * The quandrantsIIAndI() method similarly builds the pattern of dots and slashes that make up the
	 * third and fourth quadrants of the middle section of the rocket. Unique to this method is the logic
	 * structured to build the slashes in decreasing amounts.
	 */

	
	private void quadrantsIIIAndIV(){
		for(int i = 0; i < SIZE; i++){
			int slashes = SIZE - i;
			int slashes2 = slashes*2;
			int dots = SIZE - slashes;
			print("|", Color.RED);
			for(int k =0; k < dots; k++){
				print(".", Color.BLUE);
			}
			for(int j = 0; j < slashes2; j++){
				print("\\", Color.RED);
				j++;
				if(j < slashes2){
					print("/", Color.RED);
				}
			}
			for(int k =0; k < dots; k++){
				print(".", Color.BLUE);
			}
			print("|", Color.RED);
			println();
		}
	}
	}