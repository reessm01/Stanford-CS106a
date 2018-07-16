
/* 
 * File Name: Hangman.java
 * Author: Scott Reese
 * Section Leader: Anand Shankar
 * Date:7/19/17
 * Description:
 * This program prompts the user for a presumed known .txt file, randomly chooses
 * a word from that list, and begins a game of hangman. After a game ends the user
 * is prompted if they want to play again, as well statistics are stored and presented
 * whenever the user declares they are done playing.
 */

import acm.program.*;
import acm.util.*;
import java.io.*; // for File
import java.util.*; // for Scanner

public class Hangman extends HangmanProgram {

	public void run() {
		intro();
		String fileName = promptUserForFile("Dictionary filename? ", "res");
		println();
		String secretWord = getRandomWord(fileName);
		int guessCount = playOneGame(secretWord);
		int gamesWon = gamesWon(guessCount, 0);
		int bestGuess = bestGuessTracker(guessCount, 0);
		int gamesCount = 1;
		while (readBoolean("Play again (Y/N)?", "Y", "N") != false) {
			println();
			secretWord = getRandomWord(fileName);
			guessCount = playOneGame(secretWord);
			bestGuess = bestGuessTracker(bestGuess, guessCount);
			gamesWon = gamesWon(guessCount, gamesWon);
			gamesCount++;
		}
		stats(gamesCount, gamesWon, bestGuess);
	}

	/*
	 * gamesWon() evaluates the count of guesses from a game that was just
	 * played and determines if it was won or not, and, if they won, adds one to
	 * the total gamesWon count and returns the new or previous total.
	 */

	private int gamesWon(int guessCount, int gamesWon) {
		if (guessCount > 0) {
			gamesWon++;
			return gamesWon;
		} else {
			return gamesWon;
		}
	}

	/*
	 * bestGuessTracker() keeps track of the best guesses by comparing the
	 * latest guess count versus the previous guest count and respectively
	 * returns whichever is highest.
	 */

	private int bestGuessTracker(int bestGuess, int newGuess) {
		if (newGuess > bestGuess) {
			return newGuess;
		} else {
			return bestGuess;
		}
	}

	/*
	 * intro() presents the user with a description of the program.
	 */

	private void intro() {
		println("CS106A Hangman!");
		println("I will think of a random word.");
		println("You'll try to guess its letters.");
		println("Every time you guess a letter");
		println("that isn't in my word,  a new body");
		println("part of the hanging man appears.");
		println("Guess correctly to avoid the gallows!");
		println("");
	}

	/*
	 * playOneGame() is a updating loop that displays a hint, guesses made, and
	 * remaining guess count. The user is prompted for a guess until the user
	 * wins or the guess count becomes zero. The guess count is returned in both
	 * cases.
	 */

	private int playOneGame(String secretWord) {
		int guessCount = 8;
		String guessedLetters = "";
		String hint = "";
		while (guessCount > 0) {
			displayHangman(guessCount);
			hint = createHint(secretWord, guessedLetters);
			println("Secret word: " + hint);
			println("Your guesses: " + guessedLetters);
			println("Guesses left: " + guessCount);
			char guessedLetter = readGuess(guessedLetters);
			String stringGuessed = Character.toString(guessedLetter);
			guessedLetters += stringGuessed;
			hint = createHint(secretWord, guessedLetters);
			if (guessWork(guessedLetter, secretWord) == true) {
				println("Correct!");
				println();
			} else {
				println("Incorrect.");
				if (guessCount > 1) {
					println();
				}
				guessCount--;
			}
			if (hint.contains("-") == false) {
				println("You win! My word was \"" + secretWord + "\".");
				println();
				return guessCount;
			}
		}
		displayHangman(guessCount);
		println("You lose! My word was \"" + secretWord + "\".");
		return guessCount;
	}

	/*
	 * guessWork() takes the inputs of guessed letter and the secret word and
	 * then sequentially compares them until the guessed letter is
	 * found or not. The output is true or false depending if the letter was
	 * found or not.
	 */

	private boolean guessWork(char guessedLetter, String secretWord) {
		for (int i = 0; i < secretWord.length(); i++) {
			char chSecretWord = secretWord.charAt(i);
			if (guessedLetter == chSecretWord) {
				return true;
			}
		}
		return false;
	}

	/*
	 * createHint() takes the inputs of secret word and guessed letters and
	 * converts them into a hint only revealing the guessed letters. If there
	 * are no guessed letters only dashes will be stored. The hint is then
	 * returned.
	 */

