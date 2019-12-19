package Assignments;

public class Numeric_String {

	public static void main(String[] args) {

		String cs = "12345";
		boolean flag = false;
		int len = cs.length();
		for (int i = 0; i < len; i++) {
			if (!Character.isDigit(cs.charAt(i))) {
				flag=true;
			}
			else
			{
				flag=false;
			}
		}
		if (flag==false)
		{
			System.out.println("String is NUMERIC");
		}
		
	}

}
