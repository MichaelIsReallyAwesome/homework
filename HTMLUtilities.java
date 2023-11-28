/**
 *	Utilities for handling HTML 
 *
 *	@author	Michael Yeung 
 *	@since	October 31, 2023
 */
public class HTMLUtilities {
	// NONE = not nested in a block, COMMENT = inside a comment block
	// PREFORMAT = inside a pre-format block
	private enum TokenState { NONE, COMMENT, PREFORMAT };
	// the current tokenizer state
	private TokenState state;
	
	public HTMLUtilities() {
		state = TokenState.NONE; //default state is none
	}
	
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
			char let = str.charAt(count); // current letter being analyzed4
			if (state == TokenState.NONE) { //if state not comment or preformatted
				if (inToken) { //if letter is a token
					while (str.charAt(count) != '>' && state != TokenState.COMMENT) { //stores whole token
						//if is comment block
						if (count - 1 >= 0 && str.charAt(count - 1) == '<' 
								&& str.charAt(count) == '!') {
							state = TokenState.COMMENT;
						}
						else {
							token += str.charAt(count);
							count++;
						}
					}
					token += str.charAt(count);
					System.out.print(token);
					if (token.equals("<pre>")) {
						state = TokenState.PREFORMAT;
					}
					else if (state != TokenState.COMMENT) {
						inToken = false;
						result[tokCount] = token;
						tokCount++;
						token = ""; // wipes token
						if (isPunctuation(let)) { //checks if punctuation
							result[tokCount] = "" + let;
							tokCount++;
						}
					}
					
					
					/*
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
					* */
				} else if (inWord) { //if letter is a word
					//if let is a character or a hyphen between two letters
					if (Character.isLetter(let) || count - 1 >= 0
							&& count + 1 < str.length() && Character.isLetter(str.charAt(count - 1))
							&& Character.isLetter(str.charAt(count + 1))
							&& let == '-') {
						token += let;
					}
					else { //figure out punctuation right after tags
						inWord = false; //not in word anymore
						result[tokCount] = token; //stores word
						tokCount++;
						token = ""; //wipes token
						
						if (let == '<') {
							inToken = true;
							count--; //goes back a letter
							//token += let;
						}
						//accounts for all versions of digit
						else if (Character.isDigit(let)) {
							inNumber = true;
							token += let;
						}
						else if (count + 1 < str.length()) {
							//System.out.println(let + " " + token);
							char let2 = str.charAt(count + 1);
							if (let == '-' && Character.isDigit(let2)
									|| let == '.' && Character.isDigit(let2)) {
								inNumber = true;
								token += let;
							}
							else if (count + 2 < str.length()) {
								char let3 = str.charAt(count + 2);
								if (let == '-' && let2 == '.' && Character.isDigit(let3)) {
									inNumber = true;
									token += let;
								}
								else if (isPunctuation(let)) { //checks if punctuation
									result[tokCount] = "" + let; 
									tokCount++;
									System.out.println(let);
								}	
							}	
							else if (isPunctuation(let)) { //checks if punctuation
								result[tokCount] = "" + let; 
								tokCount++;
								System.out.println(let);
							}					
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
							//System.out.println(let);
						}
						else if (let == '<') {
							inToken = true;
							count--;
						}
					}
					
				} else { //else checks if letter is start of token or word
					if (count + 3 < str.length() && let == '<' && str.charAt(count + 1) == '!'
							&& str.charAt(count + 2) == '-' && str.charAt(count + 3) == '-') {
						state = TokenState.COMMENT;
					}
					else if (let == '<') {
						inToken = true;
						token += let;
					}
					else if (Character.isLetter(let)) {
						inWord = true;
						token += let;
					}
					//accounts for all versions of a number
					else if (Character.isDigit(let)) {
						inNumber = true;
						token += let;
					}
					else if (count + 1 < str.length()) {
						
						char let2 = str.charAt(count + 1);
						if (let == '-' && Character.isDigit(let2)
								|| let == '.' && Character.isDigit(let2)) {
							inNumber = true;
							token += let;
						}
						else if (count + 2 < str.length()) {
							char let3 = str.charAt(count + 2);
							if (let == '-' && let2 == '.' && Character.isDigit(let3)) {
								inNumber = true;
								token += let;
							}
						}
					}
					else if (isPunctuation(let)) { //checks if punctuation
						result[tokCount] = "" + let;
						tokCount++;
					}
					/*
					//checks if let is digit or -digit or .digit or -.digit
					else if (count + 2 < str.length()) {
						char let2 = str.charAt(count + 1);
						char let3 = str.charAt(count + 2);
						if (Character.isDigit(let)  
							|| let == '-' && Character.isDigit(let2)
							|| let == '.' && Character.isDigit(let2)
							|| let == '-' && let2 == '.' && Character.isDigit(let3)) {
						inNumber = true;
						token += let;
						}
					}		
					*/			
				}	
				if (token.length() > 0 && count + 1 >= str.length()) {
					result[tokCount] = token;
					tokCount++;
				}
			}
			else if (state == TokenState.PREFORMAT) {
				result[tokCount] = str;
				count = str.length();
				tokCount++;
				if (str.equals("</pre>")) state = TokenState.NONE;
			}
			else if (state == TokenState.COMMENT) {
				//checks if let is -->, where the comment block ends
				if (count + 2 < str.length() && str.charAt(count + 1) == '-' 
						&& str.charAt(count + 2) == '>' && let == '-') {
					count += 2;
					state = TokenState.NONE;		
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
			if (a % 5 == 0) {}//System.out.print("\n  ");
			//System.out.print("[token " + a + "]: " + tokens[a] + " ");
		}
		//System.out.println();
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
