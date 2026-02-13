package game;

import java.util.ArrayList;
import java.util.Scanner;

public class Play {
	static String randomWord = IO.pickWord(); //setup required variables and arraylist
	static String playerName;
	static Scanner scanner = new Scanner(System.in);
	static boolean[] revealed;
	static int lives = 6;
	static int round = 1;
	static ArrayList<String> usedLetters = new ArrayList<>();
	static boolean playing = true;


	public static void startGame() {
		lives = 6; //reset key game variables here, so a replay is started with no carryover
		round = 1;
		playing = true;
		usedLetters.clear();
		randomWord = IO.pickWord(); //use pickWord helper to randomly pull a word
		revealed = new boolean[randomWord.length()]; //setup a hidden array to track letters that have been guessed correctly

		System.out.print("\nEnter your name: "); //game instructions and player input
		playerName = scanner.nextLine();
		System.out.println("Welcome to HangMan, " + playerName + "!\n");
		System.out.println("""
				For each round, enter a single letter.
				If the hidden word contains that letter, it will be revealed.
				If not, you lose a life.
				Each round you may attempt to guess the word.
				Be careful, as a wrong guess costs a life. You begin with 6.
				Try to get your name on the High Score table!.
				=============================================================
				""");
		System.out.println("Current High Scores");
		IO.loadScores(); //load and display current high scores
	}


	public static void runGame() {


		int wordLength = randomWord.length();
		String[] wordSplit = randomWord.split(""); //split the random word into an array of individual characters


		while (lives > 0 && playing) { //main game logic in a do:while loop to allow for exiting back to main, which facilitates the 'play again' option
			try {
				System.out.println("=============================================================\n");
				System.out.println("Round: " + round + "\t\tLives: " + lives);
				for (int i = 0; i < wordLength; i++) { //loop prints out underscore for each letter in the word
					if (revealed[i]) { //if the corresponding 'revealed' array elemebnt is 'true', the letter is didplayed rather than an underscore
						System.out.print(wordSplit[i] + " ");
					} else System.out.print("_ ");
				}
				System.out.print("\nUsed letters: ");
				for (String used : usedLetters) { // print the array containing already entered letteers to avoid repeats
					System.out.print(used + " ");
				}
				System.out.println();
				if (round != 1) {
					guessWord(); //guessWord allows for the player to attempt a guess at the full word
				}
				System.out.print("Guess a letter: ");
				char input = scanner.next().charAt(0);
				String letter = Character.toString(input).toLowerCase();
				usedLetters.add(letter); //array for already used letters
				checkLetter(letter, randomWord, wordSplit, wordLength); //helper to check if the word contains the inputted letter
			} catch (Exception e) {
				System.out.println("Something went wrong."); //error handling
			}
		}
		System.out.println("\nYou ran out of lives!\nThe word was: " + randomWord + "\nGAME OVER!");
		playing = false;
		System.out.println("=============================================================\n");
		//when lives run out, display message and set boolean playing to false - returns player to start and option to play again

	}

	public static void checkLetter(String letter, String word, String[] wordSplit, int length) {
		round++;
		if (!word.contains(letter)) { //check if the in inputted letter is in the hidden word - if not loose a life
			lives--;
			System.out.println("You lost a life!");
			Gallows.printGallows(lives); // print the gallows with the correct number of stages
			return;
		}
		System.out.println("You found a letter!");
		for (int i = 0; i < length; i++) {
			if (wordSplit[i].equals(letter)) {
				revealed[i] = true; // the guessed letter is in the word, flip the corresponding related boolean index to true
			}
		}

		for (boolean b : revealed) { //check if all letters have been guessed - if not return to guessing
			if (!b) return; //if any entry is false, the entire word has not been guessed
		}
		for (int i = 0; i < word.length(); i++) {
			System.out.print(wordSplit[i] + " ");
		}
		System.out.println("\n\nCongratulations! You Win!"); //all letters have been guessed
		int myScore = Scores.calcScore(lives, round); //calculate player score
		System.out.println("You scored: " + myScore + "!\n");
		Scores.addScore(playerName, myScore); //add player score to arraylist
		System.out.println("=============================================================\n");
		playing = false; //exit do loop and return to  main, prompting player to play again
	}

	public static void guessWord() { // player is offered a chance to guess the entire word each round
		System.out.println("Do you want to guess the word? Enter y or n");
		char guess = scanner.next().charAt(0);//confirm yes or no
		scanner.nextLine();
		String guessYN = Character.toString(guess).toLowerCase();
		if (guessYN.equals("y")) {
			System.out.print("Please enter your guess: ");//if yes, enter guess, other wise loop exist and returns to guessing letters
			String guessedWord = scanner.nextLine();
			if (guessedWord.equals(randomWord)) {
				System.out.println("You guessed correctly! You Win!");//if player guesses correctly, they win
				int myScore = Scores.calcScore(lives, round);//calculate the score
				System.out.println("You scored: " + myScore + "!\n");
				Scores.addScore(playerName, myScore);//add the score to arraylist
				System.out.println("=============================================================\n");
				playing = false; //boolean set to false, retruning back to main
			} else {
				System.out.println("You got that wrong!\nYou loose a life"); // guess is incorrect, loose a life and back to guessing
				lives--;
				Gallows.printGallows(lives);
				System.out.println("Current lives: " + lives);
			}
		}
	}
}
