/**
 *	Keeps track of the player's score through an array that can be updated
 *  with the score for each category
 *	@author	Michael Yeung
 *	@since	October 3, 2023
 */
public class YahtzeeScoreCard {
	private int[] catScore; //score in each category
	private final int TOK_INDEX = 6; //index of three of a kind
	private final int FOK_INDEX = 7; //index of four of a kind
	private final int FH_INDEX = 8; //index of full house
	private final int SS_INDEX = 9; //index of small straight
	private final int LS_INDEX = 10; //index of large straight
	private final int CHA_INDEX = 11; //index of chance
	private final int YAH_INDEX = 12; //index of yahtzee

	private final int TOK_MIN = 3; //min num of dice needed in three of a kind
	private final int FOK_MIN = 4; //min num of dice needed in four of a kind
	private final int FH_SET_1 = 3; //num of dice needed in first set for full house
	private final int FH_SET_2 = 2; //num of dice needed in second set for full house

	/** Costructor method */
	public YahtzeeScoreCard() {
		catScore = new int[13]; //13 categories, set to -1
		for (int i = 0; i < catScore.length; i++) catScore[i] = -1;
		
	}
	/**
	 *	Get a category score on the score card.
	 *	@param category		the category number (1 to 13)
	 *	@return				the score of that category
	 */
	public int getScore(int category) {
		return catScore[category-1];
	}
	
	/**
	 *  Print the scorecard header
	 */
	public void printCardHeader() {
		System.out.println("\n");
		System.out.printf("\t\t\t\t\t       3of  4of  Fll Smll Lrg\n");
		System.out.printf("  NAME\t\t  1    2    3    4    5    6   Knd  Knd  Hse " +
						"Strt Strt Chnc Ytz!\n");
		System.out.printf("+----------------------------------------------------" +
						"---------------------------+\n");
	}
	
	/**
	 *  Prints the player's score
	 */
	public void printPlayerScore(YahtzeePlayer player) {
		System.out.printf("| %-12s |", player.getName());
		for (int i = 1; i <= catScore.length; i++) {
			if (getScore(i) > -1)
				System.out.printf(" %2d |", getScore(i));
			else System.out.printf("    |");
		}
		System.out.println();
		System.out.printf("+----------------------------------------------------" +
						"---------------------------+\n");
	}


	/**
	 *  Change the scorecard based on the category choice 1-13.
	 *
	 *  @param choice The choice of the player 1 to 13
	 *  @param dg  The DiceGroup to score
	 *  @return  true if change succeeded. Returns false if choice already taken.
	 */
	public boolean changeScore(int choice, DiceGroup dg) {
		if (choice < 1 || choice > catScore.length) return false;
		if (catScore[choice-1] == -1) {
			if (choice >= 1 && choice <= 6) {
				numberScore(choice, dg);
			}
			else if (choice==7) {
				threeOfAKind(dg);
			}
			else if (choice==8) {
				fourOfAKind(dg);
			}
			else if (choice==9) {
				fullHouse(dg);
			}
			else if (choice==10) {
				smallStraight(dg);
			}
			else if (choice==11) {
				largeStraight(dg);
			}
			else if (choice==12) {
				chance(dg);
			}
			else if (choice==13) {
				yahtzeeScore(dg);
			}
			return true;
		}
		else return false;
	}
	
	/**
	 *  Change the scorecard for a number score 1 to 6
	 *
	 *  @param choice The choice of the player 1 to 6
	 *  @param dg  The DiceGroup to score
	 */
	public void numberScore(int choice, DiceGroup dg) {
		if (catScore[choice-1] == -1) catScore[choice-1] = 0;
		for (int i = 0; i < dg.getNumDie(); i++) {
			if (dg.getDie(i).getLastRollValue() == choice) 
				catScore[choice-1] += choice; 
		}
	}
	
	/**
	 *	Updates the scorecard for Three Of A Kind choice.
	 *
	 *	@param dg	The DiceGroup to score
	 */	
	public void threeOfAKind(DiceGroup dg) {
		int[] dieValues = dg.getAllDieValues();
		int counter = 0;
		for (int i = 1; i <= dg.getDiceSides(); i++) { //loops through all die faces
			for (int j = 0; j < dieValues.length; j++) { //loops through the player's dice
				if (dieValues[j] == i) counter++; //if player's die value = a die face, counter increases 
			}
			//updates score if at least 3 of die value is same value
			if (counter >= TOK_MIN) catScore[TOK_INDEX] = dg.getTotal();
			counter = 0;
		}
		if (catScore[TOK_INDEX] == -1) catScore[TOK_INDEX] = 0; //if condition not met, score becomes 0
	}

	/**
	 *	Updates the scorecard for Four Of A Kind choice.
	 *
	 *	@param dg	The DiceGroup to score
	 */	
	public void fourOfAKind(DiceGroup dg) {
		int[] dieValues = dg.getAllDieValues();
		int counter = 0;
		for (int i = 1; i <= dg.getDiceSides(); i++) { //loops through all die faces
			for (int j = 0; j < dieValues.length; j++) { //loops through the player's dice
				if (dieValues[j] == i) counter++; //if player's die value = a die face, counter increases 
			}
			//updates score if at least 3 of die value is same value
			if (counter >= FOK_MIN) catScore[FOK_INDEX] = dg.getTotal();
			counter = 0;
		}
		if (catScore[FOK_INDEX] == -1) catScore[FOK_INDEX] = 0; //if condition not met, score becomes 0
	}
	
