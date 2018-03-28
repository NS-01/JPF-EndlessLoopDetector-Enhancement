package nonterminatingLoopModel;

/**
 * This class consists of infinite while / for loops. To be tested with Endless
 * Loop Detector in JPF-Core
 * 
 * @author Varsha
 *
 */
public class InfiniteLoop {
	/**
	 * Main method used for executing/verifying jpf config file
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		InfiniteLoop loop = new InfiniteLoop();
		loop.infiniteForLoop();
		loop.infiniteWhileLoop();
	}

	/**
	 * Endless For Loop
	 */
	public void infiniteForLoop() {
		for (;;) {
			System.out.print("");
		}
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
