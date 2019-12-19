package feb17;

public class StringSpacesReplace {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String s = "abc       def   ghi jkl";
		String k = s.replaceAll("\\s","");
		System.out.println(k);
		
		String j = "";
		String a[] = s.split(" ");
		for (String temp: a)
		{
			j = j+temp;
		}
		System.out.println(j);

	}
}
