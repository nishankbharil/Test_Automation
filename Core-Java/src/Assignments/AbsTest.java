package Assignments;

public abstract class AbsTest 
{
	int add(int a, int b)
	{
		String s = "554";
		int i = Integer.parseInt(s);
		System.out.println(i+1);
		//double k = Math.random();
		//System.out.println(k);
		return a+b;
	}
	
	abstract double root(int x);

}

class MyTest extends AbsTest
{
	double root(int x)
	{
		return Math.sqrt(x);
		
	} 
}