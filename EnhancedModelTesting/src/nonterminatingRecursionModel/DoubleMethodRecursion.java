package nonterminatingRecursionModel;

/**
 * Infinite Recursion between two methods causing circularity
 * 
 * @author Varsha Ragavendran, Nisha Sharma
 *
 */
public class DoubleMethodRecursion {

	/**
	 * Main method used for executing/verifying jpf config file
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		DoubleMethodRecursion doubleMethodRecursion = new DoubleMethodRecursion();
		// circularity -- infinite loop
		doubleMethodRecursion.methodA();
	}

	/**
	 * Invokes methodB. Causes circularity between the two methods.
	 */
	public void methodA() {
		methodB();
	}

	/**
	 * Invokes methodA. Causes circularity between the two methods.
	 */
	public void methodB() {
		methodA();
	}
}
