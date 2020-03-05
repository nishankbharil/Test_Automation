import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Only_Practice {

	public static void main(String[] args) throws IOException {

		String columnData = "";
		FileInputStream fis = new FileInputStream("C://My Documents//Selenium//Excel_Read_Write//TestData.xlsx");

		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Data1");

		for (int i = 0; i <= sheet.getRow(0).getLastCellNum(); i++) {
			String columnName = sheet.getRow(0).getCell(i).getStringCellValue();

			if (columnName.equalsIgnoreCase("Data3")) {
				columnData = sheet.getRow(1).getCell(i).getStringCellValue();
				break;
			}
		}

		System.out.println(columnData);
		
		XSSFRow row = sheet.getRow(1);
		
		XSSFCell ce = row.getCell(3);
		
		ce.setCellValue("Hello I am put data");
		
		FileOutputStream fos = new FileOutputStream("C://My Documents//Selenium//Excel_Read_Write//TestData.xlsx");
		
		wb.write(fos);
		
		wb.close();

	}

}
