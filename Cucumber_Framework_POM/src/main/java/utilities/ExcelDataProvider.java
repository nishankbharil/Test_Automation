package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataProvider {

	public static XSSFWorkbook wb;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static ExcelDataProvider edp;
	public static DataFormatter format;

	public ExcelDataProvider() {

//		File file = new File(System.getProperty("user.dir") + "/TestData/TestData.xlsx");

		File file = new File(System.getProperty("user.dir") + "/TestData/SanityTest.xlsx");

		try {

			FileInputStream fis = new FileInputStream(file);
			wb = new XSSFWorkbook(fis);

		} catch (Exception e) {

			System.out.println("Unable to read excel file" + e.getMessage());

		}

	}

	public static String getData(String sheetName, String TC_id, String subIteration, String ColumnName) {

		sheet = wb.getSheet(sheetName);

		int rowCount = sheet.getLastRowNum();
		int columnCount = sheet.getRow(0).getLastCellNum();

		for (int i = 3; i <= columnCount; i++) {
			row = sheet.getRow(0);
			String CName = row.getCell(i).getStringCellValue();
			if (CName.equalsIgnoreCase(ColumnName)) {
				columnCount = i;
				break;
			}
		}

		for (int i = 0; i <= rowCount; i++) {
			row = sheet.getRow(i);

			Object TCID = format.formatCellValue(row.getCell(0));
			Object subIt = format.formatCellValue(row.getCell(2));

//			String TCID = row.getCell(0).getStringCellValue();
//			String subIt = row.getCell(2).getStringCellValue();
//
//			if (TCID.equalsIgnoreCase(TC_id) && (subIt.equalsIgnoreCase(subIteration))) {

			if (((String) TCID).equalsIgnoreCase(TC_id) && (((String) subIt).equalsIgnoreCase(subIteration))) {

				rowCount = i;
				break;
			}
		}
		String requiredData;
		XSSFRow row1 = sheet.getRow(rowCount);
		Cell cell2 = row1.getCell(columnCount);
		if (cell2 == null) {
			requiredData = "";
		} else {
//			requiredData = cell2.getStringCellValue();
			requiredData = format.formatCellValue(row1.getCell(columnCount));
		}

		return requiredData;

	}

	public static void putData(String sheetName, String TC_id, String subIteration, String ColumnName, String TestData)
			throws IOException {

		sheet = wb.getSheet(sheetName);

		int rowCount = sheet.getLastRowNum();
		int columnCount = sheet.getRow(0).getLastCellNum();

		for (int i = 3; i <= columnCount; i++) {
			row = sheet.getRow(0);
			String CName = row.getCell(i).getStringCellValue();
			if (CName.equalsIgnoreCase(ColumnName)) {
				columnCount = i;
				break;
			}
		}

		for (int i = 0; i <= rowCount; i++) {
			row = sheet.getRow(i);

//			Object TCID = format.formatCellValue(row.getCell(0));
//			Object subIt = format.formatCellValue(row.getCell(2));
			String TCID = row.getCell(0).getStringCellValue();
			String subIt = row.getCell(2).getStringCellValue();

			if (TCID.equalsIgnoreCase(TC_id) && (subIt.equalsIgnoreCase(subIteration))) {

//			if (((String) TCID).equalsIgnoreCase(TC_id) && (((String) subIt).equalsIgnoreCase(subIteration))) {

				rowCount = i;
				break;
			}
		}

//		row = sheet.getRow(rowCount);
//		cell = row.getCell(columnCount+1);
//		cell.setCellValue(TestData);
//		FileOutputStream fileOut = new FileOutputStream(
//				"..\\"+sheetName+".xlsx");
//		wb.write(fileOut);
//		fileOut.close();

		XSSFRow row1 = sheet.getRow(rowCount);
		row1.getCell(columnCount).setCellValue("");
		row1.getCell(columnCount).setCellValue(TestData);
//		ce.setCellValue(TestData);
		FileOutputStream fileOut = new FileOutputStream("..\\" + sheetName + ".xlsx");
		wb.write(fileOut);
		fileOut.close();
	}

	public String getStringData(int sheetIndex, int row, int column) { // Method overloading
		return wb.getSheetAt(sheetIndex).getRow(row).getCell(column).getStringCellValue();
	}

	public String getStringData(String sheetName, int row, int column) { // Method overloading
		return wb.getSheet(sheetName).getRow(row).getCell(column).getStringCellValue();
	}

	public double getNumericData(String sheetName, int row, int column) {
		return wb.getSheet(sheetName).getRow(row).getCell(column).getNumericCellValue();
	}

}
