package ExcelUtility_ByNaveen;

public class TestUtility {

	public static void main(String[] args) {

		
		ExcelUtility eu = new ExcelUtility();
		
		eu.Xls_Reader("C:\\My Documents\\Selenium\\Excel_Read_Write\\TestData.xlsx");
		
		String s1 = eu.getCellData("TestData", 1, 1);
		
		System.out.println(s1);

	}

}
