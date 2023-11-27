/**
 *	HTMLRender
 *	This program renders HTML code into a JFrame window.
 *	It requires your HTMLUtilities class and
 *	the SimpleHtmlRenderer and HtmlPrinter classes.
 *
 *	The tags supported:
 *		<html>, </html> - start/end of the HTML file
 *		<body>, </body> - start/end of the HTML code
 *		<p>, </p> - Start/end of a paragraph.
 *					Causes a newline before and a blank line after. Lines are restricted
 *					to 80 characters maximum.
 *		<hr>	- Creates a horizontal rule on the following line.
 *		<br>	- newline (break)
 *		<b>, </b> - Start/end of bold font print
 *		<i>, </i> - Start/end of italic font print
 *		<q>, </q> - Start/end of quotations
 *		<hX>, </hX> - Start/end of heading with size X = 1, 2, 3, 4, 5, 6
 *		<pre>, </pre> - Preformatted text
 *
 *	@author Michael Yeung
 * 	@since November 16, 2023
 *	@version 1.0
 */

import java.util.Scanner;
public class HTMLRender {
	
	// the array holding all the tokens of the HTML file
	private String [] tokens;
	private final int TOKENS_SIZE = 100000;	// size of array

	// SimpleHtmlRenderer fields
	private SimpleHtmlRenderer render;
	private HtmlPrinter browser;
	
	//object of html utilities
	private HTMLUtilities util;	
	private int tokenCounter;
	private int lineLimit; //how many chars can be in each line
	private int lineCount; //how many chars are in each line

	private int tokLoop; //index of the while loop going through tokens
	private enum TextState {NONE, PREFORMAT, BOLD, ITALIC, H1, H2, H3, H4, H5, H6};
	private TextState state = TextState.NONE;

	int num;

	public HTMLRender() {
		// Initialize token array
		tokens = new String[TOKENS_SIZE];
		
		// Initialize Simple Browser
		render = new SimpleHtmlRenderer();
		browser = render.getHtmlPrinter();
		
		util = new HTMLUtilities();

		tokenCounter = 0;
		tokLoop = 0;
		lineLimit = 80;
		lineCount = 0;

		num = 0;
	}
	
	
	public static void main(String[] args) {
		HTMLRender hf = new HTMLRender();
		if (args.length > 0)
			hf.storeTokens(args[0]);
		// otherwise print out usage message
		else {
			System.out.println("Usage: java HTMLTester <htmlFileName>");
			System.exit(0);
		}
		hf.run();
	}
	
