package Practice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Read_Text_File {

	public static void main(String[] args) throws IOException {
		// Approach1
		FileReader fr = new FileReader(
				"C:\\My Documents\\Selenium\\Git_Repository_Learn_Selenium\\Core-Java\\Text.txt");
		BufferedReader br = new BufferedReader(fr);
		String str;
		while ((str = br.readLine()) != null) {
			System.out.println(str);
		}
		br.close();

		// Approach2
		File file = new File("C:\\My Documents\\Selenium\\Git_Repository_Learn_Selenium\\Core-Java\\Text.txt");
		Scanner sc = new Scanner(file);
		while (sc.hasNextLine()) {
			System.out.println(sc.nextLine());
		}

		// Approach3

//		sc.useDelimiter("\\Z");
//		System.out.println(sc.next());

	}

}
