package Assignments;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CreateFolder {

	public static void main(String[] args) throws IOException {
		String path = "C:/Bell Canada/Hey I created folder2";
		File theDir = new File(path);

		// if the directory does not exist, create it
		if (!theDir.exists()) {
			System.out.println("creating directory: " + theDir.getName());
			boolean result = false;

			try {
				theDir.mkdir();
				result = true;

			} catch (SecurityException se) {
				// handle it
			}
			if (result) {
				System.out.println("DIR created");
			}
		}
		File file = new File(path + "/testFile1.pdf");

		// Create the file
		if (file.createNewFile()) {
			System.out.println("File is created!");
		} else {
			System.out.println("File already exists.");
		}

		// Write Content
		FileWriter writer = new FileWriter(file);
		writer.write("Ankita Jain");
		writer.close();
	}

}
