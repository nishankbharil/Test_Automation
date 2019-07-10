package dec30;

public class RepeatWordCount {

	public static void main(String[] args) {

		String s = "Pune is a good city, I live in Pune, is a";
		int counter = 0;
		String split1[] = s.split(" ");
//		String split2[] = s.split(" ");
		
		int Counter = 0;
		for (String temp: split1 ) 
		{
			if (split1[0].equals(temp))
			{
				Counter = Counter+1;
			}
			
		}
		

//		for (int i = 0; i <= split1.length - 1; i++) {
//			for (int j = 0; j <= split2.length - 1; j++) {
//				if (split1[i].equals(split2[j])) {
//					counter = counter + 1;
//					if (j == split2.length - 1) {
//						System.out.println(split1[i] + " - " + counter);
//					}
//				}
//			}
//		}
		System.out.println(split1.length);
		// for (String temp: split)
		// {
		// String s1 = temp;
		// if (s1.equalsIgnoreCase(split[0]))
		// {
		// counter = counter + 1;
		// System.out.println(temp);
		// }
		// }
	}
}
