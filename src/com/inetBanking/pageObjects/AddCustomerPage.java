package com.inetBanking.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AddCustomerPage {

    WebDriver ldriver;

    public AddCustomerPage(WebDriver rdriver)
    {
        ldriver = rdriver;
        PageFactory.initElements(rdriver, this);
    }

    @FindBy(how= How.XPATH, using ="/html/body/div[3]/div/ul/li[2]/a")
    @CacheLookup
    WebElement lnkAddNewCustomer;

    @FindBy(how= How.NAME, using ="name")
    @CacheLookup
    WebElement txtCustomerName;

    @FindBy(how= How.NAME, using ="rad1")
    @CacheLookup
    WebElement rdGender;

    @FindBy(how= How.ID_OR_NAME, using ="dob")
    @CacheLookup
    WebElement txtDOB;

    @FindBy(how= How.NAME, using ="addr")
    @CacheLookup
    WebElement txtAddress;

    @FindBy(how= How.NAME, using ="city")
    @CacheLookup
    WebElement txtCity;

    @FindBy(how= How.NAME, using ="state")
    @CacheLookup
    WebElement txtState;

    @FindBy(how= How.NAME, using ="pinno")
    @CacheLookup
    WebElement txtPIN;

    @FindBy(how= How.NAME, using ="telephoneno")
    @CacheLookup
    WebElement txtPhoneNo;

    @FindBy(how= How.NAME, using ="emailid")
    @CacheLookup
    WebElement txtEmailID;

    @FindBy(how= How.NAME, using ="password")
    @CacheLookup
    WebElement txtPassword;

    @FindBy(how= How.NAME, using ="sub")
    @CacheLookup
    WebElement btnSubmit;


    public void clickAddNewCustomer()
    {
        lnkAddNewCustomer.click();
    }

    public void custName(String cname)
    {
        txtCustomerName.sendKeys(cname);
    }

    public void custGender(String cgender)
    {
        rdGender.click();
    }

    public void custDOB(String mm, String dd, String yy)
    {
        txtDOB.sendKeys(mm);
        txtDOB.sendKeys(dd);
        txtDOB.sendKeys(yy);
    }

    public void custAddress(String caddress)
    {
        txtAddress.sendKeys(caddress);
    }

    public void custCity(String ccity)
    {
        txtCity.sendKeys(ccity);
    }

    public void custState(String cstate)
    {
        txtState.sendKeys(cstate);
    }

    public void custPIN(String cpin)
    {
        txtPIN.sendKeys(String.valueOf(cpin));
    }

    public void custPhoneNo(String cphone)
    {
        txtPhoneNo.sendKeys(cphone);
    }

    public void custEmailID(String cemail)
    {
        txtEmailID.sendKeys(cemail);
    }

    public void custPassword(String cpwd)
    {
        txtPassword.sendKeys(cpwd);
    }

    public void custSubmit()
    {
        btnSubmit.click();
    }
}
