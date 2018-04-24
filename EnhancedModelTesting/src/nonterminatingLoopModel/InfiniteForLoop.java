package nonterminatingLoopModel;

/**
 * Problematic Code.
 * 
 * This class consists of infinite while / for loops. To be tested with Endless
 * Loop Detector in JPF-Core
 * 
 * @author Varsha Ragavendran, Nisha Sharma
 *
 */
public class InfiniteForLoop {
	/**
	 * Main method used for executing/verifying jpf config file
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		InfiniteForLoop loop = new InfiniteForLoop();
		loop.infiniteForLoop();
	}

	/**
	 * Endless For Loop
	 */
	public void infiniteForLoop() {
		for (;;) {
			System.out.print("");
		}
	}

}
