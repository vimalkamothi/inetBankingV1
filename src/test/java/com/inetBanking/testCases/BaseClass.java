package com.inetBanking.testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetBanking.utilities.ReadConfig;

import org.apache.log4j.PropertyConfigurator;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

public class BaseClass {

	ReadConfig readconfig = new ReadConfig();
	
	public String baseURL = readconfig.getApplicationURL();
	public String username = readconfig.getUserName();
	public String password = readconfig.getPassword();
	public static WebDriver driver;
	public static Logger logger;
	
	@Parameters("browser")
	@BeforeClass
	public void setup(String br) throws InterruptedException 
	{

		logger = Logger.getLogger("ebanking");
		PropertyConfigurator.configure("Log4j.properties");

		if(br.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", readconfig.getChromePath());
			//ChromeOptions options= new ChromeOptions();
			//options.setHeadless(true);
			//driver = new ChromeDriver(options);
			driver = new ChromeDriver();
		}
		else if (br.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", readconfig.getFirefoxPath());
			//FirefoxOptions options= new FirefoxOptions();
			//options.setHeadless(true);
			//driver = new FirefoxDriver(options);
			driver = new FirefoxDriver();
		}
		else if (br.equals("ie"))
		{
			System.setProperty("webdriver.ie.driver", readconfig.getIEPath());
			driver = new InternetExplorerDriver();
		}
		
		driver.get(baseURL);logger.info("URL is opened.");
		
		driver.manage().window().maximize();
		//clearcache();
		clearcookies();
	}
	
	
	public void clearcache() 
	
	{
	
		// It will return the parent window name as a String
		String parent=driver.getWindowHandle();
		
		Set <String> s=driver.getWindowHandles();
		
		System.out.println("getWindowHandles list "+s);
		
		// Now iterate using Iterator
		Iterator <String> I1= s.iterator();
		
			while(I1.hasNext())
			{
	
			String child_window=I1.next();
	
			if(!parent.equals(child_window))
				{
				driver.switchTo().window(child_window);
				System.out.println("This is Child Window Title Text:- " + driver.switchTo().window(child_window).getTitle());
				driver.close();
				}
	
			}
			
		//switch to the parent window
		driver.switchTo().window(parent);
		
	}
	
	
	
	public void clearcookies() throws InterruptedException
	{
		Thread.sleep(5000);
		
		driver.switchTo().frame(1);
		
		WebElement element = driver.findElement(By.tagName("body"));  
		element.sendKeys(Keys.TAB);  
		element.sendKeys(Keys.TAB);
		element.sendKeys(Keys.TAB);
		element.sendKeys(Keys.TAB);
		element.sendKeys(Keys.TAB);
		element.sendKeys(Keys.TAB);
		element.sendKeys(Keys.ENTER);
		
		Thread.sleep(5000);

		driver.switchTo().parentFrame();
		Thread.sleep(5000);

	}
	
	
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}	
	
	@AfterClass
	public void teardown()
	{
		driver.quit();
	}
	
}
