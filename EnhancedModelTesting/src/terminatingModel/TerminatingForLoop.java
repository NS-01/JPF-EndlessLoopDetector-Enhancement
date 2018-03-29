package terminatingModel;

/**
 * Class that invokes a method based on the number of times specified.
 * Guarenteed to terminate.
 * 
 * @author Varsha
 *
 */
public class TerminatingForLoop {

	public static void main(String[] args) {
		final int numberOfLoops = 1000;
		for (int i = 0; i < numberOfLoops; i++) {
			printMethod(i);
		}
	}

	public static void printMethod(int num) {
		System.out.println("number: " + num);
	}
}
