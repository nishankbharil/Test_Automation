package Practice2;

public class First_Letter_Bold {

	public static void main(String[] args) {

		
		String s1 = "welcome to pune";
		
		char [] c = s1.toCharArray();
		c[0] = Character.toUpperCase(c[0]);
		for (int i = 0; i<c.length; i++)
		{
			if ((c[i]==' ') && (c[i+1]>='a') && (c[i+1]<='z'))
			{
				c[i+1] = Character.toUpperCase(c[i+1]);
			}
		}
		System.out.println(c);

	}

}
