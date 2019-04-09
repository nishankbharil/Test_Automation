package jan20;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class FileReadWriteAssignment 
{

	public static void main(String[] args) throws IOException 
	{
		
		
		FileReadWriteAssignment.writeFile();
		FileReadWriteAssignment.readFile();

	}
	
	public static void readFile() throws IOException
	{
		File f = new File("C:\\My Documents\\Selenium\\Git_Repository_Learn_Selenium\\Core-Java\\doc\\AssignmentText.txt");
		FileReader fr;
		try 
		{
			fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			
			String s = br.readLine();
			System.out.println(s);
			
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public static void writeFile()
	{
		PrintWriter pw;
		
		try {
			pw = new PrintWriter("C:\\My Documents\\Selenium\\Git_Repository_Learn_Selenium\\Core-Java\\doc\\AssignmentText.txt");
			pw.write("Hello World");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
