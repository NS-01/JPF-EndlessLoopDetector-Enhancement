package nonterminatingLoopModel;

import java.util.List;
import java.util.ArrayList;

/**
 * Problematic Code.
 * 
 * Memory Leak causes JPF to run out of memory when executing an infinite loop.
 * EndlessLoopDetector verifies that statespacelimit has not reached, to verify
 * if JPF will run out of memory.
 * 
 * @author Nisha Sharma
 *
 */
public class MemoryLeak {
	public static List<Integer> list = new ArrayList<Integer>();

	/**
	 * @param args
	 *            Loops forever causing JPF to run out of memory.
	 */
	public static void main(String[] args) {
		for (int i = 0; i >= 0; i++) {
			list.add(i);
		}
		System.out.println("List Size: " + list.size());
	}

}
