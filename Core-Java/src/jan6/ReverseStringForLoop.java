package jan6;

public class ReverseStringForLoop {

	public static void main(String[] args) {

		
		String arr[] = {"a", "b", "c", "d", "e"};
		int len = arr.length;
		int arrlen = len-1;
		
		for (int i=arrlen; i>=0; i--)
		{
			System.out.println(arr[i]);
		}
	}
}
