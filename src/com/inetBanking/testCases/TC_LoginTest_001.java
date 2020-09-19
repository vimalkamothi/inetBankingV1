package com.inetBanking.testCases;
import com.inetBanking.pageObjects.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC_LoginTest_001 extends BaseClass
{

    @Test
    public void loginTest() throws InterruptedException, IOException {
        /*
        driver.get(baseURL);

        logger.info("URL is opened");
        driver.manage().window().maximize();
         */

        LoginPage lp = new LoginPage(driver);

        lp.setUsername(username);
        logger.info("Username is entered");

        lp.setPassword(password);
        logger.info("Password is entered");

        lp.clickSubmit();

        Thread.sleep(5000);

        System.out.println(driver.getTitle());

        if (driver.getTitle().equals("Guru99 Bank Manager HomePage"))
        {
            Assert.assertTrue(true);
            logger.info("Login test passed");
        }
        else
        {
            captureScreen(driver, "loginTest");
            Assert.assertTrue(false);
            logger.info("Login test failed");
        }
    }

}
