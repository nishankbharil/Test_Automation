package jan6;

import jan6.p1.Nested;
import jan6.Parameters;

public class TestPackage
{

	public static void main(String[] args) 
	{
		Nested n = new Nested();
		Parameters A1 = new Parameters();
		int d = A1.add(10, 20);
		System.out.println(d);
		System.out.println(n);
	}
}
