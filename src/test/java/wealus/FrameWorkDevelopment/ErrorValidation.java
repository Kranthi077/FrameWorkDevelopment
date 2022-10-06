package wealus.FrameWorkDevelopment;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import wealus.FrameWorkDevelopment.pageObjects.CartPage;
import wealus.FrameWorkDevelopment.pageObjects.PaymentsPage;
import wealus.FrameWorkDevelopment.pageObjects.ProductsHomePage;

public class ErrorValidation extends BaseTest {

	@Test(groups = {"Login Errors"})
	public void incorrectUserPassedTest() {
		landingPage.loginToApplication("dummyuser@gmail.com", "Password@12");
		String expectedMesssage = "Incorrect email or password.";
		String actualMessage = landingPage.validateErrorMessage();
		Assert.assertEquals(actualMessage, expectedMesssage);
	}
	
	@Test
	public void productValidationError() {
		String input = "ADIDAS ORIGINAL";
		ProductsHomePage productsHomePage = landingPage.loginToApplication("dummyuser@gmail.com", "Password@123");
		List<WebElement> products = productsHomePage.getProducts();
		
		CartPage cartPage = productsHomePage.addProductToCart(products, input);
		cartPage.goToCart();
		List<WebElement> cartSection = cartPage.getCartList();
		 boolean status = cartPage.verifyProductAvailability(cartSection, "ADIDAS ORIGINA");
		 Assert.assertTrue(status);
	}

}
