package nonterminatingRecursionModel;

public class DoubleMethodRecursion {

	/**
	 * Main method used for executing/verifying jpf config file
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		DoubleMethodRecursion doubleMethodRecursion = new DoubleMethodRecursion();
		doubleMethodRecursion.methodA();
	}

	public void methodA() {
		methodB();
	}

	public void methodB() {
		methodA();
	}
}
