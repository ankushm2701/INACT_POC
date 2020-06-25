package Base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

public class commonComponents {

        public static WebDriver driver;
        public static WebElement element;
    	public static WebDriverWait wait;
    	public static boolean bStatus;

        public void launchBrowser(String url){
            try {
                DesiredCapabilities capabilities= DesiredCapabilities.chrome();
                ChromeOptions options=new ChromeOptions();
                options.addArguments("incognito");
                options.addArguments("--disable-notifications");
                capabilities.setCapability(ChromeOptions.CAPABILITY, options);

                System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_EXE);
                driver=new ChromeDriver(options);
            } catch (Exception e){
                System.out.println("Exception in Chrome Browser");
                e.printStackTrace();
                Assert.fail("Error opening Browser");
            }
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            driver.navigate().to(url);
        }
        
        
        public static boolean clickButton(By locator)
    	{
    		try
    		{
    		driver.findElement(locator).click();
    		return true;
    		}
    		catch(Exception ex)
    		{
    			return false;
    			
    		}
    	}
        
        public static void insertText(By locator, String value)
    	{
    		try {
    			driver.findElement(locator).sendKeys(value);
    		}
    		catch (Exception e) 
    		{
    			System.out.println(e);
    		}
    	}
        
        public static boolean waitForElementVisible(By locator,long timeOut) //Wait method for Element visibility 
    	{
    		try
    		{
    			wait = new WebDriverWait(driver, timeOut);
    			element = wait.until(ExpectedConditions.elementToBeClickable(locator));
    			if(element!=null)
    				return true;
    			else
    				return false;
    		}
    		catch(Exception e)
    		{
    			return false;
    		}
    	}
        
        
        public static boolean AssertContains(By locator, String ExpectedValue) 
  	  {
  		  try
  		  {
  			  String elementText = driver.findElement(locator).getText();
  			  assertTrue(elementText.contains(ExpectedValue));
  			  return true;
  		  }
  		  catch (Exception e) 
  		  {
  			  System.out.println(e);
  			  return false;
  		  }
  	  }
}

