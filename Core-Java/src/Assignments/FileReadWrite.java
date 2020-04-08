package Assignments;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileReadWrite 
{

	public static void main(String[] args) throws IOException
	{
		FileReadWrite.writeToFile();
		FileReadWrite.readFile();
	}

	public static void readFile()
	{
		try {
			File file = new File(System.getProperty("user.dir")+"/Text.txt");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			System.out.println(fr.read());
			System.out.println("---------------");
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

	public static void writeToFile() throws IOException
	{
		PrintWriter pw;
		try {
			
			FileWriter fr = new FileWriter(System.getProperty("user.dir")+"/Text.txt");
			fr.write("Hello I am updtated");
			fr.close();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
