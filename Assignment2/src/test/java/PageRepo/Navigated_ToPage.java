package PageRepo;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import GenericFile.Resources_Utility;

public class Navigated_ToPage extends HomePage {

	public static String email_Id;
	public static Random rand = new Random();

	@Test(enabled = true, priority = 1)																	
	public void navigation_To_Site() {

		try {
			test=extent.createTest("Navigate to Sign-up Page");										
			driver.manage().window().maximize();													// Maximizing window	
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(Resources_Utility.config("implicit.wait")),	
					TimeUnit.SECONDS);

			driver.get(Resources_Utility.config("sign_Up_URL"));									// Here we will navigate to the site
			test.pass("Actual URL of page is - "+Resources_Utility.config("sign_Up_URL"));
			Assert.assertTrue(true);
		}catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	@Test(enabled = true, priority = 2)
	public void url_Matching() {																		
		try {
			current_URl = driver.getCurrentUrl();														//getting current url
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.urlToBe(actual_SignUp_URL));													

			verifyEquals(current_URl, actual_SignUp_URL, driver);										//Matching the URL of site opened
			test.pass("Actual URL of page is - "+current_URl);
			System.out.println("URL Matched successfully");
			Assert.assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	@Test(enabled = true, priority = 3)
	public void sign_Up() throws Throwable {

		try {
			email_Id = "taxmanntester++" + rand.nextInt(1000) + "@gmail.com";

			type(By.name(Resources_Utility.xpath("email_ByName")), email_Id, driver);				//enter email id
			type(By.name(Resources_Utility.xpath("password_ByName")), Resources_Utility.config("password"), driver);	//enter password
			type(By.name(Resources_Utility.xpath("confirmPass_ByName")), Resources_Utility.config("password"), driver);	//enter confirm password

			click(By.id(Resources_Utility.xpath("uncheck_ById")), driver);							//uncheck the check box
			click(By.xpath(Resources_Utility.xpath("join_ByXpath")), driver);						//click on join
			element_Displayed(By.xpath(Resources_Utility.xpath("thankYou_ByXpath")), "Thank You", driver);	//looking for thank you text

			test.pass("signed up with id - "+email_Id);
			Assert.assertTrue(true);
		}catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	@Test(enabled = false, priority = 4)
	public void login() throws Throwable {

		try {
			click(By.xpath(Resources_Utility.xpath("login_Tab_ByXpath")), driver);					//click on login option
			type(By.name(Resources_Utility.xpath("email_ByName")), Resources_Utility.config("email_Id"), driver);	//enter email id
			type(By.name(Resources_Utility.xpath("password_ByName")), Resources_Utility.config("password"), driver);//enter password

			click(By.xpath(Resources_Utility.xpath("login_ByXpath")), driver);						//click on login button
			element_Displayed(By.xpath(Resources_Utility.xpath("edit_Profile_ByXpath")), "Edit Profile", driver);	//looking for edit profile option

			test.pass("signed up with id - "+email_Id);
			Assert.assertTrue(true);
		}catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	@Test(enabled = true, priority = 5)
	public void navigate_To_Profile() throws Throwable {

		try {
			driver.get(Resources_Utility.config("profile_URL"));							//navigate to profile url given in assignment
			test.pass("Navigated to profile url");
			Assert.assertTrue(true);
		}catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}	

	@Test(enabled = true, priority = 6)
	public void hoverOver_Stars() throws Throwable {																				
		try {

			scrollbyElement(null, 400, driver);									// scroll down upto review option
			Actions action = new Actions(driver);	

			for(int i=1; i<5; i++) {

				sleep(1000);
				action.moveToElement(driver.findElement(By.xpath("//*[local-name()='svg' and @role='radio']["+i+"]"))).perform();
				if(i==4) {
					click(By.xpath("//*[local-name()='svg' and @role='radio']["+i+"]"), driver);
				}
			}

			click(By.xpath(Resources_Utility.xpath("dropdown_ByXpath")), driver);				//click on dropdown
			sleep(1000);

			click(By.xpath(Resources_Utility.xpath("healthInsurance")), driver);				//choose health insurance option
			click(By.xpath(Resources_Utility.xpath("write_Review")), driver);					//click on review	
			type(By.xpath(Resources_Utility.xpath("write_Review")), Resources_Utility.config("Review"), driver);	//write review
			test.pass("Review typed");

			click(By.xpath(Resources_Utility.xpath("submit")), driver);							// click on submit
			test.pass("Review Updated");
			Assert.assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
}