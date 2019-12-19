package Practice;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

public class Generate_Random_Number_String {

	public static void main(String[] args) {

		// Approach1
		Random rand = new Random();
		int a = rand.nextInt(100);

		System.out.println(a);

		// Approach2
		System.out.println(Math.random() * 1000);

		// Approach2- Apache common lang
		String str = RandomStringUtils.randomNumeric(5);
		System.out.println(str);

		// Generate Random String
		String rnd = (RandomStringUtils.randomAlphabetic(20)).toUpperCase();
		System.out.println(rnd);
		
//		System.out.println('a'+'b');
//		
//		int x= 10;
//		int y=(++x)++;
//		System.out.println(y++);

	}

}
