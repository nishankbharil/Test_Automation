package Practice;

public class Reverse_String {

	public static void main(String[] args) {
		
		// 1] string Concatination operator

		String s = "NISHANK BHARIL";
		String rev = "";

		int len = s.length();
		for (int i = len - 1; i >= 0; i--) {
			rev = rev + s.charAt(i);
		}
		System.out.println(rev);

		// --------------------------------------
		// 2. Character Array

		char a[] = s.toCharArray();
		int leng = a.length;

		for (int i = leng - 1; i >= 0; i--) {
			rev = rev + a[i];
		}
		System.out.println(rev);

		// --------------------------------------
		// 3. String Buffer Class

		StringBuffer sb = new StringBuffer(s);
		StringBuffer s1 = sb.reverse();
		System.out.println(s1);

	}

}
