package com.nagarro.tests;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.framework.actions.UserActions;
import com.framework.actions.WebActions;
import com.framework.driver.RunnerTest;
import com.framework.driver.web.WebBrowserDriver;
import com.web.pageObjects.JqueryPage;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class WebStepDefinition extends RunnerTest {
	UserActions actions = null;
	JqueryPage jqueryPage;

	@Before("@Web")
	public void setupWebControls() {
		if (actions == null) {
			actions = new WebActions(new WebBrowserDriver().getDriver(browser));
		}
	}

	@After("@Web")
	public void killWebController() {
		actions.quitDriver();
	}

	@Given("^User is on \"([^\"]*)\" url$")
	public void user_is_on_url(String url) throws Throwable {
		jqueryPage = new JqueryPage(actions);
		Assert.assertEquals(jqueryPage.launchApplication(url), "jQuery UI");
	}

	@Given("^User select \"([^\"]*)\" from left menu$")
	public void user_select_from_left_menu(String interaction) throws Throwable {
		Assert.assertTrue(jqueryPage.selectInteraction(interaction));
	}

	@When("^User drag Drag me to my target to drop here$")
	public void user_drag_to_drop_here() throws Throwable {
		jqueryPage.dragAndDropTheBox();
	}

	@Then("^The component should be placed inside drop here box$")
	public void the_component_should_be_placed_inside_drop_here_box() throws Throwable {
		Assert.assertTrue(jqueryPage.verifyDragAndDrop());
	}

	List<WebElement> itemList = new ArrayList<WebElement>();

	@When("^User select items$")
	public void user_select_items(List<String> itemNames) throws Throwable {
		itemList = jqueryPage.selectItems(itemNames);
	}

	@Then("^The items should be in selectd state$")
	public void the_items_should_be_in_selectd_state() throws Throwable {
		Assert.assertTrue(jqueryPage.verifyItemSelected(itemList));
	}

	@Given("^User select \"([^\"]*)\" from left menu under widgets$")
	public void user_select_from_left_menu_under_widgets(String widget) throws Throwable {
		Assert.assertTrue(jqueryPage.selectWidgets(widget));
	}

	@When("^User selects \"([^\"]*)\" \"([^\"]*)\" in first rental car$")
	public void user_selects_in_first_rental_car(int numberOfCars, String carType) throws Throwable {
		jqueryPage.selectCarWithCount(1, carType, numberOfCars);
	}

	@When("^User selects \"([^\"]*)\" \"([^\"]*)\" in second rental car$")
	public void user_selects_in_second_rental_car(int numberOfCars, String carType) throws Throwable {
		jqueryPage.selectCarWithCount(2, carType, numberOfCars);
	}

	@Then("^User clicks on book now for the \"([^\"]*)\"$")
	public void user_clicks_on_bool_not_for_the(String arg1) throws Throwable {
		Assert.assertTrue(jqueryPage.clickBook());
	}

}
