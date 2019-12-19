package Assignments;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class Date_Time {
	

	public static void main(String[] args) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-mm-yyyy HH:mm:Ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		
		SimpleDateFormat s = new SimpleDateFormat("YY-MM-DD HH:MM:SS");
		Date d = new Date();
		System.out.println(s.format(d));
		
		
				
		
	}
	
}
