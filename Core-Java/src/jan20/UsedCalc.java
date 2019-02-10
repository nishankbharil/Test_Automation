package jan20;

public class UsedCalc
{

	public static void main(String[] args)
	{
		ICalc rc = new RealCalc();  //RealCalc rc = new RealCalc();
		int res = rc.add(3, 4);
		System.out.println(res);
		
		int mul = ((RealCalc)rc).mul(4, 5);
		System.out.println(mul);
	}

}
