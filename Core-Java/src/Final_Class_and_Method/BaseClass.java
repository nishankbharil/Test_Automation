package Final_Class_and_Method;

final public class BaseClass {

	public static void main(String[] args) {

		FinalClassAndMethod f = new FinalClassAndMethod();

		f.sum();
//		f.a = 30;
		System.out.println(f.a);

	}

	public static void sum() {
		System.out.println("test");
	}

}
