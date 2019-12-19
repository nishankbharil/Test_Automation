package Assignments;

public class AllStringFunctions
{

	public static void main(String[] args)
	
	{
		String s = "Hello World";
		String s2 = "Program";
		String s3 = s + s2;
		System.out.println(s3);//Hello WorldProgram
		
		int lengthofStr = s.length();
		System.out.println("Length = "+ lengthofStr);//11
		
		String capitalS = s.toUpperCase();
		System.out.println("Capital = "+ capitalS);//HELLO WORLD
		
		String smallS = s.toLowerCase();
		System.out.println("Small = "+ smallS);//hello world
		
		String s4 = "HELLO World";
		boolean eq = s.equals(s4);
		System.out.println("eq = "+ eq);//False

		boolean eq1 = s.equalsIgnoreCase(s4);
		System.out.println("eq1 = "+ eq1);//True
		
		String s5 = "      This is Java   ";
		String s6 = s5.trim();
		System.out.println("s6 = "+ s6);//This is Java
		
		boolean containsWor = s.contains("wor");
		System.out.println("containwor = "+ containsWor);//False
		
		boolean startsWith = s.startsWith("hell");
		System.out.println("startWith = "+ startsWith );//True
		
		boolean endsWith = s.endsWith("rld");
		System.out.println("startWith = "+ endsWith );//True
		
		String s7 = s.replace("llo", "LLO");
		System.out.println("s7 = "+ s7 );// HeLLO World
		
		String s8 = s.substring(4);
		System.out.println("s8 = "+ s8 );//o World
		
		String s9 = s.substring(4, 7);
		System.out.println("s9 = "+ s9 );//o w
		
		String [] arr = s.split(" ");
		for (String temp: arr)
		{
			System.out.println(temp);
		}
	}
}
