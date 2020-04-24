package Encapsulation_Concept;

public class Test {

	
	private int a;
	
	
	public int getA() {
		return a;
	}


	public void setA(int a) {
		this.a = a;
	}


	public static void main(String[] args) {
		
		Test t = new Test();
		t.setA(10);
		System.out.println(t.getA());
	}

}
