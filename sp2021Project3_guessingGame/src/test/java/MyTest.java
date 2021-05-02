import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MyTest {
	
	GameStatus object;
	@BeforeEach
	void start() {
		object = new GameStatus();
	}
	
	@Test
	void test1() {
		assertEquals(false, object.choiceMade);
		
	}
	
	@Test
	void getWordFromCategory(){
		//String marvel[] = {"iron man", "captain america", "black widow", "thor", "hawkeye", "hulk", "scarlet witch", "vision", "falcon", "winter soldier",
		//		"captain marvel", "nick fury", "doctor strange", "spiderman", "war machine"};
		// Only generate a word to guess when a category has been chosen
		assertEquals("",object.wordToGuess);
		object.choiceMade = true;
		object.getWord("Marvel");
		assertNotEquals("",object.wordToGuess);
		
	}
	@Test
	void categoryNotChosen(){
		// Only generate a word to guess when a category has been chosen
		assertEquals("",object.wordToGuess);
		object.choiceMade = false;
		object.getWord("Marvel");
		assertEquals("",object.wordToGuess);
		
	}
	@Test
	void wordToGuessExists(){
		// Only generate a word to guess when a category has been chosen
		String marvel[] = {"iron man", "captain america", "black widow", "thor", "hawkeye", "hulk", "scarlet witch", "vision", "falcon", "winter soldier",
					"captain marvel", "nick fury", "doctor strange", "spiderman", "war machine"};

		assertEquals("",object.wordToGuess);
		object.choiceMade = true;
		object.getWord("Marvel");
		assertNotEquals("",object.wordToGuess);
		
		String guessWord = object.wordToGuess;
		boolean flag = false;
		for (int i = 0; i < marvel.length; i++) {
			if(guessWord.equals(marvel[i])) {
				flag = true;
				break;
			}
		}
		assertEquals(true, flag);
	}
	@Test
	void checkLetterDoesNotExist() {
		// Checking that the checkExists method returns false for characters that don't exist in the guessing string;
		object.wordToGuess = "hulk";
		String letters = "qwertyiopasdfgjzxcvbnm";
		char letter[] = letters.toCharArray();
		for (int i = 0; i < letter.length; i++) {
			assertNotEquals(true, object.checkExists(letter[i]));
		}
	}
	@Test
	void checkLetterExists() {
		// Checking that the checkExists method returns false for characters that don't exist in the guessing string;
		object.wordToGuess = "vision";
		object.attemptIndex = 0;
		char array[] = {'_', '_', 's', '_', '_', '_'};
		object.wordArray = array;
		assertEquals(true, object.checkExists('i'));
		assertEquals(true, object.checkExists('v'));
		assertEquals(true, object.checkExists('o'));
		assertEquals(true, object.checkExists('n'));
		
	}
	@Test
	void checkWordsMatch() {
		// Checking that the checkExists method returns false for characters that don't exist in the guessing string;
		object.wordToGuess = "vision";
		object.attemptIndex = 0;
		char array[] = {'_', '_', 's', '_', '_', '_'};
		object.wordArray = array;
		assertEquals(true, object.checkExists('i'));
		assertEquals(true, object.checkExists('v'));
		assertEquals(true, object.checkExists('o'));
		assertEquals(true, object.checkExists('n'));
		String wordGuessed ="";
		for(int i = 0; i < object.wordArray.length; i++) {
			wordGuessed = wordGuessed + object.wordArray[i];
		}
		assertEquals(object.wordToGuess, wordGuessed);
		
	}
	@Test
	void checkWordsDontMatch() {
		// Checking that the checkExists method returns false for characters that don't exist in the guessing string;
		object.wordToGuess = "vision";
		object.attemptIndex = 0;
		char array[] = {'_', '_', 's', '_', '_', '_'};
		object.wordArray = array;
		assertEquals(true, object.checkExists('i'));
		assertEquals(true, object.checkExists('v'));
		assertEquals(true, object.checkExists('o'));
		assertEquals(true, object.checkExists('p'));
		String wordGuessed ="";
		for(int i = 0; i < object.wordArray.length; i++) {
			wordGuessed = wordGuessed + object.wordArray[i];
		}
		assertNotEquals(object.wordToGuess, wordGuessed);
		
	}
	
	@Test
	void checkWinCounter() {
		// Checking that win counter is incremented when the word is complete
		object.wordToGuess = "vision";
		object.attemptIndex = 0;
		assertEquals(0, object.winCounter);
		assertEquals(false, object.winArray[object.attemptIndex]);
		char array[] = {'_', '_', 's', '_', '_', '_'};
		object.wordArray = array;
		assertEquals(true, object.checkExists('i'));
		assertEquals(true, object.checkExists('v'));
		assertEquals(true, object.checkExists('o'));
		assertEquals(true, object.checkExists('n'));
//		String wordGuessed ="";
//		for(int i = 0; i < object.wordArray.length; i++) {
//			wordGuessed = wordGuessed + object.wordArray[i];
//		}
		assertEquals(1, object.winCounter);
		assertEquals(true, object.winArray[object.attemptIndex]);
	}
	@Test
	void checkWinCounter2() {
		// Checking that win counter is incremented when the word is complete
		object.wordToGuess = "vision";
		object.attemptIndex = 0;
		assertEquals(0, object.winCounter);
		assertEquals(false, object.winArray[object.attemptIndex]);
		char array[] = {'_', '_', 's', '_', '_', '_'};
		object.wordArray = array;
		assertEquals(true, object.checkExists('i'));
		assertEquals(true, object.checkExists('v'));
		assertEquals(true, object.checkExists('o'));
//		String wordGuessed ="";
//		for(int i = 0; i < object.wordArray.length; i++) {
//			wordGuessed = wordGuessed + object.wordArray[i];
//		}
		assertEquals(0, object.winCounter);
		assertEquals(false, object.winArray[object.attemptIndex]);
	}
	
	
	
//	@Test
//	void reset() {
//		
//	}
	

}

