package PageRepo;

import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import GenericFile.Resources_Utility;
import GenericFile.selenium_utility;

public class HomePage extends selenium_utility {

	private static String driverpath = System.getProperty("user.dir") + "\\src\\test\\resources\\driver\\";
	public static WebDriver driver = null;
	public static String actual_SignUp_URL="https://wallethub.com/join/light";
	public static String actual_Profile_URl = "https://wallethub.com/profile/13732055i";
	public String current_URl;
	public static ExtentTest test;
	public static ExtentSparkReporter htmlReporter;
	public static ExtentReports extent;

	@BeforeSuite
	public void startup() {
		try {
			if (Resources_Utility.config("browser").equals("chrome")) {											//Demonstration for multiple browsers selection

				System.setProperty("webdriver.chrome.driver", driverpath + "chromedriver.exe");					//Setting the chromedriver
				driver = new ChromeDriver();
			} else if (Resources_Utility.config("browser").equals("ie")) {										//Setting the IE browser

				System.setProperty("webdriver.ie.driver", driverpath + "IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@BeforeTest
	public void setExtent() {

		Date d = new Date();
		String filename = "Extent_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";

		ExtentSparkReporter htmlReporter = new ExtentSparkReporter(
				System.getProperty("user.dir") + "\\reports\\" + filename);

		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("Taxmann Reports");
		htmlReporter.config().setReportName("Automation Test reults");
		htmlReporter.config().setTheme(Theme.STANDARD);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Automation Tester", "Lovepreet");
		extent.setSystemInfo("Organization", "Wallet Hub");
		extent.setSystemInfo("Build No", "WH-1");

	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {

		if (result.getStatus() == ITestResult.FAILURE) {

			String methodName = result.getMethod().getMethodName();
			String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " FAILED";
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
			test.pass(m);

			String screenshotPath = takeScreenShot(result.getName());
			test.fail("<b><font color=red>" + "Screenshot of failure" + "</font></b><br>",
					MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());

		} else if (result.getStatus() == ITestResult.SKIP) {

			test.log(Status.SKIP, "Test Case SKIPPED IS " + result.getName());

		} else if (result.getStatus() == ITestResult.SUCCESS) {

			String methodName = result.getMethod().getMethodName();
			String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " PASSED";
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
			test.pass(m);
		}
	}

	@AfterTest
	public void endReport() {
		extent.flush();
	}

	@AfterSuite
	public void browser_Closure() {																				
		driver.quit();																								//Closing all the tabs
	}
}