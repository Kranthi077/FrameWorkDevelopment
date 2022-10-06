package wealus.FrameWorkDevelopment;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.filefilter.TrueFileFilter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import wealus.FrameWorkDevelopment.pageObjects.LandingPage;

public class BaseTest {
	WebDriver d = null;
	LandingPage landingPage;

	public WebDriver driverInitialization() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\Aishwarya Jangampeta\\Kranthi\\eclipse-workspace\\FrameWorkDevelopment\\src\\test\\java\\wealus\\FrameWorkDevelopment\\resources\\GlobalData.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome"))

		{
			WebDriverManager.chromedriver().setup();
			d = new ChromeDriver();
		}

		else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			d = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			d = new EdgeDriver();
		}
		d.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		d.manage().window().maximize();
		return d;
	}

	@BeforeMethod (alwaysRun = true)
	public LandingPage launchApplication() throws IOException {

		d = driverInitialization();
		landingPage = new LandingPage(d);
		d.get("https://rahulshettyacademy.com/client");
		return landingPage;
	}

	@AfterMethod (alwaysRun = true)
	public void closeBrowser() {
		d.close();

	}
}
