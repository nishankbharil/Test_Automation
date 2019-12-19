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
		System.out.println(g);
	}

}
