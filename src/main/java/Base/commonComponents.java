package Base;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.PropertiesConfigurationLayout;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import static org.testng.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import Base.EnumActions.*;
import locator.locator.UILocator;

public class commonComponents extends Constants {

        public static WebDriver driver;
		public static PropertiesConfiguration config = null;
		public static PropertiesConfigurationLayout keyConfig = null;
		public static  String xpath;
		public static  String[] KeyWords ;
		static ExtentTest logger;
		static ExtentReports report;
		
		@BeforeClass
		public static void startTest()
		{
		report = new ExtentReports(System.getProperty("user.dir")+"\\ExtentReportResults.html");
		logger = report.startTest("Intac");
				}
		
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
        
        
        public static void clickButton(LocatorType locatorType, String element)
    	{
			JavascriptExecutor script=(JavascriptExecutor)driver;
				switch (locatorType) {
					case Xpath:
						waitUntilCondition(10, EnumActions.ExpectedElementCondition.ElementToBeClickable, LocatorType.Xpath, element, "");
						script.executeScript("arguments[0].click();", driver.findElement(By.xpath(element)));
						break;
					case CssSelector:
						waitUntilCondition(10, EnumActions.ExpectedElementCondition.ElementToBeClickable, LocatorType.CssSelector, element, "");
						script.executeScript("arguments[0].click();", driver.findElement(By.cssSelector(element)));
						break;
				}
    	}
    	public static  void clickDropDown(LocatorType locatorType, String element)
		{
			JavascriptExecutor script=(JavascriptExecutor)driver;
			Actions action = new Actions(driver);
			switch (locatorType) {
				case Xpath:
					waitUntilCondition(10, EnumActions.ExpectedElementCondition.ElementToBeClickable, LocatorType.Xpath, element, "");
					script.executeScript("arguments[0].click();", driver.findElement(By.xpath(element)));
					action.moveToElement(driver.findElement(By.xpath(element))).click(driver.findElement(By.xpath(element))).build().perform();
					break;
				case CssSelector:
					waitUntilCondition(10, EnumActions.ExpectedElementCondition.ElementToBeClickable, LocatorType.CssSelector, element, "");
					script.executeScript("arguments[0].click();", driver.findElement(By.xpath(element)));
					action.moveToElement(driver.findElement(By.xpath(element))).click(driver.findElement(By.xpath(element))).build().perform();
					break;
			}
		}
    	
    	public static void clearingFields(LocatorType locatorType, String element)
    	{
    		try {
    			switch (locatorType) {
					case Xpath:
					waitUntilCondition(10, EnumActions.ExpectedElementCondition.VisibilityOfElement, LocatorType.Xpath, element, "");
					driver.findElement(By.xpath(element)).sendKeys(Keys.chord(Keys.CONTROL+ "a"));
					driver.findElement(By.xpath(element)).sendKeys(Keys.chord(Keys.DELETE));
					break;
					case CssSelector:
					waitUntilCondition(10, EnumActions.ExpectedElementCondition.VisibilityOfElement, LocatorType.CssSelector, element, "");
					driver.findElement(By.xpath(element)).sendKeys(Keys.chord(Keys.CONTROL+ "a"));
					driver.findElement(By.xpath(element)).sendKeys(Keys.chord(Keys.DELETE));
					break;
				}
    		}
    		catch (Exception e) 
    		{
    			System.out.println(e);
    		}
    	}
        
        
        public static void insertText(LocatorType locatorType, String element, String value)
    	{
    		try {
    			switch (locatorType) {
					case Xpath:
					waitUntilCondition(10, EnumActions.ExpectedElementCondition.VisibilityOfElement, LocatorType.Xpath, element, "");
					driver.findElement(By.xpath(element)).clear();
					driver.findElement(By.xpath(element)).sendKeys(value);
					break;
					case CssSelector:
					waitUntilCondition(10, EnumActions.ExpectedElementCondition.VisibilityOfElement, LocatorType.CssSelector, element, "");
					driver.findElement(By.cssSelector(element)).clear();
					driver.findElement(By.cssSelector(element)).sendKeys(value);
					break;
				}
    		}
    		catch (Exception e) 
    		{
    			System.out.println(e);
    		}
    	}
        
