package Java_Concepts1;

public class Java_Concept11 implements Cloneable {

	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	String name = "Microprocessor";

	public static void main(String[] args) {
		Java_Concept11 obj1 = new Java_Concept11(); // creating object of class
		try 
		{
			Java_Concept11 obj2 = (Java_Concept11) obj1.clone();
			System.out.println(obj1.name);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
