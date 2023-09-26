import java.util.Scanner;

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
	public MVCipher() { }
	
	/** Main method runs the program */
	public static void main(String[] args) {
		MVCipher mvc = new MVCipher();
		mvc.run();
	}
	
	/**
	 *	Calls methods in order to encrypt or decrypt the message
	 */
	public void run() {
		Scanner input=new Scanner(System.in);
		boolean validKey=false;
		System.out.println("\n Welcome to the MV Cipher machine!\n");
		
		/* Prompt for a key and change to uppercase
		   Do not let the key contain anything but alpha
		   Use the Prompt class to get user input */
		
		do {
			System.out.println("Please enter a key that only contains letters, no spaces");
			key=input.next();
			for (int i = 0; i<key.length(); i++) {
				if (((key.charAt(i) >= 'A' && key.charAt(i) <= 'Z') || 
					(key.charAt(i) >='a' && key.charAt(i) <='z'))) {
						validKey=true;
				}
			}
		} while (!validKey);
		/* Prompt for encrypt or decrypt */
			
			
		/* Prompt for an input file name */
		
		
		/* Prompt for an output file name */
		
		
		/* Read input file, encrypt or decrypt, and print to output file */
		
		
		/* Don't forget to close your output file */
	}
	public void shiftLine(boolean encrypt, String line) {
		
	}
	// other methods go here
	
}
