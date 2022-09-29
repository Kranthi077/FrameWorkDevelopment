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
		System.out.println("tap");
	}

//WebElement userEmail1= d.findElement(By.id("userEmail"));

	// PageFactory method
	@FindBy(id = "userEmail")
	WebElement email;

	@FindBy(id = "userPassword")
	WebElement password;

	@FindBy(id = "login")
	WebElement submit;

	public ProductsHomePage loginToApplication(String user, String Password) throws InterruptedException {

		email.sendKeys(user);
		password.sendKeys(Password);
		submit.click();
		Thread.sleep(5000);

		return new ProductsHomePage(d);
	}

}
