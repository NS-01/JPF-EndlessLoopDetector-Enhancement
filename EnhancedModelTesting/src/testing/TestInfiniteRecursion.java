package testing;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import gov.nasa.jpf.util.test.TestJPF;
import nonterminatingRecursionModel.InfiniteRecursion;

/**
 * Tests the Infinitely recursive method in class Infinite Recursion
 * 
 * @author Varsha
 *
 */
public class TestInfiniteRecursion extends TestJPF {
	InfiniteRecursion infiniteRecursion;

	/**
	 * Creating required instances that will be used in testing
	 */
	@Before
	public void setUp() {
		infiniteRecursion = new InfiniteRecursion();
	}

	/**
	 * Testing default constructor
	 */
	@Test
	public void testDefaultConstructor() {
		Assert.assertNotNull("Constructor returns null", infiniteRecursion);
	}

	/**
	 * Testing the Infinitely recursive method
	 */
	@Test
	public void testRecursiveMethod() {
		// endless loop detector will detect loop
		infiniteRecursion.recursiveMethod();
	}

	/**
	 * Allows use to use jpf config file to verify
	 * 
	 * @param testMethods
	 */
	public static void main(String[] testMethods) {
		runTestsOfThisClass(testMethods);
	}

}
