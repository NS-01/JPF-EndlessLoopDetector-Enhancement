package nonterminatingRecursionModel;

public class RecursionTwo {
	// recursion should stop after 9 attempts
	static int stopindex = 9;
	
	public static void main(String[] args) {
		// a=number of "O"s and b=number of "X"s
		int a = 9;
		int b = 1;
		recursion(a, b);
	}

	// Problem
	public static void recursion(int a, int b) {
		
		// start of recursion at index 1
		int startindex = 1;

		// stop condition of recursion
		if (startindex == stopindex)
			return;

		// printing of pattern
		for (int i = a; i > 0; i--) {
			System.out.print("O");
		}
		for (int j = 0; j < b; j++) {
			System.out.print("X");
		}
		System.out.println();

		--a;
		++b;
		++startindex;
		recursion(a, b);
	}
}
