package dec30;

public class Assignment3 {

	public static void main(String[] args) {
		
		int a = 10;
		boolean bflag = false;
		
		for (int i =2; i<a; i++)
		{
			if (a%i!=0)
			{
				
			}
			else
			{
				bflag = true;
			}
		}
		if (bflag == true)
		{	
		System.out.println("Number - "+a +" is a prime number");
		}
	}
}
