package Practice2;

public class Vovle_Next_Letter {

	public static void main(String[] args) {

		String s1 = "faeiougdygdywue7tgdyuagdgasfdu";
		String s2 = s1.toLowerCase();

		char[] c = s2.toCharArray();

		for (int i = 0; i < c.length; i++) {

			switch (c[i]) {
			case 'a':
				c[i] = 'b';
				break;
			case 'e':
				c[i] = 'f';
				break;
			case 'i':
				c[i] = 'j';
				break;
			case 'o':
				c[i] = 'p';
				break;
			case 'u':
				c[i] = 'v';
				break;
			
			}
		}
		System.out.println(c);

	}

}
