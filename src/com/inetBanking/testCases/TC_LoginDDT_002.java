package com.inetBanking.testCases;

import com.inetBanking.pageObjects.LoginPage;
import com.inetBanking.utilities.XLUtils;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import org.testng.Assert;

public class TC_LoginDDT_002 extends BaseClass
{
   @Test(dataProvider="LoginData")
    public void loginDDT(String username, String password) throws IOException, InterruptedException {
        LoginPage lp = new LoginPage(driver);
        lp.setUsername(username);
        logger.info("Username provided");
        lp.setPassword(password);
        logger.info("Password provided");
        lp.clickSubmit();
        Thread.sleep(3000);

        if(isAlertPresent()) {
            driver.switchTo().alert().accept();
            driver.switchTo().defaultContent();
            Assert.assertTrue(false);
            logger.warn("Login failed");
        }
        else
        {
            Assert.assertTrue(true);
            logger.info("Login passed");
            lp.clickLogout();
            Thread.sleep(3000);
            driver.switchTo().alert().accept();
            driver.switchTo().defaultContent();
        }
    }

    public boolean isAlertPresent()
    {
        try
        {
            driver.switchTo().alert();
            return true;
        }
        catch(NoAlertPresentException e)
        {
            return false;
        }
    }

    @DataProvider(name="LoginData")
    Object[][] getData() throws IOException
    {
        String path = System.getProperty("user.dir")+"/src/com/inetBanking/testData/LoginTestData.xlsx";

        int rownum = XLUtils.getRowCount(path,"Sheet1");
        int colcount = XLUtils.getCellCount(path, "Sheet1", 1);

        Object[][] logindata = new Object[rownum][colcount];

        for(int i=1;i<=rownum;i++)
        {
            for(int j=0;j<colcount;j++)
            {
                logindata[i-1][j] = XLUtils.getCellData(path,"Sheet1",i,j);
            }
        }
        return logindata;
    }

}
