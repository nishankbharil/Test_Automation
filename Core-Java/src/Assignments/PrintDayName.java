package Assignments;

public class PrintDayName {

	public static void main(String[] args) {

		String i = "Tuesday";
		switch (i) {
		case "Monday":
			System.out.println("Mon -2");
			break;
		case "Tuesday":
			System.out.println("Tue -3");
			break;
		case "Wednesday":
			System.out.println("Wed -4");
			break;
		case "Thrusday":
			System.out.println("Thr -5");
			break;
		case "Friday":
			System.out.println("Fri -6");
			break;
		case "Saturday":
			System.out.println("Sat -7");
			break;
		case "Sunday":
			System.out.println("Sun -1");
			break;
		default:
			System.out.println("Invalid");
			break;

		}

	}

}
