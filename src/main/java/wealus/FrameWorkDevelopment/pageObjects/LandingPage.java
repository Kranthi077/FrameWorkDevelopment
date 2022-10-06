package wealus.FrameWorkDevelopment.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import wealus.FrameWorkDevelopment.commonComponents.commonMethods;

public class LandingPage extends commonMethods {

	WebDriver d;

	public LandingPage(WebDriver d) {
		super(d);
		this.d = d;
		PageFactory.initElements(d, this);
	}

//WebElement userEmail1= d.findElement(By.id("userEmail"));

	// PageFactory method
	@FindBy(id = "userEmail")
	WebElement email;

	@FindBy(id = "userPassword")
	WebElement password;

	@FindBy(id = "login")
	WebElement submit;

	@FindBy(xpath = "//*[contains(text(), 'Incorrect email or password.')]")
	WebElement errorMessage;

	public String validateErrorMessage() {

		waitForElemetToBrPresent(errorMessage);
		return errorMessage.getText();

	}

	public ProductsHomePage loginToApplication(String user, String Password) {

		email.sendKeys(user);
		password.sendKeys(Password);
		submit.click();

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new ProductsHomePage(d);
	}

}
