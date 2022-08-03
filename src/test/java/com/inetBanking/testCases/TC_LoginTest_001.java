package com.inetBanking.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetBanking.pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass {

	@Test
	public void logintest() throws InterruptedException, IOException
	{
		
		LoginPage lp = new LoginPage(driver);

		lp.setUserName(username);logger.info("Entered UserName");
		lp.setPassword(password);logger.info("Entered Password");
		lp.clickSubmit();logger.info("Clicked on Submit button");
		
		
		if(driver.getTitle().equals("Guru99 Bank Manager HomePage"))
		{
			Assert.assertTrue(true);logger.info("Login test Passed.");
			System.out.println("This is updated and for the PUSH record change.");
		}
		else
		{
			captureScreen(driver,"Login Test");
			Assert.assertTrue(false);logger.info("Login test Failed.");
		}
	}
	
}
