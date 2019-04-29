package commons;

import java.util.Random;

public class AbstractTest {
	public int RandomEmail() {
		Random random = new Random();
		int number = random.nextInt(999999);
		return number;
	}
}
