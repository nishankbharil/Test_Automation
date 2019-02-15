package feb9;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import Lib.Common;

public class Hawtio
{
	public static void main(String[] args) 
	{
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get("http://localhost:8999/hawtio");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		Common.sleep(2000);
		WebElement linkEle = driver.findElement(By.linkText("Threads"));
		linkEle.click();
		
		Common.sleep(2000);
		int cols = Hawtio.getColCount(driver);
		System.out.println(cols);
		int rows = Hawtio.getRowCount(driver);
		System.out.println(rows);
		
		String text = Hawtio.getTestIncell(4,3,driver);
		System.out.println(text);
		
		String text1 = Hawtio.getColumnName(4,driver);
		System.out.println(text1);
	}
	public static int getColCount(WebDriver driver)
	{
		String xpath = "//table/thead/tr/th";
		List<WebElement> allThEles= driver.findElements(By.xpath(xpath));//returns list of web elements
		int count = allThEles.size();
		return count;
	}
	public static int getRowCount(WebDriver driver)
	{
		String xpath = "//table/tbody/tr";
		List<WebElement> allTrEles= driver.findElements(By.xpath(xpath));//returns list of web elements
		int count = allTrEles.size();
		return count;
	}
	public static String getTestIncell(int row, int col, WebDriver driver)
	{
		WebElement Ele5 = driver.findElement(By.xpath("((//tbody/tr)["+row+"]/td)["+col+"]"));
		String s = Ele5.getText();
		return s;
	}
	public static String getColumnName(int col, WebDriver driver)
	{
		WebElement Ele6 = driver.findElement(By.xpath("(//thead/tr/th)["+col+"]"));
		String s = Ele6.getText();
		return s;
	}
	
//	public static void GetRowWithCellText(WebDriver driver)
//	{
//		WebElement Ele6 = driver.findElement(By.xpath("(//thead/tr/th)["+col+"]"));
//	}
	
	
}
