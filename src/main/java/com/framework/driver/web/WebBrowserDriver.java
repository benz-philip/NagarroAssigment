package com.framework.driver.web;

import org.openqa.selenium.WebDriver;

import com.cucumber.listener.Reporter;

public class WebBrowserDriver {

	public WebDriver getDriver(String browser) {
		WebDriver driver = null;

		switch (browser.toUpperCase()) {

		case "IE":
			driver = new GetIEDriver().createDriver();
			break;

		case "FIREFOX":
			driver = new GetFirefoxDriver().createDriver();
			break;

		case "CHROME":
			driver = new GetChromeDriver().createDriver();
			break;

		default:
			break;

		}
		try {
			driver.manage().window().maximize();
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.addStepLog("Driver was not created");
		}
		return driver;
	}

}
