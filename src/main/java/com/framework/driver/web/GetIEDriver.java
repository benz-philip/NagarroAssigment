package com.framework.driver.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class GetIEDriver {

	String driverPath = "src/main/resources/drivers";
	WebDriver driver = null;

	public WebDriver createDriver() {
		try {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			System.setProperty("webdriver.ie.driver", driverPath + "/IEDriverServer.exe");
			InternetExplorerOptions ieOptions = new InternetExplorerOptions(capabilities);
			driver = new InternetExplorerDriver(ieOptions);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return driver;
	}

}
