package StaticOverloadConcept;

public class StaticOverload_C {

	public static void main(String[] args)
	{

		StaticOverload_B obj1 = new StaticOverload_B();
		StaticOverload_A.sum();
		obj1.sum();
		obj1.mul();
		
		StaticOverload_A obj2 = new StaticOverload_B();
		
		obj2.sum();
		obj2.mul();
	}

}
