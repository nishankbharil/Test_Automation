package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ConnectPage

{

	WebElement name;
	WebElement scheme;
	WebElement host;
	WebElement port;
	WebElement path;

	public void fillConnectForm(String nameToType, String schemeToSelect, String hostToType, String portToType,
			String pathToType) {

		name.sendKeys(nameToType);

		Select listbox = new Select(scheme);
		listbox.selectByVisibleText(schemeToSelect);
		
		host.clear();
		host.sendKeys(hostToType);
		port.clear();
		port.sendKeys(portToType);
		path.clear();
		path.sendKeys(pathToType);

	}
}
