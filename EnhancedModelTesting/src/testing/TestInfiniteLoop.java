package testing;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import gov.nasa.jpf.util.test.TestJPF;
import nonterminatingLoopModel.InfiniteLoop;

/**
 * Tests the Infinite For Loop and Infinite While Loop method in class Infinite
 * Loop
 * 
 * @author Varsha
 *
 */
public class TestInfiniteLoop extends TestJPF {

	InfiniteLoop infiniteLoop;

	/**
	 * Creating required instances that will be used in testing
	 */
	@Before
	public void setUp() {
		infiniteLoop = new InfiniteLoop();
	}

	/**
	 * Testing default constructor
	 */
	@Test
	public void testDefaultConstructor() {
		Assert.assertNotNull("Constructor returns null", infiniteLoop);
	}

	/**
	 * Testing the Infinite For Loop Method
	 */
	@Test
	public void testInfiniteForLoop() {
		// endless loop detector will detect loop
		infiniteLoop.infiniteForLoop();
	}

	/**
	 * Testing the Infinite While Loop Method
	 */
	@Test
	public void testInfiniteWhileLoop() {
		// endless loop detector will detect loop
		infiniteLoop.infiniteWhileLoop();
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
