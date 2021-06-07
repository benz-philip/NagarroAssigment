package com.mobile.pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cucumber.listener.Reporter;
import com.framework.actions.UserActions;
import com.framework.reporting.Report;

public class HomeScreen {

	UserActions actions;

	public HomeScreen(UserActions actions) {
		this.actions = actions;
		PageFactory.initElements(actions.getDriver(), this);
	}

	@FindBy(id = "android:id/title")
	private WebElement title;

	@FindBy(id = "io.selendroid.testapp:id/buttonTest")
	private WebElement enBtn;

	@FindBy(id = "io.selendroid.testapp:id/buttonStartWebview")
	private WebElement browserBtn;

	@FindBy(id = "io.selendroid.testapp:id/startUserRegistration")
	private WebElement regBtn;

	@FindBy(id = "io.selendroid.testapp:id/my_text_field")
	private WebElement textBox;

	@FindBy(id = "io.selendroid.testapp:id/waitingButtonTest")
	private WebElement progressBarBtn;

	@FindBy(id = "io.selendroid.testapp:id/input_adds_check_box")
	private WebElement checkBox;

	@FindBy(id = "io.selendroid.testapp:id/visibleButtonTest")
	private WebElement dispTextBtn;

	@FindBy(id = "io.selendroid.testapp:id/showToastButton")
	private WebElement dispToastBtn;

	@FindBy(id = "io.selendroid.testapp:id/showPopupWindowButton")
	private WebElement popupBtn;

	@FindBy(id = "io.selendroid.testapp:id/exceptionTestButton")
	private WebElement excptBtn;

	@FindBy(id = "io.selendroid.testapp:id/exceptionTestField")
	private WebElement textExcptn;

	@FindBy(id = "io.selendroid.testapp:id/topLevelElementTest")
	private WebElement dispFcsBtn;

	@FindBy(id = "android:id/button2")
	private WebElement noBtn;

	@FindBy(id = "android:id/content")
	private WebElement homeScreen;

	@FindBy(id = "io.selendroid.testapp:id/waitingButtonTest")
	private WebElement btnProgressBar;

	@FindBy(id = "android:id/progress_percent")
	private WebElement progressBar;

	public boolean verifyHomeScreenTitle(String titleText) {
		boolean flag = false;
		try {

			String actualTitleText = actions.getText(title);
			if (actualTitleText.equals(titleText)) {
				flag = true;
				Reporter.addStepLog("The app title verified as: " + actualTitleText);
			} else {
				Reporter.addStepLog(
						"The app title is not verified, expected: " + titleText + ", actual: " + actualTitleText);
				Report.getScreenshot();
			}
		} catch (Exception e) {
			Reporter.addStepLog("Exception while verifying app title text");
			Report.getScreenshot();
		}
		return flag;
	}

	public boolean verifyHomeScreenElement() {
		boolean flag = false;
		List<Boolean> flagList = new ArrayList<Boolean>();
		try {

			List<WebElement> homeScreenElements = new ArrayList<WebElement>();

			homeScreenElements.add(enBtn);
			homeScreenElements.add(browserBtn);
			homeScreenElements.add(regBtn);
			homeScreenElements.add(textBox);
			homeScreenElements.add(progressBarBtn);
			homeScreenElements.add(checkBox);
			homeScreenElements.add(dispTextBtn);
			homeScreenElements.add(dispToastBtn);
			homeScreenElements.add(popupBtn);
			homeScreenElements.add(excptBtn);
			homeScreenElements.add(textExcptn);
			homeScreenElements.add(dispFcsBtn);

			for (WebElement ele : homeScreenElements) {
				if (actions.waitUntilElementIsVisible(ele, UserActions.timeout)) {
					flagList.add(true);
				} else {
					flagList.add(false);
				}
			}
			if (!flagList.contains(false)) {
				flag = true;
				Reporter.addStepLog("All the elements are verified on the Home Screen");
			}

		} catch (Exception e) {
			Report.getScreenshot();
		}
		return flag;
	}

	public void tapHomeScreenElement(String elementName) {
		try {
			switch (elementName) {
			case "EN button":
				actions.click(enBtn);
				break;
			case "chrome":
				actions.click(browserBtn);
				break;
			case "registration":
				actions.click(regBtn);
				break;
			case "show progress bar":
				actions.click(progressBarBtn);
				break;
			case "display toast":
				actions.click(dispToastBtn);
				break;
			case "display popup":
				actions.click(popupBtn);
				break;
			case "Press to throw unhandled exception":
				actions.click(excptBtn);
				break;

			default:
				break;
			}
		} catch (Exception e) {
			Reporter.addStepLog("Wxception ocurred while clicking button " + elementName);
			Report.getScreenshot();
		}
	}

	public void selectNoBtn() {
		if (actions.waitUntilElementIsVisible(noBtn, UserActions.timeout)) {
			actions.click(noBtn);
			Reporter.addStepLog("Successfully selected 'No, no' option for En button");
		} else {
			Reporter.addStepLog("[No, no] button is not available");
			Report.getScreenshot();
		}
	}

	public void waitUntilProgressBarDisappear() {
		actions.waitUntilElementIsInVisible(progressBar, UserActions.timeout);
	}

	public void typeInExceptionText() {
		try {
			actions.enterText(textExcptn, "test");

		} catch (Exception e) {
			Report.getScreenshot();
		}
	}
}
