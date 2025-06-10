package tests;
import java.io.IOException;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import library.BaseClass;
import library.UtilityClass;
import pages.LoginPage;

public class LoginPageTest extends BaseClass {
	LoginPage login;
	
	@BeforeMethod
	public void setup() {
		login = new LoginPage(driver);
	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.SUCCESS) 
		{
			UtilityClass.captureScreenshot();
		}
		else if(result.getStatus() == ITestResult.FAILURE) 
		{
			UtilityClass.captureScreenshot();
		}
	driver.quit();
	}
	
	@Test(dataProvider = "credentials", dataProviderClass = UtilityClass.class)
	public void verifyItemAddedOrderPlaced(String scenario, String username, String password, String result, String fname, String lname, String pstlcode) throws Exception {
		System.out.println("If Scenario is "+scenario);
		if (scenario.trim().equalsIgnoreCase("valid"))
		{
			login.enterCredentials(username, password);
			System.out.println("HomePage is displayed");   
            boolean isLogoDisplayed = login.verifyAppLogo();
            Assert.assertTrue(isLogoDisplayed, "App logo is not displayed after logging in with valid credentials.");
            System.out.println("AppLogo is verified");  
            login.verifybackPackItem();             
            login.verifybackPackAddToCart();
            login.verifyshoppingCart();
            System.out.println("Sauce Labs Backpack item is added to the cart");    
            login.verifycheckoutBtn(); 
            System.out.println("Checkout button is clicked");    
            login.enterCheckoutInfo(fname, lname, pstlcode);
            login.verifycontineBtn();
            System.out.println("Checkout information is entered and clicked on continue button");    
            login.verifyfinishBtn();   
        } else {
           System.out.println("Test skipped for invalid scenario: " + scenario);
        }
	}

	@Test
	public void verifyURL() throws Exception {
		String actualURL = login.verifyURL();
		String expectedURL = UtilityClass.readPFData("url");
		Assert.assertEquals(actualURL, expectedURL);

	}
	
	@Test(dataProvider = "credentials", dataProviderClass = UtilityClass.class)
	public void verifyLoginFunctionality(String scenario, String username, String password, String result)
			throws Exception {
		login.enterCredentials(username, password);
		if (scenario != null && scenario.equals("invalid")) {
			String actualResult = login.getErrorMsg();
			String expectedResult = result;
			Assert.assertEquals(actualResult, expectedResult);
		} else if (scenario != null && scenario.equals("invalidun")) {
			String actualResult = login.getErrorMsg();
			String expectedResult = result;
			Assert.assertEquals(actualResult, expectedResult);
		} else if (scenario != null && scenario.equals("invalidpwd")) {
			String actualResult = login.getErrorMsg();
			String expectedResult = result;
			Assert.assertEquals(actualResult, expectedResult);
		} else if (scenario != null && scenario.equals("blank")) {
			String actualResult = login.getErrorMsg();
			String expectedResult = result;
			Assert.assertEquals(actualResult, expectedResult);
	} 
			else if(scenario != null && scenario.equals("valid")) {
			String actualResult = login.verifyURL();
			String expectedResult = result;	
		    Assert.assertEquals(actualResult, expectedResult);
		}	
	}
		
	@Test(dataProvider = "credentials", dataProviderClass = UtilityClass.class)
	public void verifyCheckoutInfo(String scenario, String username, String password, String result) throws Exception {
		if (scenario.trim().equalsIgnoreCase("valid"))
		{
			login.enterCredentials(username, password);
            boolean isLogoDisplayed = login.verifyAppLogo();
            Assert.assertTrue(isLogoDisplayed, "App logo is not displayed after logging in with valid credentials.");
            login.verifybackPackItem();             
            login.verifybackPackAddToCart();
            login.verifyshoppingCart();
            System.out.println("Sauce Labs Backpack item is added to the cart");    
            login.verifycheckoutBtn(); 
            System.out.println("Checkout button is clicked"); 
            login.verifycontineBtn();
            login.getErrorMsg();   
		}
	  else {
         System.out.println("Test skipped for invalid scenario: " + scenario);
      }
	}
}
	


