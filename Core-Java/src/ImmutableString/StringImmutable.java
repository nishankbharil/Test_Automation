package ImmutableString;

public class StringImmutable 
{

	public static void main(String[] args) 
	{
		String s1 = "Test";
		String s2 = s1;
		
		System.out.println(s1.concat("Automation")); //TestAutomation
		
		System.out.println(s2);//Test
		System.out.println(s1);//Test
		
	}
}
