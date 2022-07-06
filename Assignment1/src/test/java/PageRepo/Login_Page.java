package PageRepo;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import GenericFile.Resources_Utility;

public class Login_Page extends BasePage {

	@Test(enabled = true, priority = 1)																			
	public void navigation_To_Site() {

		try {
			test=extent.createTest("Login Page");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(Resources_Utility.config("implicit.wait")),
					TimeUnit.SECONDS);

			driver.get(Resources_Utility.config("FB_URL"));											// Here we will navigate to the site		
			test.pass("Actual URL of page is - "+Resources_Utility.config("FB_URL"));
			Assert.assertTrue(true);
		}catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	@Test(enabled = true, priority = 2)
	public void url_Matching() {																		//Matching the URL of site opened
		try {
			current_URl = driver.getCurrentUrl();
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.urlToBe(actual_URl));

			verifyEquals(current_URl, actual_URl, driver);
			test.pass("Actual URL of page is - "+current_URl);
			System.out.println("URL Matched successfully");
			Assert.assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	@Test(enabled = true, priority = 3)
	public void login_To_Fb() throws Throwable {																	//login to fb
		try {
			type(By.id(Resources_Utility.xpath("email_ByID")), Resources_Utility.config("username"), driver);
			type(By.id(Resources_Utility.xpath("password_ByID")), Resources_Utility.config("password"), driver);
			click(By.name(Resources_Utility.xpath("login_ByName")), driver);
			sleep(3000);
			test.pass("Logged into Facebook");
			Assert.assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	@Test(enabled = true, priority = 4)
	public void write_Status() throws Throwable {															//write status
		try {
			click(By.xpath(Resources_Utility.xpath("status_Area_ByXpath")), driver);
			type(By.xpath(Resources_Utility.xpath("write_Status_ByXpath")), Resources_Utility.config("status"), driver);
			click(By.xpath(Resources_Utility.xpath("post_Status_ByXpath")), driver);
			test.pass("Status posted");
			Assert.assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	@Test(enabled = true, priority = 5)
	public void check_Status() throws Throwable {														//Check the status on new feed
		try {
			String status = driver.findElement(By.xpath("(//*[contains(text(), '"+Resources_Utility.config("status")+"')])[1]")).getText();
			System.out.println(status);

			Assert.assertEquals(status, Resources_Utility.config("status"));
			test.pass("Status matched!");
			Assert.assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}	
}