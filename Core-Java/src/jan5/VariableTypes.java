package jan5;

public class VariableTypes
{
	
	int a = 10;  //instance variable
	static int b = 20; //static variable
	public static void main(String[] arg)
	{
		VariableTypes n = new VariableTypes();
		n.printPi();
	}
	 
	public float printPi()
	{
		int c = a+5;
		System.out.println(c);
		float pi = 3.14f;  // Local variable
		return pi;
	}
	public void printValue(String name)  //method parameter
	{
		System.out.println(name); 
	}
}
