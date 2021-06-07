package com.mobile.pageObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cucumber.listener.Reporter;
import com.framework.actions.UserActions;

public class RegistrationScreen {

	UserActions actions;

	public RegistrationScreen(UserActions actions) {
		this.actions = actions;
		PageFactory.initElements(actions.getDriver(), this);
	}

	@FindBy(xpath = "//android.widget.TextView[@text='Welcome to register a new User']")
	private WebElement regTitle;

	@FindBy(id = "io.selendroid.testapp:id/inputName")
	private WebElement dfltName;

	@FindBy(id = "android:id/text1")
	private WebElement dflLang;

	@FindBy(id = "io.selendroid.testapp:id/inputUsername")
	private WebElement instUserName;

	@FindBy(id = "io.selendroid.testapp:id/inputEmail")
	private WebElement instEmail;

	@FindBy(id = "io.selendroid.testapp:id/inputPassword")
	private WebElement instPwd;

	@FindBy(id = "io.selendroid.testapp:id/input_adds")
	private WebElement accptAdd;

	@FindBy(id = "io.selendroid.testapp:id/btnRegisterUser")
	private WebElement regUser;

	@FindBy(id = "io.selendroid.testapp:id/label_name_data")
	private WebElement name;

	@FindBy(id = "io.selendroid.testapp:id/label_username_data")
	private WebElement username;

	@FindBy(id = "io.selendroid.testapp:id/label_email_data")
	private WebElement email;

	@FindBy(id = "io.selendroid.testapp:id/label_password_data")
	private WebElement password;

	@FindBy(id = "io.selendroid.testapp:id/label_preferedProgrammingLanguage_data")
	private WebElement language;

	@FindBy(id = "io.selendroid.testapp:id/buttonRegisterUser")
	private WebElement fnlRegUser;

	public boolean verifyTitleAndElement() {
		boolean flag = false;
		List<Boolean> flagList = new ArrayList<Boolean>();
		try {

			if (actions.waitUntilElementIsVisible(regTitle, UserActions.timeout)) {
				Reporter.addStepLog("The title for user registration page is present and verified");
				actions.getMobileDriver().hideKeyboard();
				List<WebElement> regdScreenElements = new ArrayList<WebElement>();

				regdScreenElements.add(dfltName);
				regdScreenElements.add(dflLang);
				regdScreenElements.add(instUserName);
				regdScreenElements.add(instEmail);
				regdScreenElements.add(instPwd);
				regdScreenElements.add(accptAdd);
				regdScreenElements.add(regUser);

				for (WebElement ele : regdScreenElements) {
					if (actions.waitUntilElementIsVisible(ele, UserActions.timeout)) {
						flagList.add(true);
					} else {
						flagList.add(false);
					}
				}
				if (!flagList.contains(false)) {
					flag = true;
					Reporter.addStepLog("All the elements are verified on the Registration Screen");
				} else {
					Reporter.addStepLog("All the elements are not verified on the Registration Screen");
				}
			} else {
				Reporter.addStepLog("The title for user registration page is not verified");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.addStepLog("Exception ocurred while verifying title and elements on registration screen");
		}
		return flag;
	}

	public boolean verifyDefaultNameAndLanguage(String name, String lang) {
		boolean flag = false;
		try {

			String defaultName = actions.getText(dfltName);
			String defaultLang = actions.getText(dflLang);

			if (defaultName.equalsIgnoreCase(name) && defaultLang.equalsIgnoreCase(lang)) {
				flag = true;
				Reporter.addStepLog("The default name " + name + " and language " + lang + " is verified");
			} else {
				Reporter.addStepLog("The default name " + name + " and language " + lang + " is not verified");
			}
		} catch (Exception e) {
			Reporter.addStepLog("Exception ocurred while verifying default name and language");
		}
		return flag;
	}

	public void sumitForm(String username, String email, String password) {
		try {

			actions.enterText(instUserName, username);
			actions.enterText(instEmail, email);
			actions.enterText(instPwd, password);
			actions.click(accptAdd);
			actions.click(regUser);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public boolean verifySuccessScreen(String name, String username, String passwrd, String email, String lang) {
		boolean flag = false;

		flag = verifyElementText("Name", this.name, name) && verifyElementText("Username", this.username, username)
				&& verifyElementText("Password", this.password, passwrd)
				&& verifyElementText("Email", this.email, email) && verifyElementText("Language", this.language, lang);

		return flag;
	}

	public boolean verifyElementText(String fieldName, WebElement ele, String value) {
		boolean flag = false;
		try {

			String text = actions.getText(ele);

			if (text.equalsIgnoreCase(value)) {
				flag = true;
				Reporter.addStepLog(
						"Value verified for the field " + fieldName + " in success screen. The value is: " + text);
			} else {
				Reporter.addStepLog("Value for the field " + fieldName + " is not verified in success screen");

			}
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.addStepLog("Exception while verifying Value for the field " + fieldName);
		}
		return flag;
	}

	public void tapRegister() {
		actions.click(fnlRegUser);
	}

	public Map<String, String> createTestData(Map<String, String> testData) {
		int random = new Random().nextInt(100);
		String user = "TestUser" + random;
		String email = user + "@test.com";
		String password = "TestPass" + random;

		testData.put("user", user);
		testData.put("email", email);
		testData.put("password", password);

		return testData;
	}

}
