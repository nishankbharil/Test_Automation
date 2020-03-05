package staticNonStatic;

public class Static_InnerClass {

	static class a {
		private void m1() {
			System.out.println("m1");
		}
	}

	a obj1 = new a();

}
