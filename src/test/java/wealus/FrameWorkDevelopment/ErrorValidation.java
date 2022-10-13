package wealus.FrameWorkDevelopment;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import wealus.FrameWorkDevelopment.pageObjects.CartPage;
import wealus.FrameWorkDevelopment.pageObjects.PaymentsPage;
import wealus.FrameWorkDevelopment.pageObjects.ProductsHomePage;

public class ErrorValidation extends BaseTest {

	@Test(groups = {"Login Errors"} , dataProvider = "getData")
	public void incorrectUserPassedTest(HashMap<String, String> data) {
		landingPage.loginToApplication(data.get("email"), data.get("password"));
		String expectedMesssage = "Incorrect email or password.";
		String actualMessage = landingPage.validateErrorMessage();
		Assert.assertEquals(actualMessage, expectedMesssage);
	}
	
	@Test(dataProvider = "getData1")
	public void productValidationError(HashMap<String, String> data) {
		ProductsHomePage productsHomePage = landingPage.loginToApplication(data.get("email"), data.get("password"));
		List<WebElement> products = productsHomePage.getProducts();
		
		CartPage cartPage = productsHomePage.addProductToCart(products, data.get("correctInput"	));
		cartPage.goToCart();
		List<WebElement> cartSection = cartPage.getCartList();
		 boolean status = cartPage.verifyProductAvailability(cartSection, data.get("wrongInput"));
		 Assert.assertTrue(status);
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		String filePath = System.getProperty("user.dir")
				+ "\\src\\test\\java\\wealus\\FrameWorkDevelopment\\dataProvider\\ErrorValidation.json";

		List<HashMap<String, String>> data = getJsonDataToMap(filePath);
		Object[][] Credentials = new Object[][] { { data.get(0) } };
		return Credentials;
	}
	@DataProvider
	public Object[][] getData1() throws IOException {
		String filePath = System.getProperty("user.dir")
				+ "\\src\\test\\java\\wealus\\FrameWorkDevelopment\\dataProvider\\ErrorValidation.json";

		List<HashMap<String, String>> data = getJsonDataToMap(filePath);
		Object[][] Credentials = new Object[][] { { data.get(1) } };
		return Credentials;
	}

}
