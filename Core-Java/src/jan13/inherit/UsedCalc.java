package jan13.inherit;

public class UsedCalc
{

	public static void main(String[] args) 
	
	{
		Calc obj1 = new Calc();
		obj1.add(3, 4);
		obj1.sub(3, 4);
		obj1.mul(3, 4);
		obj1.div(3, 4);
		
		AdvanceCalc obj2 = new AdvanceCalc();
		
		obj2.add(3, 4);
		obj2.sub(3, 4);
		obj2.mul(3, 4);
		obj2.div(3, 4);
		obj2.square(10);
	}

}
