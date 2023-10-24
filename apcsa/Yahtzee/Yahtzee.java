/**
 *	A fun, two player dice game where you aim to reroll dice strategically
 * 	The goal of the game is to fill up the categories with as many points
 *  as possible
 *
 *	@author	Michael Yeung
 *	@since	October 3, 2023
 */
 
 public class Yahtzee {
	private YahtzeePlayer p1; //Represents player 1
	private YahtzeePlayer p2; //Represents player 2
	private DiceGroup dg; //class containing all of the dicegroup methods
	private final int NUM_ROUNDS = 13;
	/** Constructor method */
	public Yahtzee() {
		p1 = new YahtzeePlayer();
		p2 = new YahtzeePlayer();
		dg = new DiceGroup();
	}

	/** 
	 * Prints the header containing info on how to play the game
	 */
	public void printHeader() {
		System.out.println("\n");
		System.out.println("+------------------------------------------------------------------------------------+");
		System.out.println("| WELCOME TO MONTA VISTA YAHTZEE!                                                    |");
		System.out.println("|                                                                                    |");
		System.out.println("| There are 13 rounds in a game of Yahtzee. In each turn, a player can roll his/her  |");
		System.out.println("| dice up to 3 times in order to get the desired combination. On the first roll, the |");
		System.out.println("| player rolls all five of the dice at once. On the second and third rolls, the      |");
		System.out.println("| player can roll any number of dice he/she wants to, including none or all of them, |");
		System.out.println("| trying to get a good combination.                                                  |");
		System.out.println("| The player can choose whether he/she wants to roll once, twice or three times in   |");
		System.out.println("| each turn. After the three rolls in a turn, the player must put his/her score down |");
		System.out.println("| on the scorecard, under any one of the thirteen categories. The score that the     |");
		System.out.println("| player finally gets for that turn depends on the category/box that he/she chooses  |");
		System.out.println("| and the combination that he/she got by rolling the dice. But once a box is chosen  |");
		System.out.println("| on the score card, it can't be chosen again.                                       |");
		System.out.println("|                                                                                    |");
		System.out.println("| LET'S PLAY SOME YAHTZEE!                                                           |");
		System.out.println("+------------------------------------------------------------------------------------+");
		System.out.println("\n\n");
	}
	
	/**
	 * Runs the Yahtzee game
	 */
	public void run() {
		//prints the header
		printHeader();
		
		//asks user for player names
		p1.setName(Prompt.getString("Player 1, please enter your first name"));
		p2.setName(Prompt.getString("\nPlayer 2, please enter your first name"));
		
		//determines which player goes first, returns '1' or '2'
		char startPlayer = determineStartPlayer();

		//prints starting scoreboard
		p1.getScoreCard().printCardHeader();
		p1.getScoreCard().printPlayerScore(p1);
		p2.getScoreCard().printPlayerScore(p2);

		//plays 13 rounds of yahtzee
		for (int i = 0; i < NUM_ROUNDS; i++) {
			System.out.println("\nRound " + (i+1) + " of 13 rounds. \n");

			if (startPlayer == '1') {
				p1 = playRound(p1);
				p2 = playRound(p2);
			} else if (startPlayer == '2') {
				p2 = playRound(p2);
				p1 = playRound(p1);
			}
		}
		
		int p1Score = 0;
		int p2Score = 0;
		int[] p1TotScore = p1.getScoreCard().getCatScore();
		int[] p2TotScore = p2.getScoreCard().getCatScore();
		for (int i = 0; i < NUM_ROUNDS; i++) {
			p1Score += p1TotScore[i];
		}
		for (int i = 0; i < NUM_ROUNDS; i++) {
			p2Score += p2TotScore[i];
		}
		System.out.printf("%n%n%n%-10s had a score of %d%n", p1.getName(), p1Score);
		System.out.printf("%-10s had a score of %d%n%n%n%n", p2.getName(), p2Score);
	}

	/**
	 * Plays half a round, where one player goes once
	 * @param player Object containing player info
	 * @return Same object passed in, but updated
	 */
	public YahtzeePlayer playRound(YahtzeePlayer player) {
		String holdDice = ""; //which di(c)e will not be rerolled

		//first roll
		Prompt.getString("\n" + player.getName() + ", it's your turn to play. Please hit enter to roll the dice ");
		dg.rollDice();
		
		//dice to hold
		holdDice = Prompt.getString("Which di(c)e would you like to keep? Enter the values you'd like to 'hold' without"
			+ " spaces. For examples, if you'd like to 'hold' die 1, 2, and 5, enter 125"
			+ " (enter -1 if you'd like to end the turn) ");
			
		//if no numbers entered, roll all dice
		if (holdDice.equals("")) dg.rollDice();

		//if -1 not entered, second roll
		if (!holdDice.equals("-1")) {
			dg.rollDice(holdDice);
			
			//dice to hold
			holdDice = Prompt.getString("Which di(c)e would you like to keep? Enter the values you'd like to 'hold' without"
			+ " spaces. For examples, if you'd like to 'hold' die 1, 2, and 5, enter 125"
			+ " (enter -1 if you'd like to end the turn) ");
			
			//if no numbers entered, roll all dice
			if (holdDice.equals("")) dg.rollDice();
			
			//if -1 not entered, third roll
			if (!holdDice.equals("-1")) {
				dg.rollDice(holdDice);		
			}
		}
		//prints score card
		player.getScoreCard().printCardHeader();
		p1.getScoreCard().printPlayerScore(p1);
		p2.getScoreCard().printPlayerScore(p2);
		System.out.println("                  1    2    3    4    5    6    7    8    9   10   11   12   13");

		//choosing which category to put the die in
		int catChoice = 0;
		do {
			catChoice = Prompt.getInt("\n" +player.getName() + ", now you need to make a choice. "
				+"Pick a valid integer from the list above ");
		} while (!player.getScoreCard().changeScore(catChoice, dg));
		//keeps going until choice is within boundaries (1-13) or 
		
		//prints score card
		player.getScoreCard().printCardHeader();
		p1.getScoreCard().printPlayerScore(p1);
		p2.getScoreCard().printPlayerScore(p2);

		//update the info in YahtzeePlayer
		return player;
	}
	/**
	 * Determines which player starts first, by seeing who rolls the highest score
	 * @return Char symbolizes which person starts, '1' or '2'
	 */
	public char determineStartPlayer() {
		int p1Score = 0;
		int p2Score = 0;
		char startPlayer; //Identity of player that starts the game, '1' or '2'
		do {
			Prompt.getString("\nLet's see who will go first. " + p1.getName() + ", please hit enter to roll the dice ");
			dg.rollDice();
			p1Score = dg.getTotal();
			
			Prompt.getString("\n" + p2.getName() + ", it's your turn. Please hit enter to roll the dice ");
			dg.rollDice();
			p2Score = dg.getTotal();
			
			if (p1Score == p2Score) {
				System.out.println("Whoops, we have a tie (both rolled " + p1Score
					+"). Looks like we'll have to try that again . . .");
			}
		} while (p1Score == p2Score);				
		
		System.out.println(p1.getName() + ", you rolled a sum of " +p1Score 
			+ ", and " + p2.getName() + ", you rolled a sum of " + p2Score + ".");
			
		if (p1Score > p2Score) {
			System.out.println(p1.getName() + ", since your sum was higher, you'll roll first.");
			startPlayer = '1';
		} else {
			System.out.println(p2.getName() + ", since your sum was higher, you'll roll first.");
			startPlayer = '2';
		}
		return startPlayer;
	}

	/**
	 * Main method
	 */
	public static void main (String[] args) {
		Yahtzee game = new Yahtzee();
		game.run();
	}
}
