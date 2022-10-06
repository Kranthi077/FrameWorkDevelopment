package wealus.FrameWorkDevelopment.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import wealus.FrameWorkDevelopment.commonComponents.commonMethods;

public class CartPage extends commonMethods {

	WebDriver d;

	public CartPage(WebDriver d) {
		super(d);
		this.d = d;
		PageFactory.initElements(d, this);
	}

	@FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
	WebElement cartButton;

	@FindBy(className = "infoWrap")
	List<WebElement> cartList;

	By prductFromList = By.xpath(".//div/h3");

	By buyNow = By.xpath(".//div/button[text()='Buy Now']");

	public void goToCart() {
		cartButton.click();
	}

	public List<WebElement> getCartList() {
		return cartList;
	}

	public PaymentsPage verifyProductAndClickBuyNow(List<WebElement> cartSection, String input) {
		for (WebElement item : cartSection) {
			String text = item.findElement(prductFromList).getText();
			if (text.equalsIgnoreCase(input)) {
				item.findElement(buyNow).click();
				break;
			}
			
		}

		return new PaymentsPage(d);
	}
	
	
	public boolean verifyProductAvailability(List<WebElement> cartSection, String input) {
		
		boolean status = false;
		for (WebElement item : cartSection) {
			String text = item.findElement(prductFromList).getText();
			if (text.equalsIgnoreCase(input)) {
				status = true;
			}
			else {
				status = false;
			}
			
		}
		return status;

		
	}
	
	

}
