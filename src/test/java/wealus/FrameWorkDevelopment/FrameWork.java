package wealus.FrameWorkDevelopment;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.asynchttpclient.webdav.WebDavResponse;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import wealus.FrameWorkDevelopment.pageObjects.CartPage;
import wealus.FrameWorkDevelopment.pageObjects.LandingPage;
import wealus.FrameWorkDevelopment.pageObjects.PaymentsPage;
import wealus.FrameWorkDevelopment.pageObjects.ProductsHomePage;

public class FrameWork extends BaseTest {

	@Test
	public void AddingPoductToCart() throws InterruptedException, IOException {

		LandingPage landingPage = launchApplication();
		ProductsHomePage productsHomePage = landingPage.loginToApplication("dummyuser@gmail.com", "Password@123");
		List<WebElement> products = productsHomePage.getProducts();
		String input = "ADIDAS ORIGINAL";
		CartPage cartPage = productsHomePage.addProductToCart(products, input);
		cartPage.goToCart();
		List<WebElement> cartSection = cartPage.getCartList();
		PaymentsPage paymentsPage = cartPage.verifyProductAndClickBuyNow(cartSection, input);
		String country = "India";
		paymentsPage.selectCountry(country);
		paymentsPage.clickPlaceOrder();
		boolean status = paymentsPage.verifySuccessMessage();
		Assert.assertTrue(status);
		d.close();
	}
}
