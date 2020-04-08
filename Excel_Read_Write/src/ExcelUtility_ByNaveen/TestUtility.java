package ExcelUtility_ByNaveen;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestUtility {

	public static void main(String[] args) throws IOException {

		
		ExcelUtility eu = new ExcelUtility();
		
		eu.Xls_Reader("C:\\My Documents\\Selenium\\Excel_Read_Write\\TestData.xlsx");
		
		String s1 = eu.getCellData("TestData", 1, 1);
		
		System.out.println(s1);
		
		XSSFWorkbook wb = new XSSFWorkbook("");
		XSSFSheet sheet = wb.getSheet("Sheet1");
		String row  = sheet.getRow(0).getCell(0).getStringCellValue();
		
		System.out.println(row);
		
		wb.close();
		

	}

}
