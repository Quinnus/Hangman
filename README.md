HANGMAN

A simple terminal based Hangman game.
Run the Hangman.java file to start the game.
A prompt will ask for your name, followed by brief intructions.

The game loops as follows:
- the hidden word is displayed as a list of underscores ' _ '
- the player is prompted to guess a letter
- if the hidden word contains that letter, it is displayed (one or multiple times, depending on the word)
- if it doesn't the player looses a life
- the player is asked if they would like to guess the word each round - this impacts their score as that is calculated as a function of the number of rounds completed
- if they guess wrong, a life is lost, if they guess correctly, it's game over
- if the player guesses the word wrong, or chooses not to attempt a guess, thw game loops back to a prompt for a letter
- this continues until either the player runs out of lives or reveals the word

Any time the player looses a life, a simple representation of a gallows is printed on screen, displaying progress.  This graphic is updated based on the number of remaining lives.
Each time the player is prompted to enter a letter, a list of previously used characters is displayed, to avoid double entry.
At the end of a game, the players score will be added to the high score list, and saved.
The player will then be prompted to either play again, or exit.
As the scores are saved, they will be reloaded the next time the game is opened.
