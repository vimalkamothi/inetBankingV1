package com.inetBanking.testCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetBanking.pageObjects.LoginPage;
import com.inetBanking.utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass {

	@Test(dataProvider="LoginData")
	public void loginDDT(String user, String pwd) throws IOException
	{
		LoginPage lp = new LoginPage(driver);

		lp.setUserName(user);logger.info("Entered UserName");
		lp.setPassword(pwd);logger.info("Entered Password");
		lp.clickSubmit();logger.info("Clicked on Submit button");
		
		
		if(isAlertPresent()==true)
		{
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			logger.warn("Login failed.");
		}
		else
		{
			if(driver.getTitle().equals("Guru99 Bank Manager HomePage"))
			{
				Assert.assertTrue(true);logger.info("Login test Passed.");
			}
			else
			{
				captureScreen(driver,"Login Test");
				Assert.assertTrue(false);logger.info("Login test Failed.");
			}
			
			lp.clickLogout();
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
		}
		

	}
	
	
	public boolean isAlertPresent() 
	//user defined method created to check if alert is present or not
	{
		try {
			driver.switchTo().alert();
			return true;
		}
		catch(NoAlertPresentException e)
		{
			return false;
		}
	}
	
	
	@DataProvider(name="LoginData")
	String[][] getData() throws IOException
	{
		String path = System.getProperty("user.dir")+"/src/test/java/com/inetBanking/testData/LoginData.xlsx";
		
		int rownum = XLUtils.getRowCount(path, "Sheet1");
		int colnum = XLUtils.getCellCount(path, "Sheet1", 1);
		
		String logindata[][] = new String[rownum][colnum];
		
		for(int i=1; i<=rownum;i++)
		{
			for(int j=0;j<colnum;j++)
			{
				logindata[i-1][j] = XLUtils.getCellData(path, "Sheet1", i, j);
			}
		}
		
		return logindata;
	}
	
}
