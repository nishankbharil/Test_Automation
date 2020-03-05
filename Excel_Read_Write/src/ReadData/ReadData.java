package ReadData;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadData {

	public static void main(String[] args) throws IOException {
		getData("TestData.xlsx", "TestData", "URL");
		putData("TestData.xlsx", "TestData", "Password", "https://abc/");
		getData("TestData.xlsx", "TestData", "URL");
	}

	public static void getData(String workbookname, String sheetName, String columnName) throws IOException {
		FileInputStream fis = new FileInputStream(
				"C:\\My Documents\\Selenium\\Git_Repository_Learn_Selenium\\Excel\\" + workbookname);
		XSSFWorkbook wb = new XSSFWorkbook(fis);

		XSSFSheet sheet = wb.getSheet(sheetName);

		int row = sheet.getLastRowNum();
		int col = sheet.getRow(0).getLastCellNum();

//		System.out.println("Row Number : " + row);
//		System.out.println("Column Number : " + col);
		
		for (int i = 0; i <= col; i++) {
			String ColumnName = sheet.getRow(0).getCell(i).getStringCellValue();

			if (ColumnName.equalsIgnoreCase(columnName)) {
				String data1 = sheet.getRow(1).getCell(i).getStringCellValue();
				System.out.println(data1);
				break;
			}
		}
		
	}
	
	public static void putData(String workbookname, String sheetName, String columnName, String testData ) throws IOException {
		FileInputStream fis = new FileInputStream(
				"C:\\My Documents\\Selenium\\Git_Repository_Learn_Selenium\\Excel\\" + workbookname);
		XSSFWorkbook wb = new XSSFWorkbook(fis);

		XSSFSheet sheet = wb.getSheet(sheetName);

		int row = sheet.getLastRowNum();
		int col = sheet.getRow(0).getLastCellNum();

//		System.out.println("Row Number : " + row);
//		System.out.println("Column Number : " + col);
		
		for (int i = 0; i <= col; i++) {
			String ColumnName = sheet.getRow(0).getCell(i).getStringCellValue();

			if (ColumnName.equalsIgnoreCase(columnName)) {
				XSSFCell ce = sheet.getRow(1).getCell(i);
				ce.setCellValue(testData);
				FileOutputStream fos = new FileOutputStream("C:\\My Documents\\Selenium\\Git_Repository_Learn_Selenium\\Excel\\" + workbookname);
				wb.write(fos);
				fos.close();
				break;
			}
		}
		
	}

}
