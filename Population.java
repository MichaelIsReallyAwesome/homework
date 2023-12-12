import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Comparator;
/**
 *	Population - <description goes here>
 *
 *	Requires FileUtils and Prompt classes.
 *
 *	@author	Michael Yeung
 *	@since	11 December, 2023
 */
public class Population { //implements Comparator<City> {
	
	// List of cities
	private List<City> cities;
	
	// US data file
	private final String DATA_FILE = "usPopData2017.txt";
	
	/** Constructor */
	public Population() {
		cities = new ArrayList<City>();
	}
	public void storePopulationData() {
		int numCities = 0;
		String state = "";
		String citName = "";
		String citType = "";			
		int pop = 0;
		
		Scanner input = FileUtils.openToRead(DATA_FILE);
		input.useDelimiter("[\t\n]");
		while (input.hasNext()) {
			state = input.next();
			citName = input.next();
			citType = input.next();
			pop = input.nextInt();
			System.out.println(state +"  " + citName+"  "+citType+"  "+pop);
			cities.add(numCities, new City(citName, state, citType, pop));
			numCities++;
		}
		
	}
	/**	Prints the introduction to Population */
	public void printIntroduction() {
		System.out.println("   ___                  _       _   _");
		System.out.println("  / _ \\___  _ __  _   _| | __ _| |_(_) ___  _ __ ");
		System.out.println(" / /_)/ _ \\| '_ \\| | | | |/ _` | __| |/ _ \\| '_ \\ ");
		System.out.println("/ ___/ (_) | |_) | |_| | | (_| | |_| | (_) | | | |");
		System.out.println("\\/    \\___/| .__/ \\__,_|_|\\__,_|\\__|_|\\___/|_| |_|");
		System.out.println("           |_|");
		System.out.println();
	}
	
	/**	Print out the choices for population sorting */
	public void printMenu() {
		System.out.println("1. Fifty least populous cities in USA (Selection Sort)");
		System.out.println("2. Fifty most populous cities in USA (Merge Sort)");
		System.out.println("3. First fifty cities sorted by name (Insertion Sort)");
		System.out.println("4. Last fifty cities sorted by name descending (Merge Sort)");
		System.out.println("5. Fifty most populous cities in named state");
		System.out.println("6. All cities matching a name sorted by population");
		System.out.println("9. Quit");
	}
	public static void main(String[] args) {
		Population pop = new Population();
		pop.storePopulationData();
	}
}
