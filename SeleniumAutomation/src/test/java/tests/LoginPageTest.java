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
	int TCID;
	//ring username,password, scenario, result;

	@BeforeMethod
	public void setup() {
		login = new LoginPage(driver);
	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) // screenshot is taken when testcase is failed
		{
			UtilityClass.captureScreenshot(TCID);
		}
		driver.quit();
	}

	@Test
	public void verifyURL() throws Exception {
		TCID = 1;
		String actualURL = login.verifyURL();
		String expectedURL = UtilityClass.readPFData("url");
		Assert.assertEquals(actualURL, expectedURL);

	}

	@Test
	public void verifyLogo() {
		TCID = 2;
		boolean actualLogo = login.verifyLogo();
		boolean expectedLogo = true;
		Assert.assertEquals(actualLogo, expectedLogo);

	}

	@Test
	public void verifyPageTitle() {
		TCID = 3;
		login.verifyPageTitle();

	}
	
	@Test(dataProvider = "credentials", dataProviderClass = UtilityClass.class)
	public void verifyLoginFunctionality(String scenario, String username, String password, String result)
			throws Exception {
		login.enterCredentials(username, password);
		if (scenario != null && scenario.equals("invalid")) {
			TCID = 4;
			String actualResult = login.getErrorMsg();
			String expectedResult = result;
			Assert.assertEquals(actualResult, expectedResult);
		} else if (scenario != null && scenario.equals("invalidun")) {
			TCID = 5;
			String actualResult = login.getErrorMsg();
			String expectedResult = result;
			Assert.assertEquals(actualResult, expectedResult);
		} else if (scenario != null && scenario.equals("invalidpwd")) {
			TCID = 6;
			String actualResult = login.getErrorMsg();
			String expectedResult = result;
			Assert.assertEquals(actualResult, expectedResult);
		} else if (scenario != null && scenario.equals("blank")) {
			TCID = 7;
			String actualResult = login.getErrorMsg();
			String expectedResult = result;
			Assert.assertEquals(actualResult, expectedResult);
		}else if(scenario != null && scenario.equals("valid")) {
			TCID =8;
			String actualResult = login.verifyURL();
			String expectedResult = result;	
		    Assert.assertEquals(actualResult, expectedResult);
			}	
	}

	@Test(dataProvider = "credentials", dataProviderClass = UtilityClass.class)
	public void verifyItemAdded(String username, String password, String scenario,String result) throws Exception {
		if (scenario != null && scenario.equals("valid")) 
		{TCID =9;
			login.enterCredentials(username, password);
            boolean isLogoDisplayed = login.verifyAppLogo();
            Assert.assertTrue(isLogoDisplayed, "App logo is not displayed after logging in with valid credentials.");
            
            boolean isItemDisplayed = login.verifybackPackItem();
            Assert.assertTrue(isItemDisplayed, "BackPack item is not displayed after logging in with valid credentials.");
            
            login.verifybackPackAddToCart();
            System.out.println("Backpack item added to cart");
            
            login.verifyshoppingCart();
            System.out.println("Item added to the cart");

            // Proceed to checkout
            login.verifycheckoutBtn();
            System.out.println("Checkout button clicked");
            
        } else {
            System.out.println("Test skipped for invalid scenario: " + scenario);
        }
    }
}

	


