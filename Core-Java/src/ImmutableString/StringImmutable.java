package ImmutableString;

public class StringImmutable 
{

	public static void main(String[] args) 
	{
		String s1 = "Test";
		String s2 = "Automation";
		
		StringImmutable obj1 = new StringImmutable();
		
		System.out.println(s1.concat(s2)); //TestAutomation
		
		s1 = "Change";
		
		
		System.out.println(s1);
	}
}
