package Assignments;

public class ReverseStringForLoop {

	public static void main(String[] args) {

		
		String arr[] = {"a", "b", "c", "d", "e"};
		int len = arr.length;
		int arrlen = len-1;
		
		for (int i=arrlen; i>=0; i--)
		{
			System.out.println(arr[i]);
		}
		
		String arr1[] = new String[len];
		int j = 0;
		for (int i=arrlen; i>=0; i--)
		{
			arr1[j] = arr[i];
			j++;
		}
		
		for(String temp: arr1)
		{
			System.out.print(temp);
		}
	}
}
