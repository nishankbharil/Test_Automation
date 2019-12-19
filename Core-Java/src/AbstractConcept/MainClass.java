package AbstractConcept;

public class MainClass {

	public static void main(String[] args) {
		Bank b = new Bank();
		b.sum(10, 20);
		
		AbstractConcept ab = new Bank();
		ab.div(10, 20);
		
	}

}
