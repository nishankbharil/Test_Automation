package Read_Write_Excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Read_Excel_All_Rows {

	public static void main(String[] args) throws IOException {
		
		File file = new File("C:\\My Documents\\Selenium\\Git_Repository_Learn_Selenium\\Excel\\TestData.xlsx");

		FileInputStream fis = new FileInputStream(file);

		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet ws = wb.getSheet("Sheet3");
		XSSFRow row= ws.getRow(0);
		
		int rowCount = ws.getLastRowNum();
		for (int i = 1; i<=rowCount; i++)
		{
			System.out.println("User Name is: "+ws.getRow(i).getCell(0).getStringCellValue());
			System.out.println("Password is: "+ws.getRow(i).getCell(1).getStringCellValue());
		}
		
	}

}
