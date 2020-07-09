package Base;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.PropertiesConfigurationLayout;
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
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class commonComponents extends Constants {

        public static WebDriver driver;
        public static WebElement element;
    	public static WebDriverWait wait;
    	public static boolean bStatus;
		public static PropertiesConfiguration config = null;
	    public static Map<String, String> value= new LinkedHashMap<>();
		public static  String xpath;
        public static void launchBrowser(String url){
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
        
        
        public static boolean clickButton(String xpath)
    	{
    		try
    		{
    			driver.findElement(By.xpath(xpath)).click();
    			return true;
    		}
    		catch(Exception ex)
    		{
    			return false;
    		}
    	}
        
        public static void insertText(String xpath, String value)
    	{
    		try {
				driver.findElement(By.xpath(xpath)).clear();
    			driver.findElement(By.xpath(xpath)).sendKeys(value);
    		}
    		catch (Exception e) 
    		{
    			System.out.println(e);
    		}
    	}
        
        public static void waitForElementVisible(String element,long timeOut) //Wait method for Element visibility
    	{
    		try
    		{
    			wait = new WebDriverWait(driver, timeOut);
    			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(element)));
    		}
    		catch(Exception e)
    		{
    			System.out.println(e.getMessage());
    		}
    	}
        
        
        public static void clickLink(String xpath)
    	{
    		try
    		{
    			commonComponents.waitForElementVisible(xpath, 30);
    			driver.findElement(By.xpath(xpath)).click();
    		}
    		catch(Exception ex)
    		{
    			System.out.println(ex);
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

	public static PropertiesConfigurationLayout propertyFileReader() throws IOException {
		String propFileName = Constants.Property_File;
		try {
			config = new PropertiesConfiguration(propFileName);
		}
		catch (ConfigurationException e)
		{
			e.printStackTrace();
		}
		PropertiesConfigurationLayout layout = config.getLayout();
		return config.getLayout();
	}

	public static void performAction(PropertiesConfigurationLayout layout)
	{
		Set<String> keys = layout.getKeys();
		for (String key: keys) {
			String[] arrOfStr = layout.getConfiguration().getProperty(key).toString().split(";");
			for (int i = 0; i < arrOfStr.length; i++) {
				String[] data = arrOfStr[i].split("\\|");
				value.put(data[0].toLowerCase().trim(), data[1].trim());
			}
			switch (value.get(Action).toLowerCase()) {
				case Click:
					clickButton(value.get(Xpath));
					break;
				case EnterText:
					insertText(value.get(Xpath), value.get(Data));
					break;
			}
		}
	}
}

