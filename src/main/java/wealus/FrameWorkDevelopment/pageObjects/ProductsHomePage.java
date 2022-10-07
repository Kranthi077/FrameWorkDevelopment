package wealus.FrameWorkDevelopment.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import wealus.FrameWorkDevelopment.commonComponents.commonMethods;

public class ProductsHomePage extends commonMethods {

	WebDriver d;

	public ProductsHomePage(WebDriver d) {
		// super keyword will pass object instance(life) to parent
		super(d);
		this.d = d;
		PageFactory.initElements(d, this);
	}
	@FindBy(xpath = "//button[contains(@routerlink,'/dashboard/myorders')]")
	WebElement orders;

	// d.findElements(By.xpath("//div[contains(@class,'md-0 ')]"));
	@FindBy(xpath = "//div[contains(@class,'md-0 ')]")
	List<WebElement> Products;

	@FindBy(xpath = "//*[contains(text(), 'Product Added To Cart')]")
	WebElement ProductAddedSuccessMessage1;

	By productsList = By.xpath("//div[contains(@class,'md-0 ')]");

	By AddToCart = By.xpath(".//button[contains(text(),'Add To Cart')]");

	By ProductAddedSuccessMessage = By.xpath("//*[contains(text(), 'Product Added To Cart')]");

	public List<WebElement> getProducts() {
		waituntilElementsToBeVisible(productsList);
		return Products;

	}

	public OrdersPage clickOrders() {

		waitForElemetToBrPresent(orders);
		orders.click();
		return new OrdersPage(d);
	}
	

	public CartPage addProductToCart(List<WebElement> products, String input) {

		String productName = "";

		for (WebElement product : products) {

			productName = product.findElement(By.tagName("b")).getText();

			if (productName.equalsIgnoreCase(input)) {
				product.findElement(AddToCart).click();
				waituntilElementToBeVisible(ProductAddedSuccessMessage);
				// d.findElement(ProductAddedSuccessMessage).isDisplayed();
				ProductAddedSuccessMessage1.isDisplayed();
				
				break;
			}
		}
		return new CartPage(d);
	}

}
