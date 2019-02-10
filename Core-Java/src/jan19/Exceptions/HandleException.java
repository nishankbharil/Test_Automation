package jan19.Exceptions;
//import jan12.*;
import java.io.File;
import java.io.IOException;

public class HandleException
{

	public static void main(String[] args)
	{
		System.out.println("First");
		try {
		int a = 1/0;
		System.out.println(a);
		
		File file = new File("K/test.txt");
		file.createNewFile();
		
		}catch(ArithmeticException ex)
		{
			System.out.println("Got Some exception while division");
			ex.printStackTrace();
		}catch(IOException fx)
		{
			System.out.println("Got Some exception while creating a file");
			fx.printStackTrace();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}catch(Throwable tw)
		{
			System.out.println("Throwable Exception");
		}

		System.out.println("Last");
	}
}
