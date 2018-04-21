package nonterminatingLoopModel;

public class SuspiciousForLoop {
	public static void main(String args[]) {
		System.out.println("Counter: " + increment());
	}

	public static int increment() {
		int value = 1;
		for (int i = 1; i != 10; value++) {
			// Noncompliant. Infinite; JPF runs out of memory.
			value++;
		}
		return value;
	}
}
