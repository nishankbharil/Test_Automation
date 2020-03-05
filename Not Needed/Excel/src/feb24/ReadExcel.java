package feb24;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
	public static void main(String[] args) {

		try {
			XSSFWorkbook book = new XSSFWorkbook("TestData.xlsx");
			XSSFSheet sheet1 = book.getSheet("Sheet1");
			XSSFRow row3 = sheet1.getRow(2);
			Cell cell3 = row3.getCell(2);
			String cellVal = cell3.getStringCellValue(); //getnumericcellvalue
			System.out.println(cellVal);

			for (Row row : sheet1) {

				for (Cell cell : row) {
					String data = cell.getStringCellValue();
					System.out.println(data);
				}
			}

			book.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<String> getTestData(String testName) {
		ArrayList<String> testData = new ArrayList<String>();
		try {
			XSSFWorkbook book = new XSSFWorkbook("TestData.xlsx");

			XSSFSheet sheet1 = book.getSheet("Sheet1");

			for (Row row : sheet1) {
				Cell cell0 = row.getCell(0);
				String cell0Data = cell0.getStringCellValue();
				if (cell0Data.equals(testName)) {
					for (Cell cell : row) {
						String data = cell.getStringCellValue();
						testData.add(data);
					}
					break;
				}
			}
			book.close();
			testData.remove(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return testData;
	}
	
	
}