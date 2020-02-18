package Test_Practice;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Test_Practice {

	public static void main(String[] args) throws Exception {

		
		FileInputStream fis = new FileInputStream("..\\TestCases\\1378 Attachment Upload- Test Cases.xlsx");
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		XSSFSheet sheet = wb.getSheet("Attachment Upload");
		
		String row = sheet.getRow(0).getCell(0).getStringCellValue();
		
		System.out.println(row);
		
		
		
	}

}
