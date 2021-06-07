package com.mobile.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cucumber.listener.Reporter;
import com.framework.actions.UserActions;

public class BrowserScreen {
	UserActions actions;
	final String contextWeb = "WEBVIEW";
	final String contextNative = "NATIVE";

	public BrowserScreen(UserActions actions) {
		this.actions = actions;
		PageFactory.initElements(actions.getDriver(), this);
	}

	@FindBy(xpath = "//android.widget.TextView[@text='Web View Interaction']")
	private WebElement webViewHome;

	@FindBy(xpath = "//android.widget.TextView[@text='Hello, can you please tell me your name?']")
	private WebElement webViewTxt;

	@FindBy(xpath = "//android.widget.EditText[@resource-id='name_input']")
	private WebElement webViewTxtBox;

	@FindBy(xpath = "//android.widget.Spinner[@text='Volvo']")
	private WebElement carDropdown;

	@FindBy(xpath = "//*[@text='Send me your name!']")
	private WebElement sendNameBtn;

	@FindBy(xpath = "//android.view.View[text()='This is my way of saying hello']")
	private WebElement successTxt;

	@FindBy(xpath = "(//android.view.View[@content-desc='Your name is:']" + "/following-sibling::android.view.View)[1]")
	private WebElement nameTxt;

	@FindBy(xpath = "(//android.view.View[@content-desc='Your prefered car is:']"
			+ "/following-sibling::android.view.View)[1]")
	private WebElement carTxt;

	@FindBy(xpath = "//android.view.View[text()='here']")
	private WebElement clickHere;

	public boolean verifyTheBrowserHeaderText() {
		boolean flag = false;
		try {
			actions.switchContext(contextWeb);
			if (actions.waitUntilElementIsVisible(webViewTxt, UserActions.timeout)) {
				flag = true;
				Reporter.addStepLog("Web view header text 'Hello, can you please tell me your name?' verified");
			} else {
				Reporter.addStepLog("Web view header text 'Hello, can you please tell me your name?' is not visible");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.addStepLog("Exception ocurred while verifying text 'Hello, can you please tell me your name?'");
		}
		return flag;
	}

	public void enterNameAndCarDetails(String name, String car) {
		try {
			actions.enterText(webViewTxtBox, name);
			actions.click(carDropdown);
			actions.waitUntilElementIsVisible(
					actions.getWebElement("xpath", "//android.widget.CheckedTextView[@text='" + car + "']"),
					UserActions.timeout);
			actions.click(actions.getWebElement("xpath", "//android.widget.CheckedTextView[@text='" + car + "']"));
			actions.click(sendNameBtn);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public boolean verifyBrowserSubmitScreen(String name, String car) {
		boolean flag = false;

		try {
			if (actions.waitUntilElementIsVisible(successTxt, UserActions.timeout)) {
				Reporter.addStepLog("The success text " + actions.getText(successTxt) + " is displayed");
				String displayedName = actions.getText(nameTxt);
				String displayedCar = actions.getText(carTxt);

				if (name.equalsIgnoreCase(displayedName) && car.equalsIgnoreCase(displayedCar)) {
					flag = true;
					Reporter.addStepLog("The name: " + name + " and the car: " + car + " verified on success screen");
				} else {
					Reporter.addStepLog(
							"The name: " + name + " and the car: " + car + " is not verified on success screen");
				}

			} else {
				Reporter.addStepLog("The success text is not displayed");
			}

		} catch (Exception e) {
			e.printStackTrace();
			Reporter.addStepLog("Exception while verifying the name and car displayed");
		}
		return flag;
	}

	public void navigateBackFromSuccessScreen() {
		actions.click(clickHere);
	}

	public boolean verifyDefaultCar() {
		boolean flag = false;
		try {
			if (actions.waitUntilElementIsVisible(carDropdown, UserActions.timeout)) {
				flag = true;
				Reporter.addStepLog("The default car selected is Volvo");
			} else {
				Reporter.addStepLog("The default car selected is not verified");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return flag;
	}
}
