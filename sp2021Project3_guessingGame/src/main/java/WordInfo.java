// client side
import java.io.Serializable;
import java.util.HashMap;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.util.*;
public class GameStatus  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String wordToGuess;
	public String currentCategory;
	public String current_progress;
	public char guess_letter;
	public String GuessingString = "";
	public char wordArray[];
	boolean choiceMade;
	boolean sentChar;
	boolean validChar;
	boolean winFlag;
	int countWrong;
	int winCounter;
	int attemptsLeft[] = {3, 3, 3};
	boolean winArray[] = {false, false, false};
	int attemptIndex;
	String clientMessage;
	
	// All the Arraylist elements.
	private String cat1[] = {"iron man", "captain america", "black widow", "thor", "hawkeye", "hulk", "scarlet witch", "vision", "falcon", "winter soldier",
			"captain marvel", "nick fury", "doctor strange", "spiderman", "war machine"};
	
	private String cat2[] = {"meryl streep", "tom hanks", "leonardo dicaprio", "taylor swift", "anne hathaway", "robert de niro", "emily blunt",
			"scarlett johannson", "beyonce", "oprah winfrey", "tom cruise", "halle berry"};
	
	private String cat3[] = {"pizza", "pasta", "yogurt", "burger", "burrito", "taco", "quesadilla", "avocado", "milkshake", "banana", "sandwich", "chicken wings"};
	
	private String cat4[] = {"target", "apple", "sony", "champion", "toyota", "ikea", "reebok", "puma", "adidas", "nike", "samsung", "microsoft", "amazon", "nintendo"};
	
	private String cat5[] = {"usa", "india", "switzerland", "germany", "france", "kenya", "austrailia", "brazil", "argentina", "chile", "italy", "canada", "mexico", "singapore", "thailand", "japan"};
	
	private String cat6[] = {"alligator", "antelope", "bear", "butterfly", "cobra", "deer", "elephant", "flamingo", "goldfish", "kangaroo", "leopard", "mongoose", "rhinoceros", "zebra"};
	
	
	
	
	private HashMap<String, String[]> categories;
	// constructor
	GameStatus(){
		attemptIndex = -1;
		winCounter = 0;
		countWrong = 0;
		sentChar = false;
		validChar = false;
		winFlag = false;
		wordToGuess = "";
		currentCategory = "";
		current_progress = "";
		guess_letter = '\0';
		choiceMade = false;
		categories = new HashMap<String, String[]>();
		categories.put("Marvel", cat1);
		categories.put("Celebrities", cat2);
		categories.put("Food", cat3);
		categories.put("Brands", cat4);
		categories.put("Countries", cat5);
		categories.put("Animals", cat6);
		
	}
	
	// stores the word in wordToGuess string.
	public void getWord(String category) {
		Random randomNumber = new Random();
		String tempArray[] = categories.get(category);
		int index = randomNumber.nextInt(tempArray.length - 1);
		currentCategory = category;
		wordToGuess = tempArray[index];
		choiceMade = true;
	}
	
	// making a default string with random words.
	public void makeGuessingWord() {
		String word = wordToGuess;
		Random randomNumber = new Random();
		int index = randomNumber.nextInt(word.length() - 1);
		char temp[] = wordToGuess.toCharArray();
		char ch = word.charAt(index);
		wordArray = new char[word.length()];
		// Updating the string through the loop with random words and "_"
		for (int i = 0; i < word.length(); i++) {
			if(temp[i] == ch) {
				wordArray[i] = ch;
			} else if (temp[i] == ' '){
				wordArray[i] = ' ';
			} else {
				wordArray[i] = '_';
			}
			GuessingString = GuessingString + wordArray[i] + " ";
		}
		GuessingString = GuessingString.toUpperCase();
		System.out.println("WORD CHOSEN : " + word);
		System.out.println("STRING GENERATED : " + GuessingString);
	}
	
	// Checks if the character is valid or not
	public boolean checkExists(char ch) {
		String dummy = "";
		for (int i = 0; i < wordToGuess.length(); i++) {
			//System.out.println(wordArray);
			if(wordToGuess.charAt(i) == ch) {
				wordArray[i] = ch;
				validChar = true;
			}
		}
		if(validChar) {
			winFlag = true;
			for (int i = 0; i < wordToGuess.length(); i++) {
				if(wordArray[i] == '_') {
					winFlag = false;
				}
				dummy = dummy + wordArray[i] + " ";
			}
			GuessingString = dummy;
			if(winFlag) {
				//GuessingString = "YAYY!";
				winArray[attemptIndex] = true;
				winCounter++;
			}
		}
		return validChar;
		
	}
}
