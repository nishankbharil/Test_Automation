package AbstractConcept;

public abstract class AbstractConcept

//abstract class can have both abstract and non abstract methods
//abstract class can not be created
//

{
	
	
	public AbstractConcept()
	{
		
	}

	public abstract int sum(int a, int b);

	private void mul(int a, int b) {
		int c = a * b;
	}
	
	public void div(int a, int b) {
		int c = a * b;
	}
}
