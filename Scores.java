package game;

import java.io.*;
import java.util.*;

public class Scores {

	private String playerName;
	private int playerScore;
	static ArrayList<Scores> highScoreList = new ArrayList<>();

	public Scores(String name, int score) { //score object to handle names and score, avoiding management of 2 separate arrays
		this.playerName = name;
		this.playerScore = score;
	}

	public int getPlayerScore() {
		return this.playerScore;
	} //used for writing score to to file

	public String getPlayerName() {
		return this.playerName;
	} //as above

	public static int calcScore(int lives, int rounds) { //fabricated scoring mechanism, forcing use of a double cast to int (1000.0 multiplier)
		return (int) ((1000.0 * lives) / (rounds * 10));
	}


	public static void addScore(String name, int score) {

		highScoreList.add(new Scores(name, score)); //when a new player finishes, add their score to arraylist
		highScoreList.sort(Comparator.comparingInt(Scores::getPlayerScore).reversed());//sort the arraylist based on score value only
		if (highScoreList.size() > 5) { //high score list limited to 5 entries
			highScoreList.remove(5);
		}
		IO.writeScores();//write the updated file to storage
	}


}
