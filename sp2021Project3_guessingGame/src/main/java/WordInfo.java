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
	
	private String cat1[] = {"iron man", "captain america", "black widow", "thor", "hawkeye", "hulk", "scarlet witch", "vision", "falcon", "winter soldier",
			"captain marvel", "nick fury", "doctor strange", "spiderman", "war machine"};
	
	private String cat2[] = {"meryl streep", "tom hanks", "leonardo dicaprio", "taylor swift", "anne hathaway", "robert de niro", "emily blunt",
			"scarlett johannson", "beyonce", "oprah winfrey", "tom cruise", "halle berry"};
	
	private String cat3[] = {"pizza", "pasta", "yogurt", "burger", "burrito", "taco", "quesadilla", "avocado", "milkshake", "banana", "sandwich", "chicken wings"};
	
	private String cat4[] = {"Target", "Apple", "Sony", "Champion", "toyota", "ikea", "reebok", "puma", "adidas", "nike", "samsung", "microsoft", "amazon", "nintendo"};
	
	private String cat5[] = {"USA", "india", "switzerland", "germany", "france", "kenya", "austrailia", "brazil", "argentina", "chile", "italy", "canada", "mexico", "singapore", "thailand", "japan"};
	
	private String cat6[] = {"Alligator", "Antelope", "Bear", "Butterfly", "Cobra", "Deer", "Elephant", "Flamingo", "Goldfish", "Kangaroo", "Leopard", "Mongoose", "Rhinoceros", "Zebra"};
	
	
	
	
	private HashMap<String, String[]> categories;
	GameStatus(){
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
	
	public void getWord(String category) {
		Random randomNumber = new Random();
		String tempArray[] = categories.get(category);
		int index = randomNumber.nextInt(tempArray.length - 1);
		currentCategory = category;
		wordToGuess = tempArray[index];
		choiceMade = true;
		//return wordToGuess;
	}
	
	public void makeGuessingWord() {
		// PARTH -> 2
		// __R__
		// PRANAV -> 
		// __A_A_
		String word = wordToGuess;
		Random randomNumber = new Random();
		int index = randomNumber.nextInt(word.length() - 1);
		char temp[] = word.toCharArray();
		char ch = word.charAt(index);
		wordArray = new char[word.length()];
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
//		return GuessingString;
	}
	
	
}
