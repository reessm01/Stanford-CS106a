/* 
 * File Name: AsciiArt.java
 * Author: Scott Reese
 * Section Leader: Anand Shankar
 * Date:7/9/17
 * Description:
 * This console program generates a ASCII head of Mario and
 * goes through power cycle of invisibility. In similar fashion
 * to the game Mario power fades and resumes to being a
 * mortal, heroic plumber.
 * 
 * Citation, Photo used for reference: 
 * https://pbs.twimg.com/media/BuPegfVCQAAwFyj.jpg
*/

import java.awt.Color;
import acm.program.*;

public class AsciiArt extends ConsoleProgram {
	
	private static final int SIZE = 64;
	
	public void run() {
		int pause = 50;
		while(pause != 430){
			println("CS106A Art by Scott Reese");
			println();
			redHat();
			topPartOfFace();
			botPartOfFace();
			chin();
			if(pause>200){
				println();
				print("                         ");
				print("POWER FADING!!");
			}
			pause(pause);
			clearConsole();
			println("CS106A Art by Scott Reese");
			println();
			blueHat();
			topPartOfFace();
			botPartOfFace();
			chin();
			pause(pause);
			clearConsole();
			println("CS106A Art by Scott Reese");
			println();
			yellowHat();
			topPartOfFace();
			botPartOfFace();
			chin();
			if(pause>200){
				println();
				print("                         ");
				print("POWER FADING!!");
			}
			pause(pause);
			clearConsole();
			println("CS106A Art by Scott Reese");
			println();
			cyanHat();
			topPartOfFace();
			botPartOfFace();
			chin();
			pause(pause);
			clearConsole();
			println("CS106A Art by Scott Reese");
			println();
			pinkHat();
			topPartOfFace();
			botPartOfFace();
			chin();
			if(pause>200){
				println();
				print("                         ");
				print("POWER FADING!!");
			}
			pause(pause);
			clearConsole();
			pause+=20;
		}
		println("CS106A Art by Scott Reese");
		println();
		redHat();
		topPartOfFace();
		botPartOfFace();
		chin();
	}
	
	private void redHat(){
		int width = 8;
		for(int i = 0; i < 7; i++){
			int spaces = SIZE / 2 - width;
			for(int j = 0; j < spaces; j++){
				print(" ");
			}
			for(int k = 0; k < width*2; k++){
				if((i==1 || i==2) && k>=width-2 && k<=width+2){
					print("@", Color.yellow);
				}
				else if(i==3 && k>=7 && k<=32){
					print("N");
				} 
				else if(i==4 && k>=5 && k<=34){
					print("N");
				} 
				else if((i==5 || i==6) && k>=2 && k<=37){
					print("N");
				}
				else{
					print("M",Color.RED);
				}
			}
			println();
			if(width < 20){
				width +=4;
			}
		}
		int spaces = SIZE / 2 - width;

		for(int l = 0; l < spaces; l++){
				print(" ");
			}
		for(int k = 0; k < width*2; k++){
			if(k<=1 || k>=width*2-2){
				print("M",Color.RED);
			}
			else if((k>1 && k<=6) || (k>32 && k<=125)){
				print("N");
			}
			else if((k>=7 && k<=15) || (k>=24 && k<=32)){
				print(" ");
			}
			else if(k>=16 && k<=23){
				print("#",Color.pink);
			}
		}
		println();
	}
	
	private void blueHat(){
		int width = 8;
		for(int i = 0; i < 7; i++){
			int spaces = SIZE / 2 - width;
			for(int j = 0; j < spaces; j++){
				print(" ");
			}
			for(int k = 0; k < width*2; k++){
				if((i==1 || i==2) && k>=width-2 && k<=width+2){
					print("@", Color.yellow);
				}
				else if(i==3 && k>=7 && k<=32){
					print("N");
				} 
				else if(i==4 && k>=5 && k<=34){
					print("N");
				} 
				else if((i==5 || i==6) && k>=2 && k<=37){
					print("N");
				}
				else{
					print("M",Color.blue);
				}
			}
			println();
			if(width < 20){
				width +=4;
			}
		}
		int spaces = SIZE / 2 - width;

		for(int l = 0; l < spaces; l++){
				print(" ");
			}
		for(int k = 0; k < width*2; k++){
			if(k<=1 || k>=width*2-2){
				print("M",Color.blue);
			}
			else if((k>1 && k<=6) || (k>32 && k<=125)){
				print("N");
			}
			else if((k>=7 && k<=15) || (k>=24 && k<=32)){
				print(" ");
			}
			else if(k>=16 && k<=23){
				print("#",Color.pink);
			}
		}
		println();
	}
	
