package wealus.FrameWorkDevelopment.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {

	WebDriver d;

	public LandingPage(WebDriver d) {
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

	public void loginToApplication() throws InterruptedException {

		email.sendKeys("dummyuser@gmail.com");
		password.sendKeys("Password@123");
		submit.click();
		Thread.sleep(5000);
	}

}
