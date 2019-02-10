package jan19;

public class UsedCalc 
{

	public static void main(String[] args) 
	{
		
		Calc cc = new Calc();
		cc.add(2, 3);
		cc.div(33, 11);
		
		AdvanceCalc ac = new AdvanceCalc();
		ac.add(2, 3, 4);
		ac.div(4, 1);
		
		Calc obj1 = new AdvanceCalc();
		obj1.add(2, 3);  	// from Calc
		obj1.sub(22, 2);    // from Calc
		obj1.mul(2, 3);     // from Calc
		obj1.div(4, 2);     // from AdvanceCalc
		
		//obj1.add(2, 3, 3); //Error because compiler thinks its a Calc object
		((AdvanceCalc)obj1).add(2, 3, 4);
	}
}
