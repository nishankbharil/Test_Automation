package Java_Concepts1;

public class Java_Concepts1 {

//	static int Count = 0;
	int Count = 0;

	Java_Concepts1() {
		Count = Count + 1;
		System.out.println(Count);
	}

	public static void main(String[] args) {

		Java_Concepts1 obj1 = new Java_Concepts1();
		Java_Concepts1 obj2 = new Java_Concepts1();
		Java_Concepts1 obj3 = new Java_Concepts1();

	}

}
