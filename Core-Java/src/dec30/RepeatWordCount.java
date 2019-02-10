package dec30;

public class RepeatWordCount {

	public static void main(String[] args) {
		
		String s = "Pune is a good city, I live in Pune, is a";
		int counter = 0;
		String split[] = s.split(" ");
		for (int i = 0; i<=s.length()-1; i++)
		{
			if (split[i].equals(split[i+1]))
			{
				counter = counter + 1;
				System.out.println(i);
			}
		}
	}
}
