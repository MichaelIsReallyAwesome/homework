import java.util.Scanner;
import java.io.PrintWriter;

/**
 *	MVCipher - Add your description here
 *	Requires Prompt and FileUtils classes.
 *	
 *	@author	Michael Yeung
 *	@since	September 21, 2023
 */
public class MVCipher {
	private String key; //the key for encrypting/decrypting the program

	/** Constructor */
	public MVCipher() { 
		key="";
		inputContent="";
	}
	
	/** Main method runs the program */
	public static void main(String[] args) {
		MVCipher mvc = new MVCipher();
		mvc.run();
	}
	
	/**
	 *	Calls methods in order to encrypt or decrypt the message
	 */
	public void run() {
		boolean validKey=true; //whether the key is valid or not
		boolean encrypt=true; //whether to encrypt or decrypt the code
		String inputFileName="";
		String outputFileName="";
		
		System.out.println("\n Welcome to the MV Cipher machine!\n");
		
		/* Prompt for a key and change to uppercase
		   Do not let the key contain anything but alpha
		   Use the Prompt class to get user input */
		   		
		//asks user for key, verifies to see it contains only letters
		do {
			validKey=true;
			key=Prompt.getString("Please enter a key that only contains letters, no spaces");
			for (int i = 0; i<key.length(); i++) {
				if (!((key.charAt(i) >= 'A' && key.charAt(i) <= 'Z') || 
					(key.charAt(i) >='a' && key.charAt(i) <='z'))) {
						validKey=false;
						i=key.length()-1;
				}
			}
		} while (!validKey);
		
		//change word to all uppercase
		for (int i = 0; i<key.length(); i++) {
			if (key.charAt(i) >= 'a' && key.charAt(i) <= 'z') {
				key=key.substring(0, i)+(char)(key.charAt(i)+('A'-'a'))+key.substring(i+1);
			}
		}
		
		/* Prompt for encrypt or decrypt */
		char translateChoice=' ';
		do {
			translateChoice=Prompt.getChar("Do you want to encrypt or decrypt the text? (e/d)");
		} while (translateChoice!='e' && translateChoice!='d');
		
		if (translateChoice=='e') {
			encrypt=true;
		}
		else if (translateChoice=='d') {
			encrypt=false;
		}
			
		/* Prompt for an input file name */
		inputFileName=Prompt.getString("Please enter the input file name");
		
		/* Prompt for an output file name */
		outputFileName=Prompt.getString("Please enter the output file name");
		
		/* Read input file, encrypt or decrypt, and print to output file */
		Scanner reader = FileUtils.openToRead(inputFileName);
		//inputContent=getContentString(reader);
		encryptText(getContentString(reader));
		reader.close();
		
		PrintWriter writer = FileUtils.openToWrite(outputFileName);
		writer.close();
	}
	/**
	 * Reads the file and returns the contents as a String
	 * @param reader Scanner goes through the file
	 * Precondition: Scanner must be initialized
	 * Postcondition: File will be untouched
	 */
	public String getContentString(Scanner reader) {
		String content="";
		while (reader.hasNextLine()) {
			content+=reader.nextLine();
		}
		return content;
	}
	/**
	 * 
	 * @param content Contains contents of text file
	 * Preconditions: String must not be empty
	 */
	public String encryptText(String content) {
		String decryptedMessage =  "";
		int decryptedChar = 0;
		for (int i = 0; i < content.length(); i++) {
			if (isLowerCase(content.charAt(i))) {
				if (content.charAt(i)+key.charAt(i%key.length()) > 'z') {
					decryptedChar = 'a'+(content.charAt(i)+key.charAt(i%key.length()) - 'z');
				}
				else {
					decryptedChar = content.charAt(i)+key.charAt(i%key.length()) - 'z';
				}
				System.out.println("lower case yay");
			}
			else if (isUpperCase(content.charAt(i))) {
				if (content.charAt(i)+key.charAt(i%key.length()) > 'Z') {
					decryptedChar = 'A'+(content.charAt(i)+key.charAt(i%key.length()) - 'Z');
				}
				else {
					decryptedChar = content.charAt(i)+key.charAt(i%key.length()) - 'Z';
				}
			}
			else {
				decryptedChar=content.charAt(i);
			}
			decryptedMessage+=(char)(decryptedChar);
		}
		//System.out.println(decryptedMessage);
		return decryptedMessage;
	}
	/**
	 * Checks if letter is lowercase
	 * @param let The letter that will be checked
	 * Postcondition: returns a true if lowercase
	 */
	public boolean isLowerCase(char let) {
		if (let >= 'a' && let <= 'z') return true;
		else return false;
	}

	/**
	 * Checks if letter is uppercase
	 * @param let The letter that will be checked
	 * Postcondition: returns a true if uppercase
	 */
	public boolean isUpperCase(char let) {
		if (let >= 'A' && let <= 'Z') return true;
		else return false;
	}
	/**
	 * 
	 * @param shift
	 * @param original
	 * @return
	 */
	public char shiftLowerChar(char shift, char original) {
		return ' ';
	}
	public char shiftUpperChar(char shift, char original) {
		return ' ';
	}
	public void shiftLine(boolean encrypt, String line) {
		
	}
	// other methods go here
	
}
