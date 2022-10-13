package extentReportsemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExtentReportsDemo {
	ExtentReports extent;

	@BeforeTest
	public void config() {

		// ExtentReports // ExtentSparkReporter

		String path = System.getProperty("user.dir") + "\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("FrameWork Results");
		reporter.config().setDocumentTitle("Selenium with TestNG");

		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Kranthi");

	}

	@Test
	public void sampleTest() {

		ExtentTest test = extent.createTest("Amazon URL Launch");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		test.log(Status.PASS, "Driver initialization");
		driver.get("https://www.amazon.in/");
		test.log(Status.PASS, "url launch");
		System.out.println(driver.getTitle());
		test.log(Status.PASS, "get Title");
		driver.close();
		test.log(Status.PASS, "browser closed");
		test.fail("I failed this test case");
		extent.flush();

	}

	@Test
	public void sampleTest2() {

		ExtentTest test = extent.createTest("Amazon URL Launch failed");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		test.log(Status.PASS, "Driver initialization");
		driver.get("https://www.amazon.in/");
		test.log(Status.PASS, "url launch");

		if (driver.getTitle().equals("abc")) {
			test.log(Status.PASS, "get Title");
		}else {
			test.log(Status.FAIL, "get Title");
		}

		driver.close();
		test.log(Status.PASS, "browser closed");
		test.fail("I failed this test case");
		extent.flush();

	}

}
