package com.inetBanking.testCases;

import com.inetBanking.pageObjects.AddCustomerPage;
import com.inetBanking.pageObjects.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC_AddCustomerTest_003 extends BaseClass
{
    @Test
    public void addNewCustomer() throws InterruptedException, IOException {
        LoginPage lp = new LoginPage(driver);
        lp.setUsername(username);
        logger.info("Username is provided");
        lp.setPassword(password);
        logger.info("Password is provided");
        lp.clickSubmit();

        Thread.sleep(5000);

        AddCustomerPage addcust = new AddCustomerPage(driver);
        logger.info("providing customer details");

        addcust.clickAddNewCustomer();
        addcust.custName("Test");
        addcust.custGender("male");
        addcust.custDOB("09","14","1980");
        Thread.sleep(5000);
        addcust.custAddress("Address Test");
        addcust.custCity("Mumbai");
        addcust.custState("Maharashtra");
        addcust.custPIN("410001");
        addcust.custPhoneNo("1234567890");
        String email = randomString()+"@gmail.com";
        addcust.custEmailID(email);
        addcust.custPassword("test123");
        addcust.custSubmit();
        Thread.sleep(5000);

        logger.info("validation started");
        boolean res = driver.getPageSource().contains("Customer Registered Successfully!!!");

        if(res==true)
        {
            Assert.assertTrue(true);
            logger.info("Test case passed");
        }
        else
        {
            captureScreen(driver, "addNewCustomer");
            Assert.assertTrue(false);
            logger.info("Test case failed");
        }
    }
}
