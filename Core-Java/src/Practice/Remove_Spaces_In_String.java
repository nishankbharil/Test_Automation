package Practice;

public class Remove_Spaces_In_String {

	public static void main(String[] args) {
		String s = "Java is a programming language";
		String s1 = s.replaceAll("\\s", "");
		System.out.println(s1);

	}

}