	private void yellowHat(){
		int width = 8;
		for(int i = 0; i < 7; i++){
			int spaces = SIZE / 2 - width;
			for(int j = 0; j < spaces; j++){
				print(" ");
			}
			for(int k = 0; k < width*2; k++){
				if((i==1 || i==2) && k>=width-2 && k<=width+2){
					print("@", Color.yellow);
				}
				else if(i==3 && k>=7 && k<=32){
					print("N");
				} 
				else if(i==4 && k>=5 && k<=34){
					print("N");
				} 
				else if((i==5 || i==6) && k>=2 && k<=37){
					print("N");
				}
				else{
					print("M",Color.yellow);
				}
			}
			println();
			if(width < 20){
				width +=4;
			}
		}
		int spaces = SIZE / 2 - width;

		for(int l = 0; l < spaces; l++){
				print(" ");
			}
		for(int k = 0; k < width*2; k++){
			if(k<=1 || k>=width*2-2){
				print("M",Color.yellow);
			}
			else if((k>1 && k<=6) || (k>32 && k<=125)){
				print("N");
			}
			else if((k>=7 && k<=15) || (k>=24 && k<=32)){
				print(" ");
			}
			else if(k>=16 && k<=23){
				print("#",Color.pink);
			}
		}
		println();
	}
	
	private void cyanHat(){
		int width = 8;
		for(int i = 0; i < 7; i++){
			int spaces = SIZE / 2 - width;
			for(int j = 0; j < spaces; j++){
				print(" ");
			}
			for(int k = 0; k < width*2; k++){
				if((i==1 || i==2) && k>=width-2 && k<=width+2){
					print("@", Color.yellow);
				}
				else if(i==3 && k>=7 && k<=32){
					print("N");
				} 
				else if(i==4 && k>=5 && k<=34){
					print("N");
				} 
				else if((i==5 || i==6) && k>=2 && k<=37){
					print("N");
				}
				else{
					print("M",Color.cyan);
				}
			}
			println();
			if(width < 20){
				width +=4;
			}
		}
		int spaces = SIZE / 2 - width;

		for(int l = 0; l < spaces; l++){
				print(" ");
			}
		for(int k = 0; k < width*2; k++){
			if(k<=1 || k>=width*2-2){
				print("M",Color.cyan);
			}
			else if((k>1 && k<=6) || (k>32 && k<=125)){
				print("N");
			}
			else if((k>=7 && k<=15) || (k>=24 && k<=32)){
				print(" ");
			}
			else if(k>=16 && k<=23){
				print("#",Color.pink);
			}
		}
		println();
	}
	
	private void pinkHat(){
		int width = 8;
		for(int i = 0; i < 7; i++){
			int spaces = SIZE / 2 - width;
			for(int j = 0; j < spaces; j++){
				print(" ");
			}
			for(int k = 0; k < width*2; k++){
				if((i==1 || i==2) && k>=width-2 && k<=width+2){
					print("@", Color.yellow);
				}
				else if(i==3 && k>=7 && k<=32){
					print("N");
				} 
				else if(i==4 && k>=5 && k<=34){
					print("N");
				} 
				else if((i==5 || i==6) && k>=2 && k<=37){
					print("N");
				}
				else{
					print("M",Color.pink);
				}
			}
			println();
			if(width < 20){
				width +=4;
			}
		}
		int spaces = SIZE / 2 - width;

		for(int l = 0; l < spaces; l++){
				print(" ");
			}
		for(int k = 0; k < width*2; k++){
			if(k<=1 || k>=width*2-2){
				print("M",Color.pink);
			}
			else if((k>1 && k<=6) || (k>32 && k<=125)){
				print("N");
			}
			else if((k>=7 && k<=15) || (k>=24 && k<=32)){
				print(" ");
			}
			else if(k>=16 && k<=23){
				print("#",Color.pink);
			}
		}
		println();
	}
	
