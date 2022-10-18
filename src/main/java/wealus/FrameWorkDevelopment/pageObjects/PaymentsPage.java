package wealus.FrameWorkDevelopment.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import wealus.FrameWorkDevelopment.commonComponents.commonMethods;

public class PaymentsPage extends commonMethods {

	public WebDriver d;

	public PaymentsPage(WebDriver d) {
		super(d);
		this.d = d;
		PageFactory.initElements(d, this);

	}

	@FindBy(xpath = "//*[@placeholder='Select Country']")
	WebElement country;

	@FindBy(className = "ta-results")
	WebElement countryList;

	@FindBy(className = "action__submit")
	WebElement placeOrder;

	@FindBy(className = "hero-primary")
	WebElement thankYouMessage;

	public void selectCountry(String countryName) {

		country.sendKeys(countryName);
		waitForElemetToBrPresent(countryList);
		List<WebElement> results = countryList.findElements(By.tagName("button"));
		for (WebElement result : results) {
			String option = result.getText().trim();
			if (option.equals(countryName)) {
				result.click();
				break;
			}
		}
	}

	public void clickPlaceOrder() {
		placeOrder.click();
	}

	public boolean verifySuccessMessage() {
		String success = thankYouMessage.getText();
		return success.equalsIgnoreCase("Thankyou for the order.");
	}

}
