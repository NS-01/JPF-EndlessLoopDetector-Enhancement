package nonterminatingLoopModel;

/**
 * This class consists of infinite while / for loops. To be tested with Endless
 * Loop Detector in JPF-Core
 * 
 * @author Varsha Ragavendran, Nisha Sharma
 *
 */
public class InfiniteWhileLoop {
	/**
	 * Main method used for executing/verifying jpf config file
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		InfiniteWhileLoop loop = new InfiniteWhileLoop();
		loop.infiniteWhileLoop();
	}

	/**
	 * Endless While Loop
	 */
	public void infiniteWhileLoop() {
		while (true) {
			System.out.print("");
		}
	}
}
