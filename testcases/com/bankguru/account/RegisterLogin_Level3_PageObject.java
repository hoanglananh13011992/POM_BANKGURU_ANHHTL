package com.bankguru.account;

import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.Constant;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class RegisterLogin_Level3_PageObject extends AbstractTest{
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
	  
	  loginPage = new LoginPageObject(driver);
	  email = "hoanglan" + RandomEmail() + "@gmail.com";
	  System.out.println(email);
  }
  
  @Test
  public void TC_01_RegisterToSystem() {
	  loginPage.openLoginPage(Constant.LOGIN_URL_DEV);
	  loginURL = loginPage.getCurrentURL(driver);
	  loginPage.clickToHereLink();
	  
	  registerPage = new RegisterPageObject(driver);
	  registerPage.inputToEmailIDTextbox(email);
	  registerPage.clickToSubmitButton();
	  userID = registerPage.getUserIDText();
	  pass = registerPage.getPasswordText();
  }
  
  @Test
  public void TC_02_LoginWithAboveInformation() {
	  registerPage.openLoginPage(loginURL);
	  
	  loginPage = new LoginPageObject(driver);
	  loginPage.inputToUserIDTextbox(userID);
	  loginPage.inputToPasswordTextbox(pass);
	  loginPage.clickToLoginButton();
	  
	  homePage = new HomePageObject(driver);
	  Assert.assertTrue(homePage.isHomePageDisplayed());
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }
  
  public int RandomEmail() {
	  Random random = new Random();
	  int number = random.nextInt(999999);
	  return number;
	  
  }

}
