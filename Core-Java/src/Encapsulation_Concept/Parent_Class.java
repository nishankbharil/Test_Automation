package Encapsulation_Concept;

public class Parent_Class {

	private int rollnumber;

	public int getRollnumber() {
		return rollnumber;
	}

	public void setRollnumber(int rollnumber) {
		this.rollnumber = rollnumber;
	}

	public static void main(String[] args) {

		Parent_Class p = new Parent_Class();
		p.setRollnumber(110);
		int a = p.getRollnumber();

		System.out.println(a);

	}

}
