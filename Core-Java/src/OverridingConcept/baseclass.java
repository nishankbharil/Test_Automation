package OverridingConcept;

public class baseclass extends Override {

	public static void main(String[] args) {

		baseclass obj1 = new baseclass();

		int d = obj1.sum(20, 10);

		System.out.println(d);

		Override obj2 = new Override();

		System.out.println(obj2.sum(30, 40));
		int c = obj2.b;

	}


	public int sum(int a, int b) {
		int c = a - b;
		return c;

	}
	
	public int sayHello()
	{
		System.out.println("Hello");
		return 10;
	}

}
