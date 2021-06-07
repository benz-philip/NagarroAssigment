package com.nagarro.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;

import com.framework.actions.MobileActions;
import com.framework.actions.UserActions;
import com.framework.driver.RunnerTest;
import com.framework.driver.mobile.MobileDriver;
import com.mobile.pageObjects.BrowserScreen;
import com.mobile.pageObjects.HomeScreen;
import com.mobile.pageObjects.RegistrationScreen;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MobileStepDefinition extends RunnerTest {
	static UserActions actions;
	HomeScreen homeScreen;
	BrowserScreen browserScreen;
	RegistrationScreen regdScreen;

	String homeScreenTitle = "selendroid-test-app";

	@Before("@Mobile")
	public void setupWebControls() {
		if (actions == null) {
			actions = new MobileActions(new MobileDriver().getDriver(deviceType));
		} else {
			actions.getMobileDriver().launchApp();
		}
	}

	@After("@Mobile")
	public void killWebController() {
		actions.getMobileDriver().terminateApp("io.selendroid.testapp");
	}

	@Given("^The selendroid app is launched$")
	public void the_selendroid_app_is_launched() throws Throwable {
		homeScreen = new HomeScreen(actions);
		Assert.assertTrue(homeScreen.verifyHomeScreenTitle(homeScreenTitle));

	}

	@Then("^verify elements on the screen$")
	public void verify_elements_on_the_screen() throws Throwable {
		Assert.assertTrue(homeScreen.verifyHomeScreenElement());
	}

	@When("^user taps on \"([^\"]*)\" button$")
	public void user_taps_on_button(String elementName) throws Throwable {
		homeScreen.tapHomeScreenElement(elementName);
	}

	@When("^Select option \\[No, no\\]$")
	public void select_option_No_no() throws Throwable {
		homeScreen.selectNoBtn();
	}

	@Then("^Homepage should be displayed$")
	public void homepage_should_be_displayed() throws Throwable {
		Assert.assertTrue(homeScreen.verifyHomeScreenTitle(homeScreenTitle));
	}

	@Then("^text Hello, can you\\.\\. should appear$")
	public void text_Hello_can_you_should_appear() throws Throwable {
		browserScreen = new BrowserScreen(actions);
		Assert.assertTrue(browserScreen.verifyTheBrowserHeaderText());
	}

	@When("^user submits details  name \"([^\"]*)\" and car \"([^\"]*)\"$")
	public void user_submits_details_name_and_car(String name, String car) throws Throwable {
		browserScreen.enterNameAndCarDetails(name, car);
	}

	@Then("^text This is my\\.\\. should appear with name \"([^\"]*)\" and preferred car \"([^\"]*)\"$")
	public void text_This_is_my_should_appear_with_name_and_preferred_car(String name, String car) throws Throwable {
		Assert.assertTrue(browserScreen.verifyBrowserSubmitScreen(name, car));
	}

	@When("^user clicks link here$")
	public void user_clicks_link_here() throws Throwable {
		browserScreen.navigateBackFromSuccessScreen();
	}

	@Then("^default car should be \"([^\"]*)\"$")
	public void default_car_should_be(String arg1) throws Throwable {
		Assert.assertTrue(browserScreen.verifyDefaultCar());
	}

	@Then("^Verify title text Welcome to register\\.\\. and elements on screen$")
	public void verify_title_text_Welcome_to_register_and_elements_on_screen() throws Throwable {
		regdScreen = new RegistrationScreen(actions);
		Assert.assertTrue(regdScreen.verifyTitleAndElement());
	}

	@Then("^name should be \"([^\"]*)\" and language \"([^\"]*)\" by default$")
	public void name_should_be_and_language_by_default(String name, String lang) throws Throwable {
		testData.put("name", name);
		testData.put("language", lang);
		Assert.assertTrue(regdScreen.verifyDefaultNameAndLanguage(name, lang));
	}

	Map<String, String> testData = new HashMap<String, String>();

	@When("^user fill the fields with new value, check I accept and registers$")
	public void user_fill_the_fields_with_new_value_check_I_accept_and_registers() throws Throwable {
		testData = regdScreen.createTestData(testData);
		regdScreen.sumitForm(testData.get("user"), testData.get("email"), testData.get("password"));
	}

	@Then("^user deatils should match on next screen$")
	public void user_deatils_should_match_on_next_screen() throws Throwable {
		Assert.assertTrue(regdScreen.verifySuccessScreen(testData.get("name"), testData.get("user"),
				testData.get("password"), testData.get("email"), testData.get("language")));
	}

	@When("^user taps on register$")
	public void user_taps_on_register() throws Throwable {
		regdScreen.tapRegister();
	}

	@Then("^user should land on home screen$")
	public void user_should_land_on_home_screen() throws Throwable {
		Assert.assertTrue(homeScreen.verifyHomeScreenTitle(homeScreenTitle));
	}

	@Then("^user should wait for loader to disappear$")
	public void user_should_wait_for_loader_to_disappear() throws Throwable {
		homeScreen.waitUntilProgressBarDisappear();
	}

	@Then("^Verify element on registration screen$")
	public void verify_element_on_registration_screen() throws Throwable {
		regdScreen = new RegistrationScreen(actions);
		Assert.assertTrue(regdScreen.verifyTitleAndElement());
	}

	@Then("^user should see toast text$")
	public void user_should_see_toast_text() throws Throwable {

	}

	@Then("^user should be able to close pop up window$")
	public void user_should_be_able_to_close_pop_up_window() throws Throwable {

	}

	@Then("^he should see home screen$")
	public void he_should_see_home_screen() throws Throwable {
		Assert.assertTrue(homeScreen.verifyHomeScreenTitle(homeScreenTitle));
	}

	@When("^user types to throw unhandled exception$")
	public void user_types_to_throw_unhandled_exception() throws Throwable {
		homeScreen.typeInExceptionText();
	}

}
