package wealus.FrameWorkDevelopment.commonComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class commonMethods {
	public WebDriver d;
	public WebDriverWait wait;

	public commonMethods(WebDriver d) {
		this.d = d;
		wait = new WebDriverWait(d, Duration.ofSeconds(10));

	}

	public void waituntilElementsToBeVisible(By locator) {

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	public void waituntilElementToBeVisible(By locator) {

		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void waitForElemetToBrPresent(WebElement ele) {
		wait.until(ExpectedConditions.visibilityOf(ele));

	}

}
