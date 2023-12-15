import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Comparator;
/**
 *	Population - Sorts the information of cities in the US based on their name,
 *	population, or state.
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
	
	private List<City> temp; //temporary array for merge sort

	private CompareByPop cbp;
	private CompareByName cbn;
	private CompareByState cbs;

	private int numCities; //number of city data points
	private long startMillisec;
	private long endMillisec; 
	/** Constructor */
	public Population() {
		cities = new ArrayList<City>();
		cbp = new CompareByPop();
		cbn = new CompareByName();
		cbs = new CompareByState();
	}

	/**
	 * Uses selection sort to sort cities in ascending population order
	 */
	public void sortAscendPop() {
		//sort array + record timestamps
		startMillisec = System.currentTimeMillis();
		for (int outer = cities.size() - 1; outer > 0; outer--) {
			int maxIndex = 0;
			for (int inner = 0; inner <= outer; inner++) {
				if (cbp.compare(cities.get(inner), cities.get(maxIndex)) > 0)  {
					maxIndex = inner;
				}
			}
			swap(cities, maxIndex, outer);
		}
		endMillisec = System.currentTimeMillis();

		//print out sorted array
		System.out.println("\nFifty least populous cities");
		System.out.printf("      %-23s%-23s%-12s %12s\n", "State", 
				"City", "Type", "Population");
		for (int i = 0; i < 50; i++) {
			System.out.printf("%4d: %s\n", i + 1, cities.get(i).toString());
		}
	}

	/**
	 * Uses merge sort to sort cities in descending population order
	 */
	public void sortDescendPop() {
		temp = new ArrayList<City>(cities);

		//call recursive method to sort array + record timestamps
		startMillisec = System.currentTimeMillis();
		sortDescendPop(cities, 0, cities.size() - 1);
		endMillisec = System.currentTimeMillis();

		//print out sorted array
		System.out.println("\nFifty most populous cities");
		System.out.printf("      %-23s%-23s%-12s %12s\n", "State", 
				"City", "Type", "Population");
		for (int i = 0; i < 50; i++) {
			System.out.printf("%4d: %s\n", i + 1, cities.get(i).toString());
		}
	}

	/** Helper recursive method for sorting in descend population order*/
	private void sortDescendPop(List<City> arr, int start, int end) {
		if (start != end) { //if small array not 1 in length
			sortDescendPop(arr, start, (start + end + 1) / 2 - 1);
			sortDescendPop(arr, (start + end + 1) / 2, end);
			
			int counter = start; //number of elements stored
			int pr = (start + end + 1) / 2; //right pointer
			for (int pl = start; pl < (start + end + 1) / 2; pl++) { //left pointer
				while (pr <= end && arr.get(pr).compareTo(arr.get(pl)) > 0) {
					temp.set(counter, arr.get(pr));
					counter++;
					pr++;
				}
				temp.set(counter, arr.get(pl));
				counter++;
			}
			while (pr <= end) {
				temp.set(counter, arr.get(pr));
				pr++;
				counter++;
			}
			for (int i = start; i <= end; i++) {
				arr.set(i, temp.get(i));
			}
		}		 
	}
	
	/**
	 * Uses insertion sort to sort cities in ascending name order
	 */
	public void sortAscendName() {
		//sort array + record timestamps
		startMillisec = System.currentTimeMillis();
		for (int outer = 1; outer < cities.size(); outer++) {
			int inner = outer;
			while (inner > 0 && cbn.compare(cities.get(inner), cities.get(inner - 1)) < 0) {
				swap(cities, inner, inner - 1);
				inner--; 
			}
		}
		endMillisec = System.currentTimeMillis();

		//print out sorted array
		System.out.println("\nFifty cities sorted by name");
		System.out.printf("      %-23s%-23s%-12s %12s\n", "State", 
				"City", "Type", "Population");
		for (int i = 0; i < 50; i++) {
			System.out.printf("%4d: %s\n", i + 1, cities.get(i).toString());
		}
	}

	/**
	 * Uses merge sort to sort cities in descending name order
	 */
	public void sortDescendName() {
		temp = new ArrayList<City>(cities);

		//sort array + record timestamps
		startMillisec = System.currentTimeMillis();
		sortDescendName(cities, 0, cities.size() - 1);
		endMillisec = System.currentTimeMillis();

		//print out sorted array
		System.out.println("\nFifty cities sorted by name descending");
		System.out.printf("      %-23s%-23s%-12s %12s\n", "State", 
				"City", "Type", "Population");
		for (int i = 0; i < 50; i++) {
			System.out.printf("%4d: %s\n", i + 1, cities.get(i).toString());
		}
	}

	/** Helper recursive method for sorting in descend name order*/
	private void sortDescendName(List<City> arr, int start, int end) {
		if (start != end) { //if small array not 1 in length
			sortDescendName(arr, start, (start + end + 1) / 2 - 1);
			sortDescendName(arr, (start + end + 1) / 2, end);
			
			int counter = start; //number of elements stored
			int pr = (start + end + 1) / 2; //right pointer
			for (int pl = start; pl < (start + end + 1) / 2; pl++) { //left pointer
				while (pr <= end && cbn.compare(arr.get(pr), arr.get(pl)) > 0) {
					temp.set(counter, arr.get(pr));
					counter++;
					pr++;
				}
				temp.set(counter, arr.get(pl));
				counter++;
			}
			while (pr <= end) {
				temp.set(counter, arr.get(pr));
				pr++;
				counter++;
			}
			for (int i = start; i <= end; i++) {
				arr.set(i, temp.get(i));
			}
		}		 
	}

	/**  Uses insertion sort to find the most populous cities in a chosen state */
	public void mostPopInState() {
		//sort array + record timestamps
		startMillisec = System.currentTimeMillis();
		for (int outer = 1; outer < cities.size(); outer++) {
			int inner = outer;
			while (inner > 0 && cbs.compare(cities.get(inner), cities.get(inner - 1)) > 0) {
				swap(cities, inner, inner - 1);
				inner--; 
			}
		}
		endMillisec = System.currentTimeMillis();
		System.out.println();

		//loop verifying state name exists
		boolean stateValid = false;
		int loopCount = 0;
		String state = "";
		do {
			loopCount = 0;

			//ask for state name
			state = Prompt.getString("Enter state name (ie. Alabama)");

			//check to see if state name valid
			while (loopCount < cities.size() && !stateValid) {
				if (cities.get(loopCount).getState().equals(state)) stateValid = true;
				else loopCount++;
			}
			if (!stateValid) System.out.println("ERROR: " + state + " is not valid");
		} while (!stateValid);

		//prints out top 50 elements of sorted array
		int limFifty = 0;
		System.out.println("\nFifty most populous cities in " + state);
		System.out.printf("      %-23s%-23s%-12s %12s\n", "State", 
				"City", "Type", "Population");
		while (limFifty < 50 && loopCount + limFifty < cities.size() && 
				cities.get(loopCount + limFifty).getState().equals(state)) {
			System.out.printf("%4d: %s\n", limFifty + 1, cities.get(loopCount + limFifty).toString());
			limFifty++;
		}
		System.out.println();
		
		// //print out sorted array
		// System.out.println("\nFifty cities sorted by name descending");
		// System.out.printf("      %-25s%-24s%-16s%12s\n", "State", 
		// 		"City", "Type", "Population");
		// for (int i = 0; i < 50; i++) {
		// 	// City current = cities.get(i);
		// 	// System.out.printf("%,4d: %-25s%-24s%-16s%,12d\n", i+1, current.getState(),
		// 	// 		 current.getCityName(), current.getCityType(), current.getPopulation());
		// 	System.out.printf("%4d: %s\n", i + 1, cities.get(i).toString());
		// }
	}
	/** 
	 * Uses insertion sort to find all the cities with a certain name sorted by population
	 **/
	public void sameCityName() {
		//sort array + record timestamps
		startMillisec = System.currentTimeMillis();
		for (int outer = 1; outer < cities.size(); outer++) {
			int inner = outer;
			while (inner > 0 && cbn.compare(cities.get(inner), cities.get(inner - 1)) < 0) {
				swap(cities, inner, inner - 1);
				inner--; 
			}
		}
		endMillisec = System.currentTimeMillis();
		System.out.println();

		//loop verifying city name exists
		boolean citValid = false;
		int loopCount = 0;
		String city = "";
		do {
			loopCount = 0;

			//ask for city name
			city = Prompt.getString("Enter city name");

			//check to see if state name valid
			while (loopCount < cities.size() && !citValid) {
				if (cities.get(loopCount).getCityName().equals(city)) citValid = true;
				else loopCount++;
			}
			if (!citValid) System.out.println("ERROR: " + city + " is not valid");
		} while (!citValid);

		//prints out all the cities with the given name
		int limFifty = 0;
		System.out.println("\nCity " + city + " by population");
		System.out.printf("      %-23s%-23s%-12s %12s\n", "State", 
				"City", "Type", "Population");
		while (loopCount + limFifty < cities.size() && 
				cities.get(loopCount + limFifty).getCityName().equals(city)) {
			System.out.printf("%4d: %s\n", limFifty + 1, cities.get(loopCount + limFifty).toString());
			limFifty++;
		}
		System.out.println();
	}
	/**
	*	Swaps two Integer objects in array arr
	*	@param arr		array of Integer objects
	*	@param x		index of first object to swap
	*	@param y		index of second object to swap
	*/
    private void swap(List<City> arr, int x, int y) {
	   City tempCit = arr.get(x);
	   arr.set(x, arr.get(y)); // = arr.get(y);
	   arr.set(y, tempCit); // = tempCit;
   }


	/**
	 * Reads the file with the population data, and stores each city's
	 * information as a City object in an ArrayList
	 */
	public void storePopulationData() {
		numCities = 0;
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

	/** Runs the program */
	public void run() {
		int typeSort = 0;
		
		//make array with the data from the population file
		storePopulationData();

		//print intro
		printIntroduction();
		System.out.println(numCities + " cities in database\n");

		//loop that prints out prompt
		while (typeSort != 9) {
			//print prompt and store response
			printMenu();
			typeSort = Prompt.getInt("Enter selection");

			//call method which sorts/prints
			if (typeSort == 1) {
				sortAscendPop();
			}
			else if (typeSort == 2) {
				sortDescendPop();
			}
			else if (typeSort == 3) {
				sortAscendName();
			}
			else if (typeSort == 4) {
				sortDescendName();
			}
			else if (typeSort == 5) {
				mostPopInState();
			}
			else if (typeSort == 6) {
				sameCityName();
			}
			//record time at end
			if (typeSort >= 1 && typeSort <= 4) System.out.println("\nEllapsed Time " 
					+ (endMillisec - startMillisec) + " milliseconds\n");
		}

		//print thank you message
		System.out.println("\nThank you for using Population!");

		// long startMillisec1 = System.currentTimeMillis();
		// sortAscendPop();
		// long endMillisec1 = System.currentTimeMillis();
		// long startMillisec2 = System.currentTimeMillis();
		// sortDescendPop();
		// long endMillisec2 = System.currentTimeMillis();
		// long startMillisec3 = System.currentTimeMillis();
		// sortAscendName();
		// long endMillisec3 = System.currentTimeMillis();
		
		// long startMillisec3a = System.currentTimeMillis();
		// sortAscendName();
		// long endMillisec3a = System.currentTimeMillis();
		
		// long startMillisec4 = System.currentTimeMillis();
		// sortDescendName();
		// long endMillisec4 = System.currentTimeMillis();
		// System.out.println(startMillisec1 - endMillisec1); //select
		// System.out.println(startMillisec2 - endMillisec2); //merge
		// System.out.println(startMillisec3 - endMillisec3); //insert
		
		// System.out.println(startMillisec3a - endMillisec3a); //insert special
		
		// System.out.println(startMillisec4 - endMillisec4); //merge
		// mostPopInState();
		//sameCityName();
	}

	/** Main method */
	public static void main(String[] args) {
		Population pop = new Population();
		pop.run();
	}
}
/** Comparator class that compares the population first */
class CompareByPop implements Comparator<City> {
	public int compare(City cit1, City cit2) {
		if (cit1.getPopulation() != cit2.getPopulation()) return cit1.getPopulation() - cit2.getPopulation();
		else if (!cit1.getState().equals(cit2.getState())) return cit1.getState().compareTo(cit2.getState());
		else if (!cit1.getCityName().equals(cit2.getCityName())) return cit1.getCityName().compareTo(cit2.getCityName());
		else return cit1.getCityType().compareTo(cit2.getCityType());
	}
}
/** Comparator class that compares the city name first */
class CompareByName implements Comparator<City> {
	public int compare(City cit1, City cit2) {
		if (!cit1.getCityName().equals(cit2.getCityName())) return cit1.getCityName().compareTo(cit2.getCityName());
		else if (cit1.getPopulation() != cit2.getPopulation()) return cit2.getPopulation() - cit1.getPopulation();
		else if (!cit1.getState().equals(cit2.getState())) return cit1.getState().compareTo(cit2.getState());
		else return cit1.getCityType().compareTo(cit2.getCityType()); 
	}
}
/** Comparator class that compares the state first */
class CompareByState implements Comparator<City> {
	public int compare(City cit1, City cit2) {
		if (!cit1.getState().equals(cit2.getState())) return cit2.getState().compareTo(cit1.getState()); 
		else if (cit1.getPopulation() != cit2.getPopulation()) return cit1.getPopulation() - cit2.getPopulation();
		else if (!cit1.getCityName().equals(cit2.getCityName())) return cit1.getCityName().compareTo(cit2.getCityName());
		else return cit1.getCityType().compareTo(cit2.getCityType()); 
	}
}
