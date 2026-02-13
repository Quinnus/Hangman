package game;

/*simple text representation of the gallows for the game.
Depending on the number of lives remaining, the method will print the appropratie picture
escape character '\' used for the right hand side limbs
*/

public class Gallows {
	static String a = "          +---+\n";
	static String b = "          |   |\n";
	static String c = "          O   |\n";
	static String d = "          |   |\n";
	static String e = "         /|   |\n";
	static String f = "         /|\\  |\n";
	static String g = "         /    |\n";
	static String h = "         / \\  |\n";
	static String i = "              |\n";
	static String j = "       =========";

	public static void printGallows(int lives) {
		if (lives == 6) {
			return;
		} else if (lives == 5) {
			System.out.println(a + b + c);
		} else if (lives == 4) {
			System.out.println(a + b + c + d);
		} else if (lives == 3) {
			System.out.println(a + b + c + e);
		} else if (lives == 2) {
			System.out.println(a + b + c + f);
		} else if (lives == 1) {
			System.out.println(a + b + c + f + g);
		} else if (lives == 0) {
			System.out.println(a + b + c + f + h + i + j);
		}
	}
}