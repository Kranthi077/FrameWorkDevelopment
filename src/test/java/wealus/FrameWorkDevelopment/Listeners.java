package wealus.FrameWorkDevelopment;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import wealus.FrameWorkDevelopment.resources.ExtentReportTestNG;

public class Listeners extends BaseTest implements ITestListener {

	ExtentReports extent = ExtentReportTestNG.config();

	ExtentTest test;

	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);// unique thread id-1(incorrectUserPassedTest)->test
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		//test.log(Status.PASS, "Test Passed");
		extentTest.get().log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().fail(result.getThrowable());
		extentTest.get().fail("Test Failed");
		extentTest.get().log(Status.FAIL, "Test Failed");
		// screenshot

		try {
			d = (WebDriver) result.getTestClass().getRealClass().getField("d").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}

		String file = null;
		try {
			file = getScreenshot(result.getMethod().getMethodName(), d);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// attach screenshot to the extent report
		extentTest.get().addScreenCaptureFromPath(file, result.getMethod().getMethodName());

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
	}

}
