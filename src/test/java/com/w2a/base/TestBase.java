package com.w2a.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.w2a.utilities.ExcelReader;
import com.w2a.utilities.ExtentManager;
import com.w2a.utilities.TestUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	public static WebDriver driver;
	public static Properties Config=new Properties();
	public static Properties OR=new Properties();
	public static FileInputStream fis;
	public static Logger log=Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel =new ExcelReader("C:\\Users\\Aparna\\eclipse-workspace\\LiveProjects\\DDFFourthAttempt\\src\\test\\resources\\excels\\testdata2.xlsx");
	public static WebDriverWait wait;
	public ExtentReports rep=ExtentManager.getInstance();
	public static ExtentTest test;
	
	
	
	
	@BeforeSuite
	public void setUp()
	{
		if(driver==null)
		{
			try {
				fis=new FileInputStream("C:\\Users\\Aparna\\eclipse-workspace\\LiveProjects\\DDFFourthAttempt\\src\\test\\resources\\Propeties\\Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Config.load(fis);
				log.debug("Config File Loaded !!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				fis=new FileInputStream("C:\\Users\\Aparna\\eclipse-workspace\\LiveProjects\\DDFFourthAttempt\\src\\test\\resources\\Propeties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				log.debug("OR File Loaded !!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(Config.getProperty("browser").equals("Chrome"))
			{
				WebDriverManager.chromedriver().setup();
				driver=new ChromeDriver();
				log.debug("Chrome Driver launched");
				
			}else if (Config.getProperty("browser").equals("Firefox"))
			{
				WebDriverManager.firefoxdriver().setup();
				driver=new FirefoxDriver();
				log.debug("Firefox Driver launched");
			}
			
			driver.get(Config.getProperty("testsiteurl"));
			log.debug("URL under test "+Config.getProperty("testsiteurl")+" launched !!");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(Config.getProperty("Implicit.wait")), TimeUnit.SECONDS);
			wait=new WebDriverWait(driver,10);
			
			
		}
		
	}
	
	public void click(String locator)
	{
		if(locator.endsWith("CSS"))
		{
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		}else if(locator.endsWith("XPATH"))
		{
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
		}
		test.log(LogStatus.INFO, "Clicking on : "+locator);
		
	}
	public void type(String locator,String value)
	{
		driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		test.log(LogStatus.INFO, "Typing in the locator "+locator+" sending value as : "+value);
	}
	
	static WebElement dropdown;
	public void select(String locator,String value)
	{
		dropdown=driver.findElement(By.cssSelector(OR.getProperty(locator)));
		Select select=new Select(dropdown);
		select.selectByVisibleText(value);
		test.log(LogStatus.INFO, "Selected values: "+value);
	}
	
	public boolean isElementPresent(By by)
	{
		try
		{
			driver.findElement(by);
			return true;
		}catch(NoSuchElementException e)
		{
			return false;
			
		}
	}
	
	public static void verifyEquals(String expected,String actual) throws IOException
	{
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		try
		{
			Assert.assertEquals(expected, actual);
			
		}catch(Throwable t)
		{
			TestUtil.captureScreenshot();
			
			Reporter.log("<br>"+"Verficiation failure: "+t.getMessage()+"<br>");
			Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+"><img src="+TestUtil.screenshotName+" height=200 widht=200></img</a>");
			Reporter.log("<br>");
			Reporter.log("<br>");
			
			test.log(LogStatus.FAIL, "Verification Failed with exception: "+t.getMessage());
			test.log(LogStatus.FAIL,test.addScreenCapture(TestUtil.screenshotName));
			
			
		}
	}
	@AfterSuite
	public void tearDown()
	{
		if(driver!=null)
		{
			driver.quit();
		}
		log.debug("Test Completed !!!");
	}

}
