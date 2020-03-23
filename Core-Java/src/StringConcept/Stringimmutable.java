package StringConcept;

public class Stringimmutable {

	public static void main(String[] args) {

		
		String s1 = "hello";
		String s2 = "hello";
		
		System.out.println(s1.intern());
		
		System.out.println(s2.indexOf('e'));

	}

}
