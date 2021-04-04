import java.io.Serializable;
import java.util.HashMap;
import java.util.*;
public class WordInfo  implements Serializable {
	private String wordToGuess;
	private String currentCategory;
	
	private String cat1[] = {"iron man", "captain america", "black widow", "thor", "hawkeye", "hulk", "scarlet witch", "vision", "falcon", "winter soldier",
			"captain marvel", "nick fury", "doctor strange", "spiderman", "war machine"};
	
	private String cat2[] = {"meryl streep", "tom hanks", "leonardo dicaprio", "taylor swift", "anne hathaway", "robert de niro", "emily blunt",
			"scarlett johannson", "beyonce", "oprah winfrey", "tom cruise", "halle berry"};
	
	private String cat3[] = {"pizza", "pasta", "yogurt", "burger", "burrito", "taco", "quesadilla", "avocado", "milkshake", "banana", "sandwich", "chicken wings"};
	
	private String cat4[] = {"Target", "Apple", "Sony", "Champion", "toyota", "ikea", "reebok", "puma", "adidas", "nike", "samsung", "microsoft", "amazon", "nintendo"};
	
	private String cat5[] = {"USA", "india", "switzerland", "germany", "france", "kenya", "austrailia", "brazil", "argentina", "chile", "italy", "canada", "mexico", "singapore", "thailand", "japan"};
	
	private String cat6[] = {"Alligator", "Antelope", "Bear", "Butterfly", "Cobra", "Deer", "Elephant", "Flamingo", "Goldfish", "Kangaroo", "Leopard", "Mongoose", "Rhinoceros", "Zebra"};
	
	
	
	
	private HashMap<String, String[]> categories;
	WordInfo(){
		categories = new HashMap<String, String[]>();
		categories.put("Marvel", cat1);
		categories.put("Celebrities", cat2);
		categories.put("Food", cat3);
		categories.put("Brands", cat4);
		categories.put("Countries", cat5);
		categories.put("Animals", cat6);
		
	}
	
	public String getWord(String category) {
		Random randomNumber = new Random();
		String tempArray[] = categories.get(category);
		int index = randomNumber.nextInt(tempArray.length - 1);
		currentCategory = category;
		wordToGuess = tempArray[index];
		return wordToGuess;
	}
	
	
}