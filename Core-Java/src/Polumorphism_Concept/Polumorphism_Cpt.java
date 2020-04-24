package Polumorphism_Concept;

public class Polumorphism_Cpt {

	public static void main(String[] args) {
		
		Polumorphism_Cpt obj1 = new Polumorphism_Cpt();
		
		obj1.sum(10, "ABC");

	}

	public int sum(int a, String b) {
		System.out.println("I am in Integer Class");

		return 10;
	}

	public String sum(String b, int a) {
		System.out.println("I am in String Class");

		return "test";
	}

}
