package nonterminatingLoopModel;

import java.util.LinkedList;
import java.util.Random;

/**
 * A program which keeps writing infinitely utilizing a lot of memory.
 * 
 * @author Nisha Sharma
 *
 */
public class ListGenerator {
	/**
	 * Main method which populates a linked list randomly and infinitely
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		Random random = new Random();
		LinkedList<Integer> list = new LinkedList<>();
		// System.out.println(list.size());

		// Create lots of list data at random
		while (random.nextBoolean()) {
			list.add(random.nextInt(10));
			// System.out.println(list.size());
		}
		// System.out.println(list.size());

	}
}
