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

	private CompareByName cbn;
	
	/** Constructor */
	public Population() {
		cities = new ArrayList<City>();
		cbn = new CompareByName();
	}

	/**
	 * Uses selection sort to sort cities in ascending population order
	 */
	public void sortAscendPop() {
		for (int outer = cities.size() - 1; outer > 0; outer--) {
			int maxIndex = 0;
			for (int inner = 0; inner <= outer; inner++) {
				if (cities.get(inner).getPopulation() > cities.get(maxIndex).getPopulation())  {
					maxIndex = inner;
				}
			}
			swap(cities, maxIndex, outer);
		}
		//for (City result : cities) System.out.println(result.getCityName() 
		//+" " +result.getState() +" "+result.getCityType() + " " + result.getPopulation());
		
	}

	/**
	 * Uses merge sort to sort cities in descending population order
	 */
	public void sortDescendPop() {
		temp = new ArrayList<City>(cities);
		sortDescendPop(cities, 0, cities.size() - 1);
		//for (City result : cities) System.out.println(result.getState() 
		//+" " +result.getCityName() +" "+result.getCityType() + " " + result.getPopulation());
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
		for (int outer = 1; outer < cities.size(); outer++) {
			int inner = outer;
			while (inner > 0 && cities.get(inner).getCityName()
					.compareTo(cities.get(inner - 1).getCityName()) < 0) {
				swap(cities, inner, inner - 1);
				inner--;
			}
		}
		//for (City result : cities) System.out.println(result.getCityName() 
		//+" " +result.getState() +" "+result.getCityType() + " " + result.getPopulation());
	}

	
	/**
	 * Uses merge sort to sort cities in descending name order
	 */
	public void sortDescendName() {
		temp = new ArrayList<City>(cities);
		sortDescendName(cities, 0, cities.size() - 1);
		//for (City result : cities) System.out.println(result.getCityName() 
		//+" " +result.getState() +" "+result.getCityType() + " " + result.getPopulation());
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
		storePopulationData();

		long startMillisec1 = System.currentTimeMillis();
		sortAscendPop();
		long endMillisec1 = System.currentTimeMillis();
		long startMillisec2 = System.currentTimeMillis();
		sortDescendPop();
		long endMillisec2 = System.currentTimeMillis();
		long startMillisec3 = System.currentTimeMillis();
		sortAscendName();
		long endMillisec3 = System.currentTimeMillis();
		
		long startMillisec3a = System.currentTimeMillis();
		sortAscendName();
		long endMillisec3a = System.currentTimeMillis();
		
		long startMillisec4 = System.currentTimeMillis();
		sortDescendName();
		long endMillisec4 = System.currentTimeMillis();
		System.out.println(startMillisec1 - endMillisec1); //select
		System.out.println(startMillisec2 - endMillisec2); //merge
		System.out.println(startMillisec3 - endMillisec3); //insert
		
		System.out.println(startMillisec3a - endMillisec3a); //insert special
		
		System.out.println(startMillisec4 - endMillisec4); //merge
	}

	/** Main method */
	public static void main(String[] args) {
		Population pop = new Population();
		pop.run();
	}
}
class CompareByName implements Comparator<City> {
	public int compare(City cit1, City cit2) {
		if (!cit1.getCityName().equals(cit2.getCityName())) return cit1.getCityName().compareTo(cit2.getCityName());
		else if (cit1.getPopulation() != cit2.getPopulation()) return cit1.getPopulation() - cit2.getPopulation();
		else return cit1.getState().compareTo(cit2.getState());
	}
}
