import java.util.Scanner;
/**
 *	Provides utilities for word games:
 *	1. finds all words in the dictionary that match a list of letters
 *	2. prints an array of words to the screen in tabular format
 *	3. finds the word from an array of words with the highest score
 *	4. calculates the score of a word according to a table
 *
 *	Uses the FileUtils and Prompt classes.
 *	
 *	@author Michael Yeung
 *	@since	October 19, 2023
 */
 
public class WordUtils
{
	private String[] dictionaryWords;		// the dictionary of words
	
	// File containing dictionary of almost 100,000 words.
	private final String WORD_FILE = "wordList.txt";
	
	//num words in dictionary file, initialized in constructor
	private final int NUM_DICTIONARY_WORDS;
	
	private String [][] words;	// words that match letters, 1st dimension
								// is number of letters in the word
	private int [] numWords;	// the number of words found
	
	/* Constructor */
	public WordUtils() { 
		//finds number of words in dictionary, sets array to that length
		NUM_DICTIONARY_WORDS = getNumWords();
		dictionaryWords = new String[NUM_DICTIONARY_WORDS];	
		words = new String [NUM_DICTIONARY_WORDS][100];
		numWords = new int [NUM_DICTIONARY_WORDS];
	}
	/**
	 * Returns the number of words in a file
	 * @return number of words in a file
	 */
	public int getNumWords() {
		Scanner input = new Scanner(WORD_FILE);	
		numWords = 0;
		while (input.hasNext()) {
			numWords++;
		}
		return numWord;
		//could have error
	}
	
	/**	Load all of the dictionary from a file into words array. */
	private void loadWords () {
		Scanner input = new Scanner(WORD_FILE);
		for (int i = 0; i < dictionaryWords.length; i++) {
			dictionaryWords[i] = input.next();
		}
	}
	
	/**	Find all words that can be formed by a list of letters.
	 *  @param letters	string containing list of letters
	 *  @return			array of strings with all words found.
	 */
	public String [] findAllWords (String letters)
	{		
		int numValid = 0;
		for (int i = 0; i < NUM_DICTIONARY_WORDS; i++) {
			if (isWordMatch(dictionaryWords[i], letters)) {
				numValid++;
				//int numChars = word.length();
				//words[numChars][numWords[numChars]] = word;
				//numWords[numChars]++;
			}
		}	
		//iterates through dictionary, if letters found in word, word stored
		String[] allWords = new String[numValid];
		int wordCounter = 0;
		for (int i = 0; i < NUM_DICTIONARY_WORDS; i++) {
			if (isWordMatch(dictionaryWords[i], letters)) {
				allWords[wordCounter] = dictionaryWords[i];
				wordCounter++;
			}
		}
		return allWords;
	}
	
	/**
	 *  Decides if a word matches a group of letters.
	 *
	 *  @param word  The word to test.
	 *  @param letters  A string of letters to compare
	 *  @return  true if the word matches the letters, false otherwise
	 */
	public boolean isWordMatch (String word, String letters) {
		for (int a = 0; a < word.length(); a++) {
			char c = word.charAt(a);
			if (letters.indexOf(c) > -1) {
				letters = letters.substring(0, letters.indexOf(c)) +
						letters.substring(letters.indexOf(c) + 1);
			}
			else 
				return false;
		}
		return true;
	}
	
	/**	Print the words found to the screen.
	 *  @param words	array containing the words to be printed
	 */
	public void printWords (String [] wordList) {
		
		
	}
	
	/**	Finds the highest scoring word according to a score table.
	 * 	In the case of a tie, returns the last word in the array
	 * 	which has the tied score.
	 *
	 *  @param wordList  	An array of words to check
	 *  @param scoreTable	An array of 26 integer scores in letter order
	 *  @return   			The word with the highest score
	 */
	public String bestWord (String [] wordList, int [] scoreTable)
	{
		String best = "";
		int score = 0;
		int bestScore = 0;
		//iterates through word list
		for (int i = 0; i < wordList.length; i++) {
			if (getScore(wordList[i], scoreTable) >= bestScore) {
				bestScore = getScore(wordList[i], scoreTable);
				best = wordList[i];
			}
		}
		return best;
	}
	
	/**	Calculates the score of one word according to a score table.
	 *
	 *  @param word			The word to score
	 *  @param scoreTable	An array of 26 integer scores in letter order
	 *  @return				The integer score of the word
	 */
	public int getScore (String word, int [] scoreTable)
	{
		int score;
		//iterates through individual word from word list
		for (int j = 0; j < word.length(); j++) {
			score += scoreTable[word.toLowerCase().charAt(j) - 'a'];
		}
		return score;
	}
	
	/***************************************************************/
	/************************** Testing ****************************/
	/***************************************************************/
	public static void main (String [] args)
	{
		WordUtils wu = new WordUtils();
		wu.run();
	}
	
	public void run() {
		String letters = Prompt.getString("Please enter a list of letters, from 3 to 12 letters long, without spaces");
		String [] word = findAllWords(letters);
		System.out.println();
		printWords(word);
		
		// Score table in alphabetic order according to Scrabble
		int [] scoreTable = {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
		String best = bestWord(word,scoreTable);
		System.out.println("\nHighest scoring word: " + best + "\nScore = " 
							+ getScore(best, scoreTable) + "\n");
	}
}
