package com.inetBanking.testCases;

import com.inetBanking.utilities.ReadConfig;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;


public class BaseClass {

    ReadConfig readconfig = new ReadConfig();

    public String baseURL = readconfig.getApplicationURL();
    public String username = readconfig.getUsername();
    public String password = readconfig.getPassword();
    public static WebDriver driver;
    public static Logger logger;

    @Parameters("browser")
    @BeforeClass
    public void setup(String br) throws InterruptedException {
        if (br.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", readconfig.getChromePath());
            driver = new ChromeDriver();
        }
        else if (br.equals("firefox"))
        {
            System.setProperty("webdriver.gecko.driver", readconfig.getFireFoxPath());
            driver = new FirefoxDriver();
        }

        logger = Logger.getLogger("ebanking");
        driver.get(baseURL);
        PropertyConfigurator.configure("Log4j.properties");
        Thread.sleep(2000);
        driver.manage().window().maximize();

        String parent=driver.getWindowHandle();
        Set<String>Browsers=driver.getWindowHandles();

        Iterator<String> I1= Browsers.iterator();

        String child_window=I1.next();

            driver.switchTo().window(child_window);
            driver.close();
        driver.switchTo().window(parent);

    }

    @AfterClass
    public void tearDown() throws InterruptedException {

        Thread.sleep(5000);
        driver.quit();
    }

    public void captureScreen(WebDriver driver, String tname) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File target = new File(System.getProperty("user.dir")+"/Screenshots/" + tname+".png");
        FileUtils.copyFile(source,target);
        System.out.println("Screenshot taken");
    }

    public String randomString()
    {
        String generatedstring = RandomStringUtils.randomAlphabetic(8);
        return generatedstring;
    }
}
