import java.util.Scanner;

/**
 *	Counts the frequency of letters in a file and produces a histogram.
 *
 *	@author	Michael Yeung
 *	@since	September 14, 2023
 */
public class LetterCount {
	
	// Fields go here, all must be private
	private int frequency[]; //Contains the amount of times each letter shows up (alphabetical order), used to calculate length of each bar
	private int highestFrequency; //Contains the value of the letter that shows up the most, denominator for length of each bar
	
	/* Constructor, initializes frequency array and sets the highestFrequency to 0 */							
	public LetterCount() {
		frequency=new int[26];
		highestFrequency=0;
	}
	
	/* Main routine, runs the program and passes the file name to run() */
	public static void main(String[] args) {
		LetterCount lc = new LetterCount();
		if (args.length != 1)
			System.out.println("Usage: java LetterCount <inputFile>");
		else
			lc.run(args);
	}
	
	/**	The core program of the class, it
	 *	- opens the input file
	 *	- reads the file and counts the letters
	 *	- closes the input file
	 *	- prints the histogram of the letter count
	 */
	public void run(String[] args) {
		String fileName = args[0];
		FileUtils fu=new FileUtils();
		Scanner reader=fu.openToRead(fileName);
		while (reader.hasNext()) {
			String phrase=reader.next();
			for (int i = 0; i<phrase.length(); i++) {
				char letter=phrase.charAt(i);
				if (letter>=65 && letter<=90) {
					frequency[letter-65]++;
				}
				else if (letter>=97 && letter<=122) {
					frequency[letter-97]++;
				}
			}
		}
		reader.close();
		
		for (int i = 0; i<frequency.length; i++) {
			if (highestFrequency<frequency[i]) {
				highestFrequency=frequency[i];
			}
		}
		System.out.println("\nHistogram of letter frequency in "+fileName+"\n");
		for (int i=0; i<frequency.length; i++) {
			String bar="";
			double length=(1.0*frequency[i]/highestFrequency)*60;
			for (int j = 0; j<length; j++) bar+="+";
			System.out.printf("%-4s%5d %s%n", (char)(i+97)+":", frequency[i], bar);
		}
	}
}
