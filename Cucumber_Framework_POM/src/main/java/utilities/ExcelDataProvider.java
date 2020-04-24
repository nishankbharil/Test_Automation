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
	public static DataFormatter format;

	public String sheetPath;

	public ExcelDataProvider(String filePath) {

		File file = new File(System.getProperty("user.dir") + filePath);

		try {
			FileInputStream fis = new FileInputStream(file);
			wb = new XSSFWorkbook(fis);
		} catch (Exception e) {
			System.out.println("Unable to read excel file" + e.getMessage());
		}
		this.sheetPath = filePath;

	}

	public String getData(String sheetName, String TC_id, String Iteration, String ColumnName) {

		sheet = wb.getSheet(sheetName);

		int rowCount = sheet.getLastRowNum();
		int columnCount = sheet.getRow(0).getLastCellNum();

		for (int i = 2; i <= columnCount; i++) {
			row = sheet.getRow(0);
			String CName = row.getCell(i).getStringCellValue();
			if (CName.equalsIgnoreCase(ColumnName)) {
				columnCount = i;
				break;
			}
		}

		for (int i = 0; i <= rowCount; i++) {
			row = sheet.getRow(i);

			String TCID = row.getCell(0).getStringCellValue();
			String subIt = row.getCell(1).getStringCellValue();

			if (TCID.equalsIgnoreCase(TC_id) && (subIt.equalsIgnoreCase(Iteration))) {

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
			requiredData = cell2.getStringCellValue();
		}

		return requiredData;

	}

	public void putData(String sheetName, String TC_id, String Iteration, String ColumnName, String TestData)
			throws IOException {

		sheet = wb.getSheet(sheetName);

		int rowCount = sheet.getLastRowNum();
		int columnCount = sheet.getRow(0).getLastCellNum();

		for (int i = 2; i <= columnCount; i++) {
			row = sheet.getRow(0);
			String CName = row.getCell(i).getStringCellValue();
			if (CName.equalsIgnoreCase(ColumnName)) {
				columnCount = i;
				break;
			}
		}

		for (int i = 0; i <= rowCount; i++) {
			row = sheet.getRow(i);

			String TCID = row.getCell(0).getStringCellValue();
			String subIt = row.getCell(1).getStringCellValue();

			if (TCID.equalsIgnoreCase(TC_id) && (subIt.equalsIgnoreCase(Iteration))) {
				rowCount = i;
				break;
			}
		}

		XSSFRow row1 = sheet.getRow(rowCount);
		row1.getCell(columnCount).setCellValue("");
		row1.getCell(columnCount).setCellValue(TestData);
		FileOutputStream fileOut = new FileOutputStream(System.getProperty("user.dir") + sheetPath);
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
