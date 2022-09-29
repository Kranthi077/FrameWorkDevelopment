package wealus.FrameWorkDevelopment;

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

import io.github.bonigarcia.wdm.WebDriverManager;
import wealus.FrameWorkDevelopment.pageObjects.LandingPage;

public class FrameWork2 {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		WebDriver d = new ChromeDriver();
		d.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(10));
		d.manage().window().maximize();
		d.get("https://rahulshettyacademy.com/client");
		Thread.sleep(5000);
		d.findElement(By.id("userEmail")).sendKeys("dummyuser@gmail.com");
		d.findElement(By.id("userPassword")).sendKeys("Password@123");
		d.findElement(By.id("login")).click();
		Thread.sleep(5000);
		String input = "ADIDAS ORIGINAL";
		List<WebElement> products = d.findElements(By.xpath("//div[contains(@class,'md-0 ')]"));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(@class,'md-0 ')]")));
		String productName = "";
		for (WebElement product : products) {

			productName = product.findElement(By.tagName("b")).getText();
			System.out.println(productName);
			if (productName.equalsIgnoreCase(input)) {
				product.findElement(By.xpath(".//button[contains(text(),'Add To Cart')]")).click();
				System.out.println("done");
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Product Added To Cart')]")));
				d.findElement(By.xpath("//*[contains(text(), 'Product Added To Cart')]")).isDisplayed();
				break;
			}
		}

		d.findElement(By.xpath("//button[@routerlink=\"/dashboard/cart\"]")).click();

		List<WebElement> cartSection = d.findElements(By.className("infoWrap"));
		for (WebElement item : cartSection) {

			String text = item.findElement(By.xpath(".//div/h3")).getText();
			System.out.println(text);
			if (text.equalsIgnoreCase(input)) {
				item.findElement(By.xpath(".//div/button[text()='Buy Now']")).click();
				break;
			}
		}

		String country = "Sri Lanka";
		d.findElement(By.xpath("//*[@placeholder=\"Select Country\"]")).sendKeys(country);

		WebElement list = d.findElement(By.className("ta-results"));
		List<WebElement> results = list.findElements(By.tagName("button"));
		for (WebElement result : results) {
			String option = result.getText().trim();
			if (option.equals(country)) {
				System.out.println(option);
				result.click();
				break;
			}
		}
		d.findElement(By.className("action__submit")).click();
		String success = d.findElement(By.className("hero-primary")).getText();
		boolean status = success.equalsIgnoreCase("Thankyou for the order.");
		Assert.assertTrue(status);
		d.close();
	}
}
