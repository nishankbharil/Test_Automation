package EncapsulationConcept;

public class ChildClass extends Encap
{
	private String name;
	private int age;
	private String company;
	
	
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public int getAge() {
		return age;
	}



	public void setAge(int age) {
		this.age = age;
	}



	public String getCompany() {
		return company;
	}



	public void setCompany(String company) {
		this.company = company;
	}



	public static void main(String[] args) 
	
	{
		ChildClass obj  = new ChildClass();
		
		obj.setAge(20);
		obj.setCompany("IBM");
		obj.setName("Testing");
		
		System.out.println(obj.getAge());
		System.out.println(obj.getCompany());
		System.out.println(obj.getName());
		
	}

}
