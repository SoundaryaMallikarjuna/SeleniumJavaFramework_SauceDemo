package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import library.BaseClass;

public class LoginPage extends BaseClass {
	@FindBy(xpath = "//div[@class='login_logo']") private WebElement logo;
	@FindBy(id = "user-name") private WebElement un;
	@FindBy(id = "password") private WebElement pwd;
	@FindBy(id = "login-button") private WebElement loginbutton;
	@FindBy(xpath = "//div[@class='error-message-container error']") private WebElement errormsg;
	@FindBy(xpath = "//div[@class='app_logo']") private WebElement applogo;
	@FindBy(xpath = "//div[text()='Sauce Labs Backpack']") private WebElement backPackItem;
	@FindBy(id = "add-to-cart-sauce-labs-backpack") private WebElement backPackAddToCart;
	@FindBy(xpath = "//a[@class='shopping_cart_link']") private WebElement shoppingCart;
	@FindBy(id = "checkout") private WebElement checkoutBtn;
	@FindBy(id = "first-name") private WebElement firstname;
	@FindBy(id = "last-name") private WebElement lastname;
	@FindBy(id = "postal-code") private WebElement postalcode;
	@FindBy(id = "continue") private WebElement continueBtn;
	
	
	public LoginPage(WebDriver driver) // Constructor name is same as Classname
	{
		PageFactory.initElements(driver, this); // this driver will intialize this method
	}

	public String verifyURL() throws Exception {
		return driver.getCurrentUrl();
		
	}

	public boolean verifyLogo() {
		return logo.isDisplayed();
	}
	public String verifyPageTitle() {
		return driver.getTitle();
	}
	public void enterCredentials(String Username, String Password) {
		if (Username != null && !Username.isEmpty()) {
	        un.clear();
	        un.sendKeys(Username);
	    } else {
	        System.out.println("Username is null or empty");
	    }

	    if (Password != null && !Password.isEmpty()) {
	        pwd.clear();
	        pwd.sendKeys(Password);
	    } else {
	        System.out.println("Password is null or empty");
	    }
	    loginbutton.click();
		}
	
	
	public String getErrorMsg() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    wait.until(ExpectedConditions.visibilityOf(errormsg));
		return errormsg.getText();
	}
	
	public boolean verifyAppLogo() {
		 System.out.println("Inside verifyAppLogo method");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    wait.until(ExpectedConditions.visibilityOf(applogo));
	    System.out.println("App logo is verified");
		 return applogo.isDisplayed();
		 
	}
	
	public boolean verifybackPackItem() {
	 return backPackItem.isDisplayed();
	}
	public void verifybackPackAddToCart() {
		backPackAddToCart.click();
	}
	
	public void verifyshoppingCart() {
		shoppingCart.click();
	
	}
	
	public void verifycheckoutBtn() {
		checkoutBtn.click();
	}

	
}
