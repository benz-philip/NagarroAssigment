package com.framework.actions;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public interface UserActions {

	int timeout = 60;
	Duration pollingDuration = Duration.of(250, ChronoUnit.MILLIS);
	Duration timeOutDuration = Duration.of(5, ChronoUnit.SECONDS);

	public WebDriver getDriver();

	public boolean pinch();

	public AppiumDriver<MobileElement> getMobileDriver();

	public void launchUrl(String url);

	public void enterText(WebElement element, String textToEnter);

//	public boolean enterTextInDisabledTextArea(String element, String properties, String textToEnter,
//			String stepDescription);

	public boolean clearTextBox(WebElement element);

//	public boolean isTextboxEditable(String element, String properties, String stepDescription, boolean toReport);

	public boolean click(WebElement element);

	public boolean doubleClick(WebElement element);

	public boolean rightClick(WebElement element);

	public void pressEnter(WebElement element);

//	public boolean pressTabKey(String element, String properties, String stepDescription);

	public boolean selectDropDown(WebElement element, String visibleText);

	public boolean selectDropDownValueByIndex(WebElement element, int index);

	public String getText(WebElement element);

//	public String getTextValueFromTextBoxOrDropBox(String element, String properties, String stepDescription);

	public boolean clickUsingActionBuilder(WebElement element);

	public String getValueOfAttribute(WebElement element, String attribute);

//	public boolean isTextPresent(String element, String text);

	public WebElement getWebElement(String propertyType, String property);

//	public List<MobileElement> getMobileElementsList(String stepDescription, String properties);

	// public List<WebElement> getWebElementList(String properties);

	public List<String> getTextFromWebElementList(List<WebElement> element);

	public String getPageTitle();

	public boolean isElementExists(WebElement element, boolean toReport);

//	public boolean switchAndAcceptAlert();

//	public boolean switchAndCancelAlert(String stepDescription, boolean toReport);

//	public String switchAndGetTextFromAlert(String stepDescription);

	public boolean waitUntilElementIsVisible(WebElement element, long timeOutInSeconds);

	public boolean waitUntilElementIsInVisible(WebElement element, long timeOutInSeconds);

	public boolean waitUntilElementToBeClickable(WebElement element);

//	public boolean areElementsPresent(String element, String properties, String stepdDescription);

//	public String getCurrentWindowHandle(String stepDescription);

//	public Set<String> getAllWindowHandles(String stepDescription);

//	public boolean switchToWindow(String element, String previousWindowHandel, String stepDescription);

//	public void switchToNewWindow(String stepDescription, int i);

//	public void closeWindow(String stepDescription, int i, boolean toReport);

//	public void refreshPage();

	public void navigateToPreviousPage();

	public void mouseHover(WebElement element);

	public void dragAndDrop(WebElement elementFrom, WebElement elementTo);

	public void selectMultipleElement(List<WebElement> element);

//	public void mouseHoverClickChild(String element, String parentProperties, String childProperties,
//			String stepDescription);

//	public String getPageSource(String stepDescription, boolean toReport);

//	public void deleteAllCookies(String stepDescription);

//	public String getCurrentUrl(String stepDescription);

	public void resetImplicitWaitToDefault();

	public void resetImplicitWait(String stepDescription, int newWaitTime_InSeconds);

	public void switchToIframe();

//	public void switchToIframeById(String element, String iFrameId, String stepDescription);

//	public void switchToIframeByIndex(String stepDescription, int frameIndex);

//	public boolean switchToIframe(String element, String properties, String stepDescription);

	public void deselectIframe();

	public void executeJavaScript(String jScript);

	public void waitFor(int enterMiliSeconds);

	public void waitForPageLoad();

	public void quitDriver();

//	public void closeCurrentWindow(String stepDescription);

	// public PropFileHandler propFileHandler();

	public String getDropdownSelectedVisibleText(WebElement element);

	public List<String> getDropdownOptions(WebElement element);

	public boolean verifyTitle(String title);

	public void switchContext(String mobileContext);

	public boolean tap(MobileElement elementName);

	public void tap(int xAxis, int yAxis);

//	public boolean doubleTap(String elementName);

	public void doubleTap(int xAxis, int yAxis);

	public boolean scroll(WebElement fromElement, WebElement toElement);

	public void scroll(int fromXCordinate, int fromYCordinate, int toXCordinate, int toYCordinate);

//	public boolean zoom(WebElement elementName);

//	public boolean performMultiTouch(List<TouchAction> actions);

}
