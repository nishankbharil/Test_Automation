package com.automation.utility;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

public class Helper {

	public static String captureScreenshot(WebDriver driver) {
		File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String screenshotPath = System.getProperty("user.dir") + "/Results/Screenshots/OHRM_" + getCurrentDateTime()
				+ ".png";
		try {

			FileHandler.copy(file, new File(screenshotPath));
		} catch (IOException e) {
			System.out.println("Unable to capture screenshot" + e.getMessage());
		}

		System.out.println("Screenshot Captured");
		return screenshotPath;

	}

	public static String getCurrentDateTime() {

		DateFormat customFormat = new SimpleDateFormat("dd_MMM_yyyy_HH_mm_ss");
		Date currentDate = new Date();
		return customFormat.format(currentDate);
	}

	public void handleAlert() {

	}

	public void handleFrames() {

	}

	public void MultipleWindows() {

	}

	// Apache common-lang API
	// http://commons.apache.org/

	public static String randomNumbers(int digits) {
		String str = RandomStringUtils.randomNumeric(digits);
		return str;
	}

	public static String randomCharacters(int characters) {
		String str = RandomStringUtils.randomAlphabetic(characters).toUpperCase();
		return str;
	}

	public static String randomAlphanumeric(int digichar) {
		String str = RandomStringUtils.randomAlphanumeric(digichar).toUpperCase();
		return str;
	}
}
