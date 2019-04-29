package com.bankguru.account;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import commons.AbstractTest;
import commons.Constant;
import pageObjects.DepositPageObject;
import pageObjects.FundTransferPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.NewAccountPageObject;
import pageObjects.NewCustomerPageObject;
import pageObjects.PageFactoryManager;
import pageObjects.RegisterPageObject;
import pageUIs.DepositPageUI;
import pageUIs.FundTransferPageUI;
import pageUIs.HomePageUI;
import pageUIs.NewAccountPageUI;
import pageUIs.NewCustomerPageUI;

public class RegisterLogin_Level5_WebdriverLifeCycle extends AbstractTest{
	WebDriver driver;
	private String userID, pass;
	private String email, loginURL;
	private LoginPageObject loginPage;
	private RegisterPageObject registerPage;
	private HomePageObject homePage;
	private NewCustomerPageObject newCustomerPage;
	private NewAccountPageObject newAccountPage;
	private DepositPageObject depositPage;
	private FundTransferPageObject fundTransferPage;

  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  
	  loginPage = PageFactoryManager.getLoginPage(driver);
	  email = "hoanglan" + RandomEmail() + "@gmail.com";
  }
  
  @Test
  public void TC_01_RegisterToSystem() {
	  loginPage.openLoginPage(Constant.LOGIN_URL_DEV);
	  loginURL = loginPage.getCurrentURL(driver);
	  registerPage = loginPage.clickToHereLink();	  
	  registerPage.inputToEmailIDTextbox(email);
	  registerPage.clickToSubmitButton();
	  userID = registerPage.getUserIDText();
	  pass = registerPage.getPasswordText();
  }
  
  @Test
  public void TC_02_LoginWithAboveInformation() {
	  loginPage = registerPage.openLoginPage(loginURL);
	  loginPage.inputToUserIDTextbox(userID);
	  loginPage.inputToPasswordTextbox(pass);
	  homePage = loginPage.clickToLoginButton();
	  Assert.assertTrue(homePage.isHomePageDisplayed());
  }
  
  @Test
  public void TC_03_WebdriverLifeCycle() {
	  newCustomerPage = homePage.openNewCustomerPage(driver);
	  newCustomerPage.isControlDisplayed(driver, NewCustomerPageUI.NEW_CUSTOMER_TEXT);
	  
	  newAccountPage = newCustomerPage.openNewAccountPage(driver);
	  newAccountPage.isControlDisplayed(driver, NewAccountPageUI.NEW_ACCOUT_TEXT);
	  
	  depositPage = newAccountPage.openDepositPage(driver);
	  depositPage.isControlDisplayed(driver, DepositPageUI.DEPOSIT_TEXT);
	  
	  fundTransferPage = depositPage.openFundTransferPage(driver);
	  fundTransferPage.isControlDisplayed(driver, FundTransferPageUI.FUND_TRANSFER_TEXT);
	  
	  homePage = fundTransferPage.openHomePage(driver);
	  homePage.isControlDisplayed(driver, HomePageUI.HOME_WELCOME_MESSAGE);
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
