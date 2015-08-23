package hangman;

import java.util.ArrayList;
import java.util.Scanner;

public class GameHangman {
	
	/**
	 * @author Bojan Aleksic
	 * 
	 * (Game: hangman) Write a hangman game that randomly generates a word and
	 * prompts the user to guess one letter at a time, as shown in the sample run. Each
	 * letter in the word is displayed as an asterisk. When the user makes a correct
	 * guess, the actual letter is then displayed. When the user finishes a word, display
	 * the number of misses and ask the user whether to continue to play with another
	 * word. Declare an array to store words, as follows:
	 * // Add any words you wish in this array
	 * String[] words = {"program", "apple", ...};
	 * (Guess) Enter a letter in word ******* > p
	 * (Guess) Enter a letter in word p****** > r
	 * (Guess) Enter a letter in word pr**r** > p
	 * p is already in the word
	 * (Guess) Enter a letter in word pr**r** > o
	 * (Guess) Enter a letter in word pro*r** > g
	 * (Guess) Enter a letter in word progr** > n
	 * n is not in the word
	 * (Guess) Enter a letter in word progr** > m
	 * (Guess) Enter a letter in word progr*m > a
	 * The word is program. You missed 1 time
	 * Do you want to guess another word? Enter "y" or "n">
	 */

////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	/** Counter for missed guesses */
	public static int missedCounter = 0;
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	/** Method for storing letter into ArrayList if guess is correct */
	public static void notGuess(char[] rand2, Scanner input, ArrayList<Character> list, String[] randWord) {
		
		System.out.println("\n(Guess) Enter a letter in word: ");
		char guess = input.next().charAt(0); // User's character input
		
		/** Create boolean to check whether guessing letter is correct */
		boolean isLetterCorrect = true;
		
		for(int i = 0; i < rand2.length; i++) {
			
			/** While guessing letter match to letter in rand2[] charArray */
			while(guess == rand2[i]) {
				
				/** store that letter(rand2[i]) into ArrayList at position "i",
				 * and replace asterisk with that letter at that position using set() method
				 * note: use for loop to determine if there is a more than one same letters in the word  */
				for(int j = 0; j < rand2.length; j++) {
					if(guess == rand2[j]) {
						list.set(j, rand2[j]);
					}
				}
				
				for(Character l: list) {
					System.out.print(l + " ");
				}
				
				/** Create StringBuilder to copy guessing letters from the list into it, and then convert
				 * StringBuilder's word into String using toString() method, to obtain a word as a whole, 
				 * rather than single letters.
				 * Then compare whole word with randWord[0], and if two words are equal, game is over. */
				StringBuilder sb = new StringBuilder(list.size());
				for (Character l : list) {
					sb.append(l);
				}
				String result = sb.toString();
				if(result.equals(randWord[0])) {
					System.out.println();
					System.out.println("\nGame is over. \nWord is: " + randWord[0]);
					System.out.println("\nYou missed: " + missedCounter + " times.");
					System.exit(1);
				}
				
				System.out.println("\n(Guess) Enter another letter in word: ");
				guess = input.next().charAt(0);
				
				/** Reset iteration to 0, so it can start iterate the loop 
				 * from the beginning of rand2[] charArray. */
				i = 0; 
			}
			
			/** Check if letter already have been guessed */
			for(int j = 0; j < list.size(); j++) {
				if(list.get(j).equals(guess)) {
					System.out.println("Letter " + guess + " is already in the word.");
					break;
				}
			}
			
			/** If letter is not correct, set boolean to false and 
			 * exit the loop and prompt for another guess letter */
			if(guess != rand2[i]) {
				/** Letter is not correct, set boolean to false */
				isLetterCorrect = false;
			}
			
		}
		
		/** If letter is not correct, display message about incorrect letter */
		if(isLetterCorrect == false) {
			System.out.println("\"" + guess + "\" is not in the word.");
			missedCounter++;
		}
		
	}
	
//////////////////////////////////////////////////////////////////////////////
	                       /** Main */
//////////////////////////////////////////////////////////////////////////////	

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		/** Initialize array of words */
		String[] words = {"apple", "program", "tree", "city", "game", "fox",
				          "junk", "bakery", "candy", "drink", "taste", "gravity",
				          "salt", "space", "earth", "smoke", "reward", "table",
				          "dance", "sport", "ball", "picture", "lake", "mountain",
				          "rocket", "weather", "storm", "continent", "music"};
		
		/** One random word extracted from words array */
		String[] randWord = new String[1];
		
		/** ArrayList of characters for storing a single random word */
		ArrayList<Character> list = new ArrayList<>();
		
		/** Assign single random word to the randWord array */
		for(int i = 0; i < words.length; i++) {
			int rand = (int)(Math.random() * words.length);
			randWord[0] = words[rand];
		}
		
		/** Convert random word (twice) into character array 
		 * - First one (rand) is meant for storing asterisks in it
		 * - Second one (rand2) is for storing actual letters */
		char[] rand = randWord[0].toCharArray();
		char[] rand2 = randWord[0].toCharArray();
		
		/** First rand word make to contain asterisks only */
		for(int i = 0; i < rand.length; i++) {
			rand[i] = '*'; // Assign asterisks into rand[] charArray
			list.add(rand[i]); // Initially add asterisks into ArrayList
			System.out.print(rand[i] + " "); // Display asterisks in the length of the word
		}
		
		/** Prompt the user to guess a letter by invoking a notGuess() method */
		for(int i = 0; i < 100; i++) {
			/** Invoke notGuess() method to continue with guessing */
			notGuess(rand2, input, list, randWord);
		}
		
		input.close();
	}
}
