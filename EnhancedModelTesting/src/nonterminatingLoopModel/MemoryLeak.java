package nonterminatingLoopModel;

import java.util.List;
import java.util.ArrayList;

/**
 * Class to mock memory leak
 * 
 * @author Nisha
 *
 */
public class MemoryLeak {
	public static List list = new ArrayList();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for (int i = 0; i >= 0; i++) {
			list.add(i);
		}
		System.out.println("List Size: " + list.size());
	}

}