	/**
	 *	Updates the scorecard for Full House choice.
	 *
	 *	@param dg	The DiceGroup to score
	 */	
	public void fullHouse(DiceGroup dg) {
		int[] dieValues = dg.getAllDieValues();
		boolean firstSet = false; //whether a value appears 3 times
		boolean secondSet = false; //whether another value appears 2 times
		int counter = 0;
		for (int i = 1; i <= dg.getDiceSides(); i++) { //loops through all die faces
			for (int j = 0; j < dieValues.length; j++) { //loops through the player's dice
				if (dieValues[j] == i) counter++; //if player's die value = a die face, counter increases 
			}
			if (counter >= FH_SET_1) firstSet = true; //checks if value appears 3 times
			else if (counter >= FH_SET_2) secondSet = true; //else checks if value appears 2 times
			counter = 0;
		}
		if (firstSet && secondSet) catScore[FH_INDEX] = 25; //updates score to be 25
		else catScore[FH_INDEX] = 0; //if condition not met, score becomes 0
	}
	
	/**
	 *	Updates the scorecard for Small Straight choice.
	 *
	 *	@param dg	The DiceGroup to score
	 */	
	public void smallStraight(DiceGroup dg) {
		boolean isValid = false; //whether each die value equals side value
		boolean totalIsValid = true; //whether at least one die equals a side value, for multiple sides
		int[] dieValues = dg.getAllDieValues();

		//checks to see if values include 1-4
		for (int i = 1; i <= 4; i++) {
			for (int j = 0; j < dieValues.length; j++) {
				if (dieValues[j] == i) isValid = true;
			}
			if (!isValid) totalIsValid = false;
			isValid = false;
		}
		if (!totalIsValid) {
			totalIsValid = true;
			//checks to see if values include 2-5
			for (int i = 2; i <= 5; i++) {
				for (int j = 0; j < dieValues.length; j++) {
					if (dieValues[j] == i) isValid = true;
				}
				if (!isValid) totalIsValid = false;
				isValid = false;
			}
			if (!totalIsValid) {
				totalIsValid = true;
				//checks to see if values include 3-6
				for (int i = 3; i <= 6; i++) {
					for (int j = 0; j < dieValues.length; j++) {
						if (dieValues[j] == i) isValid = true;
					}
					if (!isValid) totalIsValid = false;
					isValid = false;
				}
			}
		}
		if (totalIsValid) catScore[SS_INDEX] = 30; //updates score to be 30
		else catScore[SS_INDEX] = 0; //if condition not met, score becomes 0
	}
	
	/**
	 *	Updates the scorecard for Large Straight choice.
	 *
	 *	@param dg	The DiceGroup to score
	 */	
	public void largeStraight(DiceGroup dg) {
		boolean isValid = false; //whether each die value equals side value
		boolean totalIsValid = true; //whether at least one die equals a side value, for multiple sides
		int[] dieValues = dg.getAllDieValues();

		//checks to see if values include 1-5
		for (int i = 1; i <= 5; i++) {
			for (int j = 0; j < dieValues.length; j++) {
				if (dieValues[j] == i) isValid = true;
			}
			if (!isValid) totalIsValid = false;
			isValid = false;
		}
		if (!totalIsValid) {
			totalIsValid = true;
			//checks to see if values include 2-6
			for (int i = 2; i <= 6; i++) {
				for (int j = 0; j < dieValues.length; j++) {
					if (dieValues[j] == i) isValid = true;
				}
				if (!isValid) totalIsValid = false;
				isValid = false;
			}
		}
		if (totalIsValid) catScore[LS_INDEX] = 40; //updates score to be 40
		else catScore[LS_INDEX] = 0; //if condition not met, score becomes 0
	}
	
	/**
	 *	Updates the scorecard for Chance choice.
	 *
	 *	@param dg	The DiceGroup to score
	 */	
	public void chance(DiceGroup dg) {
		catScore[CHA_INDEX] = dg.getTotal();
	}
	
	/**
	 *	Updates the scorecard for Yahtzee choice.
	 *
	 *	@param dg	The DiceGroup to score
	 */	
	public void yahtzeeScore(DiceGroup dg) {
		boolean isValid = true;
		int[] dieValues = dg.getAllDieValues();
		int sameSideLength = dieValues[0]; //sets the identical side length to the first die
		for (int i = 0; i < dieValues.length; i++) {
			if (sameSideLength != dieValues[i]) isValid = false;
		}
		if (isValid) catScore[YAH_INDEX] = 50; //updates score to be 30
		else catScore[YAH_INDEX] = 0; //if condition not met, score becomes 0
	}

	/**
	 * Returns the array containing the score from each category
	 * @return score from each category
	 */
	public int[] getCatScore() {
		return catScore;
	}
}
