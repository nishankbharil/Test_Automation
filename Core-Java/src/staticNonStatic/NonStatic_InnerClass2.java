package staticNonStatic;

public class NonStatic_InnerClass2 {

	public static void main(String[] args) {
		
		NonStatic_InnerClass obj2 = new NonStatic_InnerClass();
		
		NonStatic_InnerClass.a obj3 = obj2.new a();
		obj3.m1();

	}
}
