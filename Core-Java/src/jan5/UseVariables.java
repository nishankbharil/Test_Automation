package jan5;

public class UseVariables 
{

	public static void main(String[] args) 
	{
		VariableTypes v = new VariableTypes();
		v.printPi();
		
		System.out.println(v.a);
		v.a = 11;
		System.out.println(v.a);
		
		System.out.println(VariableTypes.b);
		VariableTypes.b = 21;
	
		VariableTypes v2 = new VariableTypes();
		
		System.out.println(v2.a);
	}

}