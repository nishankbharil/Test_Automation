package dec30;

public class Numeric_String {

	public static boolean isNumeric(CharSequence cs)
	{
		int len = cs.length();
		for (int i =0; i<len; i++)
		{
			if (!Character.isDigit(cs.charAt(i)))
			{
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		
		System.out.println(isNumeric("123"));

	}

}
