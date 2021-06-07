package com.framework.driver.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GetFirefoxDriver {

	String driverPath = "src/main/resources/drivers";
	WebDriver driver = null;

	public WebDriver createDriver() {
		String executableName = "gecko";
		try {
			if (System.getProperty("os.name").toLowerCase().contains("windows")) {
				executableName = executableName + ".exe";
			}
			System.setProperty("webdriver.gecko.driver", driverPath + "/" + executableName);
			driver = new FirefoxDriver();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return driver;
	}
}
