
public class InfiniteRecursion {
	public static void main(String[] args) {
		recursiveMethod();
	}

	public static String recursiveMethod() {
/*		for(int i = 0; i < 2 ; i++) { recursiveMethod(); }
*/		
		return recursiveMethod();
	}
}
