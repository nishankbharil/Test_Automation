package Practice;
public class CountLetterSpacesNumber
{
	public static void main(String[] args) {
		String test = "Aa kiu, I swd skieo 236587. GH kiu: sieo?? 25.33";
		count(test);
		
	}
	public static void count(String x){
		char[] ch = x.toCharArray();
		int letter = 0;
		int space = 0;
		int num = 0;
		int other = 0;
		for(int i = 0; i < x.length(); i++){
			if(Character.isLetter(ch[i])){
				letter ++ ;
			}
			else if(Character.isDigit(ch[i])){
				num ++ ;
			}
			else if(Character.isSpaceChar(ch[i])){
				space ++ ;
			}
			else{
				other ++;
			}
		}
		
		int p = x.length();
		System.out.println(p);
		
		double l = (letter/p)*100;
		Math.round(l);
		double n = (num/p)*100;
		Math.round(n);
		double s = (space/p)*100;
		Math.round(s);
		double o = (other/p)*100;
		Math.round(o);
		System.out.println("The string is : Aa kiu, I swd skieo 236587. GH kiu: sieo?? 25.33");
		System.out.println("letter: " + letter);
		System.out.println("space: " + space);
		System.out.println("number: " + num);
		System.out.println("other: " + other);
		System.out.println("------------------");
		System.out.println("letter: " + l);
		System.out.println("space: " + n);
		System.out.println("number: " + s);
		System.out.println("other: " + o);
	}
}