package nonterminatingRecursionModel;

/**
 * Recursive method executed endlessly
 * 
 * @author Varsha Ragavendran , Nisha Sharma
 *
 */
public class InfiniteRecursion {
	/**
	 * Main method used for executing/verifying jpf config file
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		InfiniteRecursion infiniteRecursion = new InfiniteRecursion();
		infiniteRecursion.recursiveMethod();
	}

	/**
	 * Method recursively calls itself
	 */
	public void recursiveMethod() {
		/*
		 * for(int i = 0; i < 2 ; i++) { recursiveMethod(); }
		 */
		recursiveMethod();
	}
}
