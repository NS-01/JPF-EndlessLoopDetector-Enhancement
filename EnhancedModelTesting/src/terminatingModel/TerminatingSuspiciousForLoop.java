package terminatingModel;

/**
 * Correct code.
 * 
 * Compliant Suspicious For Loop
 * 
 * @author Nisha Sharma
 *
 */
public class TerminatingSuspiciousForLoop {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Counter: " + increment());
	}

	public static int increment() {
		int value = 1;
		for (int i = 1; i != 10; i++) {
			// Fixed by changing value++ in for loop with i++
			value++;
		}
		return value;
	}
}
