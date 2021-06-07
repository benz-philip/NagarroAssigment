package com.framework.actions;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cucumber.listener.Reporter;
import com.google.common.base.Function;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class WebActions implements UserActions {

	WebDriver driver;

	public WebActions(WebDriver driver) {
		this.driver = driver;
	}

	public WebDriver getDriver() {

		return driver;
	}

	public void launchUrl(String url) {
		driver.get(url);

	}

	public boolean click(WebElement element) {
		boolean bFlag = false;
		try {
			WebElement ele = verifyElementClickable(element);
			if (ele != null) {
				ele.click();
				bFlag = true;

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return bFlag;
	}

	public void dragAndDrop(WebElement elementFrom, WebElement elementTo) {
		try {
			Actions action = new Actions(driver);
			action.moveToElement(elementFrom).clickAndHold().moveToElement(elementTo).release().build().perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public WebElement getWebElement(String propertyType, String property) {
		WebElement element = null;
		try {
			switch (propertyType.toLowerCase()) {
			case "xpath":
				element = driver.findElement(By.xpath(property));
				break;

			case "linktext":
				element = driver.findElement(By.linkText(property));
				break;

			default:
				break;

			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return element;
	}

	public boolean waitUntilElementIsVisible(WebElement element, long timeOutInSeconds) {
		boolean bFlag = false;
		try {
			Thread.sleep(300);
			if (existenceofWebelement(element, timeOutInSeconds)) {
				{
					bFlag = true;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return bFlag;

	}

	public String getText(WebElement element) {
		String text = null;
		try {
			if (visibilityofWebelement(element))
				text = element.getText();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return text;
	}

	public String getValueOfAttribute(WebElement element, String attribute) {
		String text = null;
		try {

			if (visibilityofWebelement(element)) {
				text = element.getAttribute(attribute);

			}
			return text;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public boolean selectDropDown(WebElement element, String visibleText) {
		boolean bFlag = false;
		try {

			if (visibilityofWebelement(element)) {
				Select dropDown = new Select(element);
				dropDown.selectByVisibleText(visibleText);
				bFlag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bFlag;
	}

	public String getPageTitle() {
		try {

			String pageTitle = driver.getTitle();
			return pageTitle;
		} catch (TimeoutException t) {
			Reporter.addStepLog("Timeout exceptio ocurred in method " + this.getClass().getName());
		}
		return null;
	}

	public void selectMultipleElement(List<WebElement> elements) {

		try {
			Actions action = new Actions(driver);
			action.keyDown(Keys.CONTROL);
			for (WebElement element : elements) {
				action = action.moveToElement(element).click();
			}
			action.keyUp(Keys.CONTROL).build().perform();

		} catch (Exception e) {
			e.printStackTrace();
			Reporter.addStepLog("Excpetion ocurred while selecting multiple element");
		}
	}

	private boolean visibilityofWebelement(WebElement element) {

		try {
			FluentWait<WebElement> _waitForElement = new FluentWait<WebElement>(element);
			_waitForElement.pollingEvery(pollingDuration);
			_waitForElement.withTimeout(timeOutDuration);
			_waitForElement.ignoring(NoSuchElementException.class);
			_waitForElement.ignoring(StaleElementReferenceException.class);
			_waitForElement.ignoring(ElementNotVisibleException.class);

			Function<WebElement, Boolean> elementVisibility = new Function<WebElement, Boolean>() {

				public Boolean apply(WebElement element) {
					// TODO Auto-generated method stub

					return element.isDisplayed();
				}

			};

			return _waitForElement.until(elementVisibility);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	private boolean existenceofWebelement(WebElement element, long timeOutInSeconds) {

		try {

			FluentWait<WebElement> _waitForElement = new FluentWait<WebElement>(element);
			_waitForElement.pollingEvery(pollingDuration);
			Duration timeout = Duration.of(timeOutInSeconds, ChronoUnit.SECONDS);
			_waitForElement.withTimeout(timeout);
			_waitForElement.ignoring(NoSuchElementException.class);
			_waitForElement.ignoring(StaleElementReferenceException.class);
			_waitForElement.ignoring(ElementNotVisibleException.class);

			Function<WebElement, Boolean> elementVisibility = new Function<WebElement, Boolean>() {

				public Boolean apply(WebElement element) {
					// TODO Auto-generated method stub

					return element.isEnabled();
				}

			};

			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOf(element));

			return _waitForElement.until(elementVisibility);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	private WebElement verifyElementClickable(WebElement element) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, 40);
			return wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {

			throw e;
		}

	}

	public void switchToIframe() {
		try {
			(new WebDriverWait(getDriver(), 15L))
					.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.tagName("iframe")));
		} catch (Exception e) {
			Reporter.addStepLog("Exception occured while switching to iframe");
		}
	}

	public void deselectIframe() {
		try {
			getDriver().switchTo().defaultContent();
		}

		catch (Exception e) {
			e.printStackTrace();
			Reporter.addStepLog("exception while deselecting iFrame");
		}
	}

	public void quitDriver() {

		driver.quit();
	}

	public void enterText(WebElement element, String textToEnter) {
		try {

			if (visibilityofWebelement(element)) {
				element.clear();
				element.sendKeys(textToEnter);
			}

		} catch (Exception e) {
		}
	}

	public void mouseHover(WebElement element) {
		try {

			if (visibilityofWebelement(element)) {

				Actions action = new Actions(driver);
				action.moveToElement(element).build().perform();
			}

		} catch (TimeoutException t) {

		} catch (Exception e) {

		}

	}

	public boolean clearTextBox(WebElement element) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean doubleClick(WebElement element) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean rightClick(WebElement element) {
		// TODO Auto-generated method stub
		return false;
	}

	public void pressEnter(WebElement element) {
		// TODO Auto-generated method stub
	}

	public boolean selectDropDownValueByIndex(WebElement element, int index) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean clickUsingActionBuilder(WebElement element) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isElementExists(WebElement element, boolean toReport) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<String> getTextFromWebElementList(List<WebElement> element) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean waitUntilElementIsInVisible(WebElement element, long timeOutInSeconds) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean waitUntilElementToBeClickable(WebElement element) {
		// TODO Auto-generated method stub
		return false;
	}

	public void navigateToPreviousPage() {
		// TODO Auto-generated method stub

	}

	public void resetImplicitWaitToDefault() {
		// TODO Auto-generated method stub

	}

	public void resetImplicitWait(String stepDescription, int newWaitTime_InSeconds) {
		// TODO Auto-generated method stub

	}

	public void executeJavaScript(String jScript) {
		// TODO Auto-generated method stub

	}

	public void waitFor(int enterMiliSeconds) {
		// TODO Auto-generated method stub

	}

	public void waitForPageLoad() {
		// TODO Auto-generated method stub

	}

	public String getDropdownSelectedVisibleText(WebElement element) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> getDropdownOptions(WebElement element) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean verifyTitle(String title) {
		// TODO Auto-generated method stub
		return false;
	}

	public void switchContext(String mobileContext) {
		// TODO Auto-generated method stub
	}

	public boolean tap(MobileElement elementName) {
		// TODO Auto-generated method stub
		return false;
	}

	public void tap(int xAxis, int yAxis) {
		// TODO Auto-generated method stub

	}

	public void doubleTap(int xAxis, int yAxis) {
		// TODO Auto-generated method stub

	}

	public boolean scroll(WebElement fromElement, WebElement toElement) {
		// TODO Auto-generated method stub
		return false;
	}

	public void scroll(int fromXCordinate, int fromYCordinate, int toXCordinate, int toYCordinate) {
		// TODO Auto-generated method stub

	}

	public boolean pinch() {
		// TODO Auto-generated method stub
		return false;
	}

	public AppiumDriver<MobileElement> getMobileDriver() {
		// TODO Auto-generated method stub
		return null;
	}

}
