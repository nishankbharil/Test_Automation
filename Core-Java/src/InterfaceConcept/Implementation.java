package InterfaceConcept;

public class Implementation implements Bank {

	public static void main(String[] args) 
	
	{
		Implementation a = new Implementation();
		a.credit();
		a.debiit();
	}

	@Override
	public void credit() 
	{
		System.out.println("Credit");
	}

	@Override
	public void debiit() 
	{
		System.out.println("Debit");
	}

	public int account()
	{
		System.out.println("Account");
		return 0;
	}
}