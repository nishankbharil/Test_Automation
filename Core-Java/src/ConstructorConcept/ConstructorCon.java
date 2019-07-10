package ConstructorConcept;

public class ConstructorCon 

/* Constructor is use to initialize the instance variable
 * Constructor is having the same name as class name
 * Constructor do not have return type
 * A class can have multiple constructors with polymorphism
 * constructor is called when object of a class is created * 
 */
{
	public ConstructorCon()
	{
		System.out.println("Constructor is called");
	}
	
	public ConstructorCon(int age)
	{
		System.out.println(age);
	}

	public ConstructorCon(int age, String name)
	{
		System.out.println("Age: "+age+" Name: "+name);
	}
	
	public static void main(String[] args) 
	
	{
		ConstructorCon obj1 = new ConstructorCon();
		ConstructorCon obj2 = new ConstructorCon(20);
		ConstructorCon obj3 = new ConstructorCon(20, "tom");
		System.out.println(obj1);
		System.out.println(obj2);
		System.out.println(obj3);
	}
}