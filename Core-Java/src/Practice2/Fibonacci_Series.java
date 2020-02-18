package Practice2;

public class Fibonacci_Series {

	public static void main(String[] args) {

		
		// 0 1 1 2 3 5
		
		int x = 0;
		int y = 1;
		int sum = 0;
		
		System.out.print(x+" "+y);
		for (int i=2; i<10; i++)
		{
			sum = x+y;
			System.out.print(" "+sum);
			x=y;
			y=sum;
		}
		

	}

}
