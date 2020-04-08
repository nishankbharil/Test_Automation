package Practice;

import java.util.ArrayList;

public class Find_Duplicates_In_Array {

	public static void main(String[] args) {
		String s[] = {"Java", "Python", "C", "C++", "Java"};
		
		for (int i=0; i<s.length; i++)
		{
			for (int j=i+1; j<s.length; j++ )
			{
				if(s[i]==s[j])
				{
					System.out.println("Found duplicate: "+ s[i]);
				}
			}		
		}
	}
}