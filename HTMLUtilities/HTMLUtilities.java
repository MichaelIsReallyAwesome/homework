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
		String token = "";
		boolean isToken = false;
		int resultCounter = 0;
		
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '<') {
				isToken = true;
			}
			else if (str.charAt(i) == '>') {
				isToken = false;
				result[resultCounter] = token += str.charAt(i);;
				resultCounter++;
			}
			if (isToken) {
				token += str.charAt(i);
			}			
		}
			
		// return the correctly sized array
		return result;
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
