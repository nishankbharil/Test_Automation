package dec30;

public class Assignment 

{
	public static void main(String[] args)
	{
		int a = 40;
		int b = 20;

		System.out.println("Before Swap a = "+a +" b = "+b);

		a = a+b;
		b = a-b;
		a = a-b;

		System.out.println("After Swap a = "+a +" b = "+b);
	}

}