package nonterminatingRecursionModel;

import java.util.Random;

public class SwitchRecursion {
	// constant number used in switch
	final int SWITCHCOUNT = 0;

	/**
	 * Main method used for executing/verifying jpf config file
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SwitchRecursion sR = new SwitchRecursion();
		Random random = new Random();
		if (random.nextBoolean()) {
			sR.switchOne();
		} else {
			sR.switchTwo();
		}
	}

	/**
	 * 
	 */
	public void switchOne() {
		while (true) {
			switch (SWITCHCOUNT) {
			case 0:
				switchTwo();
				break;
			}
		}

	}

	/**
	 * 
	 */
	public void switchTwo() {
		switch (SWITCHCOUNT) {
		case 1:
		case 2:
		default:
			switchOne();
		}
	}
}
