package jan20;

public class Fibonacci {

	/**
	 * [1,1,2,3,5,8]
	 * 
	 */
	public static void main(String[] args)

	{
		int x = 1;
		int y = 1;
		int z = 0;

		int num = 100;
		System.out.println(x);
		System.out.println(y);
		for (int i = 1; i <= num; i++) {
			z = x + y;

			System.out.println(z);

			x = y;
			y = z;

		}

	}

}
