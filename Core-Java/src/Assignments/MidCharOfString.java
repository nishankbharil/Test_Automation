/**
 * 
 */
package Assignments;

/**
 * @author Nishank Bharil Input : Hamburger O/p: bur
 *
 */
public class MidCharOfString {

	public static void main(String[] args) {
		String s = "Ankitauqiuwgcygedewyu7fde";
		int l = s.length();
		int m = l / 2;
		String g = s.substring(m - 1, m + 2);
		String g1 = s.substring(m, m + 1);
		System.out.println(g);
		System.out.println(g1);
	}

}
