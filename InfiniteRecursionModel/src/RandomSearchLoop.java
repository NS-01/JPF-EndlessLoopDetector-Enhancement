import java.util.Random;

public class RandomSearchLoop {
	public static void main(String[] args) {
		Random random = new Random();
		System.out.println("0");
		if (random.nextBoolean()) {
			System.out.println("2");
		} else {
			System.out.println("1");
			if (random.nextBoolean()) {
				System.out.println("4");
			} else {
				System.out.println("3");
				if (random.nextBoolean()) {
					System.out.println("6");
				} else {
					System.out.println("5");
				}
			}
		}
	}
}