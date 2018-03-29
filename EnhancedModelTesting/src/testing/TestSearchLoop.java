package testing;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import gov.nasa.jpf.util.test.TestJPF;
import nonterminatingLoopModel.SearchLoop;

/**
 * Tests the random search method in class SearchLoop
 * 
 * @author Varsha
 *
 */
public class TestSearchLoop extends TestJPF {

	SearchLoop searchLoop;

	/**
	 * Creating required instances that will be used in testing
	 */
	@Before
	public void setUp() {
		searchLoop = new SearchLoop();
	}

	/**
	 * Testing default constructor
	 */
	@Test
	public void testDefaultConstructor() {
		Assert.assertNotNull("Constructor returns null", searchLoop);
	}

	/**
	 * Testing the random search method
	 */
	@Test
	public void testSearch() {
		// endless loop detector will detect loop
		searchLoop.search();
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
