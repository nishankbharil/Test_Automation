package ConstructorConcept;

public class ThisConcept
{

	/* this keyword is used to initialise the instance or class variable
	 * this is the first keyword in constructor
	 */
	String name;
	int age;
	
	public ThisConcept(String name, int age)
	{
		this.name = name;
		this.age = age;
		
		System.out.println(name);
		System.out.println(age);
	}
	
	
	public static void main(String[] args) 
	{
		ThisConcept obj2 = new ThisConcept("Tom", 15);
	}

}
