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
		String originalContent=""; //contents of original input content
		String shiftedContent=""; //file contents that has been encrypted or decrypted

		System.out.println("\n Welcome to the MV Cipher machine!\n");
		
		/* Prompt for a key and change to uppercase
		   Do not let the key contain anything but alpha
		   Use the Prompt class to get user input */
		   		
		   /* delete this
		//asks user for key, verifies to see it contains only letters
		do {
			validKey=true;
			key=Prompt.getString("Please enter a key that only contains letters, "
				+"no spaces, and contain at least 3 letters");
			for (int i = 0; i<key.length(); i++) {
				if (!((key.charAt(i) >= 'A' && key.charAt(i) <= 'Z') || 
					(key.charAt(i) >='a' && key.charAt(i) <='z'))) {
						validKey=false;
						i=key.length()-1;
				}
			}
		} while (!validKey && key.length() < 3);
		
		//change key to all uppercase
		for (int i = 0; i<key.length(); i++) {
			if (key.charAt(i) >= 'a' && key.charAt(i) <= 'z') {
				key=key.substring(0, i)+(char)(key.charAt(i)+('A'-'a'))+key.substring(i+1);
			}
		}
		
		/* Prompt for encrypt or decrypt */
		/* delete this
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
		/* delete this
		inputFileName=Prompt.getString("Please enter the input file name");
		
		/* Prompt for an output file name */
		/* delete this
		outputFileName=Prompt.getString("Please enter the output file name");
		
		
		/* Read input file, encrypt or decrypt, and print to output file */
		inputFileName = "Macbeth.txt";
		outputFileName = "output.txt";
		encrypt = true;
		key = "key";

		Scanner reader = FileUtils.openToRead(inputFileName);
		originalContent=getContentString(reader);

		if (encrypt) shiftedContent = encryptText(originalContent);
		else shiftedContent = decryptText(originalContent);
		System.out.println(shiftedContent);

		reader.close();
		
		PrintWriter writer = FileUtils.openToWrite(outputFileName);
		writer.print(shiftedContent);
		writer.close();
	}
	
	/**
	 * 
	 * @param content Contains contents of text file
	 * Preconditions: String must not be empty
	 */
	public String decryptText(String content) {
		System.out.println("in decrypt");
		String decryptedMessage =  "";
		int oldChar;
		int keyShift; //the number you want to shift oldChar down by
		int newChar = ' ';

		for (int i = 0; i < content.length(); i++) {
			oldChar = (int)content.charAt(i);
			keyShift = (int)key.charAt(i%key.length());
			if (isLowerCase(oldChar)) {
				keyShift-='A';
				if (oldChar - keyShift < 'a') {
					newChar = oldChar - keyShift + ('z' - 'a');
				}
				else {
					newChar = oldChar - keyShift;
				}
				
				//System.out.println("lower case yay");
			}
			else if (isUpperCase(oldChar)) {
				keyShift-='A';
				if (oldChar + keyShift > 'Z') {
					newChar = oldChar + keyShift - ('Z' - 'A');
				}
				else {
					newChar = oldChar + keyShift;
				}
				//System.out.println("upper class yay");
			}
			else {
				newChar = oldChar;
			}
			decryptedMessage += (char)newChar;
		}
		System.out.println(decryptedMessage);
		return decryptedMessage;
	}

	public String encryptText(String content) {
		String encryptedMessage =  "";
		int oldChar;
		int keyShift; //the number you want to shift oldChar up by
		int newChar = ' ';

		for (int i = 0; i < content.length(); i++) {
			oldChar = (int)content.charAt(i);
			keyShift = (int)key.charAt(i%key.length());
			
			//System.out.println(oldChar +"   "+keyShift);
			if (isLowerCase(oldChar)) {
				keyShift-='A';
				if (oldChar + keyShift > 'z') {
					newChar = oldChar + keyShift - ('z' - 'a');
					System.out.println(newChar);
				}
				else {
					newChar = oldChar + keyShift;
					System.out.println(newChar);
				}
				
				//System.out.println("lower case yay");
			}
			else if (isUpperCase(oldChar)) {
				keyShift-='A';
				if (oldChar + keyShift > 'Z') {
					newChar = oldChar + keyShift - ('Z' - 'A');
					System.out.println(newChar);
				}
				else {
					newChar = oldChar + keyShift;
					System.out.println(newChar);
				}
				//System.out.println("upper class yay");
			}
			else {
				newChar = oldChar;
			}
			//System.out.println((char)newChar);
			encryptedMessage += (char)newChar;
		}
		return encryptedMessage;
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
	 * Checks if letter is lowercase
	 * @param let The letter that will be checked
	 * Postcondition: returns a true if lowercase
	 */
	public boolean isLowerCase(int let) {
		if (let >= 'a' && let <= 'z') return true;
		else return false;
	}

	/**
	 * Checks if letter is uppercase
	 * @param let The letter that will be checked
	 * Postcondition: returns a true if uppercase
	 */
	public boolean isUpperCase(int let) {
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
