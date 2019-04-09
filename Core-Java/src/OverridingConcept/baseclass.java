package OverridingConcept;

public class baseclass extends Override{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		baseclass obj1 = new baseclass();
		
		int d = obj1.sum(20, 10);
		
		System.out.println(d);

	}
	
	public int sum(int a, int b)
	{
		int c = a-b;
		return c;
		
	}

}
