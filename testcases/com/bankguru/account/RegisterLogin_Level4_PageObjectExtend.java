package com.bankguru.account;

import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.Constant;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.PageFactoryManager;
import pageObjects.RegisterPageObject;

import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class RegisterLogin_Level4_PageObjectExtend extends AbstractTest{
	WebDriver driver;
	private String userID, pass;
	private String email, loginURL;
	private LoginPageObject loginPage;
	private RegisterPageObject registerPage;
	private HomePageObject homePage;

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

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
