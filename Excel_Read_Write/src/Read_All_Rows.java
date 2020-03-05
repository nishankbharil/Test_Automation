import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Read_All_Rows {

	public static void main(String[] args) throws IOException {

		FileInputStream fis = new FileInputStream("C://My Documents//Selenium//Excel_Read_Write//TestData.xlsx");

		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Data2");

		for (int i = 0; i <= sheet.getRow(0).getLastCellNum(); i++) {
			String columnName = sheet.getRow(0).getCell(i).getStringCellValue();

			if (columnName.equalsIgnoreCase("Name")) {
				for (int j = 1; j <= sheet.getLastRowNum(); j++) {
					String columnData = sheet.getRow(j).getCell(i).getStringCellValue();

					System.out.println(columnData);
				}
			}
		}

	}

}
