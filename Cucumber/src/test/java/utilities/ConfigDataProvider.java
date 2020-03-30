package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigDataProvider {

	Properties pro;

	public ConfigDataProvider() {
		File src = new File(System.getProperty("user.dir") + "/Config/Config.properties");

		FileInputStream fis;
		try {
			fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);
		} catch (Exception e) {
			System.out.println("Not able to load config file" + e.getMessage());
		}

	}
	
	public String getDataFromConfig(String ketToSearch)
	{
		return pro.getProperty(ketToSearch);
	}
	
	public String getBrowser()
	{
		return pro.getProperty("Browser");
	}
	
	public String getTestURL()
	{
		return pro.getProperty("qaURL");
	}

}
