package pages;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import library.BaseClass;

public class LoginPage extends BaseClass {
	@FindBy(xpath = "//div[@class='login_logo']")
	private WebElement logo;
	@FindBy(id = "user-name")
	private WebElement un;
	@FindBy(id = "password")
	private WebElement pwd;
	@FindBy(id = "login-button")
	private WebElement loginbutton;
	@FindBy(xpath = "//div[@class='error-message-container error']")
	private WebElement errormsg;
	@FindBy(xpath = "//div[@class='app_logo']")
	private WebElement applogo;
	@FindBy(xpath = "//div[text()='Sauce Labs Backpack']")
	private WebElement backPackItem;
	@FindBy(id = "add-to-cart-sauce-labs-backpack")
	private WebElement backPackAddToCart;
	@FindBy(xpath = "//a[@class='shopping_cart_link']")
	private WebElement shoppingCart;
	@FindBy(id = "checkout")
	private WebElement checkoutBtn;
	@FindBy(id = "continue")
	private WebElement continueBtn;
	@FindBy(id = "first-name")
	private WebElement firstname;
	@FindBy(id = "last-name")
	private WebElement lastname;
	@FindBy(id = "postal-code")
	private WebElement postalcode;
	@FindBy(id = "finish")
	private WebElement finish;
	@FindBy(xpath = "//h2[@class='complete-header']")
	private WebElement OrderPlaced;

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
//		
//		try {
//            // Wait for the alert to be present
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); 
//            wait.until(ExpectedConditions.alertIsPresent());
//
//            Alert alert = driver.switchTo().alert();
//            System.out.println("Alert text: " + alert.getText());
//            alert.accept();  
//        } catch (NoAlertPresentException e) {
//            System.out.println("No alert present");
//      } 
//            finally {
//            driver.quit();
//        }
	}

	public String getErrorMsg() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(errormsg));
		errormsg.isDisplayed();
		String s = errormsg.getText();
		if (s.contains("Error")) {
			System.out.println("Error message is displayed when checkout all information fields are not entered or left blank");
		} else {
			System.out.println("No error message is displayed");
		}

		return errormsg.getText();
	}

	public boolean verifyAppLogo() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(applogo));
		return applogo.isDisplayed();

	}

	public boolean verifybackPackItem() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(backPackItem));
		System.out.println(backPackItem.getText());
		return backPackItem.isDisplayed();
	}

	public void verifybackPackAddToCart() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(backPackAddToCart));
		backPackAddToCart.click();
	}

	public void verifyshoppingCart() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(shoppingCart));
		shoppingCart.click();

	}

	public void verifycheckoutBtn() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(checkoutBtn));
		checkoutBtn.click();
	}

	public void verifycontineBtn() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(continueBtn));
		continueBtn.click();
	}

	public void enterCheckoutInfo(String fname, String lname, String pstlcode) {
		firstname.clear();
		firstname.sendKeys(fname);
		lastname.clear();
		lastname.sendKeys(lname);
		postalcode.clear();
		postalcode.sendKeys(pstlcode);
	}

	public void verifyfinishBtn() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,350)", "");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(finish));
		finish.click();
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(OrderPlaced));

		if (OrderPlaced.getText().contains("Thank you for your order!")) {
			System.out.println("Finish button is clicked and order id placed successfully");
		} else {
			System.out.println("Order is cancelled/not placed successfully");
		}

	}
}
