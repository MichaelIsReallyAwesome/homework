/**
 *	A fun, two player dice game where you aim to reroll dice strategically
 * 	The goal of the game is to fill up the categories with as many points
 *  as possible
 *
 *	@author	Michael Yeung
 *	@since	October 3, 2023
 */
 
public class Yahtzee {

	
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
		DiceGroup dg=new DiceGroup(); //class containing all of the dicegroup methods
		String p1Name = "";
		String p2Name = "";
		
		//prints the header
		printHeader();
		
		//asks user for player names
		p1Name = Prompt.getString("Player 1, please enter your first name");
		p2Name = Prompt.getString("\nPlayer 2, please enter your first name");
		
		//determines which player goes first
		int p1Score = 0;
		int p2Score = 0;
		char startPlayer;
		do {
			Prompt.getString("\nLet's see who will go first. One, please hit enter to roll the dice :");
			dg.rollDice();
			p1Score = dg.getTotal();
			
			Prompt.getString("\nTwo, it's your turn. Please hit enter to roll the dice :");
			dg.rollDice();
			p2Score = dg.getTotal();
			
			if (p1Score == p2Score) {
				System.out.println("Whoops, we have a tie (both rolled " + p1Score
					+"). Looks like we'll have to try that again . . .");
			}
		} while (p1Score == p2Score);				
		
		System.out.println(p1Name + ", you rolled a sum of " +p1Score 
			+ ", and " + p2Name + ", you rolled a sum of " + p2Score + ".");
			
		if (p1Score>p2Score) {
			System.out.println(p1Name + ", since your sum was higher, you'll roll first.");
			startPlayer = '1';
		} else {
			System.out.println(p2Name + ", since your sum was higher, you'll roll first.");
			startPlayer = '2';
		}
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
	}
	public static void main (String[] args) {
		Yahtzee game = new Yahtzee();
		game.run();
	}
}
