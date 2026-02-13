package game;

import java.util.Scanner;

public class Hangman {

	public static void main(String[] args) {
		boolean replay = true; //used for replay function
		do {
			IO.getList(); //all methods asbstraced to relevant classes
			Play.startGame();
			Play.runGame();

			Scanner input = new Scanner(System.in); //ask user for replay when game is over
			System.out.print("\nPlay again (y/n): ");
			String answer = input.nextLine();

			if (answer.equals("n")) replay = false; //change boolean to false and exit
		} while (replay);
	}
}

