/**
 *  Create a roll of 5 dice and display them.
 *
 *  @author Mr Greenstein and Michael Yeung
 *  @since	October 3, 2023
*/

public class DiceGroup {
	private Dice [] die;
	private final int NUM_SIDE = 6;
	private final int NUM_DIE = 5;
	// Create the seven different line images of a die
	String [] line = {	" _______ ", " _______ ",  " _______ ",  " _______ ",  " _______ ",  " _______ ", 
						"|       |", "|       |",  "|       |",  "|       |",  "|       |",  "|       |",
						"|       |", "|     O |",  "|     O |",  "| O   O |",  "| O   O |",  "| O   O |",
						"|   O   |", "|       |",  "|   O   |",  "|       |",  "|   O   |",  "| O   O |",
						"|       |", "| O     |",  "| O     |",  "| O   O |",  "| O   O |",  "| O   O |",
						"|_______|", "|_______|",  "|_______|",  "|_______|",  "|_______|",  "|_______|" };
		
	
	/**
	 *  Creates a group of 5 dice
	 */
	public DiceGroup() {
		die = new Dice[NUM_DIE];
		for (int i = 0; i < die.length; i++) {
			die[i] = new Dice();
		}
	}
		
	/**
	 *  Roll all dice.
	 */
	public void rollDice() {
		for (int i = 0; i < NUM_DIE; i++) {
			die[i].roll();
		}
		printDice();
	}
	
	/**
	 *  Roll only the dice not in the string "rawHold".
	 *  e.g. If rawHold="24", only roll dice 1, 3, and 5
	 *  @param rawHold	the numbered dice to hold
	 *  Precondition: All the characters in rawHold must be integers and no spaces
	 */
	public void rollDice(String rawHold) {
		boolean[] holdDie = new boolean[die.length]; //array listing whether die in that index will be rolled
		for (int i = 0; i < holdDie.length; i++) holdDie[i]=false;
		for (int i = 0; i < rawHold.length(); i++) {
			char let = rawHold.charAt(i);
			if (let >= '1' && let <= '5') {
				holdDie[let - '1'] = true;
			}
			else holdDie[i] = false;
		}
		for (int i = 0; i < holdDie.length; i++) {
			if (!holdDie[i]) die[i].roll();
		}
		printDice();
	}
	
	/**
	 *  Dice getter method
	 *  @param i	the index into the die array
	 */
	public Dice getDie(int i) { return die[i]; }
	
	/**
	 *  Computes the sum of the dice group.
	 *
	 *  @return  The integer sum of the dice group.
	 */
	public int getTotal() {
		int sum = 0;
		for (int i = 0; i < die.length; i++) sum += die[i].getLastRollValue();
		return sum;
	}
	
	/**
	 *  Prints out the images of the dice
	 */
	public void printDice() {
		printDiceHeaders();
		for (int i = 0; i < NUM_SIDE; i++) {
			System.out.print(" ");
			for (int j = 0; j < 5; j++) {
				printDiceLine(die[j].getLastRollValue()-1 + 6 * i);
				System.out.print("     ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	/**
	 *  Prints the first line of the dice.
	 */
	public void printDiceHeaders() {
		System.out.println();
		for (int i = 1; i <= NUM_DIE; i++) {
			System.out.printf("   # %d        ", i);
		}
		System.out.println();
	}
	
	/**
	 *  Prints one line of the ASCII image of the dice.
	 *
	 *  @param value The index into the ASCII image of the dice.
	 */
	public void printDiceLine(int value) {
		System.out.print(line[value]);
	}
	
	/**
	 *  Gets the index number into the ASCII image of the dice.
	 *
	 *  @param value The index into the ASCII image of the dice.
	 *  @return	the index into the line array image of the dice
	 */
	public int getDiceLine(int value) {
		if (value < 7) return 0;
		if (value < 14) return 1;
		switch (value) {
			case 20: case 22: case 25:
				return 1;
			case 16: case 17: case 18: case 24: case 28: case 29: case 30:
				return 2;
			case 19: case 21: case 23:
				return 3;
			case 14: case 15:
				return 4;
			case 26: case 27:
				return 5;
			default:	// value > 30
				return 6;
		}
	}
	/**
	 * Returns the number of sides on a die
	 * @return the number of sides on a die
	 */
	public int getDiceSides() {
		return NUM_SIDE;
	}

	/**
	 * Returns the number of dice
	 * @return the number of dice
	 */
	public int getNumDie() {
		return NUM_DIE;
	}

	/**
	 * Returns the last roll values of all the die in order
	 * @return The last roll values of the die
	 */
	public int[] getAllDieValues() {
		int[] dieValues = new int[NUM_DIE];
		for (int i = 0; i < dieValues.length; i++) {
			dieValues[i] = die[i].getLastRollValue();
		}
		return dieValues;
	}
}
