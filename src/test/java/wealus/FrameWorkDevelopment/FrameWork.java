package wealus.FrameWorkDevelopment;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.asynchttpclient.webdav.WebDavResponse;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import wealus.FrameWorkDevelopment.pageObjects.CartPage;
import wealus.FrameWorkDevelopment.pageObjects.LandingPage;
import wealus.FrameWorkDevelopment.pageObjects.OrdersPage;
import wealus.FrameWorkDevelopment.pageObjects.PaymentsPage;
import wealus.FrameWorkDevelopment.pageObjects.ProductsHomePage;

public class FrameWork extends BaseTest {
	String input = "ADIDAS ORIGINAL";

	@Test(dataProvider = "getData")
	public void AddingPoductToCart(HashMap<String, String> dataInput) {
		

		ProductsHomePage productsHomePage = landingPage.loginToApplication(dataInput.get("email"),
				dataInput.get("password"));
		List<WebElement> products = productsHomePage.getProducts();

		CartPage cartPage = productsHomePage.addProductToCart(products, dataInput.get("productInput"));
		cartPage.goToCart();
		List<WebElement> cartSection = cartPage.getCartList();
		PaymentsPage paymentsPage = cartPage.verifyProductAndClickBuyNow(cartSection, dataInput.get("productInput"));
		String country = "India";
		paymentsPage.selectCountry(country);
		paymentsPage.clickPlaceOrder();
		boolean status = paymentsPage.verifySuccessMessage();
		Assert.assertTrue(status);

	}

	@Test(dependsOnMethods = { "AddingPoductToCart" })
	public void verifyOrderHistory() {

		ProductsHomePage productsHomePage = landingPage.loginToApplication("demo123@gmail.com", "Password@123");
		OrdersPage ordersPage = productsHomePage.clickOrders();
		boolean status = ordersPage.verifyOrderIsPresent(input);
		Assert.assertTrue(status);

	}

	@DataProvider
	public Object[][] getData() throws IOException {
		String filePath = System.getProperty("user.dir")
				+ "\\src\\test\\java\\wealus\\FrameWorkDevelopment\\dataProvider\\FrameWork.json";

		List<HashMap<String, String>> data = getJsonDataToMap(filePath);
		Object[][] Credentials = new Object[][] { { data.get(0) }, { data.get(1) }, { data.get(2) } };
		return Credentials;
	}

	/*
	 * @DataProvider public Object[][] getData() { Object[][] Credentials = new
	 * Object[][] { { "demo123@gmail.com", "Password@123", "ADIDAS ORIGINAL" }, {
	 * "dummyuser@gmail.com", "Password@123", "ADIDAS ORIGINAL" } };
	 * 
	 * return Credentials; }
	 */

	/*
	 * @DataProvider public Object[][] getData() {
	 * 
	 * HashMap<Object, Object> map = new HashMap<Object, Object>(); map.put("email",
	 * "demo123@gmail.com"); map.put("password", "Password@123");
	 * map.put("productInput", "ADIDAS ORIGINAL");
	 * 
	 * HashMap<Object, Object> map1 = new HashMap<Object, Object>();
	 * map1.put("email", "dummyuser@gmail.com"); map1.put("password",
	 * "Password@123"); map1.put("productInput", "ADIDAS ORIGINAL"); Object[][]
	 * Credentials = new Object[][] {{map},{map1}}; return Credentials; }
	 */
}
