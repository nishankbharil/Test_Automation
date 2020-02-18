package jan5;

public class UseVariables 
{

	public static void main(String[] args) 
	{
		VariableTypes v = new VariableTypes();
		v.printPi();//15
		
		System.out.println(v.a);//10
		v.a = 11;
		System.out.println(v.a);//11
		
		System.out.println(VariableTypes.b);//20
		VariableTypes.b = 21;
	
		VariableTypes v2 = new VariableTypes();
		
		System.out.println(v2.a);//10
	}

}