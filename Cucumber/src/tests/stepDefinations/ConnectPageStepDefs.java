package tests.stepDefinations;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import Lib.Common;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class ConnectPageStepDefs {

	WebDriver driver;
	
	@Before
	public void waitForLoading()
	{
		Common.sleep(2000);
	}

	@Given("that user is on welcome page")
	public void launchWelcome() {
//		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");

//		driver = new ChromeDriver();
		driver = new FirefoxDriver();

		driver.get("http://localhost:8999/hawtio");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);// implicit wait

		Common.sleep(2000);
	}

	@When("user clicks on (.*) link")
	public void clickLinks(String link) throws IOException {
		driver.findElement(By.linkText(link)).click();
		Common.sleep(2000);
//		takeScreenshot("Threads_Page");
		takeFullPageScreenshot();
	}

	@Then("create new connection page is displayed")
	public void verifyConnectPage() throws IOException {
		driver.findElement(By.name("name")).sendKeys("Tom");
		
		WebElement linkEle5 = driver.findElement(By.name("scheme"));
		Select list = new Select(linkEle5);
		list.selectByVisibleText("https");
		
		WebElement linkEle2 = driver.findElement(By.name("host"));
		linkEle2.clear();
		linkEle2.sendKeys("Host");
		
		WebElement linkEle3 = driver.findElement(By.name("port"));
		linkEle3.clear();
		linkEle3.sendKeys("8081");
		
		WebElement linkEle4 = driver.findElement(By.name("path"));
		linkEle4.clear();
		linkEle4.sendKeys("Path");
		
		takeScreenshot("Connect_Page");
		
	}
	
	public void takeScreenshot(String fileName) throws IOException
	{
		File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File("C:\\My Documents\\Selenium\\Selenium_Practice\\Cucumber\\Screenshot\\"+fileName+".jpg"));
	}
	
	public void takeFullPageScreenshot() throws IOException
	{
		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(2000)).takeScreenshot(driver);
		ImageIO.write(screenshot.getImage(), "PNG", new File(System.getProperty("user.dir")+"\\Screenshot\\"+"FullPage_ThreadsPage.png"));
		
	}
	
	

}
