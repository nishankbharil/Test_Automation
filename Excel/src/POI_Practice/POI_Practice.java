package POI_Practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class POI_Practice {

	public static void main(String[] args) throws IOException {

		POI_Practice.putDataToExcel("MyTestData1", "TC_ABC_05", "Data1", "TC_ABC_03");
		String MyData = POI_Practice.getDataFromExcel("MyTestData1", "TC_ABC_03", "Data1");
		System.out.println(MyData);
	}

	public static String getDataFromExcel(String SheetName, String TCID, String ColumnName) throws IOException {
		int Count = 0;
		int rowNumber = 0;
		int columnNumber = 0;

		FileInputStream fis = new FileInputStream("..\\Excel\\TestData.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		int sheetCount = wb.getNumberOfSheets();
		System.out.println("Number of sheets are : " + sheetCount);

		XSSFSheet sheet = wb.getSheet(SheetName);
		XSSFRow row = sheet.getRow(0);

		int lastCellNumber = row.getLastCellNum();

		System.out.println("Number of columns are: " + lastCellNumber);

		for (int i = 0; i < lastCellNumber; i++) {

			String cell = row.getCell(i).getStringCellValue();

			if (cell.equalsIgnoreCase(ColumnName)) {
				System.out.println("Column number is : " + i);
				columnNumber = i;
				break;
			}
		}

		int lastRowNumber = sheet.getLastRowNum();

//		lastRowNumber = lastRowNumber+1;

		System.out.println("Total Rows are: " + lastRowNumber);

		Iterator<Row> it = sheet.iterator();
		while (it.hasNext()) {
			Row value = it.next();
			String rowvalue = value.getCell(0).getStringCellValue();
			Count = Count + 1;
			if (rowvalue.equals(TCID)) {
				System.out.println("Row number is: " + Count);
				rowNumber = Count;
				break;
			}

		}

		XSSFRow row1 = sheet.getRow(rowNumber - 1);
		Cell cell2 = row1.getCell(columnNumber);
		String requiredData = cell2.getStringCellValue();
		System.out.println(requiredData);
		return requiredData;
	}

	public static void putDataToExcel(String SheetName, String TCID, String ColumnName, String TestData)
			throws IOException {
		int Count = 0;
		int rowNumber = 0;
		int columnNumber = 0;

		FileInputStream fis = new FileInputStream("..\\Excel\\TestData.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		int sheetCount = wb.getNumberOfSheets();
		System.out.println("Number of sheets are : " + sheetCount);

		XSSFSheet sheet = wb.getSheet(SheetName);
		XSSFRow row = sheet.getRow(0);

		int lastCellNumber = row.getLastCellNum();

		System.out.println("Number of columns are: " + lastCellNumber);

		for (int i = 0; i < lastCellNumber; i++) {

			String cell = row.getCell(i).getStringCellValue();

			if (cell.equalsIgnoreCase(ColumnName)) {
				System.out.println("Column number is : " + i);
				columnNumber = i;
				break;
			}
		}

		int lastRowNumber = sheet.getLastRowNum();

//		lastRowNumber = lastRowNumber+1;

		System.out.println("Total Rows are: " + lastRowNumber);

		Iterator<Row> it = sheet.iterator();
		while (it.hasNext()) {
			Row value = it.next();
			String rowvalue = value.getCell(0).getStringCellValue();
			Count = Count + 1;
			if (rowvalue.equals(TCID)) {
				System.out.println("Row number is: " + Count);
				rowNumber = Count;
				break;
			}

		}

		XSSFRow row1 = sheet.getRow(rowNumber - 1);
		XSSFCell ce = row1.getCell(columnNumber);
		ce.setCellValue(TestData);
		FileOutputStream fileOut = new FileOutputStream(
				"..\\TestData.xlsx");
		wb.write(fileOut);
		fileOut.close();
	}
}
