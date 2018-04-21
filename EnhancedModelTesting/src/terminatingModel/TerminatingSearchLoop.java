package terminatingModel;

import java.util.Random;

/**
 * Endless random search class that does not terminate the search.
 * 
 * @author Varsha Ragavendran
 *
 */
public class TerminatingSearchLoop {

	public static void main(String[] args) {
		TerminatingSearchLoop searchLoop = new TerminatingSearchLoop();
		searchLoop.search();
	}

	/**
	 * Endless random search
	 */
	public void search() {
		Random random = new Random();
		System.out.println("0");
		if (random.nextBoolean()) {
			System.out.println("2");
		} else {
			System.out.println("1");
			if (random.nextBoolean()) {
				System.out.println("4");
			} else {
				System.out.println("3");
				if (random.nextBoolean()) {
					System.out.println("6");
				} else {
					System.out.println("5");
				}
			}
		}
	}
}