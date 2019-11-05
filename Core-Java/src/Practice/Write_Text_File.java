package Practice;

import java.io.BufferedWriter;

import java.io.FileWriter;
import java.io.IOException;

public class Write_Text_File {

	public static void main(String[] args) throws IOException {

		FileWriter file = new FileWriter(
				"C:\\My Documents\\Selenium\\Git_Repository_Learn_Selenium\\Core-Java\\Text.txt");

		BufferedWriter bw = new BufferedWriter(file);

		bw.write("This is written by Nishank");
		bw.close();
	}
}
