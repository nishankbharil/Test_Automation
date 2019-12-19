package Assignments;

public class Reverse_Sentence {

	public static void main(String[] args)
	{

		String s1 = "I lives in pune and pune is a good city";
		String s2 = "";
		
		StringBuffer sb = new StringBuffer(s1);
		StringBuffer s =  sb.reverse();
		String a = s.toString();
		System.out.println(a);
		
	}

}
