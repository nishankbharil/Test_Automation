package Practice;

public class Sum_Of_Elements_Of_Array {

	public static void main(String[] args) {
		int a[] = {1,2,3,4,5,6};
		
		int len = a.length;
		
		int sum = 0;
		
		for (int i = 0; i<=len-1; i++)
		{
			sum = sum + a[i];
		}
		
		System.out.println(sum);

	}

}