        public static void clickLink(LocatorType locatorType, String element)
    	{
    		try
    		{
    			switch (locatorType) {
					case Xpath:
						waitUntilCondition(10, EnumActions.ExpectedElementCondition.ElementToBeClickable, LocatorType.Xpath, element, "");
						driver.findElement(By.xpath(element)).click();
						break;
					case CssSelector:
						waitUntilCondition(10, EnumActions.ExpectedElementCondition.ElementToBeClickable, LocatorType.CssSelector, element, "");
						driver.findElement(By.cssSelector(element)).click();
						break;
				}
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

	public static  void propertyFileReader(String fileType) throws InterruptedException, ConfigurationException, IOException {
		BufferedReader br = null;
		String line ="";
		String cvsSplitBy = ";";
		if(fileType == "csv") {
			br = new BufferedReader(new FileReader(Constants.KEY_WORDS));
			while ((line = br.readLine()) != null) {
				KeyWords = line.split(cvsSplitBy);
				for (String fileName : KeyWords) {
					config = new PropertiesConfiguration(Constants.FILE_PATH + "/" + fileName + ".properties");
					requiredActionToPerform(config.getLayout());
				}
			}
		}else {
			keyConfig = new PropertiesConfiguration(Constants.FILE_PATH + "/Keyword.properties").getLayout();
			Set<String> keys = keyConfig.getKeys();
			for (String key : keys) {
				KeyWords = keyConfig.getConfiguration().getProperty(key).toString().split(";");
				for (String fileName : KeyWords) {
					config = new PropertiesConfiguration(Constants.FILE_PATH + "/" + fileName + ".properties");
					requiredActionToPerform(config.getLayout());
				}
			}
		}
	}

	public static void requiredActionToPerform(PropertiesConfigurationLayout layout) throws InterruptedException
	{
		UILocator xpathFinder = new UILocator();
		String xPathvalue = null;
		Set<String> keys = layout.getKeys();
		for (String key: keys) {
			String[] arrOfStr = layout.getConfiguration().getProperty(key).toString().split(";");
			Map<String, String> value= new LinkedHashMap<>();
			for (int i = 0; i < arrOfStr.length; i++) {
				String[] data = arrOfStr[i].split("\\|");
				//value.put(data[0].toLowerCase(),data[1]);
				value.put(data[0].toLowerCase().trim(), data[1].trim());
				logger.log(LogStatus.PASS,key, data[0]+"="+data[1] );
			}
			if(value.containsKey(URL)) {
				launchBrowser(value.get(URL));
			}
			else {
				try {
					if(value.containsKey("displaytext"))
					xPathvalue = xpathFinder.locatorGen_Xpath(driver, value.get("displaytext"), value.get("targetelement"));
					else if(value.containsKey("placeholder"))
					xPathvalue = xpathFinder.generatexPathWithplaceHolder(driver, value.get("placeholder"));
				}
					 catch (Throwable e) {
						e.printStackTrace();
					}
				System.out.println(xPathvalue);
				
				switch (value.get(Action).toLowerCase()) {
					case Click:
						clickButton(LocatorType.Xpath, xPathvalue);
						break;
					case InsertText:
						insertText(LocatorType.Xpath, xPathvalue, value.get(Data));
						break;
					case DropDown:
						clickDropDown(LocatorType.Xpath, xPathvalue);
						break;
					case Clearning:
						clearingFields(LocatorType.Xpath, xPathvalue);
						break;
				}
			}
		}  
	}

	public static void performAction(String fileType) {
		try {
			propertyFileReader(fileType);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void waitUntilCondition(int second, ExpectedElementCondition expectedCondition, LocatorType findElementBy, String element, String text){
		WebDriverWait wait=new WebDriverWait(driver, second);
		switch (expectedCondition){
			case ElementToBeSelected:
				if(LocatorType.Id == findElementBy)
					wait.until(ExpectedConditions.elementToBeSelected(By.id(element)));
				else if(LocatorType.CssSelector == findElementBy)
					wait.until(ExpectedConditions.elementToBeSelected(By.cssSelector(element)));
				else if(LocatorType.Xpath == findElementBy)
					wait.until(ExpectedConditions.elementToBeSelected(By.xpath(element)));
				break;
			case ElementToBeClickable:
				if(LocatorType.Id == findElementBy)
					wait.until(ExpectedConditions.elementToBeClickable(By.id(element)));
				else if(LocatorType.CssSelector == findElementBy)
					wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(element)));
				else if(LocatorType.Xpath == findElementBy)
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(element)));
				break;
			case ElementSelectionStateToBe:
				if(LocatorType.Id == findElementBy)
					wait.until(ExpectedConditions.elementSelectionStateToBe(By.id(element),true));
				else if(LocatorType.CssSelector == findElementBy)
					wait.until(ExpectedConditions.elementSelectionStateToBe(By.cssSelector(element), true));
				else if(LocatorType.Xpath == findElementBy)
					wait.until(ExpectedConditions.elementSelectionStateToBe(By.xpath(element),true));
				break;
			case TextToBePresentInElementLocated:
				if(LocatorType.Id == findElementBy)
					wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id(element), text));
				else if(LocatorType.CssSelector == findElementBy)
					wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(element), text));
				else if(LocatorType.Xpath == findElementBy)
					wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(element), text));
				break;
			case VisibilityOfElement:
				if(LocatorType.Id == findElementBy)
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(element)));
				else if(LocatorType.CssSelector == findElementBy)
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(element)));
				else if(LocatorType.Xpath == findElementBy)
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));
				break;
		}
	}
	@AfterClass
	public static void endTest()
	{
	report.endTest(logger);
	report.flush();
	

	}
}

