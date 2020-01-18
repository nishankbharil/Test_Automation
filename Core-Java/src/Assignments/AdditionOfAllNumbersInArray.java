package Assignments;

public class AdditionOfAllNumbersInArray {

	public static void main(String[] args) {
		
		int arr[] = {10,20,30,40,50};
		int len = arr.length;
		int sum = 0;
		for (int i=0; i<len; i++)
		{
			sum = sum + arr[i];
		}
		System.out.println(sum);
	}

}
