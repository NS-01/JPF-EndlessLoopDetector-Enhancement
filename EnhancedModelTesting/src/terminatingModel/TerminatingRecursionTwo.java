package terminatingModel;

public class TerminatingRecursionTwo {
	// recursion should stop after 9 attempts
	static int stopindex = 9;

	public static void main(String[] args) {
		// a=number of "O"s and b=number of "X"s
		int a = 9;
		int b = 1;
		recursion(a, b);
	}

	// Fix
	public static void recursion(int a, int b) {
		// stop condition of recursion
		if (a == b)
			return;

		// printing of pattern
		for (int i = a - b; i > 0; i--) {
			System.out.print("O");
		}
		for (int j = 0; j < b; j++) {
			System.out.print("X");
		}
		System.out.println();

		++b;
		recursion(a, b);
	}
}