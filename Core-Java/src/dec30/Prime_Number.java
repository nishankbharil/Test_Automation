package dec30;

public class Prime_Number {

	public static void main(String[] args) {
		
		int num = 13;
		boolean bflag = false;
		
		for (int i =2; i<num; i++)
		{
			if (num%i==0)
			{
				System.out.println("Number - "+num +" is not a prime number");
				bflag = true;
				break;
			}
		}
		
		if (bflag == false)
		{	
			System.out.println("Number - "+num +" is a prime number");
		}
	}
}