	public void run() {
		for (int i = 0; i < num; i++) {
			System.out.println(tokens[i]);
		}
		String token = ""; //holds current token
		while (tokens[tokLoop] != null) { //(tokLoop < TOKENS_SIZE) { //
			token = tokens[tokLoop];
			//System.out.println(token);
			if (state == TextState.NONE) { 
				//checks if state is to be changed
				if (token.equalsIgnoreCase("<b>")) {
					state = TextState.BOLD;
				}
				else if (token.equalsIgnoreCase("<i>")) {
					state = TextState.ITALIC;
				}
				else if (token.equalsIgnoreCase("<pre>")) {
					state = TextState.PREFORMAT;
				}
				else if (token.equalsIgnoreCase("<h1>")) {
					state = TextState.H1;
					lineLimit = 40;
				}
				else if (token.equalsIgnoreCase("<h2>")) {
					state = TextState.H2;
					lineLimit = 50;
				}
				else if (token.equalsIgnoreCase("<h3>")) {
					state = TextState.H3;
					lineLimit = 60;
				}
				else if (token.equalsIgnoreCase("<h4>")) {
					state = TextState.H4;
					lineLimit = 80;
				}
				else if (token.equalsIgnoreCase("<h5>")) {
					state = TextState.H5;
					lineLimit = 100;
				}
				else if (token.equalsIgnoreCase("<h6>")) {
					state = TextState.H6;
					lineLimit = 120;
				}
				else if (token.equalsIgnoreCase("</p>")) {
					browser.println();
					lineCount = 0;
				}
				else if (token.equalsIgnoreCase("<p>")) {} //do nothing
				else if (token.equalsIgnoreCase("<hr>")) {
					browser.printHorizontalRule();
				}
				else if (token.equalsIgnoreCase("<br>")) {
					browser.printBreak();
				}
				else { //if none of the above, verifies line char limit, prints word out normally
					printStandardText(token);
				}
			}
			else { //print tags with state/change them back if necessary
				if (state == TextState.BOLD) {
					if (token.equalsIgnoreCase("</b>")) {
						state = TextState.NONE;
					}
					else if (token.equalsIgnoreCase("<br>")) {
						browser.printBreak();
					}
					else if (token.equalsIgnoreCase("<hr>")) {
						browser.printHorizontalRule();
					}
					else {
						printStandardText(token);
					}
				}
				else if (state == TextState.ITALIC) {
					if (token.equalsIgnoreCase("</i>")) {
						state = TextState.NONE;
					}
					else if (token.equalsIgnoreCase("<br>")) {
						browser.printBreak();
					}
					else if (token.equalsIgnoreCase("<hr>")) {
						browser.printHorizontalRule();
					}
					else {
						printStandardText(token);
					}
				}
				else if (state == TextState.PREFORMAT) {
					
				}
				else if (state == TextState.H1) {
					
				}
				else if (state == TextState.H2) {
					
				}
				else if (state == TextState.H3) {
					
				}
				else if (state == TextState.H4) {
					
				}
				else if (state == TextState.H5) {
					
				}
				else if (state == TextState.H6) {
					
				}
			}

			tokLoop++;
		}
		// Sample renderings from HtmlPrinter class
		
		// Print plain text without line feed at end
		browser.print("First line");
		
		// Print line feed
		browser.println();
		
		// Print bold words and plain space without line feed at end
		browser.printBold("bold words");
		browser.print(" ");
		
		// Print italic words without line feed at end
		browser.printItalic("italic words");
		
		// Print horizontal rule across window (includes line feed before and after)
		browser.printHorizontalRule();
		
		// Print words, then line feed (printBreak)
		browser.print("A couple of words");
		browser.printBreak();
		browser.printBreak();
		
		// Print a double quote
		browser.print("\"");
		
		// Print Headings 1 through 6 (Largest to smallest)
		browser.printHeading1("Heading1");
		browser.printHeading2("Heading2");
		browser.printHeading3("Heading3");
		browser.printHeading4("Heading4");
		browser.printHeading5("Heading5");
		browser.printHeading6("Heading6");
		
		// Print pre-formatted text (optional)
		browser.printPreformattedText("Preformat Monospace\tfont");
		browser.printBreak();
		browser.print("The end");
		
	}
	/*
	 * Prints out a token as a string, taking into account spaces between words,
	 * quotation tags, text state (bold, italic, etc) and character limits per line. 
	 */
	private void printStandardText(String token) {
		if (lineCount + token.length() + 1 > lineLimit) { //if token doesn't fits within line
			if ((token.equalsIgnoreCase("<q>") || token.equalsIgnoreCase("</q>"))
					&& lineCount + 1 <= lineLimit) { //if token is "
				browser.print("\"");
				lineCount++;
			}
			else {
				browser.println();
				lineCount = 0;
				tokLoop--;
			}
		}
		else { //if token fits within line
			//System.out.println(token);
			//punctuation marks don't have an added space
			if (token.equals("!") || token.equals("?") || token.equals(".")) {
				if (state == TextState.BOLD) browser.printBold(token);
				else if (state == TextState.ITALIC) browser.printItalic(token);
				else browser.print(token);
				lineCount++;
			}
			else if (lineCount == 0) { //no extra space at beginning of line
				if (state == TextState.BOLD) browser.printBold(token);
				else if (state == TextState.ITALIC) browser.printItalic(token);
				else browser.print(token);
				lineCount += token.length();
			}
			else { //normal words that have one space between them
				if (state == TextState.BOLD) browser.printBold(" " + token);
				else if (state == TextState.ITALIC) browser.printItalic(" " + token);
				else browser.print(" " + token);
				lineCount += token.length() + 1;
			}
		}
	}
	/* Stores HTML tokens from a file into a String array */
	private void storeTokens(String fileName) {
		Scanner input = FileUtils.openToRead(fileName);
		//Stores all the tokens from each line into a big array
		while (input.hasNextLine()) {
			String [] lineTokens = util.tokenizeHTMLString(input.nextLine());
			//for (int i = 0; i < lineTokens.length; i++) {
				//tokens[tokenCounter + i] = lineTokens[i]; //tokens[tokenCounter] = lineTokens[i];
				//tokenCounter++;
				for (int i = 0; i < lineTokens.length; i++) {
					tokens[tokenCounter+i] = lineTokens[i];
					num++;
				}
			
			
			//}
			// Updates the number of tokens
			tokenCounter += lineTokens.length;


			//tokenCounter += lineTokens.length;
		}
	}	
}
