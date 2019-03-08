package Lib;

public class Common 
{
	public static void sleep(int millSec) 
	{
		try {
			Thread.sleep(millSec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
