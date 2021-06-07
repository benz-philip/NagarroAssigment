package com.framework.actions;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cucumber.listener.Reporter;
import com.google.common.base.Function;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;

public class MobileActions implements UserActions {

	public static AppiumDriver<MobileElement> driver;
	Duration timeOutDuration = Duration.of(15, ChronoUnit.SECONDS);
	Duration pollingDuration = Duration.of(250, ChronoUnit.MILLIS);
	public static long timeout = 120;

	public MobileActions(AppiumDriver<MobileElement> driver) {
		MobileActions.driver = driver;
	}

	public WebDriver getDriver() {
		// TODO Auto-generated method stub
		return driver;
	}

	public boolean pinch() {
		// TODO Auto-generated method stub
		return false;
	}

	public AppiumDriver<MobileElement> getMobileDriver() {
		return this.driver;
	}

	public void launchUrl(String url) {
		// TODO Auto-generated method stub

	}

	public void enterText(WebElement element, String textToEnter) {
		try {

			if (visibilityofWebelement(element)) {
				element.clear();
				element.sendKeys(textToEnter);
			}

		} catch (TimeoutException t) {

		} catch (Exception e) {
		}
	}

	public boolean clearTextBox(WebElement element) {
		// TODO Auto-generated method stub
		return false;
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

	public boolean doubleClick(WebElement element) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean rightClick(WebElement element) {
		// TODO Auto-generated method stub
		return false;
	}

	public void pressEnter(WebElement element) {

		try {

			waitFor(2000);
			driver.getKeyboard().pressKey(Keys.ENTER);
			waitFor(2000);
		} catch (Exception e) {
		}
	}

	public boolean selectDropDown(WebElement element, String visibleText) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean selectDropDownValueByIndex(WebElement element, int index) {
		// TODO Auto-generated method stub
		return false;
	}

	public String getText(WebElement element) {
		String text = null;
		try {
			if (visibilityofWebelement(element))
				text = element.getText();

		} catch (Exception t) {
			t.printStackTrace();
		}

		return text;
	}

	public boolean waitUntilElementIsVisible(WebElement element, long timeOutInSeconds) {
		boolean bFlag = false;
		try {
			if (existenceofWebelement(element, timeOutInSeconds)) {
				{
					bFlag = true;
				}

			}

		} catch (TimeoutException t) {

			Reporter.addStepLog("Timeout exception in finding element");
		} catch (Exception e) {
		}
		return bFlag;
	}

	public boolean waitUntilElementIsInVisible(WebElement element, long timeOutInSeconds) {
		boolean bFlag = false;
		try {
			if (verifyElementInvisibility(element, timeOutDuration.getSeconds())) {

				bFlag = true;
			}
		} catch (TimeoutException t) {
		} catch (Exception e) {
		}
		return bFlag;
	}

	public boolean waitUntilElementToBeClickable(WebElement element) {
		boolean bFlag = false;
		try {

			WebElement ele = verifyElementClickable(element);
			if (ele != null) {
				bFlag = true;

			}
		}

		catch (TimeoutException t) {

		} catch (Exception e) {
		}
		return bFlag;

	}

	private WebElement verifyElementClickable(WebElement element) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, 40);
			return wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {

			throw e;
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

	private boolean verifyElementInvisibility(WebElement element, long timeOutInSeconds) {
		boolean elem = false;
		// boolean bFlag = false;
		try {

			// Fetch webelement of all locators:-

			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);

			elem = wait.until(ExpectedConditions.invisibilityOf(element));

		} catch (

		Exception e) {

		}

		return elem;
	}

	public void switchContext(String context) {
		boolean bFlag = false;
		try {
			for (String contexts : driver.getContextHandles()) {
				if (contexts.contains(context)) {
					driver.context(contexts);
					bFlag = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.addStepLog("Exception occured while switching context to " + context);
		}
	}

	public boolean tap(MobileElement element) {
		boolean bFlag = false;
		try {
			WebElement ele = verifyElementClickable(element);
			if (ele != null) {

				TouchAction action = new TouchAction(driver);
				action.tap(new TapOptions().withElement(new ElementOption().withElement(ele))).perform();
				bFlag = true;

			}

		} catch (

		Exception e) {
			e.printStackTrace();
		}
		return bFlag;
	}

	public boolean clickUsingActionBuilder(WebElement element) {
		// TODO Auto-generated method stub
		return false;
	}

	public String getValueOfAttribute(WebElement element, String attribute) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> getTextFromWebElementList(List<WebElement> element) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPageTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isElementExists(WebElement element, boolean toReport) {
		// TODO Auto-generated method stub
		return false;
	}

	public void navigateToPreviousPage() {
		// TODO Auto-generated method stub

	}

	public void mouseHover(WebElement element) {
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
		try {
			Thread.sleep(enterMiliSeconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void waitForPageLoad() {
		// TODO Auto-generated method stub

	}

	public void quitDriver() {
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
		try {
			TouchAction action = new TouchAction(driver);
			action.longPress(PointOption.point(fromXCordinate, fromYCordinate))
					.moveTo(PointOption.point(toXCordinate, toYCordinate)).release().perform();
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public WebElement getWebElement(String propertyType, String property) {
		// TODO Auto-generated method stub
		return null;
	}

	public void dragAndDrop(WebElement elementFrom, WebElement elementTo) {
		// TODO Auto-generated method stub

	}

	public void selectMultipleElement(List<WebElement> element) {
		// TODO Auto-generated method stub

	}

	public void switchToIframe() {
	}

	public void deselectIframe() {
		// TODO Auto-generated method stub

	}

}
