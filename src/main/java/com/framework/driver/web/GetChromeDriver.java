package com.framework.driver.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class GetChromeDriver {

	String driverPath = "src/main/resources/drivers";
	WebDriver driver = null;

	public WebDriver createDriver() {
		String executableName = "chromedriver";
		try {
			if (System.getProperty("os.name").toLowerCase().contains("windows")) {
				executableName = executableName + ".exe";
			}
			System.setProperty("webdriver.chrome.driver", driverPath + "/" + executableName);
			driver = new ChromeDriver();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return driver;
	}
}
