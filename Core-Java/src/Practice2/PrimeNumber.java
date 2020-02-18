package Practice2;

public class PrimeNumber {

	public static void main(String[] args) {

		int num = 15;
		boolean flag = false;
		
		for(int i=2; i<num; i++)
		{
			if(num%i==0)
			{
				System.out.println("Not Prime");
				flag = true;
			}
		}
		
		if (flag ==false)
		{
			System.out.println("Prime");
		}
		
		
	}

}
