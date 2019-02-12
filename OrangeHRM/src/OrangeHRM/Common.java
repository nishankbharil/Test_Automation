package OrangeHRM;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Common
{

	public static void main(String[] args) 
	{
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		driver.findElement(By.id("txtUsername")).clear();
		driver.findElement(By.id("txtUsername")).sendKeys("admin");
		driver.findElement(By.id("txtPassword")).clear();
		driver.findElement(By.id("txtPassword")).sendKeys("admin123");
		driver.findElement(By.id("btnLogin")).click();
		
		driver.findElement(By.linkText("Leave")).click();
		
		driver.findElement(By.id("leaveList_chkSearchFilter_checkboxgroup_allcheck")).click();
		driver.findElement(By.id("btnSearch")).click();

		
		int RCount = Common.getRowCount(driver);
		System.out.println(RCount);
		
		int CCount = Common.getColumnCount(driver);
		System.out.println(CCount);
		
		
		for (int i=1; i<RCount; i++)
		{
			String s2 = driver.findElement(By.xpath("((//table/tbody/tr)["+i+"]/td/a)[2]")).getText();
			System.out.println(s2);
		}
		

	}
	
	public static int getRowCount(WebDriver driver)
	{
		List<WebElement> allRows= driver.findElements(By.xpath("//table/tbody/tr"));//returns list of web elements
		int count = allRows.size();
		return count;
	}
	
	public static int getColumnCount(WebDriver driver)
	{
		List<WebElement> allColumns= driver.findElements(By.xpath("//table/thead/tr/th"));//returns list of web elements
		int count1 = allColumns.size();
		return count1;
	}
	

}
