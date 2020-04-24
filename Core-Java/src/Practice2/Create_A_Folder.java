package Practice2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Create_A_Folder {

	public static void main(String[] args) throws IOException {

		File f = new File("C:\\My Documents\\Selenium\\Git_Repository_Learn_Selenium\\Core-Java\\MyFolder1");

		File f1 = new File("C:\\My Documents\\Selenium\\Git_Repository_Learn_Selenium\\Core-Java\\MyFolder1\\text.txt");

		if (!f.exists()) {
			f.mkdir();
			System.out.println("Folder Created");
			f1.createNewFile();
			FileWriter fw = new FileWriter(f1);

			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("Hello");
			bw.close();
		} else {
			System.out.println("Folder is already Created");
		}

	}

}
