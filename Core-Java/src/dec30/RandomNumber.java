package dec30;

import java.util.Random;

public class RandomNumber {
	public static void main(String[] args) {

		int n1 = 1000000000 + new Random().nextInt(900000000);

		System.out.println(n1);

	}

}