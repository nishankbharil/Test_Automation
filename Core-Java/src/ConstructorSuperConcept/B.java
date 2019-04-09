package ConstructorSuperConcept;

public class B extends A
{
/*Super keyword is used to call parent class constructor in case if parent class is having more then one constructor
 * It should be the first statement in any constructor 
 */
	public B()
	{
		super();
		System.out.println("Base class conctructor");
	}
	
	public B(int i)
	{
		super(i);
		System.out.println("Base class conctructor");
	}
	
	
	public static void main(String[] args) 
	{
		B obj2 = new B();
		B obj3 = new B(10);
	}

}
