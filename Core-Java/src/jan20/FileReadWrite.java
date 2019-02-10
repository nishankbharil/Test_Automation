package jan20;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class FileReadWrite 
{

	public static void main(String[] args)
	{
		FileReadWrite.writeToFile();
		FileReadWrite.readFile();
	}

	public static void readFile()
	{
		try {
			File file = new File("C:\\My Documents\\Selenium\\Selenium_Practice\\Core-Java\\Text.txt");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			while (true)
			{
				String line = br.readLine();
				if (line==null)
				{
					break;
				}
				System.out.println(line);
				br.close();
			}
		}catch(FileNotFoundException fnf)
		{
			fnf.printStackTrace();
		}catch (IOException ex)
		{
			ex.printStackTrace();
		}

	}

	public static void writeToFile()
	{
		PrintWriter pw;
		try {
			pw = new PrintWriter("C:\\My Documents\\Selenium\\Selenium_Practice\\Core-Java\\Text.txt");
			pw.println("Hello");
			pw.println("This file is created by java");
			pw.println("by");
			pw.close();
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
}
