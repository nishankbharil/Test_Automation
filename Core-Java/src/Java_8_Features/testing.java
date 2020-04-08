package Java_8_Features;

public class testing implements Method_With_Body, Static_Method{

	public static void main(String[] args) {

		testing obj1 = new testing();
		obj1.test1();
		obj1.test2();
		Static_Method.tes3();
		obj1.tes4();

	}

	@Override
	public void test2() {
		
		System.out.println("this is test 2 method");
	}
	
	

}
