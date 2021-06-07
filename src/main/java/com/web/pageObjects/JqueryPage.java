package com.web.pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cucumber.listener.Reporter;
import com.framework.actions.UserActions;

public class JqueryPage {

	UserActions actions;

	public JqueryPage(UserActions actions) {
		this.actions = actions;
		PageFactory.initElements(actions.getDriver(), this);
	}

	@FindBy(xpath = "//p[contains(text(),'Drag me to my target')]/parent::div")
	WebElement dragMetoTargetBox;

	@FindAll({ @FindBy(xpath = "//p[contains(text(),'Drop here')]/parent::div"),
			@FindBy(xpath = "//p[contains(text(),'Drag me to my target')]/parent::div/following-sibling::div") })
	WebElement dropHereBox;

	@FindBy(xpath = "//legend[text()='Rental Car'])[1]/following-sibling::div/select")
	WebElement firstCarTypeDorpdown;

	@FindBy(xpath = "//legend[text()='Rental Car'])[2]/following-sibling::div/select")
	WebElement secondCarTypeDorpdown;

	@FindBy(id = "book")
	WebElement buttonBook;

	public String launchApplication(String url) {
		actions.launchUrl(url);
		return actions.getPageTitle();

	}

	public boolean selectInteraction(String interaction) {
		boolean flag = false;
		try {
			actions.click(actions.getWebElement("linktext", interaction));
			if (actions.waitUntilElementIsVisible(actions.getWebElement("xpath", "//h1[text()='" + interaction + "']"),
					UserActions.timeout)) {
				flag = true;
				Reporter.addStepLog("Successfuly selected the " + interaction + " interaction item");

			} else {
				Reporter.addStepLog("The " + interaction + " interaction option is not selected");
			}

		} catch (Exception e) {
			Reporter.addStepLog("Exception ocurred while selecting interaction " + interaction);
		}
		return flag;
	}

	public void dragAndDropTheBox() {
		try {
			actions.switchToIframe();
			actions.dragAndDrop(dragMetoTargetBox, dropHereBox);
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public boolean verifyDragAndDrop() {
		boolean flag = false;
		try {

			if (actions.getText(dropHereBox).contains("Dropped")) {
				flag = true;
				Reporter.addStepLog("The status text of 'Drop Here' box changed to Dropped");
			} else {
				Reporter.addStepLog("The status text of the 'Drop Here' box did not changed to Dropped");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public List<WebElement> selectItems(List<String> itemNames) {
		List<WebElement> elements = new ArrayList<WebElement>();

		try {
			actions.switchToIframe();
			for (String itemName : itemNames) {
				elements.add(actions.getWebElement("xpath", "//ol[@id='selectable']/li[text()='" + itemName + "']"));
			}

			actions.selectMultipleElement(elements);

		} catch (Exception e) {
			Reporter.addStepLog("Exception ocurred while selecting the item in selectable interaction");
		}
		return elements;

	}

	public boolean verifyItemSelected(List<WebElement> elements) {
		List<Boolean> flagList = new ArrayList<Boolean>();
		boolean flag = false;

		try {
			for (WebElement element : elements) {
				String classAttribute = actions.getValueOfAttribute(element, "class");
				if (classAttribute.contains("ui-selected")) {
					flagList.add(true);

				} else {
					flagList.add(false);
					Reporter.addStepLog("The item " + actions.getText(element) + " was not selected");
				}
			}
			if (!flagList.contains(false)) {
				flag = true;
				Reporter.addStepLog("All the Items were successfuly selected");
			}

		} catch (Exception e) {
			Reporter.addStepLog("Exception ocurred while selecting items");
		}
		return flag;
	}

	public boolean selectWidgets(String widget) {
		boolean flag = false;
		try {
			actions.click(actions.getWebElement("xpath",
					"//h3[text()='Widgets']/following-sibling::ul/li/a[text()='" + widget + "']"));

			if (actions.waitUntilElementIsVisible(actions.getWebElement("xpath", "//h1[text()='" + widget + "']"),
					UserActions.timeout)) {
				flag = true;
				Reporter.addStepLog("Successfuly selected the " + widget + " widget item");

			} else {
				Reporter.addStepLog("The " + widget + " widget option is not selected");
			}

		} catch (Exception e) {
			Reporter.addStepLog("Exception ocurred while selecting widget " + widget);
		}
		return flag;
	}

	public void selectCarWithCount(int controlGroup, String carType, int numberOfCars) {

		try {
			actions.switchToIframe();
			String rentalCar = "(//legend[text()='Rental Car'])[" + controlGroup + "]";
			actions.mouseHover(actions.getWebElement("xpath",rentalCar));
			selectCarTypeDropdown(
					actions.getWebElement("xpath", rentalCar + "/following-sibling::div/span[@id='car-type-button']"),
					actions.getWebElement("xpath", "(//ul/li/div[text()='SUV'])[" + controlGroup + "]"));
			actions.enterText(actions.getWebElement("xpath", rentalCar + "/following-sibling::div/span/input"),
					numberOfCars + "");

			actions.click(actions.getWebElement("xpath",
					rentalCar + "/following-sibling::div/label[text()='Automatic']/span[1]"));

			actions.click(actions.getWebElement("xpath",
					rentalCar + "/following-sibling::div/label[text()='Insurance']/span[1]"));

			actions.deselectIframe();
		} catch (Exception e) {
			Reporter.addStepLog("Exception while selecting the rental car details");
		}

	}

	private void selectCarTypeDropdown(WebElement dropdown, WebElement option) {
		try {

			actions.click(dropdown);
			if (actions.waitUntilElementIsVisible(option, UserActions.timeout)) {
				actions.click(option);
			}

		} catch (Exception e) {
			e.printStackTrace();
			Reporter.addStepLog("Exception in selecting Car Type dropdown");
		}
	}

	public boolean clickBook() {
		boolean flag = false;
		try {
			actions.switchToIframe();
			flag = actions.click(buttonBook);
			actions.deselectIframe();
		} catch (Exception e) {
			Reporter.addStepLog("Exception while clicking on the 'Book Now'");

		}
		return flag;
	}
}
