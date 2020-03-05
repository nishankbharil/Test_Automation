package staticNonStatic;

import staticNonStatic.StaticMethod.A;

public class StaticMethod {

	static int a = 100;

	int b = 200;

	public class A {

//		static int c = 10;     // Compile time error Static can not be declared in side non static inner class
		int d = 20;

		public void m1() {
			System.out.println("Method - m1");
			System.out.println(a);
			System.out.println(b);
		}
	}

	public static class B {
		static int c = 10;
		int d = 20;

		public void m2() {
			System.out.println("Method - m2");
			System.out.println(c);
			System.out.println(d);
			System.out.println(a);
//			System.out.println(b);   // Compilation error
		}
	}
	
	A obj3 = new A();

	public static void main(String[] args) {

		StaticMethod obj1 = new StaticMethod();
		

		obj1.b = 10;
		obj1.a = 20;

		obj1.m4();
		obj1.m3();

	}

	public int m3() {
		int f = 30;
		System.out.println("Method - m3");
		return f;
	}

	public static int m4() {
		int e = 10;
		System.out.println("Method - m4");
		return e;
	}

}
