package GenericFile;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

//import com.relevantcodes.extentreports.LogStatus;

public class selenium_utility {

	public static String screenshotPath;
	public static String screenshotName;
	public static ArrayList<String> tabs;

	// Sleep comment
		public static void sleep(int nenoSecond) throws Throwable {

			Thread.sleep(nenoSecond);
		}

		// Explicit wait for element visibility
		public static void explicit_Wait(By by, int i, String pathName, WebDriver driver) {

			try {
				WebDriverWait wait = new WebDriverWait(driver, i);
				wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			} catch (Exception e) {
				System.out.println(
						"----- WAITED FOR " + i + " SECONDS FOR THE VISIBILITY OF '" + pathName + "', BUT NOT FOUND -----");
			}
		}

		// verify assert with logs and screen shot.
		public static void verifyEquals(String actual, String expected, WebDriver driver) throws IOException {

			try {
				Assert.assertEquals(actual, expected);
				System.out.println("('" + actual + "') is Equal to ('" + expected + "')");
				Assert.assertTrue(true);
			} catch (Throwable t) {

				Assert.assertTrue(false);
			}
		}

		// click method
		public static void click(By by , WebDriver driver) throws Throwable {
			try {
				driver.findElement(by).click();
				sleep(500);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public static void scrollbyElement(By by, int pixel, WebDriver driver) throws Throwable {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			try {
				if (by == null) {
					js.executeScript("window.scrollBy(0," + pixel + ")"); // Scrolling the window by pixels vertically
					sleep(3000);
				} else {
					WebElement element = driver.findElement(by);
					js.executeScript("arguments[0].scrollIntoView(true);", element);
					sleep(3000);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// Check Element Visibility
		public static boolean element_Displayed(By by, String element_Name, WebDriver driver) throws Throwable {
			boolean value = false;

			for (int i = 0; i < 2; i++) {
				try {
					value = driver.findElement(by).isDisplayed();
				} catch (Exception e) {
				}
			}
			if (value) {
				System.out.println("-- " + element_Name + " is displayed--");
			} else {
				System.out.println("-- " + element_Name + " is not displayed--");
			}
			return value;
		}

		// Explicit wait for the element to  be clickable
		public static void wait_Tobe_Clickable(By by, int seconds, String pathName, WebDriver driver) {

			try {
				WebElement elementClick =new WebDriverWait(driver, seconds).until(ExpectedConditions.elementToBeClickable(by));
				Actions action =new Actions(driver);
				action.moveToElement(elementClick).click().build().perform();
			}catch (Exception e) {
				System.out.println("----- WAITED FOR "+seconds+" SECONDS FOR THE VISIBILITY OF '"+pathName+"' TO BECOME CLICKABLE, BUT NOT FOUND -----");
			}
		}


		// type method
		public static void type(By by, String value, WebDriver driver) {

			try {
				driver.findElement(by).sendKeys(value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
}