	private String createHint(String secretWord, String guessedLetters) {
		String hint = "";
		String codedHint = "";
		if (guessedLetters.length() != 0) {
			codedHint += codedHint(secretWord, guessedLetters);
			String secretWordPos = "";
			for (int i = 0; i < secretWord.length(); i++) {
				secretWordPos += i + " ";
			}
			Scanner scan = new Scanner(secretWordPos);
			while (scan.hasNext()) {
				int num = scan.nextInt();
				String tempStr = "" + num;
				if (codedHint.contains(tempStr) == true) {
					hint += secretWord.charAt(num);
				} else {
					hint += "-";
				}
			}
			scan.close();
		} else {
			for (int i = 0; i < secretWord.length(); i++) {
				hint += "-";
			}
		}
		return hint;
	}

	/*
	 * codedHint() takes the inputs of secret word and guessed letters and
	 * "codes" the hint based on similar letters and stores the location(s) of
	 * the guessed letter(s) found within and from the secret word. The coded
	 * version of the hint is returned for further processing elsewhere.
	 */

	private String codedHint(String secretWord, String guessedLetters) {
		String codedHint = "";
		int secretWordLength = secretWord.length();
		for (int i = 0; i < guessedLetters.length(); i++) {
			char chGuessedLetter = guessedLetters.charAt(i);
			for (int j = 0; j < secretWordLength; j++) {
				char chSecretWord = secretWord.charAt(j);
				boolean noMoreFound = false;
				if (chGuessedLetter == chSecretWord) {
					int loc = secretWord.indexOf(chGuessedLetter);
					codedHint += " " + loc;
					loc += 1;
					while (!noMoreFound) {
						if (secretWord.indexOf(chGuessedLetter, loc) != -1) {
							loc = secretWord.indexOf(chGuessedLetter, loc);
							codedHint += " " + loc;
							loc += 1;
						} else {
							noMoreFound = true;
						}
					}
				}
			}
		}
		return codedHint;
	}

	/*
	 * readGuess() processes the input from the user for their guess. It
	 * determines if the guess was a single character or multiple character
	 * string, and if the guessed letter was already made. The user is prompted
	 * if the guess was not processed and loops until an acceptable entry is
	 * made. The guess is then returned for further processing.
	 */

	private char readGuess(String guessedLetters) {
		char chGuessed = '\0';
		boolean guessSequencing = true;
		while (guessSequencing) {
			String guessedLetter = readLine("Your guess?");
			int guessedLetterLength = guessedLetter.length();
			if ((guessedLetter.length() > 1) || (guessedLetter.length() == 0)) {
				println("Type a single letter from A-Z.");
			} else if (guessedLetterLength == 1) {
				String upperCaseLetter = guessedLetter.toUpperCase();
				if ((upperCaseLetter.charAt(0) < 65) || (upperCaseLetter.charAt(0) > 90)) {
					println("Type a single letter from A-Z");
				} else if (guessedLetters.contains(upperCaseLetter) == false) {
					chGuessed = guessedLetter.charAt(0);
					chGuessed = Character.toUpperCase(chGuessed);
					guessSequencing = false;
				} else {
					println("You already guessed that letter.");
				}
			}
		}
		return chGuessed;
	}

	/*
	 * displayHangman() displays the hangman according to how many guesses are
	 * left to be made.
	 */

	private void displayHangman(int guessCount) {
		canvas.clear();
		String fileName = "display";
		fileName += guessCount + ".txt";
		File inputFile = new File("res", fileName);
		try {
			Scanner input = new Scanner(inputFile);
			while (input.hasNextLine()) {
				String line = input.nextLine();
				canvas.println(line);
			}
			input.close();
		} catch (FileNotFoundException ex) {
			println("Error reading the file: " + ex);
		}
	}

	/*
	 * stats() takes the inputs of count of games, games won, and best game
	 * (defined based upon maximum guesses left) and displays these statistics.
	 * Win percentage is also displayed.
	 */

	private void stats(int gamesCount, int gamesWon, int best) {
		println();
		println("Overall statistics:");
		println("Games played: " + gamesCount);
		println("Games won:    " + gamesWon);
		double percent = (gamesWon / gamesCount) * 100;
		println("Win percent:  " + percent + "%");
		println("Best game:    " + best + " guess(es) remaining");
		println("Thanks for playing!");
	}

	/*
	 * getRandomWord() takes the entered filename from the user and randomly
	 * chooses a word from its list. It then returns this as the secret word for
	 * the game to start.
	 */

	private String getRandomWord(String filename) {
		String secretWord = "";
		File inputFile = new File(filename);
		try {
			Scanner input = new Scanner(inputFile);
			int maxNumber = input.nextInt();
			Random rn = new Random();
			int randomNum = rn.nextInt(maxNumber);
			for (int i = 0; i < randomNum; i++) {
				input.next();
			}
			secretWord = input.next();
			input.close();
		} catch (FileNotFoundException ex) {
			println("Error reading the file: " + ex);
		}
		return secretWord;
	}
}
