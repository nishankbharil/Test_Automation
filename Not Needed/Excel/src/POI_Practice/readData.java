package POI_Practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class readData {

	public static void main(String[] args) throws IOException {
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\TestData.xlsx");

		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("TD01");
		Iterator<Row> row1 = sheet.iterator();
		int columnNumber = 0;
		while (row1.hasNext()) {
			Row firstRow = row1.next();
			for (Cell cell : firstRow) {
				if (cell.getStringCellValue().equalsIgnoreCase("Data3")) {
					System.out.println("Column Number is : " + columnNumber);
					Row SecondRow = row1.next();
					Cell c = SecondRow.getCell(columnNumber);
					String RequiredData = c.getStringCellValue();
					System.out.println(RequiredData);
					break;
				}
				columnNumber = columnNumber + 1;
			}

		}
		FileInputStream fis2 = new FileInputStream(System.getProperty("user.dir") + "\\TestData.xlsx");
		XSSFWorkbook wb2 = new XSSFWorkbook(fis2);
		XSSFSheet sheet2 = wb2.getSheet("TD01");
		XSSFRow row2 = sheet2.getRow(0);

		Iterator<Row> row3 = sheet2.iterator();
		
		for (int i = 0; i < row2.getLastCellNum(); i++) {
			String data = row2.getCell(i).getStringCellValue();
			if (data.equalsIgnoreCase("Data3"))
			{
				System.out.println(data);
				row3.next();
			}
		}
	}
}
