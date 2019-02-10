package jan19.Exceptions;

import java.io.File;
import java.io.IOException;

public class TestThrows
{

	public static void main(String[] args) 
	{
		TestThrows Th = new TestThrows();
		try {
			Th.test();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void test() throws IOException
	{
		File f = new File("c:/test.txt");
		f.createNewFile();
	}

}
