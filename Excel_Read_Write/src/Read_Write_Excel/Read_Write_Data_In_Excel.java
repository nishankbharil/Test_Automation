package Read_Write_Excel;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Read_Write_Data_In_Excel {

	public static void main(String[] args) throws IOException {
		
		String cellContent = getDataFromExcel("TestData", "TC_Id", "ABC");

	}
	
	public static String getDataFromExcel(String sheetName, String columnName, String rowName) throws IOException
	{
		XSSFWorkbook book = new XSSFWorkbook("C:\\My Documents\\Selenium\\Git_Repository_Learn_Selenium\\Excel\\TestData.xlsx");
		XSSFSheet sheet1 = book.getSheet("Sheet2");
		XSSFRow row = sheet1.getRow(0);

		for (int i=0; i<row.getLastCellNum(); i++)
		{
			if(row.getCell(i).getStringCellValue().trim().equals(columnName))
			{
				
			}
		}
		return rowName;
	}

}
