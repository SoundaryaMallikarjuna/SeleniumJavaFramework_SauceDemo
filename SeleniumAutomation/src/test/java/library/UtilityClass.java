package library;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.DataProvider;

public class UtilityClass extends BaseClass 
{
	public static String readPFData(String key) throws IOException { //PF means PropertyFile
		FileInputStream file=new FileInputStream(System.getProperty("user.dir")+"//src/test/resources/config/config.properties");
		Properties prop=new Properties();
		prop.load(file);
		String value=prop.getProperty(key);
		return value;
	}
	
public static void captureScreenshot() throws IOException {
	String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	File destination=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	File src=new File(System.getProperty("user.dir")+"//TestCaseScreenshots//_" +timestamp+ ".jpg");
	FileHandler.copy(destination, src);
}

@DataProvider(name="credentials")
public static Object[][] getData(Method m) throws IOException
{	
	Object[][] data=null;
	System.out.println("Current Test Method: " +m.getName());
	
	if(m.getName().equals("verifyItemAddedOrderPlaced"))
	{
			ExcelUtility ex=new ExcelUtility(System.getProperty("user.dir")+"//TestData//Testdata.xlsx");
			data=ex.singleRow("checkoutInfoData");		
	}
	else if(m.getName().equals("verifyLoginFunctionality"))
	{
		ExcelUtility ex=new ExcelUtility(System.getProperty("user.dir")+"//TestData//Testdata.xlsx");
		data=ex.twoDArray("loginCredentials");
	}	
	else if(m.getName().equals("verifyCheckoutInfo"))
	{
		ExcelUtility ex=new ExcelUtility(System.getProperty("user.dir")+"//TestData//Testdata.xlsx");
		data=ex.singleRow("loginCredentials");
	}
	return data;	
}
}
