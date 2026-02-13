package game;

import java.io.*;
import java.util.*;

public class IO {
	static String filename = "src/game/words.txt"; //setup dictionary file to be read into memory
	static ArrayList<String> wordList = new ArrayList<>(); //use an arraylist for flexibility in size of dictionary
	static int numWords; // count the number of words in dictionary so random picker uses all words
	static String scoreFile = "src/game/scores.csv"; //label the file containing scores


	public static void getList() {
		try {
			File file = new File(filename); //use a scanner to read in the dictionary file
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String addWord = scanner.nextLine();
				wordList.add(addWord);//add each word to the arraylist
			}
		} catch (FileNotFoundException e) {
			System.out.println("Error: " + e.getMessage()); //error handling
		}
	}

	public static String pickWord() {
		numWords = wordList.size(); //establish the number of items in the arraylist
		Random rng = new Random();
		int value = rng.nextInt(numWords); //pick a random number in the range of the length of the arraylist
		return wordList.get(value);//pick the corresponding word
	}

	public static void loadScores() {
		try (BufferedReader reader = new BufferedReader(new FileReader(scoreFile))) { //load and parse the high scores using a reader
			String line;
			System.out.printf("%-15s %s\n", "Name", "Score");//format the scores to keep them justified
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(",");//split the csv wcore into parts for name and score
				if (parts.length < 2) { //error check on entry - ignore if there arent 2 parts
					continue;
				}
				String name = parts[0].trim();
				int score = Integer.parseInt(parts[1].trim());//convert the score string to integer
				Scores.highScoreList.add(new Scores(name, score)); //convert each entry to a score object on reading and add to arraylist
				System.out.printf("%-15s %s\n", parts[0], parts[1]);//formatted printout similar to header
			}
		} catch (IOException e) {
			System.out.println("Error reading file: " + e.getMessage()); //error handling
		}


	}

	public static void writeScores() {
		try (PrintWriter writer = new PrintWriter(new FileWriter(scoreFile))) { //rever operation writing updated scores
			for (Scores s : Scores.highScoreList) {
				writer.println(s.getPlayerName() + "," + s.getPlayerScore());//use getters to pull from score object arraylist
			}
		} catch (IOException e) {
			System.out.println("Error writing file: " + e.getMessage());//error handling
		}
	}


}
