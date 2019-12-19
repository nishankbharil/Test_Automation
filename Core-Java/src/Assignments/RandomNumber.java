package Assignments;

import java.util.Random;

public class RandomNumber {
	public static void main(String[] args) throws InterruptedException {

		for (int i = 0; i < 1000000000; i++) {
			System.out.println(RandomNumber.getRandomNumberString());
		}

	}

	public static String getRandomNumberString() {
		// It will generate 6 digit random Number from 100000 to 999999.
		Random rnd = new Random();
		int number = rnd.nextInt(999999);

		// this will convert any number sequence into 6 character.
		String strNum = String.format("%06d", number);
		if (strNum.length() == 5) {
			strNum = "1" + strNum;
		} else if (strNum.length() == 6) {
			String strFirstDigit = strNum.substring(0, 1);
			if (strFirstDigit.contains("0")) {
				strNum = strNum.substring(1);
				strNum = "1" + strNum;
			}
		}
		return strNum;
	}

}