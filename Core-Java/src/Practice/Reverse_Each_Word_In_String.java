package Practice;

public class Reverse_Each_Word_In_String {

	public static void main(String[] args) {

		// Approach1
		String s = "Welcome to Pune";
		String s1[] = s.split(" ");

		String revString = "";
		for (String temp : s1) {
			String revWord = "";
			for (int i = temp.length() - 1; i >= 0; i--) {
				revWord = revWord + temp.charAt(i);

			}
			revString = revString + revWord + " ";
		}

		System.out.println(revString);

		// Approach2
		for (String temp : s1) {

			StringBuffer w = new StringBuffer(temp);
			StringBuffer s2 = w.reverse();
			String s3 = s2.toString();

			revString = revString + s3 + " ";
			

		}

	}
}
