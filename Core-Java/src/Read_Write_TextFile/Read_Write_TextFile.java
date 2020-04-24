package Read_Write_TextFile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Stream;

public class Read_Write_TextFile {

	public static void main(String[] args) throws Exception {

		File f = new File("C:\\My Documents\\Selenium\\Git_Repository_Learn_Selenium\\Core-Java\\Text_Read.txt");

		FileReader fr = new FileReader(f);

		BufferedReader br = new BufferedReader(fr);

		while ((br.readLine()) != "") {
			String s1 = br.readLine();
			if (s1==null)
			{
				break;
			}

			System.out.println(s1);

		}

		File f1 = new File("C:\\My Documents\\Selenium\\Git_Repository_Learn_Selenium\\Core-Java\\Text_Write.txt");

		FileWriter fw = new FileWriter(f1);

		BufferedWriter bw = new BufferedWriter(fw);

		bw.write("Hello");

	}

}
