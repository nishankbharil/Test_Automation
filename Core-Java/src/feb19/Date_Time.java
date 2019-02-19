package feb19;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Date_Time {
	public static void main(String[] args) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-mm-yyyy HH:mm:Ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
	}
}
