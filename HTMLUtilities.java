/**
 *	Utilities for handling HTML 
 *
 *	@author	Michael Yeung 
 *	@since	October 31, 2023
 */
public class HTMLUtilities {

	/**
	 *	Break the HTML string into tokens. The array returned is
	 *	exactly the size of the number of tokens in the HTML string.
	 *	Example:	HTML string = "Goodnight moon goodnight stars"
	 *				returns { "Goodnight", "moon", "goodnight", "stars" }
	 *	@param str			the HTML string
	 *	@return				the String array of tokens
	 */
	public String[] tokenizeHTMLString(String str) {
		// make the size of the array large to start
		String[] result = new String[10000];
		for (int i = 0; i < result.length; i++) result[i] = "";
		
		String token = ""; //current token
		boolean inToken = false; //whether letter is in token
		boolean inWord = false; 
		boolean inNumber = false;
		int tokCount = 0; // counter of number of tokens
		int count = 0; //index of letter in word
		
		while (count < str.length()) {
			char let = str.charAt(count); // current letter being analyzed
			if (inToken) { //if letter is a token
				if (let == '>') {
					inToken = false;
					result[tokCount] = token + let;
					tokCount++;
					token = ""; // wipes token
					if (isPunctuation(let)) { //checks if punctuation
						result[tokCount] = "" + let;
						tokCount++;
					}
				}
				else {
					token += let;
				}
			} else if (inWord) { //if letter is a word
				//if let is a character or a hyphen between two letters
				if (Character.isLetter(let) || count - 1 >= 0
						&& count + 1 < str.length() && Character.isLetter(str.charAt(count - 1))
						&& Character.isLetter(str.charAt(count + 1))
						&& let == '-') {
					token += let;
				}
				else {
					inWord = false; //not in word anymore
					result[tokCount] = token; //stores word
					tokCount++;
					token = ""; //wipes token
					if (isPunctuation(let)) { //checks if punctuation
						result[tokCount] = "" + let; 
						tokCount++;
					}
				}
			} else if (inNumber) { //if letter is a number
				//will not work if letter sandwiched between numbers?, multiple special cases in num
				//not a number anymore, ensure functionality of hyphen or e
				if (Character.isDigit(let) || let == 'e' || let == '-' || let == '.') {
					token += let;
				}
				else {
					inNumber = false;
					result[tokCount] = token;
					tokCount++;
					token = ""; // wipes token
					if (isPunctuation(let)) { //checks if punctuation
						result[tokCount] = "" + let;
						tokCount++;
					}
					else if (let == '<') {
						inToken = true;
						token += let;
					}
				}
				
				
			} else { //else checks if letter is start of token or word
				if (let == '<') {
					inToken = true;
					token += let;
				}
				else if (Character.isLetter(let)) {
					inWord = true;
					token += let;
				}
				else if (Character.isDigit(let) || count + 1 < str.length() 
						&& Character.isDigit(str.charAt(count + 1)) && let == '-') {
					inNumber = true;
					token += let;
				}
				else if (isPunctuation(let)) { //checks if punctuation
					result[tokCount] = "" + let;
					tokCount++;
				}
			}			
			count++;	
		}
		
		// makes correctly sized array
		String[] sizedResults = new String[tokCount];
		for (int i = 0; i < sizedResults.length; i++) {
			sizedResults[i] = result[i];
		}
		// return the correctly sized array
		return sizedResults;
	}
	
	/**
	 *	Print the tokens in the array to the screen
	 *	Precondition: All elements in the array are valid String objects.
	 *				(no nulls)
	 *	@param tokens		an array of String tokens
	 */
	public void printTokens(String[] tokens) {
		if (tokens == null) return;
		for (int a = 0; a < tokens.length; a++) {
			if (a % 5 == 0) System.out.print("\n  ");
			System.out.print("[token " + a + "]: " + tokens[a] + " ");
		}
		System.out.println();
	}
	
	/**
	 * Returns whether or not a char is a punctuation character
	 * @return if a char is punctuation
	 */
	public boolean isPunctuation(char let) {
		switch (let) {
			case '.': case ',': case ';': case ':': case '(': case ')': 
			case '?': case '!': case '=': case '&': case '~': case '+': 
			case '-':
			return true;
		}
		return false;
	}
}
