/**
 * 
 */
package jan12;

/**
 * @author Nishank Bharil Input : Hamburger O/p: bur
 *
 */
public class MidCharOfString {

	public static void main(String[] args) {
		String s = "Nishank";
		int l = s.length();
		int m = l / 2;
		String g = s.substring(m - 1, m + 2);
		System.out.println(g);
	}

}
