package Practice;

public class Searching_An_Element_In_Array {

	public static void main(String[] args) {

		int a[] = { 10, 20, 30, 40, 50, 60 };

		int searchNum = 90;
		boolean flag = false;

		for (int i = 0; i < a.length; i++) {
			if (a[i] == searchNum) {
				flag = true;
				System.out.println("Number exist in list");
			}

		}
		if (flag != true) {
			System.out.println("Number does not exist in list");
		}
	}

}
