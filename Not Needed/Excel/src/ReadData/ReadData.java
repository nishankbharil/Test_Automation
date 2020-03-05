package ReadData;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadData {

	public static void main(String[] args) throws IOException 
	{
		String s1=getData();
		
		System.out.println(s1);
	}

	public static String getData() throws IOException {
		FileInputStream fis = new FileInputStream(
				"C:\\My Documents\\Selenium\\Git_Repository_Learn_Selenium\\Excel\\TestData.xlsx");

		XSSFWorkbook wb = new XSSFWorkbook(fis);

		XSSFSheet sheet = wb.getSheet("TestData");

		int row = sheet.getLastRowNum();
		int col = sheet.getRow(0).getLastCellNum();

		System.out.println("Row Number : " + row);
		System.out.println("Column Number : " + col);
		return null;

	}


}
