package Assignments;

public class GetNumberFromString
{

	public static void main(String[] args)
	{
		String s2 = "\"Hello World\"";
		String s3 = "\r Hello World";///n, /t, /r--carriage return
		System.out.println(s2);
		System.out.println(s3);
		
		String s = "Item #(1234)";
		//find numeric part of above string
		String[] arr = s.split("\\(");
		
		String s1 = arr[1].replace(")", "");
		
		System.out.println(s1);
	}

}