	private void topPartOfFace(){
		int width = 23;
		int spaces = SIZE / 2 - width;
		for(int j = 0; j <5; j++){
			for(int l = 0; l < spaces; l++){
				print(" ");
			}
			for(int i = 0; i < width*2+2; i++){
				if(j==0 || j==1){
					if((i<=2 || i>43 && i<=46) || (i>5 && i<=7) || (i>39 && i <= 41)){
						print("#", Color.pink);
					}
					else if((i>3 && i<=5) || (i>15 && i<=19) || (i>27&&i<=31) || (i>41 && i<=43)){
						print("N");
					}
					else if((i>7 && i<=10)||(i>19 && i<=27)||(i>36&&i<=39)){
						print("0", Color.pink);
					}
					else if((i>10 && i<=15)||(i>31 && i<=36)){
						print(" ");
					}
					}
				if(j==2){
					if((i<=2 || i>43 && i<=46)){
						print("#", Color.pink);
					}
					else if((i>3 && i<=5) || (i>15 && i<=19) || (i>27&&i<=31) || (i>41 && i<=43)){
						print("N");
					}
					else if((i>7 && i<=10)||(i>19 && i<=27)||(i>36&&i<=39) || (i>5 && i<=7) || (i>39 && i <= 41)){
						print("0", Color.pink);
					}
					else if((i>10 && i<=15)||(i>31 && i<=36)){
						print(" ");
					}
					}
				if(j==3){
					if((i<=2 || i>43 && i<=46) || (i>10 && i<=15)||(i>31 && i<=36)){
						print("#", Color.pink);
					}
					else if((i>3 && i<=7) || (i>39 && i<=43)){
						print("N");
					}
					else if((i>5 && i<=10)||(i>19 && i<=31)|| (i>15 && i<=19) || (i>36&&i<=39)){
						print("0", Color.pink);
					}
				}
				if(j==4){
					if((i<=2 || i>43 && i<=46) || (i>15 && i<=31)){
						print("#", Color.pink);
					}
					else if((i>3 && i<=5) || (i>41 && i<=43) || (i>7 && i<=15) || (i>31 && i<=39)){
						print("N");
					}
					else if((i>5 && i<=7) || (i>39 && i<=41)){
						print("0", Color.pink);
					}
				}
			}
			println();
		}
		}
	
	private void botPartOfFace(){
		int width = 20;
		int spaces = SIZE / 2 - width;
		for(int j = 0; j <3; j++){
			for(int l = 0; l < spaces; l++){
				print(" ");
			}
			if(j==0){
				for(int i = 0; i < width*2+2; i++){
					if(j==0){
						if((i>0 && i<=2) || (i>4 && i<=36) || (i>38 && i<=40)){
							print("N");
						}
						else if((i>1 && i<=4) || (i>36 && i <=38)){
							print("0", Color.pink);
						}
						}
				}
			}
			if(j==1){
				for(int i = 0; i < width*2+10; i++){
					if((i>0 && i<=2) || (i>33 && i<=35 )){
						print("X", Color.ORANGE);
					}
					if((i>4 && i<=30)){
						print("N");
					}
					else if((i>1 && i<=4) || (i>30 && i <=33)){
						print("0", Color.pink);
					}
				}
				}
			if(j==2){
				for(int i = 0; i < 33; i++){
					if((i>0 && i<=8) || (i>24 && i<=32 )){
						print("X", Color.orange);
					}
					else if((i>8 && i<=24)){
						print("#", Color.pink);
					}
				}
				}
			width-=2;
			spaces = SIZE / 2 - width;
			println();
			}
	}
	
	private void chin(){
		int width = 14;
		int spaces = 18;
		for(int j = 0; j<spaces; j++){
			print(" ");
		}
		for(int i = 0; i < width*2; i++){
			print("X", Color.orange);
		}
	}
}
	
		
