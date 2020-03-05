package ConstructorConcept;

public class OneObjectCallingThreeConstructor {
	
	OneObjectCallingThreeConstructor(int x, int y, int z)
	{
		this(x, y);
		System.out.println(z);
	}
	
	OneObjectCallingThreeConstructor(int x, int y)
	{
		this(x);
		System.out.println(y);
	}
	
	OneObjectCallingThreeConstructor(int x)
	{
		System.out.println(x);
	}

	public static void main(String[] args) {
		
		new OneObjectCallingThreeConstructor(10, 20, 30);
		

	}

}
