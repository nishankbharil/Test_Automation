package Read_Write_Excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Read_Excel {

	public static void main(String[] args) throws IOException {

		File file = new File("C:\\My Documents\\Selenium\\Git_Repository_Learn_Selenium\\Excel\\TestData.xlsx");

		FileInputStream fis = new FileInputStream(file);

		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet ws = wb.getSheet("Sheet3");

		String readData = ws.getRow(0).getCell(0).getStringCellValue();

		System.out.println(readData);

		wb.close();

	}

}
