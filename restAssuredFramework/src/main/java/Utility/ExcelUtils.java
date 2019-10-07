package Utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	private static XSSFRow Row;

	// This method is to set the File path and to open the Excel file, Pass Excel
	// Path and Sheetname as Arguments to this method

	public ExcelUtils(String Path, String SheetName) throws Exception {
		try {
			System.out.println("hi constructor");
			// Open the Excel file
			FileInputStream ExcelFile = new FileInputStream(Path);

			// Access the required test data sheet
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			XSSFRow row = ExcelWSheet.getRow(0);

		} catch (Exception e) {
			throw (e);
		}
	}

	public static void setExcelFile(String Path, String SheetName) throws Exception {
		try {
			// Open the Excel file
			FileInputStream ExcelFile = new FileInputStream(Path);

			// Access the required test data sheet
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			XSSFRow row = ExcelWSheet.getRow(0);

		} catch (Exception e) {
			throw (e);
		}
	}

	public static Object[][] getTableArray(String FilePath, String SheetName, int iTestCaseRow) throws Exception {
		String[][] tabArray = null;
		try {
			FileInputStream ExcelFile = new FileInputStream(FilePath);

			// Access the required test data sheet
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);

			int startCol = 1;
			int ci = 0, cj = 0;
			int totalRows = 1;
			int totalCols = 2;

			tabArray = new String[totalRows][totalCols];
			for (int j = startCol; j <= totalCols; j++, cj++) {
				tabArray[ci][cj] = getCellData(iTestCaseRow, j);
			}
		}

		catch (FileNotFoundException e) {
			// System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		}

		catch (IOException e) {
			// System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		}

		return (tabArray);
	}

	// This method is to read the test data from the Excel cell, in this we are
	// passing parameters as Row num and Col num
	public static String getCellData(int RowNum, int ColNum) throws Exception {
		try {
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			return "";
		}
	}

	public static String getTestCaseName(String sTestCase) throws Exception {
		String value = sTestCase;

		try {
			int posi = value.indexOf("@");
			value = value.substring(0, posi);
			posi = value.lastIndexOf(".");
			value = value.substring(posi + 1);
			return value;
		} catch (Exception e) {
			throw (e);
		}
	}

	public static int getRowContains(String sTestCaseName, int colNum) throws Exception {
		int i;

		try {
			int rowCount = ExcelUtils.getRowUsed();
			for (i = 0; i < rowCount; i++) {
				if (ExcelUtils.getCellData(i, colNum).equalsIgnoreCase(sTestCaseName)) {
					break;
				}
			}
			return i;

		} catch (Exception e) {
			throw (e);
		}
	}

	public static int getRowUsed() throws Exception {
		try {
			int RowCount = ExcelWSheet.getLastRowNum();
			return RowCount;
		} catch (Exception e) {
			// System.out.println(e.getMessage());
			throw (e);
		}
	}

	public static int getColCount() throws Exception {
		try {
			int ColCount = Row.getLastCellNum();
			return ColCount;
		} catch (Exception e) {
			// System.out.println(e.getMessage());
			throw (e);
		}
	}

	public static String getCellValueUsingColName(String ColName, int RowNo) throws Exception {
		try {
			int col_num = -1;
			Row = ExcelWSheet.getRow(0);

			for (int i = 0; i < Row.getLastCellNum(); i++) {
				if (Row.getCell(i).getStringCellValue().trim().equals(ColName))
					col_num = i;
			}
			

			Row = ExcelWSheet.getRow(RowNo);
			XSSFCell cell = Row.getCell(col_num);
			String value = cell.getStringCellValue();
			// System.out.println("Value of the Excel Cell is - "+ value);
			return value;
		} catch (Exception e)

		{
			// System.out.println(e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}

	public static String getDateCellValueUsingColName(String ColName, int RowNo) throws Exception {
		try {
			int col_num = -1;
			String cellStringValue = "";
			Row = ExcelWSheet.getRow(0);

			for (int i = 0; i < Row.getLastCellNum(); i++) {
				if (Row.getCell(i).getStringCellValue().trim().equals(ColName))
					col_num = i;
			}

			Row = ExcelWSheet.getRow(RowNo);
			XSSFCell cell = Row.getCell(col_num);
//		    Date date = cell.getDateCellValue();
//		    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//		    cellStringValue = df.format(date);
			cellStringValue = cell.getStringCellValue();
			return cellStringValue;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public static void setCellValueUsingColName(String Path, String ColName, String RowNo, String Value)
			throws IOException {
		int col_num = -1;
		Row = ExcelWSheet.getRow(0);
		for (int i = 0; i < Row.getLastCellNum(); i++) {
			if (Row.getCell(i).getStringCellValue().trim().equals(ColName))
				col_num = i;
		}
		Row = ExcelWSheet.getRow(Integer.parseInt(RowNo));
		XSSFCell cell = Row.getCell(col_num);
		cell.setCellValue("");
		cell.setCellValue(Value);
		FileOutputStream fileOut = new FileOutputStream(Path);
		ExcelWBook.write(fileOut);
		fileOut.close();
	}

	public int getIntCellValueUsingColName(String ColName, int RowNo) throws Exception {
		try {
			int col_num = -1;
			Row = ExcelWSheet.getRow(0);

			for (int i = 0; i < Row.getLastCellNum(); i++) {
				if (Row.getCell(i).getStringCellValue().trim().equals(ColName))
					col_num = i;
			}

			Row = ExcelWSheet.getRow(RowNo);
			XSSFCell cell = Row.getCell(col_num);
			int value = (int) cell.getNumericCellValue();
			// System.out.println("Value of the Excel Cell is - "+ value);
			return value;
		} catch (Exception e)

		{
			// System.out.println(e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}
}