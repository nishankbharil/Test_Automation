package jan19;

public class AdvanceCalc extends Calc
{

	public void add(int a, int b, int c)//Static binding or Method overloading
	{
		System.out.println(a+b+c);
	}
	public void div(int a, int b)// Dynamic binding or Method overriding
	{
		if (b==0)
		{
			System.out.println("Can not divide with zero");
			return;
		}
		
		System.out.println(a/b);
		
		
	}
}
