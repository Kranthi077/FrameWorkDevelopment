package wealus.FrameWorkDevelopment.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import wealus.FrameWorkDevelopment.commonComponents.commonMethods;

public class OrdersPage extends commonMethods {

	WebDriver d;

	public OrdersPage(WebDriver d) {
		super(d);
		this.d = d;
		PageFactory.initElements(d, this);
	}

	@FindBy(xpath = "//tr[@class=\"ng-star-inserted\"]/td[2]")
	List<WebElement> orderList;

	public boolean verifyOrderIsPresent(String product) {

		boolean status = false;

		for (WebElement order : orderList) {

			if (order.getText().equalsIgnoreCase(product)) {
				status = true;
			}
		}
		return status;

	}

}
