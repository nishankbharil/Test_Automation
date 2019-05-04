package ImmutableString;

public class StringImmutable 
{

	public static void main(String[] args) 
	{
		String s1 = "Test";
		
//		StringImmutable obj1 = new StringImmutable();
		
		System.out.println(s1.concat("Automation")); //TestAutomation
		
//		s1 = "Change";
		String s2 = s1.concat("Automation");
		
		System.out.println(s2);//TestAutomation
		System.out.println(s1);//Test
		
		int i = 10;
		i = 20;
		
		System.out.println(i);
		
		String s = "AAA";
		s.concat("BBB");
		
		System.out.println(s);
		
		String s13 = "aaa";
		s13 = s13.concat("bbb");
		
		//System.out.println(s14);
		System.out.println(s13);
	}
}
