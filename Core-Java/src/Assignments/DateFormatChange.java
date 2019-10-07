package Assignments;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatChange {

	public static void main(String[] args) 
	{

//		Date now = new Date();
//		long time = now.getTime();
	
		String pattern = "[dd-MM-yyyy]_[hh:mm:ss]";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		String date = simpleDateFormat.format(new Date());
		System.out.println(date);
		
	}

}
