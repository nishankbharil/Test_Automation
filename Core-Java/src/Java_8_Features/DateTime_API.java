package Java_8_Features;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Date;

public class DateTime_API {

	public static void main(String[] args) {

		//it is thread safe
		//new api is immutable
		//Old gives date and time but new one gives only date
		
		LocalDate ld = LocalDate.now();
		System.out.println(ld);
		
		LocalDate ld1 = LocalDate.of(2020, Month.AUGUST, 03);
		System.out.println(ld1);
		
		
		LocalTime lt = LocalTime.now();
		
		System.out.println(lt);
		
		Date d = new Date();
		
		
		System.out.println(d);
		System.out.println(d.getTime());

	}

}
